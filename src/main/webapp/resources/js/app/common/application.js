/**
 * Application module
 */
define("application", [
  'jquery',
  'configuration',
  'underscore',
  'postal',
  'router',
  'uiconstants',
  'app/common/machines/login-state-machine',
  'app/common/machines/uiscreen-state-machine',
  'app/common/machines/application-state-machine',
  'app/common/machines/connectivity-state-machine',
],function ($, config, _, Postal, Router, UiConstants,
  LoginFsm, UiFsm, ApplicationFsm, ConnectivityFsm) {
	
	
    var Application = function (options) {
    	return {

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
				 
				 this.fireEvent(
						 UiConstants.uiFsm, 
						 UiConstants.uiRenderEvent);
			 },
			 
			 handleLoginEvent: function(self) {
				 // if authenticated
				 return function(data) 
				 {
					 if(data.eventType == 'success') {
						 self.fireEvent(
								 UiConstants.uiFsm, 
								 UiConstants.uiSuccessEvent);
					 }
					 // if authenticated
					 if(data.eventType == 'fail') {
						 self.fireEvent(
								 UiConstants.uiFsm, 
								 UiConstants.uiFailEvent);
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
			    this.router = new Router();
			    console.log('Application set up complete');
			 },
	    	/**
	    	 * Handler function executed when processing
	    	 * enters the start state.
	    	 * 
		     */
			 handleStart: function(){
				//Begin routing
			    Backbone.history.start();
			    console.log('Application started');
			 },
	    	/**
	    	 * Handler function for the die event.
	    	 * 
		     */
			 handleDie: function(){
				//Begin routing
			    Backbone.history.start();
			    console.log('Application started');
			 },
	    	/**
	    	 * Handler function for the reboot event.
	    	 * 
		     */
			 handleReboot: function() {
				//Begin routing
			    Backbone.history.start();
			    console.log('Application started');
			 },
			
			/**
			 * 
		     */
			 fireEvent: function(channelName, eventName, data) 
			 {
				 if(this.channels[channelName]){
					 console.log('Firing event: ' + eventName + ' on channel: ' + channelName + ' on channel object: ' + JSON.stringify(this.channels[channelName], null, 4));
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
	 };
	 window.application = new Application();
	 window.application.init();
 	return window.application;
});