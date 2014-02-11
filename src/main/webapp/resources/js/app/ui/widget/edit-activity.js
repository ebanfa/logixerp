
(function($) {  
  $.widget('qurion.editactivity', {
		
	    options: {
		    activityURL: '',
		    businessObjectId: '',
		    businessObjectName: 'Product',
		    businessObjectSearchURL: 'search',
		    searchFieldURL: 'search/searchFields',
	    },
    	// Represents activities we have already rendered
    	// each element of the array is an activity lite object
    	
	    /**
	     * Widget create function
	     */
	    _create: function () {
	    	console.log('Creating edit activity plugin:::');

			if(window.application) {
		    	var UiConstants = window.application.uiConstants;
		    	window.application.subscribeToEvent(
	        			UiConstants.activityChannel, 
	        			UiConstants.uiTemplateRenderedEvent, 
	        			this._templateRenderedHanlder(this));
			}
	    	
	    },

	    /**
	     * Widget set option function
	     */
	    _setOption: function (key, value) { 
	    	this.options[ key ] = value;
	    },

	    /**
	     * Widget destroy function
	     */
	    _destroy: function () {},
	    
	    
        /**
         * Called when a template rendered event is fired
         */
        _templateRenderedHanlder: function(self) {
        	return function(data) {
        		
        	};
        },
        
	  });

})(jQuery);
    	
	
