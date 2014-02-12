/**
 * Shortcut alias definitions - will come in handy when declaring dependencies
 * Also, they allow you to keep the code free of any knowledge about library
 * locations and versions
 */
requirejs.config({
    baseUrl: "resources/js",
    paths: {
        jquery:'libs/jquery-1.11.0.min',
        'jquery-ui': 'libs/jquery-ui.min',
        underscore:'libs/underscore',
        text:'libs/text',
        i18n:'libs/i18n',
        //order:'libs/order',
        bootstrap: 'libs/bootstrap',
        'bootstrap-dropdown': 'libs/bootstrap-dropdown',
        'bootstrap-datetimepicker': 'libs/bootstrap-datetimepicker',
        backbone: 'libs/backbone-min',
        utilities: 'app/util/utilities',
        router:'app/router/desktop/router',
        uiconstants:'app/common/ui-constants',
        postal:'libs/postal.min',
        machina:'libs/machina.min',
        machinapostal:'libs/machina.postal.min',
        application:'app/common/application',
    },

    waitSeconds: 45,
    
    // We shim the following since they don't declare an AMD module
    shim: {
    	
        'jquery-ui': { 
        	deps: ['jquery'],
        },
        'bootstrap': 
        {
            deps: ['jquery',],
        },
        'bootstrap-dropdown': 
        {
            deps: ['bootstrap',],
        },
        'bootstrap-datetimepicker': 
        {
            deps: ['bootstrap',],
        },
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

define("initializer", ['jquery', 'jquery-ui'],
    function ($) {
    $('head').append('<link rel="stylesheet" href="resources/css/jquery-ui.min.css" />');
    $('head').append('<link rel="stylesheet" href="resources/css/bootstrap.css" type="text/css" media="all"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/bootstrap-responsive.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/css.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/font-awesome.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/base-admin-2.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/base-admin-responsive.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/dashboard.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/signin.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/datetimepicker.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/alantra.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/notifier-theme-plastic.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/jquery.jqplot.min.css" />');

    $('head').append('<script type="text/javascript" src="resources/js/libs/bootstrap.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/app/ui/widget/sidebar.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/app/ui/widget/toolbar.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/app/ui/widget/tab-panel.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/app/ui/widget/edit-activity.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/app/ui/widget/view-activity.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/app/ui/widget/list-activity.js"></script>');
    
    $('head').append('<script type="text/javascript" src="resources/js/libs/base.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/area.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/donut.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/signin.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/excanvas.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/jquery.jqplot.min.js"></script>');
    
});

// Now we declare all the dependencies
require([
    'initializer',
    'underscore',
    'bootstrap',
    'bootstrap-dropdown',
    //'bootstrap-datetimepicker',
    'backbone',
    'postal',
    'machina',
    'machinapostal',
    'router',
    'uiconstants',
    'application'
], function(){
});

define("configuration", {
    baseUrl : "",
    entitySearchFieldPath: "/searchFields"
});