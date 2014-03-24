/**
 * A module that contains the definition of all
 * the constants in the application
 */
define([
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'uiconstants'
],function ($, 
    		config, 
    		_, 
    		Backbone, 
    		UiConstants) {
	
    /**
     * Application wide constants.
     *
     * @type {Router}
     */

    var StringUtil = {
    		
    		isValid: function(stringValue) {
        		if(stringValue === '')
        			return false;
        		if(stringValue === undefined)
        			return false;
        		return true;
    		}
    };
    return StringUtil;
});