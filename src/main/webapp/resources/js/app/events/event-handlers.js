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
    'app/events/activity-handlers',
    'app/events/navbar-click-handler',
    'app/events/login-submit-handler',
    'app/events/navigator-click-handler',
    'app/events/navigator-dyna-listitem-clickhandler'
],function ($, 
    		config, 
    		_, 
    		Backbone, 
    		UiConstants,
    		ActivityHandlers,
    		NavbarClickHandler,
    		LoginSubmitHandler,
    		NavigatorClickHandler,
    		NavigatorDynaListItemClickHandler) {
	
    /**
     * Event handler mappings.
     *
     * @type {Router}
     */

    var EventHandlers = {
    		
    	'login.submitHandler': LoginSubmitHandler,
    	'navigator.itemClickHandler': NavigatorClickHandler,
    	'navigator.navbarClickHandler':NavbarClickHandler,
    	'navigator.dynamicListLtemClickHandler': NavigatorDynaListItemClickHandler,
    	'activity.favoriteItemClickedHandler': ActivityHandlers.FavoriteItemClickHandler,
    	'businessObject.searchFormHandler': ActivityHandlers.BusinessObjectSearchFormHandler,
    	'businessObject.editFormHandler': ActivityHandlers.BusinessObjectEditFormHandler,
    	'businessObject.viewFormHandler': ActivityHandlers.BusinessObjectViewFormHandler,
    	'businessObject.boListItemClickedHandler': ActivityHandlers.BusinessObjectListFormHandler
    	
    };
    return EventHandlers;
});