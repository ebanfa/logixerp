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

    var NavigatorClickHandler = {
    		
    		'handle': function(event) {
    			
    			event.preventDefault();
    			console.log('NavigatorClickHandler handling');
    			console.log('Using target::' + event.target.nodeName);
    			console.log('Using current target::' + event.currentTarget);
    			var moduleId = $(event.target).data('entityId');
    			console.log('Using module id::' + moduleId, 
    					+ ': From element::' + $(event.target).attr('id'));
    			// validate the entries
    			if(!StringUtilities.isValid(moduleId)){
    				console.log('Invalid module identifier provided');
    				return
    			};
    			console.log('Valid module id::' + moduleId);
    			var activityQuery = {
    					activityURL : 'activities_activity',
						uiDataQuery: {
							module: moduleId,
						}
    			};
    			if(window.application) {
        			console.log('Using activity query::'+ 
        					JSON.stringify(activityQuery, null, 4));
        			window.application.fireEvent(
        					UiConstants.activityChannel, 
        					UiConstants.uiLoadActivityEvent, 
        					activityQuery);
    			}
    			console.log('NavigatorClickHandler handling');
    		}
    		
    };
    
    return NavigatorClickHandler;
});