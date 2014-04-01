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
    'app/util/string-utilities'
],function ($, 
    		config, 
    		_, 
    		Backbone, 
    		UiConstants,
    		StringUtilities) {
	
    /**
     * Application wide constants.
     *
     * @type {Router}
     */

    var LoginSubmitHandler = {
		'handle': function(event) {
			event.preventDefault();
    		// 1. We need a reference to the page view 
			this.pageView = event.data.pageView;
    		// 2. We need a reference to the application
			this.application = this.pageView.application;
			// 3. Get the user name and password field values
			var userName = $('#login_page_username').val();
			var password = $('#login_page_password').val();
			// validate the entries
			if(!StringUtilities.isValid(userName) | !StringUtilities.isValid(password)){
				this.pageView.notifyUser('Please provide valid values for username and password');
				return
			}
			this.application.fireEvent(
					UiConstants.loginFsm, 
					UiConstants.loginLoginEvent, 
					{userName:userName, password:password});
		}
    };
    
    var LoginRegisterHandler = {
		'handle': function(event) {
			event.preventDefault();
			var activityQuery = {
            	method: 'GET',
				activityURL: 'register_business_activity',
			};
			window.application.fireEvent(
				UiConstants.activityChannel, 
				UiConstants.uiLoadActivityEvent, 
				activityQuery);
		}
    };
    
    var LoginHandler = {
    		LoginSubmitHandler:LoginSubmitHandler,
    		LoginRegisterHandler: LoginRegisterHandler,
    };
    
    return LoginHandler;
});