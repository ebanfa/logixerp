/**
 * Shortcut alias definitions - will come in handy when declaring dependencies
 * Also, they allow you to keep the code free of any knowledge about library
 * locations and versions
 */
requirejs.config({
    baseUrl: "resources/js",
    paths: 
    {
        text:'libs/text',
        i18n:'libs/i18n',
        postal:'libs/postal.min',
        machina:'libs/machina.min',
        underscore:'libs/underscore',
        backbone: 'libs/backbone-min',
        jquery:'libs/jquery-1.11.0.min',
        utilities: 'app/util/utilities',
        router:'app/router/mobile/router',
        application:'app/common/application',
        uiconstants:'app/common/ui-constants',
        machinapostal:'libs/machina.postal.min',
        jquerymobile:'libs/jquery.mobile-1.3.2.min',
    },
    waitSeconds: 45,
    // We shim the following since they don't declare an AMD module
    shim: {
    	'backbone': {
            //These script dependencies should be loaded before loading
            //backbone.js
            deps: ['underscore', 'jquery'],
            //Once loaded, use the global 'Backbone' as the
            //module value.
            exports: 'Backbone'
        },
        'underscore': {
            exports: '_'
        }
    }
});

define("configuration", function() {
    if (window.TicketMonster != undefined && TicketMonster.config != undefined) {
        return {
            baseUrl: TicketMonster.config.baseRESTUrl
        };
    } else {
        return {
            baseUrl: ""
        };
    }
});

define("initializer", 
		['jquery', 'utilities', 'text!../templates/mobile/main.html'], 
		function ($, utilities, MainTemplate) 
{
	$('head').append('<link rel="stylesheet" href="resources/css/jquery.mobile-1.3.2.min.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/m.screen.css"/>');

    console.log('Appending!!!!!!!!!!!!!!');
    $(document).bind("mobileinit", function () {

        console.log('Mobile init called!!!!!!!!!!!!!!');
        // Prior to creating and starting the router, we disable jQuery Mobile's own routing mechanism
        $.mobile.hashListeningEnabled = false;
        $.mobile.linkBindingEnabled = false;
        $.mobile.pushStateEnabled = false;
        utilities.applyTemplate($('body'), MainTemplate);
    });
    // Then (load jQueryMobile and) start the router to finally start the app
    require(['router']);
});

// Now we declare all the dependencies
require([
    'initializer',
    'underscore',
    'backbone',
    'postal',
    'machina',
    'machinapostal',
    'router',
    'uiconstants',
    'application'
], function(){
});