/**
 * A module that contains the definition of all
 * the constants in the application
 */
define([],function () {
	
    /**
     * Application wide constants.
     *
     * @type {Router}
     */

    var UiConstants = {
	        
    	idAttribute: 'id',
    	classAttribute: 'class',
    	hrefAttribute: 'href',
    	emptyString: '',
    	
    	// Service URLs
    	uiServiceURL: 'ui-service',
    	loginServiceURL: 'login-service',
    	activityServiceURL: 'activity-service',
    	
    	
    	// Static activity names
    	loginActivityURL: 'login_activity',
    	listActivityURL: 'list_bo_activity',
    	
    	loginContainer: 'login-page',
    	pageContainer: '#page-container',
    	
    	// General selectors
    	firstItemSelector: ':first',
    	
    	// Id's and classes for tab widget
    	tabLink:'#tabs a',
    	currentTabClass:'current',
    	tabSectionClass:'.tab-section',
    	tabLinkCurrent:'#tabs a.current',
    	visibleTabSection:'.tab-section:visible',
    	
    	// Jquery/Javascript event names
    	uiClickEvent:'click',
    	
    	// screen definitions
    	homeScreen: 'home-page',
    	deathScreen: 'death-screen',
    	loginScreen: 'login_activity',
    	loadingScreen: 'loading-container',
    	
        /** Names of the state machines */
		uiFsm: 'uiFsm',
		loginFsm: 'loginFsm',
		applicationFsm: 'applicationFsm',
		connectivityFsm: 'connectivityFsm',

		/**Non state machine channels */
		activityChannel : 'activityChannel',
		
		/**Non state machine events */
		uiNotificationEvent: 'activity.notifyUser',
    	uiLoadActivityEvent: 'activity.loadActivityRequest',
    	uiPostActivityEvent: 'activity.postActivityRequest',
		uiRenderActivityRequestEvent: 'activity.renderActivityRequest',
    	
    	/** State machine input events */
    	uiFailEvent: 'ui.fail',
    	uiRenderEvent: 'ui.render',
    	uiSuccessEvent: 'ui.success',
    	
    	loginLoginEvent: 'login.login',
    	loginLoginFailEvent: 'login.fail',
    	loginLoginSuccessEvent: 'login.success',
    	
    	applicationSetUpEvent: 'application.setUp',

    	/** State machine output events */
    	uiRenderedEvent: 'ui.uiRendered',
    	applicationLoginFailEvent: 'application.loginFailed',
    	applicationLoginSuccessEvent: 'application.loginSuccess',
        //loginLogoutSucess: 'login.logoutSuccess',
        
    };
    return UiConstants;
});