/**
 * Shortcut alias definitions - will come in handy when declaring dependencies
 * Also, they allow you to keep the code free of any knowledge about library
 * locations and versions
 */
require.config({
    baseUrl:"resources/js",
    paths: {
        jquery:'libs/jquery-1.11.0.min',
        i18n:'libs/i18n',
        'jquerymobile-config':'libs/jquerymobile-config',
        jquerymobile:'libs/jquery.mobile-1.3.2.min',
        jqueryvalidate: 'libs/jquery.validate.min',
        text:'libs/text',
        underscore:'libs/underscore',
        backbone: 'libs/backbone-min',
        order: 'libs/order',
        utilities: 'app/util/utilities',
        router:'app/router/router',
        postal:'libs/postal.min',
        machina:'libs/machina.min',
        machinapostal:'libs/machina.postal.min',
        postaldiagnostics: 'libs/postal.diagnostics.min',
        application:'app/common/application',
        'jqm-datepicker-core':'libs/jqm-datebox.core',
        'jqm-datebox-calbox':'libs/jqm-datebox.mode.calbox',
        //application:'app/common/app',
        uiconstants:'app/common/ui-constants',
    },
    // We shim Backbone.js and Underscore.js since they don't declare AMD modules
    shim: {
        'underscore': {
        	exports: '_'
        },
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'jqueryvalidate': ['jquery'],
        'jquerymobile-config': ['jquery', 'utilities'],
        'jquerymobile': ['jquery','jquerymobile-config'],
        'jqm-datepicker-core': ['jquerymobile'],
        'jqm-datebox-calbox': ['jqm-datepicker-core']
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

require(['jquery', 'application', 'jquerymobile'], function( $, Application ){
    $(function(){
    	Application.init();
    });
});

define("initializer", ['jquery'], function ($) {
	$.ajaxSetup({cache:false});
    $('head').append('<link rel="stylesheet" href="resources/css/jquery.mobile-1.3.1.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/m.screen.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/jqm-datebox.css" />');
    
    $('head').append('<link rel="stylesheet" href="resources/css/app.css"/>');
});

// Now we declare all the dependencies
require(['initializer']);
