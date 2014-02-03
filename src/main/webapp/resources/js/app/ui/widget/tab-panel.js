(function($) {  
  $.widget('qurion.tabs', {
		
	    options: {
	    	data: [],
	    	maxItems: 7,
	    },

	    _create: function () {
        	console.log('Plugin _create');
	    	$('.tab-section').hide();
	        $('#tabs a').bind('click', function(e){
	        	console.log('Plugin clicked');
	            $('#tabs a.current').removeClass('current');
	            $('.tab-section:visible').hide();
	            $(this.hash).show();
	            $(this).addClass('current');
	            e.preventDefault();
	        }).filter(':first').click();
	    },
	    
	    _buildMenuItem: function(menuElement) {
	    	
	    },
	    
	    _setAttributes: function(menuItemData, element) {
	    	
	    },
	    
	    _setOption: function (key, value) { 
	    	this.options[ key ] = value;
	    },
	    
	    _clickHandler: function(event){
	    	
	    },
	    
	    _destroy: function () {},
	    
	  });

})(jQuery);
    	
	
