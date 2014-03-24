/**
 * A module for the router of the desktop application.
 *
 */
define("router",[
    'jquery',
    'jquerymobile',
    'underscore',
    'utilities',
    'uiconstants',
    'app/ui/views/page-view',
    'app/models/ui-response-data',
    'text!../templates/home-view.html',
    'text!../templates/demo/login-view.html',
    'text!../templates/demo/springboard.html'
],function ($,
            jqm,
            _,
            utilities,
            UiConstants,
            PageView, 
            UiResponseData,
            HomeViewTemplate,
            LoginDemoTemplate,
            SpringBoardTemplate) {

    /**
     * The Router class contains all the routes within the application - i.e. URLs and the actions
     * that will be taken as a result.
     *
     * @type {Router}
     */
    var Router = Backbone.Router.extend({
    	
    	/**
    	 * Set up the page view on router init
    	 */
    	initialize: function(options) 
    	{
    		this.pageView = 
	    		new PageView({
	    			model: new UiResponseData(), 
	    			el:$(UiConstants.pageContainer),
	    			application: options.application
	    	});
    	},
    	
    	/**
    	 * Declare routes mapping
    	 */
        routes:{
            "":"activityRouteHandler",
            "logindemo": "loginDemo",
            "springboard": "springBoard",
            "activity/:activityURL":"activityRouteHandler",
            "*actions":"defaultHandler"
        },

    	/**
    	 * So called default handler
    	 */
        defaultHandler:function (actions) {
            if ("" != actions) {
                $.mobile.changePage("#" + actions, 
                		{transition:'slide', changeHash:false, allowSamePageTransition:true});
            }
        },
        
    	/**
    	 * Handles activity lookup 
    	 */
        activityRouteHandler: function (activityURL) 
        {
        	if(this.application)
        	{
        		if(activityURL !== null)
        			activityURL = "login_activity";
        		this.pageView.start();
        	}
        }
        
        ,
        loginDemo:function () {
            utilities.applyTemplate($("#container"), LoginDemoTemplate);
            try {
                $("#container").trigger('pagecreate');
            } catch (e) {
                // workaround for a spurious error thrown when creating the page initially
            }
        },
        springBoard:function () {
            utilities.applyTemplate($("#container"), SpringBoardTemplate);
            try {
                $("#container").trigger('pagecreate');
            } catch (e) {
                // workaround for a spurious error thrown when creating the page initially
            }
        }
    });
    
    return Router;
});