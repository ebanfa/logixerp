/**
 * A module that contains the definition of all
 * the constants in the application
 */
define([
    'jquery',
    'configuration',
    'uiconstants',
    'app/util/ajax-utilities',
    'app/util/string-utilities'
],function ($, 
    		config, 
    		UiConstants,
    		AjaxUtilities,
    		StringUtilities) {
	
    /**
     * Login controller.
     *
     * @type {LoginManager}
     */

    var LoginManager = function (application) {
    	
    	this.loginURL = '';
		this.application = application;
		this.authenticated = false;

		
		/**
		 * Main login function
		 */
		this.login = function(userName, password) {
			// 1. Validate the login parameters
			if(!StringUtilities.isValid(userName) | !StringUtilities.isValid(password)){
				//TODO Does nothing for now
				return;
			}
			// 2. Do the login process (Ajax call)
			AjaxUtilities.ajaxGET(UiConstants.loginServiceURL, 
        			{userName: userName, password: password}, 
        			this.loginSuccessCallBack(this), this.loginFailedCallBack(this));
		};
		
		/**
		 * Called upon successful of login
		 */
		this.loginSuccessCallBack = function(self) {
        	return function(response, textStatus) {
        		if(response.errors) {
    				self.application.fireEvent(
    						UiConstants.loginFsm, UiConstants.loginLoginFailEvent);
    				return;
        		}
    			this.authenticated = true;
    			var businessObject = response.data;
    			this.application.userData = 
    			{
    					userName: businessObject.dataValues.userNm.fieldValue, 
    					partyId: businessObject.dataValues.party.fieldValue,
    					universe:businessObject.dataValues.universe.fieldText
    			};
    			// Notify interested parties we have a successful login
				self.application.fireEvent(
						UiConstants.loginFsm, 
						UiConstants.loginLoginSuccessEvent, response);
			};
		};

		/**
		 * Called upon login failure
		 */
		this.loginFailedCallBack = function(self) {
			this.authenticated = true;
        	return function(data, textStatus) {
    			this.authenticated = true;
				self.application.fireEvent(
						UiConstants.loginFsm, 
						UiConstants.loginLoginFailEvent);
			};
		};
    		
    };
    return LoginManager;
});