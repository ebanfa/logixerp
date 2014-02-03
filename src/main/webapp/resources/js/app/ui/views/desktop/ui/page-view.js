define([
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'uiconstants',
    'app/util/ajax-utilities',
    'app/ui/views/desktop/ui/uirenderer-view',
    'text!../../../../../../templates/desktop/page.html'
], function ($, config, _, Backbone, UiConstants, AjaxUtil, UiRendererView, PageTemplate) {
	
    var PageView = Backbone.View.extend({
    
        start: function(options) 
        {
        	this.application = options.application;
        	//subscribe to ui component rendered events
        	this.application.subscribeToEvent(
        			UiConstants.uiFsm, 
        			UiConstants.uiRenderedEvent,
        			this.uiRenderedEventHandler(this));
        	
        	//subscribe to render activity request events
        	this.application.subscribeToEvent(
        			UiConstants.uiFsm, 
        			UiConstants.loadActivityEvent,
        			this.renderActivityEventHandler(this));
        	
        	this.uiComponentCode = options.uiComponentCode;
        	this.renderView(options);
        },
        
        /**
         * 
         */
        handleLoginEvent: function(self) {
			 // if authenticated
			 return function(data) 
			 {
				
			 };
        },

        /**
         * 
         */
        uiRenderedEventHandler: function(self) {
			 // if authenticated
        	var application = self.application;
        	return function(data) 
        	{
        		if(data.uiComponentData.name == 'loading-container'){
        			_.delay(function(msg) { 
            			application.fireEvent(UiConstants.uiFsm, UiConstants.uiSuccessEvent);
        				console.log(msg); 
        			}, 3000, 'Hello');
        		}
        	};
        	
        },
        
        /**
         * 
         */
        renderActivityEventHandler: function(self) {
			 // if authenticated
        	//var application = self.application;
        	return function(data) 
        	{
        		console.log('Page view rendering activity:' + data.activityURL);
        	};
        	
        },
        
        /**
         * 
         */
        handleApplicationUnavailable: function(data){
        	
        },
        
        /**
         * Renders a view
         */
        renderView: function(viewOptions) 
        {
        	this.uiComponentCode = viewOptions.uiComponentCode ? 
        			viewOptions.uiComponentCode : UiConstants.loginContainer;
        	this.render();
        },
        
        /**
         * Render this view
         */
        render: function() 
        {
        	this.loadUiComponentData(this.uiComponentCode);
            return this;
        },

        /**
         * Load a UiComponent from storage
         */
        loadUiComponentData: function(uiComponentCode) 
        {
        	console.log('Loading component:loadUiComponentData: ' + uiComponentCode);
        	AjaxUtil.ajaxGET(UiConstants.uiServiceURL, {componentName: uiComponentCode}, 
        			this.loadComponentDataSuccessCallBack(this), this.loadComponentDataFailCallBack(this));
        },

        /**
         * Called when a component is successfully loaded
         * from storage
         */
        loadComponentDataSuccessCallBack: function(self) {
        	this.authenticated = true;
        	console.log('Loading component:loadComponentDataSuccessCallBack: ');
        	return function(data, textStatus) {
            	console.log('Loading component:loadComponentDataSuccessCallBack1: ' + data);
				self.renderUiComponent(data);
			};
        },

        /**
         * Render a component
         */
        renderUiComponent: function(uiComponentData) {
        	var uiRendererView = new UiRendererView({el: $(this.el), pageView: this, uiComponentData: uiComponentData});
        	uiRendererView.render();
        	this.application.fireEvent(UiConstants.uiFsm, UiConstants.uiRenderedEvent, {uiComponentData:uiComponentData});
        },

        /**
         * Called when loading a component
         * from storage fails
         */
        loadComponentDataFailCallBack: function() {
        	return function(data, textStatus) {
				console.log("Error::::" + textStatus);
			};
        },
        
        notifyUser: function(message){
        	alert(message);
        }
    });

    return PageView;
});
