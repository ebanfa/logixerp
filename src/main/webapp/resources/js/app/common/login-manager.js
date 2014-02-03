/**
 * A module that contains the definition of all
 * the constants in the application
 */
define([
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'uiconstants',
    'app/util/ajax-utilities',
    'app/util/string-utilities'
],function ($, 
    		config, 
    		_, 
    		Backbone, 
    		UiConstants,
    		AjaxUtilities,
    		StringUtilities) {
	
    /**
     * Application wide constants.
     *
     * @type {Router}
     */

    var LoginManager = function (application) {
    	
    	this.loginURL = '';
		this.application = application;
		
		this.login = function(userName, password) {
			// 1. Validate the login parameters
			if(!StringUtilities.isValid(userName) | !StringUtilities.isValid(password)){
				
			}
			// 2. Do the login process (Ajax call)
			AjaxUtilities.ajaxGET(UiConstants.uiServiceURL, 
        			{userName: userName, password: password}, 
        			this.loginSuccessCallBack(this), this.loginFailedCallBack(this));
		};
		
		this.loginSuccessCallBack = function(self) {
        	return function(data, textStatus) {
        		console.log('login data for call back' + data);
    			this.authenticated = true;
				self.application.fireEvent(UiConstants.loginFsm, UiConstants.loginLoginSuccessEvent, data);
			};
		};
		
		this.loginFailedCallBack = function(self) {
			this.authenticated = true;
        	return function(data, textStatus) {
    			this.authenticated = true;
				self.application.fireEvent(UiConstants.loginFsm, UiConstants.loginLoginFailEvent);
			};
		};
    		
    };
    return LoginManager;
});