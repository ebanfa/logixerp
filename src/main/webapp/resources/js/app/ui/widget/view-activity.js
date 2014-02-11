
(function($) {  
  $.widget('qurion.viewactivity', {
		
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
	    	console.log('Creating view activity plugin:::');
	    	$('.tab-section:visible').on(
	    			'click', '#view-edit-btn', this._loadBusinessObjectEditActivity(this));
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
         * Invoked when a entity is clicked on the business
         * object list table
         */
        _loadBusinessObjectEditActivity: function(self)
        {
        	return function (event)
        	{
        		event.preventDefault();
		    	if(window.application) {
			    	var UiConstants = window.application.uiConstants;
			    	console.log('Loading edit activity:::');
			    	var editActivityURL = self.options.activityURL;
			    	editActivityURL = editActivityURL.replace(/view_/gi, "edit_");
		    		var activityQuery = {
		    				activityURL: editActivityURL,
		    				entityQuery: {
		    					id: self.options.businessObjectId,
		    					id_options: '='
		    				}
		    		};
			    	console.log('Loading edit activity with query:::' + JSON.stringify(activityQuery, null, 4));
		    		window.application.fireEvent(UiConstants.activityChannel, 
		    				UiConstants.uiLoadActivityEvent, {activityQuery: activityQuery});
		    	}
        	};
        },
        
	  });

})(jQuery);
    	
	
