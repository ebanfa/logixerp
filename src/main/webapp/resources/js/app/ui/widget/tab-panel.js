
(function($) {  
  $.widget('qurion.tabs', {
		
	    options: {
	    	// Represents the activities we want to open
	    	// each element of the array is an activity lite object
	    	activityQuery: {},
	    	// Max number of tabs
	    	maxTabs: 7,
	    },
    	// Represents activities we have already rendered
    	// each element of the array is an activity lite object
    	activitiesRendered: [],
    	
    	currentTabData: {},
	    /**
	     * Widget create function
	     */
	    _create: function () {
	    	console.log('Creating tabs plugin');
	    	var application = window.application;
	    	if(application)	
	    	{ 
		    	var UiConstants = window.application.uiConstants;
		    	// Register to listen for activity rendered events
	    		application.subscribeToEvent(UiConstants.activityChannel, 
	    				UiConstants.uiActivityRenderedEvent, this._activityRenderedHandler(this));
	    		// Register to listen for load activity events
	    		application.subscribeToEvent(UiConstants.activityChannel, 
	    				UiConstants.uiLoadActivityEvent, this._loadActivityHandler(this));
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
	    	console.log('Building tabs plugin');
	    	if(!window.application)	return;
	    	this._loadActivity();
	    },
	    
	    /**
	     * Load all the activities specified
	     * in the activities to render array. Note:
	     * to ensure that duplicate activities are not rendered,
	     * we must call _verifyActivitiesToRender.
	     */
	    _loadActivity: function() {
	    	if(this.options.activityQuery.activityURL !== undefined)
	    	{
		    	var uiConstants = window.application.uiConstants;
		    	window.application.ajaxUtil.ajaxGET(
		    			uiConstants.activityServiceURL, {activityQuery: this.options.activityQuery}, 
		    			this._loadActivitySuccessCallback(this), this._loadActivityErrorCallback(this));
	    	}
	    },

	    /**
	     * Load activity success callback
	     */
	    _loadActivitySuccessCallback: function(self){
	    	return function(data)
	    	{
	    		self._fireRenderActivityRequest(data);
	    	};
	    },

	    /**
	     * Load activity error callback
	     */
	    _loadActivityErrorCallback: function(self)
	    {
	    	return function(data)
	    	{
	    		
	    	};
	    },
	    
	    /**
	     * Called to fire a request for an activity to be
	     * rendered
	     */
	    _fireRenderActivityRequest: function(data) {
	    	console.log('Executing tab widget fucntion _fireRenderActivityRequest$$$$$$$$$$');
	    	var application = window.application;
	    	var UiConstants = application.uiConstants;
	    	application.fireEvent(UiConstants.activityChannel, 
	    			UiConstants.uiRenderActivityRequestEvent, {activityResponseData: data});
	    },

	    /**
	     * Handler for load activity requests that come in via
	     * the message bus
	     */
	    _loadActivityHandler: function(self)
	    {
	    	return function(data)
	    	{
		    	self._setOption('activityQuery', data.activityQuery);
		    	self._loadActivity();
	    	};
	    },

	    /**
	     * Callback function that is called
	     * when a activity rendered event has been
	     * fire on the message bus
	     */
	    _activityRenderedHandler: function(self)
	    {
	    	return function(data)
	    	{
	    		if(self.activitiesRendered.length == 0)
    	    		self._addActivityTab(data);
	    		else
	    		{
	    			var activityURL = data.activityData.dataValues.activityUrl;
	    			if($.inArray(activityURL, self.activitiesRendered) === -1)
	    	    		self._addActivityTab(data);
	    		}
		    		
	    	};
	    },
	    /**
	     * Builds and adds a new tab from the data
	     * provided.
	     */
	    _addActivityTab: function(data)
	    {
	    	$('.tab_section').hide();
	    	this.currentTabData.activityURL = data.activityData.dataValues.activityUrl;
    		this.currentTabData.tab = this._buildTab(data.activityData);
    		this.currentTabData.tabContent = this._buildTabContent(data);
	    	this.activitiesRendered.push(data.activityData.dataValues.activityUrl);
	    },
	    
	    _buildTab: function(activityData)
	    {
	    	var tab = $('<li>');
    		var tabLink = $('<a>');
    		tabLink.attr('href', '#' + activityData.dataValues.activityUrl);
    		tabLink.text(activityData.dataValues.displayNm);
    		this._bindTabEvent(tabLink);
    		tab.append(tabLink);
	    	return $("#tabs").append(tab);
	    },
	    
	    _buildTabContent: function(data)
	    {
	    	var contentContainer = $('<div>');
	    	contentContainer.attr('id', data.activityData.dataValues.activityUrl);
	    	contentContainer.addClass('tab-section');
	    	$('#tab-content').append(contentContainer.append(data.activityElement));
	    	return contentContainer;
	    },
	    
	    _bindTabEvent: function(tabLink)
	    {
	    	var self = this;
    		tabLink.click(function(e)
    		{
    			if(self.currentTabData.tabContent !== undefined) {
        			console.log('Current tab is:' + self.currentTabData.activityURL);
    				self.currentTabData.tabContent.css('display', 'none');
    			}
	            $('#tabs a.current').removeClass('current');
	            $('.tab-section:visible').hide();
	            $(this.hash).show();
	            $(this).addClass('current');
	            e.preventDefault();
	        }).click();
	    },
	  });

})(jQuery);
    	
	
