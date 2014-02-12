
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
	    	$('.tab-section:visible').on(
	    			'click', '#view-done-btn', this._loadBusinessObjectListActivity(this));
	    	$('.tab-section:visible').on(
	    			'click', '.lookup-field', this._loadRelatedBusinessObjectSearchDialog(this));
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
			    	var editActivityURL = self.options.activityURL;
			    	editActivityURL = editActivityURL.replace(/view_/gi, "edit_");
		    		var activityQuery = {
		    				activityURL: editActivityURL,
		    				entityQuery: {
		    					id: self.options.businessObjectId,
		    					id_options: '='
		    				}
		    		};
		    		window.application.fireEvent(UiConstants.activityChannel, 
		    				UiConstants.uiLoadActivityEvent, {activityQuery: activityQuery});
		    	}
        	};
        },
        
        /**
         * Called when the save button on the 
         * business object edit form is clicked
         */
        _loadBusinessObjectListActivity: function(self)
        {
        	return function (event)
        	{
		    	if(window.application) {
			    	var UiConstants = window.application.uiConstants;
			    	var editActivityURL = self.options.activityURL;
			    	editActivityURL = editActivityURL.replace(/view_/gi, "list_");
		    		var activityQuery = {
		    				activityURL: editActivityURL,
		    				entityQuery: {
		    					id: self.options.businessObjectId,
		    					id_options: '='
		    				}
		    		};
		    		window.application.fireEvent(UiConstants.activityChannel, 
		    				UiConstants.uiLoadActivityEvent, {activityQuery: activityQuery});
		    	}
        		event.preventDefault();
        	};
        },

        _loadRelatedBusinessObjectSearchDialog: function(self)
        {
        	return function (event)
        	{
        		event.preventDefault();
        	};
        },
        
	  });

})(jQuery);
    	
	
