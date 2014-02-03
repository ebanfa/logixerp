(function($) {  
  $.widget('qurion.toolbarwidget', {
		
	    options: {
	    	data: [],
	    	maxItems: 7,
	    },

	    _create: function () {
	    	var element = this.element;
	    	this._buildMenuItem(element);
	    },
	    
	    _buildMenuItem: function(menuElement) {
	    	for(var i =0; i < this.options.data.length;i++) 
	    	{
	    		var menuItemData = this.options.data[i];
	    		// Menu item
		    	var menuItem = this._setAttributes(menuItemData, $('<li>'));
		    	// Menu item link
		    	var menuItemLink = $('<a>').attr('href', menuItemData.href);
		    	menuItemLink.click($.proxy(this._clickHandler, this));
		    	// add the menu item icon
		    	menuItemLink.append($('<i>').attr('class', menuItemData.icon));
		    	// add the menu item text
		    	menuItemLink.append($('<span>').text(menuItemData.description));
		    	// add the bold caret
		    	menuItemLink.append($('<b>').attr('class', 'caret'));
		    	// add the menu item link to the menu item
		    	menuItem.append(menuItemLink);
		    	// Insert the menu item in between the home
		    	// menu item and the drop down menu item
		    	$('#subnav-dropdown-menu').before(menuItem);
		    }
	    	return menuElement;
	    },
	    
	    _setAttributes: function(menuItemData, element) {
	    	element.attr('id', menuItemData.name);
	    	if (typeof menuItemData.class !== 'undefined' && 
	    			menuItemData.class !== null) 
	    		element.attr('class', menuItemData.class);
	    	return element;
	    },
	    
	    _setOption: function (key, value) { 
	    	this.options[ key ] = value;
	    },
	    
	    _clickHandler: function(event){
	    	event.preventDefault();
	    	var application = window.application;
	    	if(application) {
	    		var clickedLink = event.currentTarget;
	    		application.fireEvent(application.uiConstants.uiFsm, 
	    				application.uiConstants.loadActivityEvent, {activityURL:clickedLink});
	    	}
	    },
	    
	    _destroy: function () {},
	    
	  });

})(jQuery);
    	
	
