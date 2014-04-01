define([
	'jquery',
	'configuration',
    'machina',
    'uiconstants',
    'app/common/login-manager'
], function ($, config, machina, UiConstants, LoginManager) {
    
	var LoginFsm = machina.Fsm.extend({
	    initialize: function() {
	    	this.application = null;
	    },
	    
	    namespace: "loginFsm",
	    
	    initialState: "unaunthenticated",
	    
	    states: {
	        unaunthenticated: {
	        	"login.login": function(data) {
	        		if(this.application != null)
	        			this.loginManager = new LoginManager(this.application);
	        		this.loginManager.login(data.userName, data.password);
	        		this.transition("authenticating");
	        	}
	        },
	        authenticating: {
	        	_onEnter: function(){
	        	},
	        	"login.success": function(data) {
	        		this.application.fireEvent(
	        				UiConstants.applicationFsm, 
	        				UiConstants.applicationLoginSuccessEvent, {eventType : 'success'});
	        		this.transition("aunthenticated");
	        	},
	        	"login.fail": function() {
	        		this.application.fireEvent(
	        				UiConstants.applicationFsm, 
	        				UiConstants.applicationLoginFailEvent, {eventType : 'fail'});
	        		this.transition("unaunthenticated");
	        	}
	        },
	        aunthenticated: {
	        	"login.logout": function() {
	        		this.transition("unaunthenticated");
	        	},
	        	"login.fail": function() {
	        		this.transition("unaunthenticated");
	        	}
	        },
	    }
	});

    return LoginFsm;
});
