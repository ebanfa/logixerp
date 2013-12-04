/**
 * A module for the router of the desktop application
 */
define("router", [
    'jquery',
    'underscore',
    'configuration',
    'app/views/desktop/ui/page-view'
],function ($,
            _,
            config,
            PageView) {
	
	var pageView = new PageView({el: $('body')});

    $(document).ready(new function() 
    {
    	pageView.render();
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
            "home": "renderHomePageView",
        },

    	/**
    	 * Render the page
    	 */
        renderHomePageView: function()
        {
        	pageView.renderHomeView();
        },
    });
    // Create a router instance
    var router = new Router();
    //Begin routing
    Backbone.history.start();
    return router;
});