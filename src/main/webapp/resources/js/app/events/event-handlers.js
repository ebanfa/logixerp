/**
 * A module that contains a mapping
 * for every event handler in the system
 */
define([
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'uiconstants',
    'app/events/login-submit-handler'
],function ($, 
    		config, 
    		_, 
    		Backbone, 
    		UiConstants,
    		LoginSubmitHandler) {
	
    /**
     * Event handler mappings.
     *
     * @type {Router}
     */

    var EventHandlers = {
    		
    	'login.submitHandler': LoginSubmitHandler,
    	
    };
    return EventHandlers;
});