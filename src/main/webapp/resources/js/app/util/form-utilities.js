define([
    'jquery'
], function ($) {

 /*
  * Information of an entity that was selected from the entity search page.
  */
  function SelectedRelatedEntityInfo(fieldName, entityLite)
  {
    this.fieldName = fieldName;
    this.entityLite = entityLite;
  }

 /*
  * Entity lite.
  */
  function EntityLite(id, code, name, description)
  {
    this.id = id;
    this.code = code;
    this.name = name;
    this.description = description;
  }

 /*
  * Field object's constructor function.
  */
  function Field(fieldData, fieldCSSClassName)
  {
    // List item to populate list based widgets e.g
    // drop downs but will be null for non list based 
    // widgets
    this.fieldData = fieldData;
    this.fieldCSSClassName = fieldCSSClassName;
  }
  
 /*
  * FieldBlock object's constructor function.
  */
  function FieldBlock()
  {
    this.fields = [];
    this.fieldsPerBlock = 1;
  }

 /*
  * Form object's constructor function.
  */
  function Form ()
  {
    this.mode = null;
    this.fields = [];
    this.businessObject = null;
    this.activityResponseData = null;
    this.fieldBlocks = [];
    this.relatedEntities = null;
  };

  $.fn.createEntityLite = function(id, code, name, description) {
    return new EntityLite(id, code, name, description);
  },

  $.fn.createSelectedRelatedEntityInfo = function (fieldName, entityLite) {
    return new SelectedRelatedEntityInfo(fieldName, entityLite);
  },

 /*
  * Serializes a form into an array.
  */
  $.fn.serializeObject = function() {
    var arrayData, objectData;
    arrayData = this.serializeArray();
    console.log('arrayData: \n' + JSON.stringify(arrayData, null,4));
    objectData = {};
    $.each(arrayData, function() {
      var value;

      if (this.value != null) {
        value = this.value;
      } else {
        value = '';
      }

      if (objectData[this.name] != null) {
        if (!objectData[this.name].push) {
          objectData[this.name] = [objectData[this.name]];
        }

        objectData[this.name].push(value);
      } else {
        objectData[this.name] = value;
      }
    });

    return objectData;
  };

 /*
  * Function to initialize a form's field.
  */
  $.fn.initializeField = function(form, index)
  {
      // The name and the type of the field
      var fieldCSSClassName = null;
      var requiredFg = form.fields[index].required;
      // Are we dealing with a required field
      if (requiredFg) 
      {
        fieldCSSClassName = "field_required";
      } else 
      {
        fieldCSSClassName = "field";
      }
        return new Field(form.fields[index], fieldCSSClassName);
  };

  function sortFields(first, second)
  {
    //var val = first.sequenceNo - second.sequenceNo;
    if(first.fieldSequence < second.fieldSequence)
    {
      return -1;
    }
    if(first.fieldSequence > second.fieldSequence)
    {
      return 1;
    }
    if(first.fieldSequence == second.fieldSequence)
    {
      return 0;
    }
  }
  
 /*
  * Function to populate the field blocks on a form.
  */
  $.fn.populateFieldBlocks = function(form)
  {
    var currentFieldBlock = new FieldBlock();
	console.log('processing form fileds!!!!!::' + form.fields.length);
    // Sort fields
    form.fields.sort(sortFields);
    for (var index=0; index<form.fields.length; index++) 
    {
      if(form.fields[index].fieldDataType != "ID")
      {
        // Do we have space in the current field block
        if (currentFieldBlock.fields.length < currentFieldBlock.fieldsPerBlock) 
        {
          // Initialize the field and add to field list
          currentFieldBlock.fields.push($.fn.initializeField(form, index));
          // If this is the last field in the list the we add the field block
          // which contains only one field
          if(index == (form.fields.length -1)) 
          {
            form.fieldBlocks.push(currentFieldBlock);
          }
        }
        // We don't have enough space in the field block so we add the current
        // field block to the field block lis and create a new one to hold the current field.
        else 
        {
          form.fieldBlocks.push(currentFieldBlock);
          currentFieldBlock = new FieldBlock();
          currentFieldBlock.fields.push($.fn.initializeField(form, index));
          if(index == (form.fields.length -1)) 
          {
            form.fieldBlocks.push(currentFieldBlock);
          }
        }
      }
    }
  },

  /*
   * Helper function to create a form
   * from an array of fields. The
   * function  $.fn.formBuilder = function(activity)
   * should be refactored to use this function
   */
  $.fn.blockBuilder = function(fields)
  {
	 var form = new Form();
	 form.fields = fields;
     $.fn.populateFieldBlocks(form);
     return form;
  },

 /**
  * Function to build an activity's form.
  */
  $.fn.formBuilder = function(activityResponseData)
  {
    var form = new Form();
    // validate the activity
    if(activityResponseData)
    {
    	// Short cut to the activities attributes
    	form.activityResponseData = activityResponseData;
		form.businessObjectName = activityResponseData.businessObjectName;		
		if(activityResponseData.data)
    	{
			if(activityResponseData.data.dataValues) {
	    		// Loop through all the data fields defined in the data values object
	    		// create an array consisting of these fields and set that are the 
	    		// the array of form fields.
	    		var fields = [];
	    		var dataValuesObject = activityResponseData.data.dataValues;
	    		for(key in dataValuesObject) {
	    			if(dataValuesObject.hasOwnProperty(key)) {
	    				fields.push(dataValuesObject[key]);
	    			}
	    		}
	    		form.fields = fields;
			}
    	}
		else {
			// Loop through all the data fields defined in the data fields object
    		// create an array consisting of these fields and set that are the 
    		// the array of form fields.
    		var fields = [];
    		var dataFieldsObject = activityResponseData.dataFields;
    		for(var i = 0; i < dataFieldsObject.length; i++) {
    				fields.push(dataFieldsObject[i]);
    		}
    		form.fields = fields;
		}
    	$.fn.populateFieldBlocks(form);
    }
    return form;
  };
  
  return {
	  formSerializer: $.fn.serializeObject, 
	  blockBuilder: $.fn.blockBuilder,
	  formBuilder: $.fn.formBuilder, 
	  entityLite: $.fn.createEntityLite, 
	  createSelectedRelatedEntityInfo: $.fn.createSelectedRelatedEntityInfo
   };
});

