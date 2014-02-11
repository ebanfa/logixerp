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

        	this.application.subscribeToEvent(
        			UiConstants.activityChannel, 
        			UiConstants.uiRenderActivityRequestEvent, 
        			this.renderActivityRequestHandler(this));
        	
        	this.application.subscribeToEvent(
        			UiConstants.activityChannel, 
        			UiConstants.uiRenderTemplateRequestEvent, 
        			this.renderTemplateRequestHandler(this));
        	
        	this.uiComponentCode = options.uiComponentCode;
        	this.renderView(options);
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
            	if(data.uiComponentData)
            		self.renderUiComponent(data.uiComponentData);
			};
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

        /**
         * Render a component
         */
        renderUiComponent: function(uiComponentData) {
        	this.uiRendererView = new UiRendererView({el: $(this.el), pageView: this, uiComponentData: uiComponentData});
        	this.uiRendererView.render();
        	this.application.fireEvent(UiConstants.uiFsm, UiConstants.uiRenderedEvent, {uiComponentData:uiComponentData});
        },
        
        notifyUser: function(message){
        	alert(message);
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
        renderActivityRequestHandler: function(self)
        { 
        	return function(data)
            {
        		self.uiRendererView.renderActivity(data.activityResponseData);
            };
        },
        
        /**
         * 
         */
        renderTemplateRequestHandler: function(self)
        { 
        	return function(data)
            {
	    		console.log('Rendering template:' + data.template);
    	    	var template =  self.uiRendererView.templates[data.template];
	    		console.log('Pre Rendered template data:' + JSON.stringify(data, null, 4));
	    		console.log('Pre Rendered template contents:' + template);
        		var renderedTemplate = self.uiRendererView.renderActivityTemplate(template, data);
	    		console.log('Rendered template contents:' + renderedTemplate);
            	// Notify listeners that the template has been rendered
            	self.application.fireEvent(UiConstants.activityChannel, 
            			UiConstants.uiTemplateRenderedEvent, {templatName:data.template, renderedTemplate: renderedTemplate});
            };
        },
        
        /**
         * 
         */
        handleApplicationUnavailable: function(data){
        	
        },
    });

    return PageView;
});
