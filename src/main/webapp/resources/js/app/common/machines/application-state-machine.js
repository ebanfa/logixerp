define([
	'jquery',
	'configuration',
    'uiconstants',
    'machina'
], function ($, config, UiConstants, machina) {
    
	var ApplicationFsm = machina.Fsm.extend({
	    initialize: function() {
	    },
	    
	    application: null,
	    
	    namespace: "applicationFsm",
	    
	    initialState: "initialized",
	    
	    states: {
	        initialized: {
	        	_onEnter: function() {
	        	},
	        	"application.setUp": function(data) {
	        		if(this.application) {
	        			this.application.handleSetup();
	        			window.application = this.application;
	        			this.transition("started");
	        		}
	        		else {
		        		console.log('No Blazeeeeeeeeeeeeeeeeeee');
	        		}
	        	}
	        },
	        started: {
	        	_onEnter: function() {
	        		if(this.application) {
	        			this.application.handleStart();
	        		}
	        	},

	        	"application.die": function() {
	        		if(this.application) {
	        			this.application.handleDie();
		        		this.transition("unavailable");
	        		}
	        	},
	        	
	        	"application.reboot": function() {
	        		if(this.application) {
	        			this.application.handleBoot();
		        		this.transition("initialized");
	        		}
	        	}
	        },
	        unavailable: {
	        	"application.reboot": function() {
	        		if(this.application) {
	        			this.application.handleBoot();
		        		this.transition("initialized");
	        		}
	        	}
	        },
	    }
	});

    return ApplicationFsm;
});
