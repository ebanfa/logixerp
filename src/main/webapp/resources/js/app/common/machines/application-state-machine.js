define([
	'jquery',
	'configuration',
	'underscore',
    'uiconstants',
    'machina'
], function ($, config, _, UiConstants, machina) {
    
	var ApplicationFsm = machina.Fsm.extend({
	    initialize: function() {
	    },
	    
	    application: null,
	    
	    namespace: "applicationFsm",
	    
	    initialState: "initialized",
	    
	    states: {
	        initialized: {
	        	"application.setUp": function(data) {
	        		if(this.application) {
	        			this.application.handleSetup();
	        			this.transition("started");
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
