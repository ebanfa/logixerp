define([
    'app/util/utilities',
    'configuration',
    'i18n!app/nls/entities',
    'app/views/common/base-view',
    'text!../../../../../templates/desktop/user/home.html'
], function (utilities, config, entities_strings, BaseView, HomeTemplate) {
    
    var HomeView = BaseView.extend({
    
        initialize:function (options) {
        	this.userContext = options.userContext;
        },

        events: {
        },

        render: function () {
        	var self = this;
			utilities.applyTemplate($(this.el), HomeTemplate, 
					{errors: self.errors, entities_strings: entities_strings});
        },
    });

    return HomeView;
});
