/**
 * Application module
 */
define("application", [
  'jquery',
  'configuration',
  'postal',
  'machinapostal',
  'router',
  'uiconstants',
  'postaldiagnostics',
  'app/events/event-handlers',
  'app/util/ajax-utilities',
  'app/util/form-utilities',
  'app/common/machines/login-state-machine',
  'app/common/machines/uiscreen-state-machine',
  'app/common/machines/application-state-machine',
  'app/common/machines/connectivity-state-machine',
],function ($, config, Postal, MachinaPostal, Router, UiConstants, DiagnosticsWireTap,
  EventHandlers, AjaxUtil, FormUtil, LoginFsm, UiFsm, ApplicationFsm, ConnectivityFsm) {
	
	 
    var Application = {

    		ajaxUtil: AjaxUtil,
    		
    		formUtil: FormUtil,
    		
    		channels:[],
		 
    		stateMachines: [],
    		
    		uiConstants: UiConstants,

	    	/**
	    	 * This function initializes application
	    	 */
			 init: function(options)
			 { 
				 this.createMachines(options);
				 
				 this.stateMachines[UiConstants.uiFsm].application = this;
				 this.stateMachines[UiConstants.loginFsm].application = this;
				 this.stateMachines[UiConstants.applicationFsm].application = this;
				 this.stateMachines[UiConstants.connectivityFsm].application = this;
				 
				 this.channels[UiConstants.activityChannel] = Postal.channel(UiConstants.activityChannel);
				 /*var wireTap = new DiagnosticsWireTap({
					    name: "console",
					    filters: [
					        { channel: UiConstants.activityChannel },
					        { topic: UiConstants.uiLoadActivityEvent  }
					    ]
					});*/
				 this.subscribeToEvent(
						 UiConstants.applicationFsm,
						 UiConstants.applicationLoginSuccessEvent, 
						 this.handleLoginEvent(this));
				 
				 this.subscribeToEvent(
						 UiConstants.applicationFsm, 
						 UiConstants.applicationLoginFailEvent, 
						 this.handleLoginEvent(this));
	        	
				 this.fireEvent(
						 UiConstants.applicationFsm, 
						 UiConstants.applicationSetUpEvent);
				 
				 if(!String.prototype.startsWith){
		        	    String.prototype.startsWith = function (str) {
		        	        return !this.indexOf(str);
		        	    };
		        }
			 },
			 
			 /**
			  * 
			  */
			 handleLoginEvent: function(self) 
			 {
				 return function(data) 
				 {
					 if(data.eventType == 'success') {
						 self.fireEvent(
								 UiConstants.uiFsm, 
								 UiConstants.uiSuccessEvent);
					 }
					 if(data.eventType == 'fail') {
						 self.fireEvent(
								 UiConstants.uiFsm, 
								 UiConstants.uiFailEvent, 
								 {message: 'Invalid user name or password'});
					 }
				 };
			 },
	    	/**
	    	 * Handler function for the start event for the
	    	 * initialized state.
	    	 * 
	    	 */
			 handleSetup: function() {
				// Create a router instance
			    this.router = new Router({application: this});
			    this.router.application = this;
			 },
	    	/**
	    	 * Handler function executed when processing
	    	 * enters the start state.
	    	 * 
		     */
			 handleStart: function(){
				//Begin routing
			    Backbone.history.start();
			 },
	    	/**
	    	 * Handler function for the die event.
	    	 * 
		     */
			 handleDie: function(){
				//Begin routing
			    Backbone.history.start();
			 },
			 
	    	/**
	    	 * Handler function for the reboot event.
	    	 * 
		     */
			 handleReboot: function() {
				//Begin routing
			    Backbone.history.start();
			 },
			
			/**
			 * 
		     */
			 fireEvent: function(channelName, eventName, data) 
			 {
				 if(this.channels[channelName]){
					 this.channels[channelName].publish(eventName, data);
				 }
			 },
			 
			/**
			 * 
		     */
			 subscribeToEvent: function(channelName, eventName, handler) 
			 {
				 if(this.channels[channelName])
					 this.channels[channelName].subscribe(eventName, handler);
			 },
			 
			/**
			 * 
		     */
			 createMachines: function(options)
			 {
				 this.stateMachines[UiConstants.applicationFsm] = new ApplicationFsm();
				 this.channels[UiConstants.applicationFsm] = Postal.channel(UiConstants.applicationFsm);
				 
				 this.stateMachines[UiConstants.uiFsm] = new UiFsm();
				 this.channels[UiConstants.uiFsm] = Postal.channel(UiConstants.uiFsm);

				 this.stateMachines[UiConstants.loginFsm] = new LoginFsm();
				 this.channels[UiConstants.loginFsm] = Postal.channel(UiConstants.loginFsm);
				 
				 this.stateMachines[UiConstants.connectivityFsm] = new ConnectivityFsm();
				 this.channels[UiConstants.connectivityFsm] = Postal.channel(UiConstants.connectivityFsm);
			 },
	 };
 	 return Application;
});