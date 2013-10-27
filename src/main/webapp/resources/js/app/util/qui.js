define([ 'jquery' ], function($) {

	// Initial Setup
	// -------------
	// Save a reference to the global object (`window` in the browser, `global`
	// on the server).
	var root = this;
	// The top-level namespace.

	/*
	 * Function to execute a HTTP GET request for JSON data.
	 */
	var ajaxGET = function(url, data, onSuccessCallBack, onErrorCallBack) {
		$.ajax({
			type : 'GET',
			url : 'rest/' + url,
			contentType : 'application/json; charset=utf-8',
			data : data,
			dataType : "json",
			success : function(data) {
				onSuccessCallBack(data);
			},
			error : function(request, status, error) {
				onErrorCallBack(request, status, error);
			}
		});
	};

	var ajaxPOST = function(url, data, onSuccessCallBack, onErrorCallBack) {
		$.ajax({
			type : 'POST',
			url : 'rest/' + url,
			contentType : 'application/json; charset=utf-8',
			data : data,
			dataType : "json",
			success : function(data) {
				onSuccessCallBack(data);
			},
			error : function(request, status, error) {
				onErrorCallBack(request, status, error);
			}
		});
	};

	/**
	 * List Activity Object.
	 */
	var ListActivity = function (options){
		return { 
			data : [],
			name : options.name,
			baseURL : options.baseURL,
			parentView : options.parentView,
			entityName : options.entityName,
			activityURL : options.activityURL,
			searchURLPrefix : options.searchURLPrefix,
			activityEditURL : options.activityEditURL,
			activityListTemplate : options.activityListTemplate,
		};
	};
	
	/**
	 * View Activity Object.
	 */
	var ViewActivity = function (options){
		return { 
			data : {},
			id:options.id,
			name : options.name,
			baseURL : options.baseURL,
			parentView : options.parentView,
			entityName : options.entityName,
			activityURL : options.activityURL,
			searchURLPrefix : options.searchURLPrefix,
			activityEditURL : options.activityEditURL,
			businessObjectData: options.businessObjectData,
			activityListTemplate : options.activityListTemplate,
		};
	};
	
	/**
	 * Edit Activity Object.
	 */
	var EditActivity = function (options){
		return { 
			data : {},
			id:options.id,
			name : options.name,
			baseURL : options.baseURL,
			parentView : options.parentView,
			entityName : options.entityName,
			activityURL : options.activityURL,
			searchURLPrefix : options.searchURLPrefix,
			activityViewURL : options.activityViewURL,
			businessObjectData: options.businessObjectData,
			activityListTemplate : options.activityListTemplate,
		};
	};
	
	var BusinessObjectData = function (options){
		return { 
			id: options.id,
			valid: options.valid,
			routed: options.routed,
			processed: options.processed,
			dataValues: options.dataValues,
			businessObjectName: options.businessObjectName,
			processCategoryCode: options.processCategoryCode,
			businessObjectClassName: options.businessObjectClassName,
		};
	};
	
	/**
	 * UI Component Object. This object contains meta
	 * information on HTML UI components.
	 */
	var UIComponents = function (){
		return { 
			cssId : 'id',
			addItemButton : 'add-item-btn',
			addNextItemButton : 'add-next-item-btn',
			lookUpItemSearchDialog:'entity-search-dialog',
		};
	};
	
	var ClickableItem = function(options) {
		return {
			URL: options.URL,
			name: options.name,
			image: options.image,
			children: options.children,
			itemClass: options.itemClass,
			description: options.description,
		};
	};
	
	var UIConfig = function(options) {
		return {
			modules: options.modules,
        	userName: options.userName,
			activities: options.activities,
            uIConfigURL: options.uIConfigURL,
            navbarView : options.navbarView,
            footerView : options.footerView,
            sideView:  options.sideView,
            subNavBarView : options.subNavBarView,
		};
	};
	
	var QUI = root.QUI = {
			ajaxGET: ajaxGET,
			UIConfig: UIConfig,
			ListActivity: ListActivity,
			ViewActivity: ViewActivity,
			EditActivity: EditActivity,
			UIComponents: UIComponents,
			ClickableItem: ClickableItem,
			BusinessObjectData: BusinessObjectData
	};
	return QUI;
});