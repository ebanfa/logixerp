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
 
	
	// Helper function to hide or show a component
	var hideUiComponent = function(componentSelector, hideFg){
		// Find the page
		var page = $('#register_business_page'); 
		// Find the component
		var uiComponent = page.find(componentSelector);
		// Make sure we have found the fields
		if(uiComponent.length > 0)	{
			// Hide the fields
			if(hideFg)
				uiComponent.closest('li').hide();
			else
				uiComponent.closest('li').show();
		}
	};
	
	// Hides the business name and business type fields
	$(document).on('pageinit', '#register_business_page', function (e) 
    {          
		hideUiComponent('#register_business_businessno', true);
		hideUiComponent('#register_business_businesstype', true);
		hideUiComponent('#register_business_businessname', true);
    });
	
    var RegistrationTypeHandler = {
		'handle': function(event) 
		{
			event.preventDefault();
			var target = event.target;
			$(target).find('option:selected').each(function() 
			{
				if($( this ).attr('value') === 'BIZ') 
				{
					hideUiComponent('#register_business_businessno', true);
					hideUiComponent('#register_business_businesstype', false);
					hideUiComponent('#register_business_businessname', false);
				}
				else
				{
					hideUiComponent('#register_business_businessno', false);
					hideUiComponent('#register_business_businesstype', true);
					hideUiComponent('#register_business_businessname', true);
				}
			});
		},
    };
    var RegistrationChoiceHandler = {
    		'handle': function(event) {
    			event.preventDefault();
    			var activityQuery = {
                	method: 'GET',
    				activityURL: 'registration_choice_activity',
    			};
    			window.application.fireEvent(
    				UiConstants.activityChannel, 
    				UiConstants.uiLoadActivityEvent, 
    				activityQuery);
    		}
        };
    
    var RegistrationHandlers = {
    		RegistrationTypeHandler: RegistrationTypeHandler,
    		RegistrationChoiceHandler: RegistrationChoiceHandler,
    };
    
    return RegistrationHandlers;
});