/**
 * A module for the router of the desktop application
 */
define("router", [
    'jquery',
    'underscore',
    'app/util/qui',
    'configuration',
    'utilities',
    'app/views/desktop/login/loginView',
    'app/views/desktop/home',
    'app/views/desktop/home/module-home',
    'app/models/activity/activity',
    'app/collections/activity/activity',
    'app/views/desktop/base/base-entity-edit-view',
    'app/views/desktop/base/base-entity-view-view',
    'app/views/desktop/base/base-entity-list-view',
    'app/views/desktop/activity/transaction/list-activity',
    'app/views/desktop/base/page-view'
],function ($,
            _,
            QUI,
            config,
            utilities,
            LoginView,
            HomeView,
            ModuleHomeView,
            ActivityModel,
            ActivityCollection,
            EditActivityView,
            ViewActivityView,
            ListActivityView,
            TransactionListView,
            PageView) {

    $(document).ready(new function() 
    {
    });

    /**
     * The Router class contains all the routes within the application -
     * i.e. URLs and the actions that will be taken as a result.
     *
     * @type {Router}
     */

    var Router = Backbone.Router.extend({
        routes : {
            "":"login",
            "login":"login",
            "home":"home",
            "mod/:moduleName/:moduleId":"showModuleHome",
            "list/:activityURL":"showActivityList",
            "view/:activityURL/:entityId":"showActivityView",
            "edit/:activityURL/":"showActivityEdit",
            "edit/:activityURL/:entityId":"showActivityEdit",
            "loadScreen/:userName":"loadScreenUserScreen"
        },
        home : function () {
            utilities.viewManager.showView(new HomeView({el:$("#content-container")}));
        },
        login:function()
        {
            var loginView = new LoginView({el:$("#page-container")});
            utilities.viewManager.showView(loginView);
        },
        showActivityList:function(activityURL)
        {
            var model = new ActivityCollection({activityURL:activityURL});
            var activityListView = new ListActivityView({model:model, el:$("#content-container")});
        },
        showActivityView:function(activityURL, entityId)
        {
            var model = new ActivityModel({
            	entityId:entityId,
            	pageView: pageView,
            	activityURL:activityURL
            });
            var viewActivityView = new ViewActivityView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(viewActivityView);
        },
        showActivityEdit:function(activityURL, entityId)
        {
            var model = new ActivityModel({
            	entityId:entityId,
            	pageView: pageView,
            	activityURL:activityURL
            });
            var activityEditView = new EditActivityView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(activityEditView);
        },
        showActivityCreate:function(activityURL, entityId)
        {
            var model = new ActivityModel({
            	entityId:entityId,
            	pageView: pageView,
            	activityURL:activityURL
            });
            var activityEditView = new EditActivityView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(activityEditView);
        },
        showModuleHome:function(moduleName, moduleId)
        {
        	var moduleHomeView = new ModuleHomeView({
				moduleName: moduleName, 
				moduleId:moduleId, el:$("#content-container")
			});
            utilities.viewManager.showView(moduleHomeView);
        },
        loadScreenUserScreen: function(userName)
        {
        	var pageView = new PageView({userName: userName, el:$("#body")});
        	pageView.render();
        }
    });
    // Create a router instance
    var router = new Router();
    //Begin routing
    Backbone.history.start();
    return router;
});