define([
	'jquery',
	'configuration',
	'underscore',
    'app/util/utilities',
    'machina'
], function ($, config, _, utilities, machina) {
    
	var ConnectivityFsm = machina.Fsm.extend({
	    initialize: function() {
	        // do stuff here if you want to perform more setup work
	        // this executes prior to any state transitions or handler invocations
	    },
	    
	    namespace: "connectivityFsm",
	    
	    initialState: "offline",
	    
	    states: {
	    	offline: {
	    		"network.goOnline": function() {
	        		this.transition("online");
	        	}
	        },
	        probing: {
	        	"network.heartBeat": function() {
	        		this.transition("online");
	        	},
	        	"network.noHeartBeat": function() {
	        		this.transition("disconnected");
	        	},
	        	"network.goOffline": function() {
	        		this.transition("offline");
	        	}
	        },
	        online: {
	        	"network.windowIsOffline": function() {
	        		this.transition("probing");
	        	},
	        	"network.appChaceError": function() {
	        		this.transition("probing");
	        	},
	        	"network.requestTimeOut": function() {
	        		this.transition("probing");
	        	},
	        	"network.goOffline": function() {
	        		this.transition("offline");
	        	}
	        },
	        disconnected: {
	        	"network.goOnline": function() {
	        		this.transition("online");
	        	},
	        	"network.windowOnline": function() {
	        		this.transition("online");
	        	},
	        	"network.appChaceDownloading": function() {
	        		this.transition("probing");
	        	},
	        	"network.goOffline": function() {
	        		this.transition("offline");
	        	},
	        },
	    }
	});

    return ConnectivityFsm;
});
