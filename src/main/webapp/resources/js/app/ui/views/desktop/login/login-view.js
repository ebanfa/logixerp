define([
    'app/util/utilities',
    'app/util/ajax-utilities',
    'configuration',
    'i18n!app/nls/entities',
    'app/views/common/base-view',
    'app/views/desktop/ui/uirenderer-view',
], function (utilities, AjaxUtil, config, entities_strings, BaseView, UiRendererView) {
    
    var LoginView = BaseView.extend({
    
        initialize:function (options) {
        	this.errors = [];
        	this.authenticated = false;
        	this.uiServiceURL = "ui-service";
        	this.userContext = options.userContext;
        },

        events: {
            "submit #FORM_LOGIN": "processLoginRequest"
        },

        render: function () {
        	AjaxUtil.ajaxGET(this.uiServiceURL, {componentName:'login-container',
        		password:self.password}, this.authenticationSuccessCallBack(this),
        		this.authenticationErrorCallBack(this));
        },
        
        renderUiComponent: function(uiComponentData) {
        	var uiRendererView = new UiRendererView({el: $(this.el), component:uiComponentData});
        	uiRendererView.render();
			return this;
        	this.authenticated = true;
        },
        
        isAuthenticated: function() {
        	return this.authenticated;
        },

        processLoginRequest: function(event) {
        	event.preventDefault();
        },
        
        authenticationSuccessCallBack: function(self) {
        	this.authenticated = true;
        	return function(data, textStatus) {
        		//console.log(JSON.stringify(data, null, 4));
				self.renderUiComponent(data);
			};
        },
        
        authenticationErrorCallBack: function() {
        	this.authenticated = false;
        	return function(data, textStatus) {
				console.log("Error::::" + textStatus);
			};
        }
    });

    return LoginView;
});
