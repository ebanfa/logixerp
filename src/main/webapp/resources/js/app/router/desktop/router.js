/**
 * A module for the router of the desktop application
 */
define("router", [
    'jquery',
    'configuration',
    'underscore',
    'backbone'
],function ($, config, _, Backbone) {

    $(document).ready(new function() 
    {
        //utilities.applyTemplate($('body'), MainTemplate);
    });

    /**
     * The Router class contains all the routes within the application -
     * i.e. URLs and the actions that will be taken as a result.
     *
     * @type {Router}
     */

    var Router = Backbone.Router.extend({
    	/**
    	 * Route hash
    	 */
        routes : {
            "": "showLoginView",
            "login": "showLoginView"
        },

    	/**
    	 * Show login action
    	 */
        showLoginView:function()
        {
        	console.log('Routed');
            //var loginView = new LoginView({el:$("#page-container")});
            //utilities.viewManager.showView(loginView);
        }
    });
    
    return Router;
});