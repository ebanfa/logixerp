define([
    'configuration',
    'app/util/utilities',
    'app/views/desktop/user/home-view',
    'app/views/desktop/login/login-view',
    'app/views/desktop/common/base-view',
    'text!../../../../../templates/desktop/page.html'
], function (
	config, utilities, HomeView, LoginView, BaseView, PageTemplate) {
	
    var PageView = BaseView.extend({
    
        initialize: function(options) 
        {
        	this.homeView = options.homeView;
        	this.loginView = options.loginView;
        	this.userContext = options.userContext;
        	// Ensure valid home view
        	if(!this.homeView)
        		this.homeView = 
        			new HomeView({el: $('#page-container'), userContext: this.userContext});
        	// Ensure valid login view
        	if(!this.loginView)
        		this.loginView = 
        			new LoginView({el: $('#page-container'), userContext: this.userContext});
        },
        
        render: function() 
        {
    		if(this.loginView.authenticated) 
    			this.renderHomeView();
    		else 
    			this.renderLoginView();
            return this;
        },
        
        renderHomeView: function()
        {
        	this.homeView.render();
        },
        
        renderLoginView: function()
        {
        	this.loginView.render();
        }
    });

    return PageView;
});
