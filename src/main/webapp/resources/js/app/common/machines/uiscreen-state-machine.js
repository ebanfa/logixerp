define([
    'jquery',
    'configuration',
    'machina',
    'uiconstants'
], function ($, config, machina, UiConstants) {
    
	var UiFsm = machina.Fsm.extend({
	    initialize: function() {
	    	this.application = null;
	    	
	    },
	    
	    started: false,
	    
	    namespace: "uiFsm",
	    
	    initialState: "login",
	    
	    states: {
	        login: {
	            /**
	             * This event handler gets things going 
	             * by loading the login activity
	             */
	        	"ui.render": function() {
	            	// Tell handler to load and 
	        		// render the login activity
	        		this.application.fireEvent(
        				UiConstants.activityChannel, 
        				UiConstants.uiLoadActivityEvent,
    				    {activityURL: UiConstants.loginActivityURL});
		    	},
		    	"ui.success": function() {
		    		this.transition("loading");
	        	},
	        	"ui.fail": function(data) {
	        		this.application.fireEvent(
        				UiConstants.activityChannel, 
        				UiConstants.uiNotificationEvent, data);
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
	            	// Tell handler to load and render the activity
	        		this.application.fireEvent(
	        				UiConstants.activityChannel, 
	        				UiConstants.uiLoadActivityEvent,
	        				{activityURL: 'module_navigator'});
	        	},
	        	"ui.success": function() {
	        		this.transition("home");
	        	},
	        	"ui.fail": function() {
	        		//this.pageView.loadUiComponentData(UiConstants.loadingScreen);
	        		this.transition("login");
	        	}
	        },
	        home: {
	        	_onEnter: function(){
	        		this.application.fireEvent(UiConstants.uiFsm, UiConstants.uiRenderEvent);
	        	},
	        	"ui.render": function() {
	        		//this.pageView.loadUiComponentData(UiConstants.homeScreen);
	        		this.transition("login");
	        	},
	        	"ui.logout": function() {
	        		//this.pageView.loadUiComponentData(UiConstants.loginScreen);
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
	        		//this.pageView.loadUiComponentData(UiConstants.deathScreen);
	        	},
	        	"ui.success": function() {
	        		this.transition("login");
	        	}
	        }
	    }
	});

    return UiFsm;
});
