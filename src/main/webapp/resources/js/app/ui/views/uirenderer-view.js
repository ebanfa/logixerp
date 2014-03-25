define([
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'uiconstants',
    'i18n!app/nls/messages',
    'app/util/form-utilities',
    'app/events/event-handlers',
	'text!../../../../templates/ui/page.html',
	'text!../../../../templates/ui/link.html',
	'text!../../../../templates/ui/link-button.html',
	'text!../../../../templates/ui/content.html',
	'text!../../../../templates/ui/image.html',
	'text!../../../../templates/ui/header.html',
	'text!../../../../templates/ui/footer.html',
	'text!../../../../templates/ui/heading.html',
	'text!../../../../templates/ui/navbar.html',
	'text!../../../../templates/ui/navbar-button.html',
	'text!../../../../templates/ui/list.html',
	'text!../../../../templates/ui/fieldset.html',
	'text!../../../../templates/ui/container.html',
	'text!../../../../templates/ui/list-item.html',
	'text!../../../../templates/ui/data-list.html',
	'text!../../../../templates/ui/email-field.html',
	'text!../../../../templates/ui/dynamic-list.html',
	'text!../../../../templates/ui/dynamic-form.html',
	'text!../../../../templates/ui/dynamic-form-button.html',
	'text!../../../../templates/ui/password-field.html',
	'text!../../../../templates/ui/springboard.html',
	'text!../../../../templates/ui/control-group.html',
	'text!../../../../templates/ui/form.html',
], function ($, 
		config, 
		_, 
		Backbone, 
		UiConstants,
		Messages,
		FormUtil,
		EventHandlers,
		PageTemplate,
		LinkTemplate,
		LinkButtonTemplate,
		ContentTemplate,
		ImageTemplate, 
		HeaderTemplate,
		FooterTemplate,
		HeadingTemplate,
		NavBarTemplate,
		NavBarButtonTemplate,
		ListTemplate,
		FieldSetTemplate,
		ContainerTemplate,
		ListItemTemplate,
		DataListTemplate,
		EmailFieldTemplate,
		DynamicListTemplate,
		DynamicFormTemplate,
		DynamicFormButtonTemplate,
		PasswordTemplate,
		SpringBoardTemplate, 
		ControlGroupTemplate,
		FormTemplate) {
	
	var templates = 
	{
	    'link': LinkTemplate,
    	'form': FormTemplate,
    	'page': PageTemplate,
    	'list': ListTemplate,
    	'image': ImageTemplate,	
    	'header':HeaderTemplate,
    	'navbar': NavBarTemplate,
    	'footer': FooterTemplate,
    	'heading': HeadingTemplate,
    	'content': ContentTemplate,
    	'fieldset': FieldSetTemplate,
    	'list-item': ListItemTemplate,
    	'data-list': DataListTemplate,
    	'link-button': LinkButtonTemplate,
    	'email-field': EmailFieldTemplate,
    	'container': ContainerTemplate,
    	'password-field': PasswordTemplate,
    	'springboard': SpringBoardTemplate,
    	'dynamic-list': DynamicListTemplate,
    	'dynamic-form': DynamicFormTemplate,
    	'navbar-button': NavBarButtonTemplate,
    	'control-group': ControlGroupTemplate,
    	'dynamic-form-button': DynamicFormButtonTemplate,
	 };
	
	/**
	 * Contains a mapping of back end attribute names
	 * to front end attribute names
	 */
	var attributeNames = {
			'src':'src',
			'seq':'seq',
			'size':'size',
			'href':'href',
			'name' : 'id',
			'title' : 'title',
			'method':'method',
			'target':'action',
			'class' : 'class',
			'dataRel':'data-rel',
			'dataIcon':'data-icon',
			'dataType':'data-type',
			'dataAjax':'data-ajax',
			'dataRole' : 'data-role',
			'dataShadow':'data-shadow',
			'dataFilter':'data-filter',
			'dataTheme' : 'data-theme',
			'dataInline':'data-inline',
			'dataInset' : 'data-inset',
			'description':'description',
			'dataIconpos':'data-iconpos',
			'dataPosition':'data-position',
			'dataEntityId': 'data-entity-id',
			'dataIconshadow':'data-iconshadow',
			'dataNativeMenu': 'data-native-menu',
			'dataSplitIcon' :'data-split-icon',
			'dataSplitTheme':'data-split-theme',
			'dataAddBackBtn':'data-add-back-btn',	
	};
	
	var UiAttributeData = function(attributeName, attributeValue){
		
		this.name = attributeName;
	    this.value = attributeValue;
		
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
        	this.currentPage = "";
        	this.uiData = options.uiData;
        	this.pageView = options.pageView;
        	this.application = this.pageView.application;
        },

        /**
         * Render this view.
         */
        render: function() 
        {
        	$(this.el).empty();
        	var htmlDom = 
        		this.renderUiComponentData(this.uiData.uiComponentData, this.uiData);
        	// Keep a reference of the loaded page on the document
        	// This reference will be used to prevent duplicate pages
        	// from existing in the DOM at the same time
        	document.currentPage = this.uiData.uiComponentData.name;
        	htmlDom.appendTo( $.mobile.pageContainer );
        	// Enhance and open new page
        	$.mobile.changePage( htmlDom );
    		return this;
        },
        
        /**
         * Renders a component. Recursively renders
         * all child components. Rendering in this case
         * involves filling the components template with data, and
         * processing the configured events for the component
         */
        renderUiComponentData: function(uiComponentData, uiData)
        {
        	//console.log('Processing component:' + uiComponentData.type);
        	// Sort the components
        	uiComponentData.components.sort(this.sortComponents);
        	if(uiComponentData.type ==='dynamic-form')
        	{
        		// Get the ui query data
    			// For a dynamic form the query data is a list 
    			// containing a single Business object whose 
    			// data values are the fields of the form
        		if(uiComponentData.uiQueryData.length > 0 ) {
        			var dataValues = [];
        			for (var property in uiComponentData.uiQueryData[0].dataValues) {
    		            dataValues.push(uiComponentData.uiQueryData[0].dataValues[property]);
        		    }
        			uiComponentData.form = FormUtil.blockBuilder(dataValues);
        		}
        	}
        	// Convert back end attribute names to front end attribute names
        	this.processUiComponentDataAttributes(uiComponentData);
        	// Process component translations
        	this.processMessageBundle(uiComponentData);
        		
        	var renderedTemplate = 
        		this.renderTemplate(templates[uiComponentData.type], uiData, uiComponentData);
        	var uiComponentDataElement = $(renderedTemplate);
        	// Process all child nodes
        	for(var i = 0; i < uiComponentData.components.length; i++) {
        		// Process event handlers
        		if(uiComponentData.components[i].type === "event-handler") {
        			//console.log('Adding event::' + uiComponentData.components[i].name + ' to component::' + uiComponentData.name);
        			this.addEventToUiComponent(uiComponentDataElement, uiComponentData.components[i]);
        		}
        		// Ignore business object data processors
        		else if(uiComponentData.components[i].type === "bo-data-processor") {
        		}
        		else {
        			// An element name with id '#' + uiComponentData.name + '-container'
        			// can be used as container for child components
        			// if this element is defined then all child components will be appended to
        			// this element instead of the uiComponentDataElement
        			var cubComponentContainer = '#' + uiComponentData.name + '-container';
        			var container = $(uiComponentDataElement).find(cubComponentContainer);
        			if(container.length > 0){
        				container.append(
                				this.renderUiComponentData(uiComponentData.components[i], uiData));
        			}
        			else {
                		uiComponentDataElement.append(
                				this.renderUiComponentData(uiComponentData.components[i], uiData));
        			}
        		}
        	}
        	return uiComponentDataElement;
        },
        
        /**
         * Convert back end attribute names to front end attribute names
         */
        processUiComponentDataAttributes: function(uiComponentData)
        {
        	var attributes = [];
        	jQuery.each(uiComponentData.attributes, function(name, value) 
        	{
        		var attributeName = attributeNames[name];
        		if(attributeName) 
        		{
        			attributes.push(
        					new UiAttributeData(attributeName, value));
        		}
        	});
        	// Save a reference to the unprocessed attributes
        	uiComponentData.dataAttributes = uiComponentData.attributes;
        	// Overwrite original reference with processed attributes
        	uiComponentData.attributes = attributes;
        },
        
        processMessageBundle: function(uiComponentData) {
        	var componentName = uiComponentData.name;
        	if(Messages[componentName] != undefined)
        		uiComponentData.description = Messages[componentName];

    		var businessObjectName = uiComponentData.uiQueryDataDescriptor;
    		
        	if(uiComponentData.form != undefined)
        	{
        		var fieldBlocks = uiComponentData.form.fieldBlocks;
            	for(var i = 0; i < fieldBlocks.length; i++) 
            	{
            		var fieldBlock = fieldBlocks[i];
            		for(var j = 0; j < fieldBlock.fields.length; j++) 
            		{
            			var field = fieldBlock.fields[j];
            			var fieldName = field.fieldData.fieldName;
            			var businessObjectNameText = 
            				businessObjectName.charAt(0).toLowerCase() + businessObjectName.substr(1);
            			var messageKeyName = businessObjectNameText + '_' + fieldName;
                    	if(Messages[messageKeyName] != undefined)
                    		field.fieldData.fieldDescription = Messages[messageKeyName];
                	}
            	}
        	}
        	if(Messages[businessObjectName] != undefined)
        		uiComponentData.uiQueryDataDescriptorText =
        			Messages[businessObjectName];
        	else
        		uiComponentData.uiQueryDataDescriptorText = 
        			uiComponentData.uiQueryDataDescriptor;
        },
        
        /**
         * Event handler processing.
         */
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
	        	$(uiComponentDataDomElement).on(uiComponentData.attributes.event, 
	        			{target:uiComponentData.name, pageView: this.pageView}, handlerObj['handle']);
			}
        },
        
        /**
         * Render a template.
         */
        renderTemplate:function (template, uiData, uiComponentData) {
    		// Render the template
            return _.template(template, 
            	{
            		uiData: uiData,
            		messages: Messages,
            		uiComponentData: uiComponentData
            	});
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
