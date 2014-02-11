
(function($) {  
  $.widget('qurion.listactivity', {
		
	    options: {
		    activityURL: '',
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
		    				UiConstants.uiRenderTemplateRequestEvent, {template: 'search-dialog', data: form});
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
        
        _handleSearchFieldFocus: function(event) {
			$('.search_options').hide();
        	var fieldContainerElement = $(event.currentTarget).parent();
        	var fieldOptionsElement = fieldContainerElement.find('.search_options');
        	fieldOptionsElement.show();
        },
        
        _doBusinessObjectSearch:function(self)
        {
        	return function(event){
                //this.entitySearchDialogView.closeSearchDialog();
                $.fn.formSerializer = window.application.formUtil.formSerializer;
                var searchData = $('#search-form').formSerializer();
                if(searchData === undefined) searchData = {};

                if(!searchData.entityName)
    	            searchData.entityName = self.options.businessObjectName;
                window.application.ajaxUtil.ajaxGET(self.options.businessObjectSearchURL, searchData,
                		self.onBusinessObjectSearchSuccessCallBack(self), self.onBusinessObjectSearchFailureCallBack(self));
                $('#entity-search-dialog').modal('hide');
        	};
        },
        
        /**
         * 
         */
        onBusinessObjectSearchSuccessCallBack: function(self)
        {
        	return function(data){
            	if(window.application) {
			    	var UiConstants = window.application.uiConstants;
			    	window.application.fireEvent(UiConstants.activityChannel, 
			    			UiConstants.uiRenderTemplateRequestEvent, {template: 'list-table', data: data});
            	}
        	};
        },

        /**
         * 
         */
        onBusinessObjectSearchFailureCallBack: function(self)
        {
        	return function(data) {
        		
        	};
        },
        
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
        		if('list-table' === data.templatName){
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
        _loadBusinessObjectViewActivity: function(self)
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
		    		window.application.fireEvent(UiConstants.activityChannel, 
		    				UiConstants.uiLoadActivityEvent, {activityQuery: activityQuery});
		    	}
        	};
        }
        
	  });

})(jQuery);
    	
	
