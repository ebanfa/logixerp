/**
 * Shortcut alias definitions - will come in handy when declaring dependencies
 * Also, they allow you to keep the code free of any knowledge about library
 * locations and versions
 */
require.config({
    baseUrl:"resources/js",
    paths: {
        text:'libs/text',
        i18n:'libs/i18n',
        order: 'libs/order',
        backbone: 'libs/backbone',
        jquery:'libs/jquery-1.7.1',
        utilities: 'app/utilities',
        underscore:'libs/underscore',
        router:'app/router/mobile/router',
        jquerymobile:'libs/jquery.mobile-1.3.0',
    },
    // We shim Backbone since it doesn't declare an AMD module
    shim: {
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
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

define("initializer", [
    'jquery',
    'utilities',
    'text!../templates/mobile/main.html'
], function ($, utilities, MainTemplate) {
    $('head').append('<link rel="stylesheet" href="resources/css/jquery.mobile-1.3.0.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/m.screen.css"/>');
    $(document).bind("mobileinit", function () {
        utilities.applyTemplate($('body'), MainTemplate);
    });
});

// Now we declare all the dependencies
require(['order!initializer',
         'order!underscore',
         'order!backbone',
         'order!router'],
    function(){
});

define(["configuration"],function(configuration){
    return {config: configuration };
});

