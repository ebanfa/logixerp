/* -------------------------------------------------------------------- MODULES -------------------------------------------------------------------------------------------- */

INSERT INTO MODULE values (1, 'APPLICATION', 'Application', 'Core application components', 1, 'Base', 'icon-bar-chart', 'N','2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (2, 'BUSINESSDATA', 'Business Data', 'Business data', 2, 'Business Data', 'icon-bar-chart', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (3, 'PARTY', 'People and Organizations', 'People and organizations', 3, 'People and Organizations', 'icon-user', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (4, 'PRODUCT', 'Product', 'Product', 4, 'Products', 'icon-bar-chart', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (5, 'ORDERING', 'Ordering', 'Ordering', 5, 'Orders', 'icon-bar-chart', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (6, 'INVOICE', 'Invoice', 'Invoice', 6, 'Invoices', 'icon-money', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (7, 'FIXEDASSET', 'Fixed Assets', 'Fixed assets', 7, 'Fixed Assets', 'icon-bar-chart', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (8, 'WORKEFFORT', 'Work Effort', 'Work Effort', 8, 'Work effort', 'icon-bar-chart', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (9, 'SHIPMENT', 'Shipment', 'Shipment', 9, 'Shipments', 'icon-folder-close-alt', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (10, 'ACCOUNTING', 'Accounting', 'Accounting', 10, 'Accounting', 'icon-bar-chart', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO MODULE values (11, 'HR', 'Human Resource', 'Human Resource', 11, 'HR', 'icon-bar-chart', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Activity Group Types -------------------------------------------------------------------------------------------- */

INSERT INTO ACTIVITY_GROUP_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ENTITY_GROUP', 'Entity Group', 'Entity Group', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ACTIVITY_GROUP_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PAGE_FLOW', 'Page Flow', 'Page flow (wizard)', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Activity Types -------------------------------------------------------------------------------------------- */

INSERT INTO ACTIVITY_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('LIST_ACTIVITY', 'List Activity', 'List Activity', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ACTIVITY_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EDIT_ACTIVITY', 'Edit Activity', 'Edit Activity', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ACTIVITY_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('VIEW_ACTIVITY', 'View Activity', 'View Activity', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Entity Field Types -------------------------------------------------------------------------------------------- */

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ID', 'Id', 'Id', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CODE', 'Code', 'Code', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NAME', 'Name', 'Name', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TEXT', 'Text', 'Text', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('LARGE_TEXT', 'Large Text', 'Large Text', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NUMBER', 'Number', 'Number', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DECIMAL', 'Decimal', 'Decimal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('STATUS', 'Status', 'Status', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('BOOLEAN', 'Boolean', 'Boolean', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CURRENCY', 'Currency', 'Currency', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CHAR', 'Char', 'Char', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATE', 'Date', 'Date', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATETIME', 'Date Time', 'Date Time', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RELATIONSHIP', 'Relationship', 'Relationship', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- UI Component Type -------------------------------------------------------------------------------------------- */

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('screen', 'screen', 'Screen', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('panel', 'panel', 'Panel', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('form', 'form', 'Form', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('text-field', 'text-field', 'Text Field', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('password-field', 'password-field', 'Password Field', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('search-field', 'search-field', 'Search Field', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('submit-button', 'submit-button', 'Submit Button', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('button', 'button', 'Button', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('paragraph', 'paragraph', 'Paragraph', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('heading-one', 'heading-one', 'Heading One', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('toolbar', 'toolbar', 'Tool Bar', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('activity-panel', 'activity-panel', 'Activity Panel', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('sidebar', 'sidebar', 'Side Bar', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('activity', 'activity', 'Activity', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('span', 'span', 'Span', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('bold-span', 'bold-span', 'Bold Span', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('list', 'list', 'List', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('list-item', 'list-item', 'List Item', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('link', 'link', 'Link', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('image', 'image', 'Image', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO UI_COMPONENT_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('event-handler', 'event-handler', 'Event Handler', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


/* -------------------------------------------------------------------- UI Component Attribute Type -------------------------------------------------------------------------------------------- */

INSERT INTO UI_COMPONENT_ATTRIBUTE_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DEFAULT', 'Default', 'Default', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
