define([
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'uiconstants',
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
	'text!../../../../../../templates/desktop/ui/paragraph.html',
	'text!../../../../../../templates/desktop/ui/input-text.html',
	'text!../../../../../../templates/desktop/ui/heading-one.html',
	'text!../../../../../../templates/desktop/ui/search-field.html',
	'text!../../../../../../templates/desktop/ui/submit-button.html',
	'text!../../../../../../templates/desktop/ui/password-field.html',
	'text!../../../../../../templates/desktop/ui/toolbar-widget.html',
    'text!../../../../../../templates/desktop/ui/login-form-panel.html',
], function ($, 
		config, 
		_, 
		Backbone, 
		UiConstants,
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
		ParagraphTemplate,
		InputTextTemplate,
		HeaderOneTemplate, 
		SearchFieldTemplate,
		SubmitButtonTemplate,
		PasswordFieldTemplate,
		ToolBarWidgetTemplate,
		LoginFormPanelTemplate) {
	
	var templates = 
	{
	    'link': LinkTemplate,
    	'list': ListTemplate,
    	'form': FormTemplate,
    	'span': SpanTemplate,
    	'image': ImageTemplate,	
    	'button': ButtonTemplate,
    	'tab-panel':TabPanelTemplate,
    	'sidebar': SideBarTemplate,
    	'list-item': ListItemTemplate,
    	'bold-span': BoldSpanTemplate,
    	'paragraph': ParagraphTemplate,
    	'text-field': InputTextTemplate,
    	'panel': LoginFormPanelTemplate,
    	'heading-one': HeaderOneTemplate,
    	'toolbar': ToolBarWidgetTemplate,
    	'search-field': SearchFieldTemplate,
    	'password-field': PasswordFieldTemplate,
    	'submit-button': SubmitButtonTemplate,
	 };
	
    /**
     * Responsible for rendering components.
     */
    var UiRendererView = Backbone.View.extend({

        /**
         * Constructor
         */
        initialize: function(options) 
        {
        	this.pageView = options.pageView;
        	this.uiComponentData = options.uiComponentData;
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
				console.log('Event Name:>>>' + uiComponentData.name);
				console.log('Event handler name:>>>' + handlerName);
				console.log('Parent view:>>>' + this.pageView);
				console.log('Event handler object:>>>' + handlerObj['handle']);
				// The name of the component is taken to be the name of the event
	        	$(uiComponentDataDomElement).on(uiComponentData.name, 
	        			{target:uiComponentData.name, pageView: this.pageView}, handlerObj['handle']);
			}
			
        },
        /**
         * Render a template.
         */
        renderTemplate:function (template, data) {
            return _.template(template, (data == undefined) ? {} : data);
        },
        sortComponents: function(first, second){
        	return first.sequenceNo - second.sequenceNo;
        }
    });

    return UiRendererView;
});