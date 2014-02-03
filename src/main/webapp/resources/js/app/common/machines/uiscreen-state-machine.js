define([
    'jquery',
    'configuration',
    'underscore',
    'machina',
    'uiconstants',
    'app/models/ui-response-data',
    'app/ui/views/desktop/ui/page-view'
], function ($, config, _, machina, UiConstants, UiResponseData, PageView) {
    
	var UiFsm = machina.Fsm.extend({
	    initialize: function() {
	    	this.application = null;
	    	this.pageView = 
	    		new PageView({model: new UiResponseData(), el:$(UiConstants.pageContainer)});
	    },
	    
	    started: false,
	    
	    namespace: "uiFsm",
	    
	    initialState: "login",
	    
	    states: {
	        login: {
	            /**
	             * This function will initialize the page view. 
	             * During initialization, the view will register its
	             * subscription for view events of interest to the view and
	             * will also lead to the login page being rendered.
	             */
	        	"ui.render": function() {
		    		if(!this.started) 
		    			this.pageView.start({application: this.application});
		    		else
		    			this.pageView.render();
		    	},
		    	"ui.success": function() {
		    		this.transition("loading");
	        	},
	        	"ui.fail": function() {
	        		this.pageView.loadUiComponentData(UiConstants.loginScreen);
	        	},
	        	"ui.die": function() {
	        		this.transition("unavailable");
	        	}
	        },
	        loading: {
	        	_onEnter: function(){
	        		this.application.fireEvent(UiConstants.uiFsm, UiConstants.uiRenderEvent);
	        	},
	        	"ui.render": function() {
	        		this.pageView.loadUiComponentData(UiConstants.loadingScreen);
	        	},
	        	"ui.success": function() {
	        		this.transition("home");
	        	},
	        	"ui.fail": function() {
	        		this.pageView.loadUiComponentData(UiConstants.loadingScreen);
	        		this.transition("login");
	        	}
	        },
	        home: {
	        	_onEnter: function(){
	        		this.application.fireEvent(UiConstants.uiFsm, UiConstants.uiRenderEvent);
	        	},
	        	"ui.render": function() {
	        		this.pageView.loadUiComponentData(UiConstants.homeScreen);
	        		this.transition("login");
	        	},
	        	"ui.logout": function() {
	        		this.pageView.loadUiComponentData(UiConstants.loginScreen);
	        		this.transition("login");
	        	},
	        	"ui.die": function() {
	        		this.transition("unavailable");
	        	}
	        },
	        unavailable: {
	        	_onEnter: function(){
	        		this.transition("ui.render");
	        	},
	        	"ui.render": function() {
	        		this.pageView.loadUiComponentData(UiConstants.deathScreen);
	        	},
	        	"ui.success": function() {
	        		this.transition("login");
	        	}
	        }
	    }
	});

    return UiFsm;
});
