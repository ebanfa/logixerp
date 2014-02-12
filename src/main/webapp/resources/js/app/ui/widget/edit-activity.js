
(function($) {  
  $.widget('qurion.editactivity', {
		
	    options: {
		    editMode: true,
		    activityURL: '',
		    businessObjectData: [],
		    businessObjectSearchURL: 'search',
		    searchFieldURL: 'search/searchFields',
	    },
    	// Represents activities we have already rendered
    	// each element of the array is an activity lite object
	    // By default we are in edit mode
    	
	    /**
	     * Widget create function
	     */
	    _create: function () {
	    	if(this.options.businessObjectData.id !== undefined)
	    		this.options.editMode = false;
	    	$('#edit-businessobject-form').submit(this._saveBusinessObject(this));
	    	$('.tab-section:visible').on(
	    			'click', '#edit-done-btn', this._loadBusinessObjectListActivity(this));
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
			    	editActivityURL = editActivityURL.replace(/edit_/gi, "list_");
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
        
        /**
         * Called when the done button of the  
         * business object edit form is clicked.
         */
        _saveBusinessObject: function(self) 
	    {
        	return function(event) {
                event.preventDefault();
				if(window.application) 
				{
					var formUtil = window.application.formUtil;
	                $.fn.formSerializer = formUtil.formSerializer;
	                // The business object before its data values have been updated
	                var updatedBusinessObjectData = {};
	        		for(key in self.options.businessObjectData) {
	        			if(self.options.businessObjectData.hasOwnProperty(key)) {
	        				updatedBusinessObjectData[key] = self.options.businessObjectData[key];
	        			}
	        		}
	        		var dataValues = updatedBusinessObjectData.dataValues;
	                var formData = $(event.currentTarget).formSerializer();

	        		console.log('###########formData: \n' + JSON.stringify(formData, null,4));
	        		for(key in dataValues)
	        		{
	        			if(dataValues.hasOwnProperty(key)) {
	        				if(formData[key])
	        					dataValues[key].fieldValue = formData[key];
	        			}
	        		}
	                delete updatedBusinessObjectData.secondaryRoutes;
	                delete updatedBusinessObjectData.primaryOutboundRoute;
	                delete updatedBusinessObjectData.inboundBusinessObject;
	                delete updatedBusinessObjectData.forwardBusinessObject;
	                delete updatedBusinessObjectData.outboundBusinessObject;
	                
	        		console.log('###########businessObjectData: \n' + JSON.stringify(updatedBusinessObjectData, null,4));
	                if (!self.options.editMode) 
	                	delete updatedBusinessObjectData.id;
	                // Persist
			    	var uiConstants = window.application.uiConstants;
	                window.application.ajaxUtil.ajaxPOST(
	                		uiConstants.activityServiceURL, 
	                		JSON.stringify(updatedBusinessObjectData),
	                		self._onSaveBusinessObjectSuccessCallBack(this), 
	                		self._onSaveBusinessObjectErrorCallBack(this));
				}
        	};
        },
        _onSaveBusinessObjectSuccessCallBack: function(self)
        {
        	return function(data){
        		console.log('@@@@@@@@@@@@@@@@@Bacck from save');
        	};
        },
        _onSaveBusinessObjectErrorCallBack: function(self)
        {
        	return function(data){

        		console.log('@@@@@@@@@@@@@@@@@Bacck from save error');
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
    	
	
