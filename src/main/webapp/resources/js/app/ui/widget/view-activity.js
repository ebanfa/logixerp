
(function($) {  
  $.widget('qurion.viewactivity', {
		
	    options: {
		    activityURL: '',
		    businessObjectName: '',
		    businessObjectSearchURL: 'search',
		    searchFieldURL: 'search/searchFields',
	    },
    	// Represents activities we have already rendered
    	// each element of the array is an activity lite object
    	
	    /**
	     * Widget create function
	     */
	    _create: function () {
	    	console.log('Creating view activity plugin');

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
        		if('search-dialog' === data.templatName)
        		{
	        		$('#entity-search-dialog-div').empty();
	        		$('#entity-search-dialog-div').append(data.renderedTemplate);
	                $('#entity-search-dialog').modal('show');
					$('.search_options').hide();
					$('#search-form').on('focus', '.search-field', $.proxy(self._handleSearchFieldFocus, self));
					$('#search-form').on('click', '#do-entity-search-btn', self._doBusinessObjectSearch(self));
				}
        		else {
        			var businessObjectListTable = $('.tab-section:visible').find('#list-table');
        			businessObjectListTable.empty().append(data.renderedTemplate);
					$('.tab-section:visible').on('click', '.cell-link', self._loadBusinessObjectViewActivity(self));
        			
        		}
        	};
        },
        
        /**
         * Invoked when a entity is clicked on the business
         * object list table
         */
        _loadBusinessObject: function(self)
        {
        	return function (event)
        	{
	            event.preventDefault();
	            var linkClicked = $(event.currentTarget).attr('href');
		    	if(window.application) {
			    	var UiConstants = window.application.uiConstants;
			    	var viewActivityURL = self.options.activityURL;
			    	viewActivityURL = viewActivityURL.replace(/list_/gi, "view_");
		    		var activityQuery = {
		    				activityURL: viewActivityURL,
		    				entityQuery: {
		    					id: linkClicked,
		    					id_options: '='
		    				}
		    		};
		    		console.log('Loading view activity:' + JSON.stringify(activityQuery, null, 4));
		    		window.application.fireEvent(UiConstants.activityChannel, 
		    				UiConstants.uiLoadActivityEvent, {activityQuery: activityQuery});
		    	}
        	};
        }
        
	  });

})(jQuery);
    	
	
