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

    var NavbarClickHandler = {
    		
    		'handle': function(event) {
    			
    			event.preventDefault();;
    			console.log('NavbarClickHandler handling');
    			console.log('Using target::' + event.target);
    			var activityURL = $(event.target).closest('a').data('querionActivityUrl');
    			console.log('Using activityURL::' + activityURL);
    			// validate the entries
    			if(!StringUtilities.isValid(activityURL)){
    				console.log('Invalid activity provided');
    				return
    			};
    			console.log('Valid activityURL::' + activityURL);
    			var activityQuery = {
    					activityURL : activityURL,
    			};
    			if(window.application) {
        			console.log('Using activity query::'+ 
        					JSON.stringify(activityQuery, null, 4));
        			window.application.fireEvent(
        					UiConstants.activityChannel, 
        					UiConstants.uiLoadActivityEvent, 
        					activityQuery);
    			}
    			console.log('NavbarClickHandler handling');
    		}
    		
    };
    
    return NavbarClickHandler;
});