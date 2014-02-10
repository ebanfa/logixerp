(function($) {  
  $.widget('qurion.toolbarwidget', {

	    /**
	     * Default options
	     */
	    options: {
	    	data: [],
	    	maxItems: 7,
	    },

	    /**
	     * Widget create callback
	     */
	    _create: function () {
	    	var element = this.element;
	    	this._buildMenuItem(element);
	    },
	    
	    /**
	     * Populate the tool bar
	     */
	    _buildMenuItem: function(menuElement) {
	    	// Tracks the number of buttons we have
	    	// added to the tool bar
	    	var buttonCounter = 0;
	    	// Loop through all the menu items
	    	for(var i =0; i < this.options.data.length; i++) 
	    	{
	    		// Only display items that have display flag set to Y
	    		if(this.options.data[i].dataValues.displayFg.fieldValue === 'Y')
	    		{
	    			// Constrain the number of buttons to maxItem
		    		if(buttonCounter < this.options.maxItems)
		    		{
		    			this._buildToolBarButton(this.options.data[i]);
		    			buttonCounter++;
		    		}
		    		// Other buttons are prepended to the drop down menu
		    		else
		    		{
		    	    	var menuItem = this._setAttributes(this.options.data[i], $('<li>'));
		    	    	var menuItemLink = $('<a>').attr('href', 'module_home');
		    	    	// add the menu item link to the menu item
		    	    	menuItemLink.text(
		    	    			this.options.data[i].dataValues.displayNm.fieldValue);
		    	    	menuItemLink.click($.proxy(this._clickHandler, this));
		    	    	menuItem.append(menuItemLink);
		    	    	$('#subnav-dropdown-menu-list').prepend(menuItem);
		    		}
	    		}
		    }
	    	return menuElement;
	    },
	    
	    /**
	     * Build a toolbar button
	     */
	    _buildToolBarButton: function(menuItemData)
		{
    		// Menu item
	    	var menuItem = this._setAttributes(menuItemData, $('<li>'));
	    	// Menu item link
	    	menuItem.addClass('active');
	    	var menuItemLink = $('<a>').attr('href', 'module_home');
	    
	    	menuItemLink.bind('click', {
	    		moduleCode: menuItemData.id
	    	}, this._clickHandler);
	    	// add the menu item icon
	    	menuItemLink.append($('<i>').attr('class', menuItemData.dataValues.displayImg.fieldValue));
	    	// add the menu item text
	    	menuItemLink.append($('<span>').text(menuItemData.dataValues.displayNm.fieldValue));
	    	// add the bold caret
	    	menuItemLink.append($('<b>').attr('class', 'caret'));
	    	// add the menu item link to the menu item
	    	menuItem.append(menuItemLink);
	    	// Insert the menu item in between the home
	    	// menu item and the drop down menu item
	    	$('#subnav-dropdown-menu').before(menuItem);
		},

	    /**
	     * Utility function NOTE: May be removed
	     */
	    _setAttributes: function(menuItemData, element) {
	    	element.attr('id', menuItemData.name);
	    	if (typeof menuItemData.class !== 'undefined' && 
	    			menuItemData.class !== null) 
	    		element.attr('class', menuItemData.class);
	    	return element;
	    },

	    /**
	     * Set option call back
	     */
	    _setOption: function (key, value) { 
	    	this.options[ key ] = value;
	    },

	    /**
	     * Event handler definition
	     */
	    _clickHandler: function(event){
	    	event.preventDefault();
	    	if(window.application) {
		    	var UiConstants = window.application.uiConstants;
	    		var clickedLink = $(event.currentTarget).attr('href');
	    		var activityQuery = {
	    				activityURL: 'module_navigator',
	    				entityQuery: {
	    					module: event.data.moduleCode,
	    					module_options: '='
	    				}
	    		};
	    		window.application.fireEvent(UiConstants.activityChannel, 
	    				UiConstants.uiLoadActivityEvent, {activityQuery: activityQuery});
	    	}
	    },

	    /**
	     * Widget destroy callback
	     */
	    _destroy: function () {},
	    
	  });

})(jQuery);
    	
	
