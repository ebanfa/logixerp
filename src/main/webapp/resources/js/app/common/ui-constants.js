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
    	classAttribute: ' class',
    	hrefAttribute: 'href',
    	emptyString: '',
    	uiServiceURL: 'ui-service',
    	loginContainer: 'login-container',
    	pageContainer: '#page-container',
    	
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
    	
    	/** State machine input events */
    	uiFailEvent: 'ui.fail',
    	uiRenderEvent: 'ui.render',
    	uiSuccessEvent: 'ui.success',
    	loadActivityEvent: 'ui.renderActivity',
    	
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