/**
 * Module for the Activity model
 */
define([ 
    'jquery',
    'configuration',
    'underscore',
    'backbone',
    'i18n!app/nls/messages',
], function ($, config, _, Backbone, messages) {
    /**
     * The Activity model class definition
     * Used for CRUD operations against individual Activities
     */
    var UiResponseData = Backbone.Model.extend({
        initialize: function(props){
        	
        },
        
        validate: function (attrs) {
            var errors = [];
            return errors.length > 0 ? errors : false;
        }
        ,
        // Overwrite save function
        save: function(attrs, options) {
            options || (options = {});
            options.contentType = 'application/json';
            // Get data
            options.data = JSON.stringify(attrs);
            // Filter the data to send to the server
            //delete options.data.activityName;
            //delete options.data.dontSync;
            // Proxy the call to the original save function
            Backbone.Model.prototype.save.call(this, attrs, options);
        }
    });
    // export the Activity Mode; class
    return UiResponseData;
});