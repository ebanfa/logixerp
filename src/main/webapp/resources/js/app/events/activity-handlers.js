/**
 * This is a collection of event handlers that
 * when invoked will load a new activity
 */
define([
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'uiconstants',
    'app/util/form-utilities',
    'app/util/string-utilities'
],function ($, 
    		config, 
    		_, 
    		Backbone, 
    		UiConstants,
    		FormUtilities,
    		StringUtilities) {
	
    /**
     * Handler for click
     * events generated when a favorite
     * item is clicked
     *
     * @type {Router}
     */

    var FavoriteItemClickHandler = 
    {
    		'handle': function(event) 
    		{
    			event.preventDefault();
    			var activityURL = '';
    			var activityId = $(event.target).data('entityId');
    			// Elements with the class 'search-activity-link'
    			// contain the id of the 
    			if($(event.target).hasClass('search-activity-link')){
    				activityURL = 'search_bo_activity';
    			}
    			else {
    				activityURL = 'create_bo_activity';
    				var parent = $(event.target).parent('.add-activity-link');
    				if(parent.length > 0) {
    					activityId = parent.data('entityId');
    				}
    			}
    			// validate the entries
    			if(StringUtilities.isValid(activityId) && 
    					StringUtilities.isValid(activityURL))
    			{
        			var activityQuery = {
    					activityURL : activityURL,
    					uiDataQuery: {
    						activityId: activityId,
    					}
        			};
        			console.log('Using activity query::' + JSON.stringify(activityQuery, null, 4));
        			if(window.application) {
            			window.application.fireEvent(
            					UiConstants.activityChannel, 
            					UiConstants.uiLoadActivityEvent, 
            					activityQuery);
        			}
    			}
    		}
    };

    var BusinessObjectSearchFormHandler = 
    {
    		'handle': function(event) 
    		{
    			event.preventDefault();
    			var businessObjectSearchForm =
    				$(event.target).closest('form');
    			console.log('Found forms::' + businessObjectSearchForm.length);
    			console.log('Found forms::' + businessObjectSearchForm.attr('id'));
    			if(businessObjectSearchForm.length > 0)
    			{
		            $.fn.formSerializer = FormUtilities.formSerializer;
		            var formData = $(businessObjectSearchForm).formSerializer();
		            if(!StringUtilities.isValid(formData.businessObjectName)) 
		            {
		        		window.application.fireEvent(
	        				UiConstants.activityChannel, 
	        				UiConstants.uiNotificationEvent, 
	        				{message: 'Invalid business object name'});
		        		return;
		            }
		            var dataValues = {};
		            var businessObjectData = {};
		            for(key in formData) {
		    			if(formData.hasOwnProperty(key) &&
		    				(key != 'businessObjectName')) {
		    				dataValues[key] = formData[key];
		    			}
		    		}
		            businessObjectData.dataValues = dataValues;
		            businessObjectData.businessObjectName = formData.businessObjectName;
	    			console.log('Forms::' + JSON.stringify(businessObjectData, null,4));
		            var activityQuery = {
		            	method: 'POST',
						activityURL: UiConstants.listActivityURL,
						businessObjectData: businessObjectData,
	    			};
        			window.application.fireEvent(
    					UiConstants.activityChannel, 
    					UiConstants.uiLoadActivityEvent, 
    					activityQuery);
    			}
    		}
    };
    

    var BusinessObjectViewFormHandler = 
    {
    		'handle': function(event) 
    		{
    			event.preventDefault();
    			
    			$.fn.formSerializer = FormUtilities.formSerializer;
    			var businessObjectEditForm = $(event.target).closest('form');
	            var formData = $(businessObjectEditForm).formSerializer();

    			var businessObjectName = formData.businessObjectName;
    			
	            if(!StringUtilities.isValid(businessObjectName)) 
	            {
	        		window.application.fireEvent(
        				UiConstants.activityChannel, 
        				UiConstants.uiNotificationEvent, 
        				{message: 'Invalid business object name'});
	        		return;
	            }
	            var dataValues = {};
	            var businessObjectData = {};
	            for(key in formData) {
	    			if(formData.hasOwnProperty(key) &&
	    				(key != 'businessObjectName') &&
	    					(key!= 'businessObjectId')) 
	    			{
	    				dataValues[key] = formData[key];
	    			}
	    		}
	            businessObjectData.dataValues = dataValues;
	            if(StringUtilities.isValid(formData.businessObjectId)) {
	            	businessObjectData.id = formData.businessObjectId;
	            	businessObjectData.dataValues.id = formData.businessObjectId;
	            }
	            businessObjectData.businessObjectName = businessObjectName;
	            
    			console.log('Forms::' + JSON.stringify(businessObjectData, null,4));
	            var activityQuery = {
	            	method: 'POST',
					activityURL: 'edit_bo_activity',
					businessObjectData: businessObjectData,
    			};
    			window.application.fireEvent(
    					UiConstants.activityChannel, 
    					UiConstants.uiLoadActivityEvent, 
    					activityQuery);
    		}
    };

    var BusinessObjectEditFormHandler = 
    {
    		'handle': function(event) 
    		{
    			event.preventDefault();
    			var businessObjectEditForm =
    				$(event.target).closest('form');
    			
    			if(businessObjectEditForm.length > 0)
    			{
		            $.fn.formSerializer = FormUtilities.formSerializer;
		            var formData = $(businessObjectEditForm).formSerializer();
		            if(!StringUtilities.isValid(formData.businessObjectName)) 
		            {
		        		window.application.fireEvent(
	        				UiConstants.activityChannel, 
	        				UiConstants.uiNotificationEvent, 
	        				{message: 'Invalid business object name'});
		        		return;
		            }
		            var dataValues = {};
		            var businessObjectData = {};
		            for(key in formData) {
		    			if(formData.hasOwnProperty(key) &&
		    				(key != 'businessObjectName') &&
		    					(key!= 'businessObjectId')) 
		    			{
		    				dataValues[key] = formData[key];
		    			}
		    		}
		            businessObjectData.dataValues = dataValues;
		            if(StringUtilities.isValid(formData.businessObjectId)) {
		            	businessObjectData.id = formData.businessObjectId;
		            	businessObjectData.dataValues.id = formData.businessObjectId;
		            }
		            businessObjectData.businessObjectName = formData.businessObjectName;
		            console.log('Using universe::' + window.application.userData.universe);
		            businessObjectData.dataValues.universe = window.application.userData.universe;
	    			console.log('Forms::' + JSON.stringify(businessObjectData, null,4));
		            var activityQuery = {
		            	method: 'POST',
						activityURL: UiConstants.listActivityURL,
						businessObjectData: businessObjectData,
	    			};
        			window.application.fireEvent(
    					UiConstants.activityChannel, 
    					UiConstants.uiLoadActivityEvent, 
    					activityQuery);
    			}
    		}
    };
    

    var BusinessObjectListFormHandler = 
    {
    		'handle': function(event) 
    		{
    			event.preventDefault();
    			var businessObjectId = '';
    			var businessObjectName = '';
    			var activityURL = 'view_bo_activity';
    			// Elements with the class 'search-activity-link'
    			// contain the id of the 
    			if($(event.target).hasClass('view-business-object-link')){
        			businessObjectId = $(event.target).data('businessObjectId');
    				businessObjectName = $(event.target).data('businessObjectName');
    			}
    			else {
    				var parent = $(event.target).parent('.view-business-object-link');
    				if(parent.length > 0) {
    					businessObjectId = parent.data('businessObjectId');
        				businessObjectName = parent.data('businessObjectName');
    				}
    			}
    			console.log('Using businessObjectId:: '+ businessObjectId);
    			console.log('Using businessObjectName:: '+ businessObjectName);
    			// validate the entries
    			if(StringUtilities.isValid(activityURL) &&
    					StringUtilities.isValid(businessObjectId) && 
    					StringUtilities.isValid(businessObjectName))
    			{
        			var activityQuery = {
    					activityURL: activityURL,
    					uiDataQuery: {
    						businessObjectId: businessObjectId,
    						businessObjectName: businessObjectName
    					}
        			};
        			if(window.application) {
            			window.application.fireEvent(
            					UiConstants.activityChannel, 
            					UiConstants.uiLoadActivityEvent, 
            					activityQuery);
        			}
    			}
    		}
    };
    
    /**
     * 
     **/
    var RegistrationFormHandler = 
    {
    		'handle': function(event) 
    		{
    			event.preventDefault();
    			var businessObjectEditForm =
    				$(event.target).closest('form');
    			
    			if(businessObjectEditForm.length > 0)
    			{
		            $.fn.formSerializer = FormUtilities.formSerializer;
		            var formData = $(businessObjectEditForm).formSerializer();
		            
		            var dataValues = {};
		            var businessObjectData = {};
		            for(key in formData) {
		    			if(formData.hasOwnProperty(key) &&
		    				(key != 'businessObjectName') &&
		    					(key!= 'businessObjectId')) 
		    			{
		    				dataValues[key] = formData[key];
		    			}
		    		}
		            businessObjectData.dataValues = dataValues;
		            businessObjectData.businessObjectName = 'RegistrationForm';
		            businessObjectData.dataValues.universe = window.application.userData.universe;
	    			console.log('Forms::' + JSON.stringify(businessObjectData, null,4));
		            var activityQuery = {
		            	method: 'POST',
						activityURL: UiConstants.listActivityURL,
						businessObjectData: businessObjectData,
	    			};
        			window.application.fireEvent(
    					UiConstants.activityChannel, 
    					UiConstants.uiLoadActivityEvent, 
    					activityQuery);
    			}
    		}
    };
    
    // Handler container
    var ActivityHandlers = {
    	FavoriteItemClickHandler: FavoriteItemClickHandler,
    	RegistrationFormHandler: RegistrationFormHandler,
    	BusinessObjectSearchFormHandler: BusinessObjectSearchFormHandler,
    	BusinessObjectEditFormHandler: BusinessObjectEditFormHandler,
    	BusinessObjectListFormHandler: BusinessObjectListFormHandler,
    	BusinessObjectViewFormHandler: BusinessObjectViewFormHandler
    };
    
    return ActivityHandlers;
});