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

    var NavigatorDynaListItemClickHandler = {
    		
    		'handle': function(event) {
    			
    			event.preventDefault();
    			console.log('NavigatorDynaListItemClickHandler handling');
    			console.log('Using target::' + event.target.nodeName);
    			console.log('Using current target::' + event.currentTarget);
    			var activityId = $(event.target).data('entityId');
    			console.log('Using activity id::' + activityId, 
    					+ ': From element::' + $(event.target).attr('id'));
    			// validate the entries
    			if(!StringUtilities.isValid(activityId)){
    				console.log('Invalid activity identifier provided');
    				return
    			};
    			console.log('Valid activity id::' + activityId);
    			var activityQuery = {
    					activityURL : 'search_activity',
    					entityQuery: {
    						id: activityId,
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
    			console.log('NavigatorDynaListItemClickHandler handling');
    		}
    		
    };
    
    return NavigatorDynaListItemClickHandler;
});