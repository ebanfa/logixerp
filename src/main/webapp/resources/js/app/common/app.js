define(['jquery', 
        'router',
        /*'postal',
        'uiconstants',
        'app/util/ajax-utilities',
        'app/util/form-utilities',*/
        //'app/common/machines/login-state-machine',
        //'app/common/machines/uiscreen-state-machine',
        //'app/common/machines/application-state-machine',
        //'app/common/machines/connectivity-state-machine',
        ], 
function ($, 
		router
		/*Postal, 
		UiConstants, 
		AjaxUtil, 
		FormUtil*/
		//LoginFsm, 
		//UiFsm, 
		//pplicationFsm, 
		//ConnectivityFsm
		) 
{ 
	var App = function (options) {
    	return {
    		/**
	    	 * This function initializes application
	    	 */
			 init: function(options)
			 { 
				 console.log('Mode 1 bravo!!!');
			 }
    		
    	};
	};
	return new App();
});