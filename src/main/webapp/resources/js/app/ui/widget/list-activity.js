
(function($) {  
  $.widget('qurion.listactivity', {
		
	    options: {
		    searchFieldURL: 'search/searchFields',
		    businessObjectName: 'Product'
	    },
    	// Represents activities we have already rendered
    	// each element of the array is an activity lite object
    	
	    /**
	     * Widget create function
	     */
	    _create: function () {
	    	console.log('Creating list activity plugin');

			if(window.application) {
		    	var UiConstants = window.application.uiConstants;
		    	window.application.subscribeToEvent(
	        			UiConstants.activityChannel, 
	        			UiConstants.uiTemplateRenderedEvent, 
	        			this._templateRenderedHanlder(this));
			}
	    	this._build();
	    	
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
	     * Build the initial UI
	     */
	    _build: function() 
	    {
	    	if(!window.application)	return;
	    	// Hide the delete button
	    	$('#delete-list-item-btn').hide();
	    	// Add listeners to the buttons
	    	this._bindSearchButtonEvent();
	    	this._bindCreateButtonEvent();
	    },
	    
	    /**
	     * 
	     */
	    _bindSearchButtonEvent: function(){
	    	$('body').on('click', '#show-search-view-btn', $.proxy(this._searchButtonClickHandler, this));
	    },

	    /**
	     * 
	     */
	    _searchButtonClickHandler: function(event){
	    	event.preventDefault();
	    	// load the search fields for the entity
	    	// then show the search dialog
	    	this._loadSearchFields();
	    },

	    /**
	     * 
	     */
	    _bindCreateButtonEvent: function(event){
	    	$('#show-create-view-btn').click($.proxy(this._createButtonClickHandler, this));
	    },
	    
	    /**
	     * 
	     */
	    _createButtonClickHandler: function(event){
	    	event.preventDefault();
	    },
	    
	    /**
         * Load the search fields
         */
        _loadSearchFields: function () 
        {           
            var self = this;
            // do an ajax query to fetch the search fields of the entity
            window.application.ajaxUtil.ajaxGET(
            		this.options.searchFieldURL, {entityName: this.options.businessObjectName},
            		self._onSearchFieldsSuccessCallBack(this), self._onSearchFieldsErrorCallBack(this));
            return this;
        },
        /**
         * 
         */
        _onSearchFieldsSuccessCallBack: function(self)
        {
        	return function(data){
        		//console.log($('#entity-search-dialog-div').html());
				if(window.application) {
	        		var blockBuilder = window.application.formUtil.blockBuilder;
					var form = blockBuilder(data.dataFields);
			    	var UiConstants = window.application.uiConstants;
		    		window.application.fireEvent(UiConstants.activityChannel, 
		    				UiConstants.uiRenderTemplateRequestEvent, {template: 'search-dialog', form: form});
		    	};
        	};
        },
        
        /**
         * 
         */
        _onSearchFieldsErrorCallBack:function(self)
        {
        	return function(data) {
        		console.log('Business object field search error');
        	};
        },
        
        _templateRenderedHanlder: function(self) {
        	return function(data) {
        		$('#entity-search-dialog-div').empty();
        		$('#entity-search-dialog-div').append(data.renderedTemplate);
                $('#entity-search-dialog').modal('show');
        	};
        }
        
	  });

})(jQuery);
    	
	
