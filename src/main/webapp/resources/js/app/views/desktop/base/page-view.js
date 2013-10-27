/**
 * 
 * @author Edward Banfa
 */
define([
    'utilities',
    'configuration',
    'i18n!app/nls/entities',
    'app/util/formUtilities',
    'app/util/qui',
    'text!../../../../../templates/desktop/main.html',
    'text!../../../../../templates/desktop/home/navbar.html',
    'text!../../../../../templates/desktop/home/footer.html',
    'text!../../../../../templates/desktop/home/sidebar.html',
    'text!../../../../../templates/desktop/home/subnavbar.html'
], function (utilities, config, entities_strings, formUtilities, QUI,
        MainTemplate, NavBarTemplate, FooterTemplate, SideBarTemplate, SubNavBarTemplate) {

	var NavBarView = Backbone.View.extend({
		
        initialize: function (options) {
            _.bindAll(this, 'render');
            this.render();
        },
        render:function () 
        {           
            utilities.applyTemplate($('#navbar-container'), NavBarTemplate,  {});
            return this;
        },
        events:
        {
            'click .logout-link':'handleLogout'
        },
        handleLogout:function(event)
        {
            //event.preventDefault();
        }
    });
	
    /**
     * Renders the main navigation bar
     */
    var SubNavBarView = Backbone.View.extend({
        initialize: function (options) {
            _.bindAll(this, 'render');
            this.otherMenus = {};
            this.menusToDisplay = {};
            this.uiConfig = options.uiConfig;
            this.buildSubNavBar();
    		this.render();
        },

        /**
         * Event mapping
         */
        events:
        {
            // Change related entity field value element clicked
            'click  .module-link':'showModuleHome',
        },

        /**
         * Render function
         */
        render:function () 
        {           
            utilities.applyTemplate($('#subnavbar-container'), 
            		SubNavBarTemplate, {menusToDisplay: this.menusToDisplay, otherMenus: this.otherMenus});
            return this;
        },
        
        /**
         * Call back upon successfully completing search operation
         */
        buildSubNavBar: function ()
	    {
    		var otherMenus = [];
    		var menusToDisplay = [];
    		var menusToDisplayNo = 7;
    		var numberOfMenus = this.uiConfig.modules.length;
    		// loop through all the menus
    		for(var i = 0; i < numberOfMenus; i++)
    		{
    			// the current menu
    			var menuBusinessObject = this.uiConfig.modules[i];
    			// The data values of the menu
    			var dataValues = menuBusinessObject.dataValues;
				
				var menuBusinessObjectField = dataValues['displayFg'];
				// If the name of the current field 'displayFg'
				// and if the value of the field is == 'Y'
				if(menuBusinessObjectField.fieldName == 'displayFg' 
					&& menuBusinessObjectField.fieldValue == 'Y')
				{
    				var applicationMenu = QUI.ClickableItem({
    					URL: dataValues['id'].fieldValue,
    					name: dataValues['name'].fieldValue,
    					image: dataValues['displayImg'].fieldValue,
    					description: dataValues['description'].fieldValue,
    				});
    				if(applicationMenu.URL && applicationMenu.name )
    					applicationMenu.URL =  applicationMenu.name + "/" + applicationMenu.URL;
        			if(menusToDisplay.length < menusToDisplayNo)
        				menusToDisplay.push(applicationMenu);
        			else
        				otherMenus.push(applicationMenu);
				}
    		}
    		this.menusToDisplay = menusToDisplay;
    		this.otherMenus = otherMenus;
        },
        
        /**
         * Display the module home page
         */
        showModuleHome: function(event)
        {
            event.preventDefault(); 
        	var moduleId = event.currentTarget.getAttribute("href");
            var moduleHomeURL = "mod/" +  moduleId;
            utilities.navigate(moduleHomeURL);
        }
    });

    /**
     * Renders the side bar
     */
    var SideBarView = Backbone.View.extend({
    	
        initialize: function (options) {
            _.bindAll(this, 'render'); 
            this.modules = {};
            this.activities = {};
            this.uiConfig = options.uiConfig;
            this.buildSideBarView();
    		this.render();
        },

        /**
         * Render function
         */
        render:function () 
        {           
            utilities.applyTemplate($('#sidebar-container'), SideBarTemplate,  {modules: this.modules, activities: this.activities});
            return this;
        },
        
        /**
         * Call back upon successfully completing search operation
         */
        buildSideBarView: function ()
	    {
    		var modules = [];
    		var activities = [];
    		
			var numberOfModules = this.uiConfig.modules.length;
			for(var z = 0; z < numberOfModules; z++)
    		{
    			// the current module
    			var modulesBusinessObject = this.uiConfig.modules[z];
				var module = QUI.ClickableItem({
					itemClass: '',
					URL: modulesBusinessObject.dataValues['id'].fieldValue,
					name: modulesBusinessObject.dataValues['name'].fieldValue,
					image: modulesBusinessObject.dataValues['displayImg'].fieldValue,
					description: modulesBusinessObject.dataValues['displayNm'].fieldValue,
					children: []
				});

	    		var numberOfActivities = this.uiConfig.activities.length;
	    		// loop through all the activities
	    		for(var i = 0; i < numberOfActivities; i++)
	    		{

	    			console.log("numberOfActivities:" +  i);
	    			// the current activity
	    			var activitiesBusinessObject = this.uiConfig.activities[i];
	    			// The data values of the activity
	    			var dataValues = activitiesBusinessObject.dataValues;

	    			if(modulesBusinessObject.id == 
	    				dataValues['applicationModule'].fieldValue)
	    			{
						
						var activity = QUI.ClickableItem({
							URL: dataValues['id'].fieldValue,
							name: dataValues['name'].fieldValue,
							description: dataValues['displayNm'].fieldValue,
						});
						if(activity.name ){
							var activityName = activity.name.toLowerCase();
							var activityNameSubStrings = activityName.split("_");
							activityName = activityNameSubStrings[0].split(' ')[1];
							activity.URL =  "list" + "/list_" + activityName;
						}
		    			activities.push(activity);
    					module.children.push(activity);
        			}
    			}
	    		if(module.children.length > 0)
	    			modules.push(module);
    		}
    		this.activities = activities;
    		if(modules.length > 0)
    			modules[0].itemClass = "open";
    		this.modules = modules;
        }
    });

	/**
     * Renders the page footer
     */
    var FooterView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
            this.render();
        },
        render:function () 
        {           
            utilities.applyTemplate($('#footer-container'), FooterTemplate,  {});
            return this;
        }
    });

    /**
     * Renders the entire page
     */
    var PageView = Backbone.View.extend({
    	
        initialize: function (options) {
            _.bindAll(this, 'render');
            this.uIConfig = QUI.UIConfig({
                uIConfigURL: 'screen/',
                modules: [],
                activities: [],
            	userName: options.userName,
            });
        },
        
        render:function () 
        {           
        	utilities.applyTemplate($('body'), MainTemplate);
            // Fetch the modules and activities
            QUI.ajaxGET(this.uIConfig.uIConfigURL, {userName:this.uIConfig.userName},
            		this.onSuccessCallBack(this), this.onFailureCallBack(this));
            return this;
        },
        
        /**
         * Call back upon successfully completing ajax operation
         */
        onSuccessCallBack: function (self)
	    {
			return function(data, textStatus) {

				self.uIConfig.modules = data.modules;
				self.uIConfig.activities = data.personalActivities;
				self.uIConfig.navbarView = new NavBarView({el:$('#navbar-container'), uiConfig: self.uIConfig});
	            self.uIConfig.subNavBarView = new SubNavBarView({el:$('#subnavbar-container'), uiConfig: self.uIConfig});
	            self.uIConfig.sideView = new SideBarView({el:$('#sidebar-container'), uiConfig: self.uIConfig}),
	            self.uIConfig.footerView = new FooterView({el:$('#footer-container'), uiConfig: self.uIConfig});
			};
	    },
        
        /**
         * Call back when an error was encountered during the ajax operation
         */
        onFailureCallBack: function (self)
        {
        	return function(data, textStatus) {
				
			};
        }
    });
    return PageView;
});