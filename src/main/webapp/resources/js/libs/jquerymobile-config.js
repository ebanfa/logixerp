define(['jquery', 
        'utilities', 
        'text!../templates/main.html'], 
function ($, utilities, MainTemplate) 
{
      $(document).on("mobileinit", function () {
    	  console.log('Mobile initing');
    	  // Prior to creating and starting the router, 
    	  // we disable jQuery Mobile's own routing mechanism
    	  $.mobile.ajaxEnabled = false;
    	  $.mobile.hashListeningEnabled = false;
    	  $.mobile.linkBindingEnabled = false;
    	  $.mobile.pushStateEnabled = false;
    	  $.mobile.page.prototype.options.domCache = false;
    	  
		  	// Page
		  //$.mobile.page.prototype.options.headerTheme = "b";
		  //$.mobile.page.prototype.options.footerTheme = "b";
    	  utilities.applyTemplate($('body'), MainTemplate);
      });
});