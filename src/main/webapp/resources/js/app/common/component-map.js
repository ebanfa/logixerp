define([ 
	'jquery',
    'app/views/desktop/ui/screen-view',
    'app/views/desktop/ui/panel-view',
    'app/views/desktop/ui/topbar-view',
    'app/views/desktop/ui/toolbar-view',
    'app/views/desktop/ui/activity-view',
    'app/views/desktop/ui/menu-listbox-view',
    'app/views/desktop/ui/item-listbox-view',
    'app/views/desktop/ui/form-view' 
], function($, 
		ScreenView, 
		PanelView, 
		TopBarView, 
		ToolBarView, 
		ActivityView, 
		MenuListBoxView, 
		ItemListBoxView, 
		FormView) {
	/**
	 * A mapping of component types to 
	 * Backbone views.
	 */
	var UIComponentMap = {
			"Screen": ScreenView,
			"Panel": PanelView,
			"TopBar": TopBarView,
			"ToolBar": ToolBarView,
			"Activity": ActivityView,
			"MenuListBox": MenuListBoxView,
			"ItemListBox": ItemListBoxView,
			"FormView": FormView
	};
	
	return UIComponentMap;
});