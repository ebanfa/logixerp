define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/module-home.html'
], function (utilities, config, HomeTemplate) {

    var ModuleHomeView = Backbone.View.extend({
    	initialize: function (options) {
            _.bindAll(this, 'render');
            this.moduleActivityGroups = [];
            this.moduleId = options.moduleId;
            this.moduleName = options.moduleName;
            this.menuConfigurationURL = 'search/';
            QUI.ajaxGET(this.menuConfigurationURL, {
            	entityName: 'ApplicationActivityGroup',
            	applicationModule: this.moduleId,
            	'applicationModule_options' : 'ET'
            },	this.onAjaxSuccessCallBack(this), this.onAjaxFailureCallBack(this));
        },
        
        render:function () {
            utilities.applyTemplate($(this.el),	HomeTemplate, 
            	{
            		moduleName: this.moduleName, 
            		moduleActivityGroups: this.moduleActivityGroups
            	});
            return this;
        },
        
        /**
         * Call back upon successfully completing Ajax operation
         */
        onAjaxSuccessCallBack: function (self)
	    {
        	return function(data, textStatus) 
        	{
        		var numberOfActivityGroups = data.dataList.length;
        		for(var i = 0; i < numberOfActivityGroups; i++) 
        		{
        			// The current activity group object
        			var activityGrpBusinessObject = data.dataList[i];
        			// The data values of the activity group
        			var dataValues = activityGrpBusinessObject.dataValues;
        			var groupLink = QUI.ClickableItem({
    					URL: dataValues['name'].fieldValue.toLowerCase(),
    					name: dataValues['name'].fieldValue,
    					image: dataValues['displayImg'].fieldValue,
    					description: dataValues['displayNm'].fieldValue,
    				});
        			self.moduleActivityGroups.push(groupLink);
        		}
        		self.render();
			};
	    },
        
        /**
         * Call back if Ajax call failed
         */
	    onAjaxFailureCallBack: function (self)
	    {
	    	return function(data, textStatus) {
	
			};
	    }
    });
    return ModuleHomeView;
});