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
    	activityServiceURL: 'activity-service',
    	
    	loginContainer: 'login-container',
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
    	homeScreen: 'home-container',
    	deathScreen: 'death-screen',
    	loginScreen: 'login-screen',
    	loadingScreen: 'loading-container',
    	
        /** Names of the state machines */
		uiFsm: 'uiFsm',
		loginFsm: 'loginFsm',
		applicationFsm: 'applicationFsm',
		connectivityFsm: 'connectivityFsm',
		
		/**Non state machine events */
		activityChannel : 'activityChannel',
    	uiLoadActivityEvent: 'activity.loadActivityRequest',
		uiActivityRenderedEvent: 'activity.renderActivityResponse',
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