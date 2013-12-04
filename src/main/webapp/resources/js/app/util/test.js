/**
 * A module for the router of the desktop application
 */
define([],function () {
	
	var TestScreen = {

		name:'topContainer',
		code: 'PANEL_TOPCONTAINER',
		type: 'PANEL',
		description:'',
		
		attributes: {
			name:'topContainer',
			code: 'PANEL_TOPCONTAINER',
			class:'login-form-container',
			description:'',
		},
		
		components: [{
			name:'content',
			code: 'PANEL_CONTENT',
			type: 'PANEL',
			description:'',
			
			attributes: {
				name:'content',
				code: 'PANEL_CONTENT',
				class: 'content clearfix',
				description:'',
			},
			components: [{
				name:'loginHeading',
				code: 'HEADING1_LOGINHEADING',
				type: 'HEADING1',
				description:'',
				
				attributes: {
					name:'loginHeading',
					code: 'HEADING1_LOGINHEADING',
					class:'headerzz',
					description:'',
					value:'Sign In'
				},
				components: [],
			},{
				name:'loginForm',
				code: 'FORM_LOGIN',
				type: 'FORM',
				description:'',
				
				attributes: {
					name:'loginForm',
					code: 'FORM_LOGIN',
					class:'',
					target:'./',
					method:'POST',
					description:'',
				},
				components: [{
					name:'loginFields',
					code: 'FORM_LOGINFIELDS',
					type: 'PANEL',
					description:'',
					
					attributes: {
						name:'loginFields',
						code: 'FORM_LOGINFIELDS',
						class:'login-fields',
						description:'',
					},
					components: [{
						name:'loginParagraph',
						code: 'PARAGRAPH_LOGINPARA',
						type: 'PARAGRAPH',
						description:'',
						
						attributes: {
							name:'loginParagraph',
							code: 'PARAGRAPH_LOGINPARA',
							class:'',
							value:'Sign in using your registered account.',
						},
						components: [
						],
					},{
						name:'userNameField',
						code: 'INPUT_USERNAME',
						type: 'INPUT_TEXT',
						description:'Username',
						
						attributes: {
							name:'userNameField',
							code: 'INPUT_USERNAME',
							class:'login username-field',
							value:'',
						},
						components: [
						],
					},{
						name:'userNameField',
						code: 'INPUT_USERNAME',
						type: 'INPUT_TEXT',
						description:'Username',
						
						attributes: {
							name:'userNameField',
							code: 'INPUT_USERNAME',
							class:'login username-field',
							value:'',
						},
						components: [
						],
					}],
				},{
					name:'loginActions',
					code: 'PANEL_LOGINACTIONS',
					type: 'PANEL',
					description:'',
					
					attributes: {
						name:'loginActions',
						code: 'PANEL_LOGINACTIONS',
						class:'login-actions"',
						value:'',
					},
					components: [{
						name:'loginButton',
						code: 'BITTON_LOGINBUTTON',
						type: 'SUBMIT_BUTTON',
						description:'Login',
						
						attributes: {
							name:'loginButton',
							code: 'BITTON_LOGINBUTTON',
							class:'button btn btn-warning btn-large',
							description:'Login',
						},
						components: [],
					}],
				}]
			}]
		}],
		/*components: [{
				name:'loginButton',
				code: 'BITTON_LOGINBUTTON',
				type: 'BUTTON',
				description:'',
				
				attributes: {
					name:'loginButton',
					code: 'BITTON_LOGINBUTTON',
					class:'button btn btn-warning btn-large',
					description:'',
				},
				components: {
					
				},
			}
		]*/
    };
	
    return TestScreen;
});