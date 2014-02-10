define([
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'uiconstants',
    'app/util/form-utilities',
    'app/events/event-handlers',
	'text!../../../../../../templates/desktop/ui/link.html',
	'text!../../../../../../templates/desktop/ui/list.html',
	'text!../../../../../../templates/desktop/ui/span.html',
	'text!../../../../../../templates/desktop/ui/form.html',
	'text!../../../../../../templates/desktop/ui/image.html',
	'text!../../../../../../templates/desktop/ui/button.html',
	'text!../../../../../../templates/desktop/ui/tab-panel.html',
	'text!../../../../../../templates/desktop/ui/side-bar.html',
	'text!../../../../../../templates/desktop/ui/list-item.html',
	'text!../../../../../../templates/desktop/ui/bold-span.html',
	'text!../../../../../../templates/desktop/ui/list-table.html',
	'text!../../../../../../templates/desktop/ui/paragraph.html',
	'text!../../../../../../templates/desktop/ui/input-text.html',
	'text!../../../../../../templates/desktop/ui/heading-one.html',
	'text!../../../../../../templates/desktop/ui/search-field.html',
	'text!../../../../../../templates/desktop/ui/search-dialog.html',
	'text!../../../../../../templates/desktop/ui/view-activity.html',
	'text!../../../../../../templates/desktop/ui/edit-activity.html',
	'text!../../../../../../templates/desktop/ui/list-activity.html',
	'text!../../../../../../templates/desktop/ui/submit-button.html',
	'text!../../../../../../templates/desktop/ui/password-field.html',
	'text!../../../../../../templates/desktop/ui/toolbar-widget.html',
    'text!../../../../../../templates/desktop/ui/login-form-panel.html',
    'text!../../../../../../templates/desktop/ui/navigation-activity.html',
], function ($, 
		config, 
		_, 
		Backbone, 
		UiConstants,
		FormUtil,
		EventHandlers,
		LinkTemplate,
		ListTemplate,
		SpanTemplate,
		FormTemplate, 
		ImageTemplate,
		ButtonTemplate,
		TabPanelTemplate,
		SideBarTemplate,
		ListItemTemplate,
		BoldSpanTemplate,
		ListTableTemplate,
		ParagraphTemplate,
		InputTextTemplate,
		HeaderOneTemplate, 
		SearchFieldTemplate,
		SearchDialogTemplate,
		ViewActivityTemplate,
		EditActivityTemplate,
		ListActivityTemplate,
		SubmitButtonTemplate,
		PasswordFieldTemplate,
		ToolBarWidgetTemplate,
		LoginFormPanelTemplate,
		NavigationActivityTemplate) {
	
	var templates = 
	{
	    'link': LinkTemplate,
    	'list': ListTemplate,
    	'form': FormTemplate,
    	'span': SpanTemplate,
    	'image': ImageTemplate,	
    	'button': ButtonTemplate,
    	'activity-panel':TabPanelTemplate,
    	'sidebar': SideBarTemplate,
    	'list-item': ListItemTemplate,
    	'bold-span': BoldSpanTemplate,
    	'paragraph': ParagraphTemplate,
    	'text-field': InputTextTemplate,
    	'list-table': ListTableTemplate,
    	'panel': LoginFormPanelTemplate,
    	'heading-one': HeaderOneTemplate,
    	'toolbar': ToolBarWidgetTemplate,
    	'search-field': SearchFieldTemplate,
    	'search-dialog': SearchDialogTemplate,
    	'password-field': PasswordFieldTemplate,
    	'submit-button': SubmitButtonTemplate,
    	'view-activity': ViewActivityTemplate,
    	'edit-activity': EditActivityTemplate,
    	'list-activity': ListActivityTemplate,
    	'nav-activity': NavigationActivityTemplate,
	 };
	
    /**
     * Responsible for rendering components.
     */
    var UiRendererView = Backbone.View.extend({
    	
    	templates: templates,

        /**
         * Constructor
         */
        initialize: function(options) 
        {
        	this.pageView = options.pageView;
        	this.uiComponentData = options.uiComponentData;
        	this.application = this.pageView.application;
        },

        /**
         * Render this view.
         */
        render: function() 
        {
        	$(this.el).empty();
        	$(this.el).append(this.renderUiComponent(this.uiComponentData));
    		return this;
        },
        
        /**
         * Renders a component. Recursively renders
         * all child components. Rendering in this case
         * involves filling the components template with data, and
         * processing the configured events for the component
         */
        renderUiComponent: function(uiComponentData)
        {
        	var uiComponentDataElement = 
        		$(this.renderTemplate(templates[uiComponentData.type], {uiComponentData: uiComponentData}));
        	uiComponentData.components.sort(this.sortComponents);
        	// Process all child nodes
        	for(var i = 0; i < uiComponentData.components.length; i++) {
        		// Process event handlers
        		if(uiComponentData.components[i].type === "event-handler") {
        			this.addEventToUiComponent(uiComponentDataElement, uiComponentData.components[i]);
        		}
        		else {
            		uiComponentDataElement.append(
            				this.renderUiComponent(uiComponentData.components[i]));
        		}
        	}
        	// Process all the events of the component
        	//this.configUiEvents(uiComponentDataElement, uiComponentData);
        	return uiComponentDataElement;
        },
        
        renderActivity: function(activityResponseData)
        { 
    		var activityTemplate = null;
    		var uiComponentData = activityResponseData.uiComponentData;
    		var activityData = activityResponseData.activityData;
    		var activityTyData = activityData.dataValues.activityType;
    		var activityTy = activityTyData.code;
    		// Select the appropriate template depending on the activity type
    		if(activityTy === "NAV_ACTIVITY")	activityTemplate = templates['nav-activity'];
    		if(activityTy === "VIEW_ACTIVITY")	activityTemplate = templates['view-activity'];
    		if(activityTy === "EDIT_ACTIVITY")	activityTemplate = templates['edit-activity'];
    		if(activityTy === "LIST_ACTIVITY")	activityTemplate = templates['list-activity'];
    		// Build the form for the activity **** What about nav-activities
    		var form = FormUtil.formBuilder(activityResponseData);
    		// Process the template	
        	var uiComponentDataElement = 
        		$(this.renderActivityTemplate(activityTemplate, 
        				{activityResponseData: activityResponseData, form: form}));
        	// Sort child components into order dictated by sequence no
        	uiComponentData.components.sort(this.sortComponents);
        	// Process all child nodes
        	for(var i = 0; i < uiComponentData.components.length; i++) {
        		// Process event handlers
        		if(uiComponentData.components[i].type === "event-handler") {
        			this.addEventToUiComponent(uiComponentDataElement, uiComponentData.components[i]);
        		}
        		else {
            		uiComponentDataElement.append(
            				this.renderUiComponent(uiComponentData.components[i]));
        		}
        	}
        	// Filter out non list activities
        	if(activityTy === "NAV_ACTIVITY")
        		activityResponseData.dataList = 
        			this.filterOutNonListActivities(activityResponseData.dataList);

        	// Fire
        	this.application.fireEvent(
        			UiConstants.activityChannel, UiConstants.uiActivityRenderedEvent, 
        			{activityResponseData:activityResponseData, 
        				activityData:activityData, activityElement: uiComponentDataElement});
        },
        
        addEventToUiComponent: function(uiComponentDataDomElement, uiComponentData) {
        	// A component can have an array of event handers associated with it.
        	// An event handler is an object that has an event name and event handler name
        	// properties associated with it.
        	// An event name is just the name of a jquery element event or the name of a custom
        	// event.
        	// An event handler is a module that handles specific types of events, ie an
        	// event handler can handle more that one element.
			var attributes = uiComponentData.attributes;
			var handlerName = attributes['handler'];
			if(EventHandlers[handlerName] != undefined) {
				var handlerObj = EventHandlers[handlerName];
				// The name of the component is taken to be the name of the event
	        	$(uiComponentDataDomElement).on(uiComponentData.name, 
	        			{target:uiComponentData.name, pageView: this.pageView}, handlerObj['handle']);
			}
        },
        
        /**
         * Render a template.
         */
        renderTemplate:function (template, data) {
        	// Convert the ui query data into a string
        	// this will allow us to use it as template value
        			
    		if(data.uiComponentData.uiQueryData.length > 0) 
    			data.uiComponentData.uiQueryData = 
    				JSON.stringify(data.uiComponentData.uiQueryData);
    		// Render the template
            return _.template(template, (data == undefined) ? {} : data);
        },
        
        /**
         * Render a template.
         */
        renderActivityTemplate:function (template, data) {
    		// Render the template
            return _.template(template, (data == undefined) ? {} : data);
        },
        
        filterOutNonListActivities: function(activities)
        {
        	var listActivities = [];
        	for(var i = 0; i < activities.length; i++) {
        		if(activities[i].dataValues.activityUrl.fieldValue.startsWith('list_')){
        			listActivities.push(activities[i]);
        		}
        	}
        	console.log(listActivities.length);
        	return listActivities;
        },
        /**
         * Function to sort items based
         * on the order of their sequence numbers.
         */
        sortComponents: function(first, second){
        	return first.sequenceNo - second.sequenceNo;
        }
    });

    return UiRendererView;
});
