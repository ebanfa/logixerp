define([
    'jquery',
    'configuration',
    'underscore', 
    'text!../../../../../templates/desktop/ui/login-form-panel.html',
	'text!../../../../../templates/desktop/ui/heading-one.html',
	'text!../../../../../templates/desktop/ui/form.html',
	'text!../../../../../templates/desktop/ui/paragraph.html',
	'text!../../../../../templates/desktop/ui/input-text.html',
	'text!../../../../../templates/desktop/ui/button.html',
	'text!../../../../../templates/desktop/ui/submit-button.html'
], function ($, config, _, 
		LoginFormPanelTemplate, 
		HeaderOneTemplate, 
		FormTemplate, 
		ParagraphTemplate,
		InputTextTemplate,
		ButtonTemplate,
		SubmitButtonTemplate) 
{
	
	var ComponentTemplateMap = {
	    	'panel': LoginFormPanelTemplate,
	    	'heading-one': HeaderOneTemplate,
	    	'form': FormTemplate,
	    	'paragraph': ParagraphTemplate,
	    	'text-field': InputTextTemplate,
	    	'password-field': InputTextTemplate,
	    	'button': ButtonTemplate,
	    	'submit-button': SubmitButtonTemplate,
	    };

    return ComponentTemplateMap;
});
