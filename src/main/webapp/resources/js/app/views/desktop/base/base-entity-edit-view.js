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
    'app/views/desktop/activity/entity-search',
    'text!../../../../../templates/desktop/activity/edit-activity.html',
    'text!../../../../../templates/desktop/activity/list-table.html',
], function (utilities, config, entities_strings, formUtilities, QUI, EntitySearchDialogView, EditActivityTemplate, ListTableTemplate) {
	
	/**
     * This view is in charge of rendering the search
     * result. 
     */
	var ListTableView = Backbone.View.extend({
		
		initialize: function(options)
        {
			this.linkURL = options.linkURL;
			if(this.linkURL)
				this.linkURL = this.linkURL.toLowerCase();
			this.listData = options.listData;
			this.listFields = options.listFields;
			this.isPopUp = options.isPopUp;
        },
        
	    /**
	     * Renders this view.
	     */
	    render: function () 
	    { 
	    	utilities.applyTemplate($('#list-table'), ListTableTemplate, 
	    	{
				linkURL : this.linkURL,
				listData : this.listData,
				listFields : this.listFields,
				isPopUp : this.isPopUp
			});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
	    },
        reload: function(listData, listFields, linkURL){
        	this.linkURL = linkURL;
			if(this.linkURL)
				this.linkURL = this.linkURL.toLowerCase();
        	this.listData = listData;
        	this.listFields = listFields;
        	this.render();
        }
	});

    /**
     * The view for the view activity
     */
    var EditActivityView = Backbone.View.extend(
    {
        /**
         * 
         */
        initialize: function(options)
        {
			var self = this;
			this.form = {};
			this.listTableView = null;
			this.selectedLookUpItem = null,
			this.entitySearchDialogView = null;
            this.uiComponents = QUI.UIComponents();
			this.activity = QUI.EditActivity({
				id: null,
				data : {},
				name : 'ActivityEditView',
				baseURL : 'rest/',
				parentView : self,
				entityName : '',
				activityURL : self.model.activityURL,
				businessObjectData: null,
				searchURLPrefix : 'search/',
				activityEditURL : "edit/" + self.model.activityURL,
				editActivityTemplate : EditActivityTemplate
			});
        },

        /**
         * Event mapping
         */
        events:
        {
        	
            // Change related entity field value element clicked
            'click  .lookup_field':'showEntityLookupDialog',
            'focus  .search-field':'handleSearchFieldFocusEvent',
            'click  #do-related-entity-search-btn':'handleSearchButtonClicked',
            'click  .list-item':'handleSearchListItemSelected',
            'click  .search-result-btn':'handleSearchResultButtonClicked',
            
            'submit #entity-view-form':'handleEntityFormSubmitButtonClicked',
            'click  #view-done-btn':'handleDoneButtonClicked',
        },

        /**
         * 
         */
        render:function ()  
        {
        	var self = this;
            // This will load the activity from the DB.
			this.model.fetch({ 
                success: function(activity) {
                    self.renderActivity(activity);
                },
                error: function(model, response, options) {
                    console.log(response);
                }
            });

			// Instantiate the list table
			this.listTableView = new ListTableView(
			{
				listData : {},
				listFields : {},
				linkURL : '',
				isPopUp : true
			});
            return this;
        },
        renderActivity:function(activity)
        {
        	if(activity) {
        		this.activity.id = activity.attributes.businessObjectData.id;
        		this.activity.entityName = activity.attributes.businessObjectName;
        		this.activity.businessObjectData = activity.attributes.businessObjectData;
            	this.form = this.buildActivityForm(activity);
    		    utilities.applyTemplate($(this.el), EditActivityTemplate, {form:this.form, entities_strings:entities_strings}); 
                $(this.el).trigger('pagecreate');
            	//this.delegateEvents();
                $(".form_date").datetimepicker({format: "dd/mm/yyyy", autoclose:true});
                $(".form_datetime").datetimepicker({format: "dd/mm/yyyy"});
        	}
        },
        /**
         * 
         */
        buildActivityForm:function(activity)
        {
        	var formBuilder = formUtilities.formBuilder;
            var form = formBuilder(activity);
            return form;
        },
        
        /**
         * Show the related entity search dialog
         */
        showEntityLookupDialog:function(event)
        {
        	event.preventDefault();
        	var fieldName = event.currentTarget.getAttribute("href");
        	var modalEntityName = null;
        	var modalEntityFieldName = null;
        	var fields = this.form.fields;
            for (var i=0; i<fields.length;i++)
            {
                if(fields[i].fieldName == fieldName){
                	modalEntityFieldName = fields[i].fieldName;
                	modalEntityName = fields[i].relatedBusinessObjectName;
                	modalEntityActivityURL = fields[i].relatedBusinessObjectName.toLowerCase() + "/";
                }
            }
            
            this.currentModalEntity = modalEntityName;
            this.currentModalField = modalEntityFieldName;
            var self = this;
            this.entitySearchDialogView = 
                new EntitySearchDialogView({
                	searchView: self,
                	parentView: self,
                    modalEntityName: modalEntityName,
                    activityURL: 'activity/searchFields',
                    modalFieldName: modalEntityFieldName
                });
            this.entitySearchDialogView.render();
        },
        /**
		 * Search button has been clicked. This initiates the search process.
		 */
        handleSearchButtonClicked:function(event)
        {
            event.preventDefault();
            this.entitySearchDialogView.doSearch();
            
        },
        
       /**
        * Default search implementation. The search dialog view
        * (in charge of rendering the search dialog, and calling the search view) will be called
        * with this view as the search view ie this is the view that will do the actual search.
        */
        doSearch:function()
        {
            //this.entitySearchDialogView.closeSearchDialog();
            $.fn.formSerializer = formUtilities.formSerializer;
            var searchData = $('#search-form').formSerializer();
            if(!searchData)
	            searchData = {};

            if(!searchData.entityName)
	            searchData.entityName = this.activity.entityName;
            QUI.ajaxGET(this.activity.searchURLPrefix, searchData,
            		this.onSearchSuccessCallBack(this), this.onSearchFailureCallBack(this));
        },

        /**
         * Call back upon successfully completing search operation
         */
        onSearchSuccessCallBack: function (self)
	    {
        	return function(data, textStatus) {
				self.listTableView.reload(data.dataList, data.dataFields, self.activity.entityName);
			};
        },
        
        /**
         * Call back when an error was encountered during the search operation
         */
        onSearchFailureCallBack: function (self)
        {
        	return function(data, textStatus) {
				var blockBuilder = formUtilities.blockBuilder;
				var form = blockBuilder(data);
				console.log("this.entityName" + entityName);
				utilities.applyTemplate($('#entity-search-dialog-div'), EntitySearchTemplate, {
							model: {entityName: entityName},
							form: form,
							entities_strings: entities_strings
				});
				$('#entity-search-dialog').modal('show');
				$('.search_options').hide(); // do something with
			};
        },
        
        /**
         * Handles search input field focus event
         */
        handleSearchFieldFocusEvent: function(event){
        	// Hide other field options
        	$('.search_options').hide();
        	var fieldContainerElement = $(event.currentTarget).parent();
        	var fieldOptionsElement = fieldContainerElement.find('.search_options');
        	fieldOptionsElement.show();
        },
        
        /**
         * Listen for when a search result entry has been selected
         * (radio button)
         */
        handleSearchListItemSelected:function(event)
        {
            event.preventDefault();
            console.log('1');
            // Properties of the selected record
            var recordId = null;
            var recordCode = null;
            var recordName = null;

            var parentTd = $(event.currentTarget).parent();
            var parentTr = null;

            if(parentTd)
                parentTr = parentTd.parent();
            if(parentTr)
            {

                var codeFieldCell = parentTr.find('.code-field-cell :first');
                if(codeFieldCell != null)
                {
                    recordCode = codeFieldCell.text().trim();
                    recordId = codeFieldCell.attr('href');
                }

                var codeFieldName = parentTr.find('.name-field-cell :first');
                if(codeFieldName != null)
                    recordName = codeFieldName.text().trim();
                $.fn.entityLite = formUtilities.entityLite;
                var entityLite = $.fn.entityLite(recordId, recordCode, recordName, null);
                // Set up the current selected related entity (field name and entity lite)

                $.fn.createSelectedRelatedEntityInfo = formUtilities.createSelectedRelatedEntityInfo;
                this.selectedLookUpItem = 
                    $.fn.createSelectedRelatedEntityInfo(this.currentModalField, entityLite);

            }
        },
        
        /**
         * 
         */
        handleSearchResultButtonClicked:function(event)
        {
            event.preventDefault();
            var buttonClicked = $(event.currentTarget);
            if(buttonClicked.attr(this.uiComponents.cssId) == this.uiComponents.addItemButton){
            	this.addCurrentEntity();
                 $('#' + this.uiComponents.lookUpItemSearchDialog).modal('hide');
            }
            else {
                if (this.selectedLookUpItem) 
                    this.addCurrentEntity();  
            }
        },
        
        /**
         * 
         */
        addCurrentEntity:function()
        {
        	// The entity that was selected from the search list
            var entityLite = this.selectedLookUpItem.entityLite;
            // Get the relationship field that we are currently processing
            var fieldName = this.selectedLookUpItem.fieldName;
            // Build the selector for the field
            var fieldSelector = "#" + fieldName;
            // Build the new value for the field 
           var newFieldValue = "<option value=" + entityLite.id + ">" + entityLite.code + "</option>";
           var relatedEntityField = $(fieldSelector);
           // Replace the html content of the field with newFieldValue
           relatedEntityField.html(newFieldValue);
        },
        
        /**
         * Handles the submission of the form.
         */
        handleEntityFormSubmitButtonClicked: function(event)
        {
            var self = this;
            event.preventDefault();
            $.fn.formSerializer = formUtilities.formSerializer;
            // The business object before its data values have been updated
            var businessObjectData = this.activity.businessObjectData;
            // This object will hold the data of the updated business object 
            // Should use a clone function or something
            /*var updatedBusinessObjectData = QUI.BusinessObjectData({
				id: businessObjectData.id,
				valid: businessObjectData.valid,
				routed: businessObjectData.routed,
				processed: businessObjectData.processed,
				dataValues: businessObjectData.dataValues,
				businessObjectName: businessObjectData.businessObjectName,
				processCategoryCode: businessObjectData.processCategoryCode,
				businessObjectClassName: businessObjectData.businessObjectClassName,
			});*/
            var updatedBusinessObjectData = {};
    		for(key in businessObjectData) {
    			if(businessObjectData.hasOwnProperty(key)) {
    				updatedBusinessObjectData[key] = businessObjectData[key];
    			}
    		}
    		
    		var dataValues = updatedBusinessObjectData.dataValues;
            var formData = $(event.currentTarget).formSerializer();
            

    		console.log('formData: \n' + JSON.stringify(formData, null,4));
    		for(key in dataValues)
    		{
    			if(dataValues.hasOwnProperty(key)) {
    				if(formData[key])
    					dataValues[key].fieldValue = formData[key];
    			}
    		}
            delete updatedBusinessObjectData.secondaryRoutes;
            delete updatedBusinessObjectData.primaryOutboundRoute;
            delete updatedBusinessObjectData.inboundBusinessObject;
            delete updatedBusinessObjectData.forwardBusinessObject;
            delete updatedBusinessObjectData.outboundBusinessObject;
            
    		console.log('businessObjectData: \n' + JSON.stringify(updatedBusinessObjectData, null,4));
            if (!this.activity.id) {
            	delete updatedBusinessObjectData.id;
            }

            
            this.model.save(updatedBusinessObjectData, { 
                'success': function ()
                {
                    self.navigateToActivityList();
                },
                error: function (model, errors) 
                {
                    var errorMessage = "";
                     _.each(errors, function (error) {
                        errorMessage += error.message + "\n";
                    }, this);
                    alert(errorMessage);
                }
            });
        },
        handleDoneButtonClicked: function(event)
        {
            event.preventDefault();
            this.navigateToActivityList();
        },
        navigateToActivityList:function()
        {
        	if(this.activity.entityName){
                var activityListURL = "list/list_" + this.activity.entityName.toLowerCase();
                utilities.navigate(activityListURL);
        	}
        },
        navigateToActivityEdit:function()
        {
        	 
        },
    });

    return EditActivityView;
});