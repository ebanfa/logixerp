define([
    'jquery',
    'configuration',
    'backbone',
    'uiconstants',
    'jqm-datepicker-core',
    'jqm-datebox-calbox',
    'app/util/ajax-utilities',
    'app/util/string-utilities',
    'app/ui/views/uirenderer-view',
], function ($, config, Backbone, UiConstants, DateBox, CalBox, AjaxUtil,
		StringUtilities, UiRendererView) {
	/**
	 * Manages the UI in the following steps:
	 * 1. Router receives the path requests and calls the
	 *    start function of the page view. No matter what path
	 *    was requested, the router will always only call the 
	 *    same function.
	 * 2. Page view start function gets the UI started
	 *    by firing a ui render event.
	 * 3. The Ui state machine handles the above event by 
	 * 	  firing a uiLoadActivityEvent to load the login activity
	 * 4. The page view subscribes to the uiLoadActivityEvent
	 *    and handles it with the loadActivityRequestEventHandler
	 *    function.
	 * 5. The loadActivityRequestEventHandler functions makes an AJAX GET
	 *    request to load the request activity.
	 * 6. When activity is successfully loaded the success call back
	 *    will call the page views renderActivityData function passing 
	 *    the loaded activity data.
	 * 7. The renderActivityData function initializes the UiRendererView to
	 *    render the activity data. When the this is complete the renderActivityData
	 *    function the fires uiActivityRenderedEvent event to notify all 
	 *    interested parties that the activityData is ready for display
	 * 
	 */
    var PageView = Backbone.View.extend({
    
        initialize: function(options) 
        {
        	this.application = options.application;
        	this.activityQuery = options.activityQuery;

        	this.application.subscribeToEvent(
        			UiConstants.activityChannel, 
        			UiConstants.uiLoadActivityEvent, 
        			this.loadActivityRequestEventHandler(this));
        	
        	// Notification subscriptions
        	this.application.subscribeToEvent(
        			UiConstants.activityChannel, 
        			UiConstants.uiNotificationEvent, 
        			this.notificationEventHandler(this));
        	// This will prevent duplicates of pages from existing in the dom
        	$(document).on( "pageshow", function(e, data)
        	{ 
        		if($('#' + document.currentPage).length > 1)
        		{
        			$('#' + document.currentPage).each(function(i, obj) 
            		{
        				//if(!$(this).hasClass('ui-page-active')) $(this).remove();
            		});
        		}
      	    });
        },

        /**
         * Fires a ui render event to 
         * get the UI to display the initial
         * page (Actually this event will cause the ui state
         * machine to load the login screen)
         */
        start: function() 
        {
        	this.application.fireEvent(
        			UiConstants.uiFsm, 
        			UiConstants.uiRenderEvent);
        },

        /**
         * Load a ActivityData from storage
         */
        loadActivityRequestEventHandler: function(self) 
        {
        	return function(activityQuery) {
        		// Set the user name only if we are not requesting the
        		// the login activity and if we have a valid user name
        		if(this.application.userData != undefined && 
        				 activityQuery.activityURL != UiConstants.loginScreen)
        		{
                	activityQuery.userName = this.application.userData.userName;
                	activityQuery.universe = this.application.userData.universe;
        		}
        		
            	self.activityQuery = activityQuery;
            	console.log('Executing activityQuery::' + JSON.stringify(activityQuery, null, 4));
            	if(activityQuery.method === undefined)
            		activityQuery.method = 'GET';
            	if(activityQuery.method === 'GET') {
            		AjaxUtil.ajaxGET(UiConstants.activityServiceURL, activityQuery, 
            				self.loadActivityDataSuccessCallBack(self), self.loadActivityDataFailCallBack(self));
            	}
            	else if(activityQuery.method === 'POST') {
            		AjaxUtil.ajaxPOST(UiConstants.activityServiceURL, JSON.stringify(activityQuery), 
            				self.loadActivityDataSuccessCallBack(self), self.loadActivityDataFailCallBack(self));
            	}
            	else {
            		AjaxUtil.ajaxGET(UiConstants.activityServiceURL, activityQuery, 
            				self.loadActivityDataSuccessCallBack(self), self.loadActivityDataFailCallBack(self));
            	}
            		
        	};
        },
        
        /**
         * Called when a component is successfully loaded
         * from storage
         */
        loadActivityDataSuccessCallBack: function(self) {
        	return function(data, textStatus) {
            	self.renderActivityData(data);
			};
        },

        /**
         * Called when loading a component
         * from storage fails
         */
        loadActivityDataFailCallBack: function() {
        	return function(data, textStatus) {
				console.log("Error::::" + textStatus);
			};
        },
        
        /**
         * Render a component
         */
        renderActivityData: function(activityData) {
        	this.uiRendererView = new UiRendererView(
        			{el: $(this.el), pageView: this, uiData: activityData});
        	this.uiRendererView.render();
        	// Notify interested parties that we have
        	// successfully processed the activity template
        	this.application.fireEvent(
        			UiConstants.uiFsm, 
        			UiConstants.uiActivityRenderedEvent, 
        			{activityData: activityData});
        },
        
        /**
         * Handles the event by displaying a notification
         * to the end user
         */
        notificationEventHandler: function(self) 
        {
        	return function(data) {
        		self.notifyUser(data.message);
        	};
        },
        
        notifyUser: function(message){
        	// show error message
        	$.mobile.showPageLoadingMsg( $.mobile.pageLoadErrorMessageTheme, message, true );
        	// hide after delay
        	setTimeout( $.mobile.hidePageLoadingMsg, 3000 );
        },
        
        /**
         * 
         */
        renderActivityRequestEventHandler: function(self)
        { 
        	return function(data)
            {
        		self.uiRendererView.renderActivity(data.activityResponseData);
            };
        },
        
        /**
         * 
         */
        renderTemplateRequestEventHandler: function(self)
        { 
        	return function(data)
            {
    	    	var template =  self.uiRendererView.templates[data.template];
        		var renderedTemplate = self.uiRendererView.renderActivityTemplate(template, data);
            	// Notify listeners that the template has been rendered
            	self.application.fireEvent(
            			UiConstants.activityChannel, 
            			UiConstants.uiTemplateRenderedEvent, 
            			{templatName:data.template, renderedTemplate: renderedTemplate});
            };
        },
    });

    return PageView;
});
