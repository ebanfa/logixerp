define([
    'order!underscore', 
    'configuration',
    'app/util/utilities',
    'app/views/desktop/common/base-view',
    'app/views/desktop/ui/component-template-map',
    'text!../../../../../templates/desktop/page.html'
], function (
	_, config, utilities, BaseView, ComponentTemplateMap, PageTemplate) {

    /**
     * Responsible for rendering components.
     */
    var UiRendererView = BaseView.extend({

        /**
         * Constructor
         */
        initialize: function(options) 
        {
        	this.parentView = options.parentView;
        	this.component = options.component;
        },

        /**
         * Render this view.
         */
        render: function() 
        {
        	$(this.el).append(this.renderComponent(this.component));
    		return this;
        },
        
        /**
         * Renders a component. Recursively renders
         * all child components.
         */
        renderComponent: function(component)
        {
        	var template = ComponentTemplateMap[component.type];
        	var componentElement = $(this.renderTemplate(template, {component: component}));
        	for(var i = 0; i < component.components.length; i++)
        	{
        		componentElement.append(this.renderComponent(component.components[i]));
        	}
        	return componentElement;
        },
        
        /**
         * Render a template.
         */
        renderTemplate:function (template, data) {
            return _.template(template, (data == undefined) ? {} : data);
        }
    });

    return UiRendererView;
});
