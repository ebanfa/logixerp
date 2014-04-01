/**
 *  Nathan Claire Group.
 */
package com.qurion.businesslogic.businessobject.util;

import java.util.HashMap;
import java.util.Map;

import com.qurion.businesslogic.accounting.model.AccountingPeriod;
import com.qurion.businesslogic.accounting.model.AccountingTransaction;
import com.qurion.businesslogic.accounting.model.AccountingTransactionDetail;
import com.qurion.businesslogic.accounting.model.AccountingTransactionType;
import com.qurion.businesslogic.accounting.model.Budget;
import com.qurion.businesslogic.accounting.model.BudgetItem;
import com.qurion.businesslogic.accounting.model.BudgetItemType;
import com.qurion.businesslogic.accounting.model.BudgetReview;
import com.qurion.businesslogic.accounting.model.BudgetReviewResultType;
import com.qurion.businesslogic.accounting.model.BudgetRevision;
import com.qurion.businesslogic.accounting.model.BudgetRevisionImpact;
import com.qurion.businesslogic.accounting.model.BudgetRole;
import com.qurion.businesslogic.accounting.model.BudgetRoleType;
import com.qurion.businesslogic.accounting.model.BudgetScenario;
import com.qurion.businesslogic.accounting.model.BudgetScenarioApplication;
import com.qurion.businesslogic.accounting.model.BudgetScenarioRule;
import com.qurion.businesslogic.accounting.model.BudgetStatus;
import com.qurion.businesslogic.accounting.model.BudgetStatusType;
import com.qurion.businesslogic.accounting.model.BudgetType;
import com.qurion.businesslogic.accounting.model.DepreciationMethod;
import com.qurion.businesslogic.accounting.model.FixedAssetDepreciationMethod;
import com.qurion.businesslogic.accounting.model.GeneralLedgerAccount;
import com.qurion.businesslogic.accounting.model.GeneralLedgerAccountType;
import com.qurion.businesslogic.accounting.model.GlBudgetXref;
import com.qurion.businesslogic.accounting.model.OrganizationGeneralLedgerAccount;
import com.qurion.businesslogic.accounting.model.OrganizationGlAccountBalance;
import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.ActivityGroup;
import com.qurion.businesslogic.application.model.ActivityGroupType;
import com.qurion.businesslogic.application.model.ActivityGrouping;
import com.qurion.businesslogic.application.model.ActivityRelationship;
import com.qurion.businesslogic.application.model.ActivityRelationshipType;
import com.qurion.businesslogic.application.model.ActivityType;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.EntityFieldType;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.model.TempRelatedActivity;
import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.model.UiComponentAttribute;
import com.qurion.businesslogic.application.model.UiComponentAttributeType;
import com.qurion.businesslogic.application.model.UiComponentType;
import com.qurion.businesslogic.application.model.Universe;
import com.qurion.businesslogic.businessdata.model.GeoBoundry;
import com.qurion.businesslogic.businessdata.model.GeoBoundryType;
import com.qurion.businesslogic.businessdata.model.PeriodType;
import com.qurion.businesslogic.businessdata.model.StandardTimePeriod;
import com.qurion.businesslogic.businessdata.model.TermType;
import com.qurion.businesslogic.businessdata.model.Uom;
import com.qurion.businesslogic.businessdata.model.UomConversion;
import com.qurion.businesslogic.fixedasset.model.Facility;
import com.qurion.businesslogic.fixedasset.model.FacilityType;
import com.qurion.businesslogic.fixedasset.model.FixedAsset;
import com.qurion.businesslogic.fixedasset.model.FixedAssetType;
import com.qurion.businesslogic.hr.model.BenefitType;
import com.qurion.businesslogic.hr.model.Deduction;
import com.qurion.businesslogic.hr.model.DeductionType;
import com.qurion.businesslogic.hr.model.EmploymentApplication;
import com.qurion.businesslogic.hr.model.EmploymentApplicationSourceType;
import com.qurion.businesslogic.hr.model.EmploymentApplicationStatusType;
import com.qurion.businesslogic.hr.model.PartyBenefit;
import com.qurion.businesslogic.hr.model.PartyQualification;
import com.qurion.businesslogic.hr.model.PartyRate;
import com.qurion.businesslogic.hr.model.PartySkill;
import com.qurion.businesslogic.hr.model.PayGrade;
import com.qurion.businesslogic.hr.model.PayHistory;
import com.qurion.businesslogic.hr.model.PayrollReference;
import com.qurion.businesslogic.hr.model.PerformanceNote;
import com.qurion.businesslogic.hr.model.PerformanceNoteType;
import com.qurion.businesslogic.hr.model.PerformanceReview;
import com.qurion.businesslogic.hr.model.PerformanceReviewItem;
import com.qurion.businesslogic.hr.model.PerformanceReviewItemType;
import com.qurion.businesslogic.hr.model.PersonTraining;
import com.qurion.businesslogic.hr.model.Position;
import com.qurion.businesslogic.hr.model.PositionClassificationType;
import com.qurion.businesslogic.hr.model.PositionFulfillment;
import com.qurion.businesslogic.hr.model.PositionReportingStructure;
import com.qurion.businesslogic.hr.model.PositionResponsibility;
import com.qurion.businesslogic.hr.model.PositionStatusType;
import com.qurion.businesslogic.hr.model.PositionType;
import com.qurion.businesslogic.hr.model.PositionTypeClass;
import com.qurion.businesslogic.hr.model.PositionTypeRate;
import com.qurion.businesslogic.hr.model.QualificationType;
import com.qurion.businesslogic.hr.model.RateType;
import com.qurion.businesslogic.hr.model.ResponsibilityType;
import com.qurion.businesslogic.hr.model.Resume;
import com.qurion.businesslogic.hr.model.SalaryStep;
import com.qurion.businesslogic.hr.model.SkillType;
import com.qurion.businesslogic.hr.model.TerminationReason;
import com.qurion.businesslogic.hr.model.TerminationType;
import com.qurion.businesslogic.hr.model.TimeEntry;
import com.qurion.businesslogic.hr.model.TimeEntryBilling;
import com.qurion.businesslogic.hr.model.TimeSheet;
import com.qurion.businesslogic.hr.model.TimeSheetRole;
import com.qurion.businesslogic.hr.model.TimeSheetRoleType;
import com.qurion.businesslogic.hr.model.TrainingClassType;
import com.qurion.businesslogic.hr.model.UnemploymentClaim;
import com.qurion.businesslogic.hr.model.UnemploymentClaimStatusType;
import com.qurion.businesslogic.hr.model.ValidResponsibility;
import com.qurion.businesslogic.invoice.model.BillingAccount;
import com.qurion.businesslogic.invoice.model.BillingAccountRole;
import com.qurion.businesslogic.invoice.model.BillingAccountRoleType;
import com.qurion.businesslogic.invoice.model.FinancialAccount;
import com.qurion.businesslogic.invoice.model.FinancialAccountRole;
import com.qurion.businesslogic.invoice.model.FinancialAccountRoleType;
import com.qurion.businesslogic.invoice.model.FinancialAccountTransaction;
import com.qurion.businesslogic.invoice.model.FinancialAccountTransactionType;
import com.qurion.businesslogic.invoice.model.FinancialAccountType;
import com.qurion.businesslogic.invoice.model.Invoice;
import com.qurion.businesslogic.invoice.model.InvoiceItem;
import com.qurion.businesslogic.invoice.model.InvoiceItemType;
import com.qurion.businesslogic.invoice.model.InvoiceRoleType;
import com.qurion.businesslogic.invoice.model.InvoiceStatus;
import com.qurion.businesslogic.invoice.model.InvoiceStatusType;
import com.qurion.businesslogic.invoice.model.InvoiceTerm;
import com.qurion.businesslogic.invoice.model.Payment;
import com.qurion.businesslogic.invoice.model.PaymentApplication;
import com.qurion.businesslogic.invoice.model.PaymentBudgetApplication;
import com.qurion.businesslogic.invoice.model.PaymentMethodType;
import com.qurion.businesslogic.invoice.model.PaymentType;
import com.qurion.businesslogic.ordering.model.Addendum;
import com.qurion.businesslogic.ordering.model.Agreement;
import com.qurion.businesslogic.ordering.model.AgreementGeoboundryApplicability;
import com.qurion.businesslogic.ordering.model.AgreementItem;
import com.qurion.businesslogic.ordering.model.AgreementItemType;
import com.qurion.businesslogic.ordering.model.AgreementOrganizationApplicability;
import com.qurion.businesslogic.ordering.model.AgreementProductApplicability;
import com.qurion.businesslogic.ordering.model.AgreementRole;
import com.qurion.businesslogic.ordering.model.AgreementRoleType;
import com.qurion.businesslogic.ordering.model.AgreementTerm;
import com.qurion.businesslogic.ordering.model.AgreementType;
import com.qurion.businesslogic.ordering.model.DesiredFeature;
import com.qurion.businesslogic.ordering.model.OrderAdjustment;
import com.qurion.businesslogic.ordering.model.OrderAdjustmentType;
import com.qurion.businesslogic.ordering.model.OrderContactMechanism;
import com.qurion.businesslogic.ordering.model.OrderItem;
import com.qurion.businesslogic.ordering.model.OrderItemAssociation;
import com.qurion.businesslogic.ordering.model.OrderItemBilling;
import com.qurion.businesslogic.ordering.model.OrderItemContactMechanism;
import com.qurion.businesslogic.ordering.model.OrderItemRole;
import com.qurion.businesslogic.ordering.model.OrderItemRoleType;
import com.qurion.businesslogic.ordering.model.OrderItemType;
import com.qurion.businesslogic.ordering.model.OrderRequirementCommitment;
import com.qurion.businesslogic.ordering.model.OrderRole;
import com.qurion.businesslogic.ordering.model.OrderRoleType;
import com.qurion.businesslogic.ordering.model.OrderShipment;
import com.qurion.businesslogic.ordering.model.OrderStatus;
import com.qurion.businesslogic.ordering.model.OrderStatusType;
import com.qurion.businesslogic.ordering.model.OrderTerm;
import com.qurion.businesslogic.ordering.model.OrderType;
import com.qurion.businesslogic.ordering.model.OrderValue;
import com.qurion.businesslogic.ordering.model.Orders;
import com.qurion.businesslogic.ordering.model.QuantityBreak;
import com.qurion.businesslogic.ordering.model.Quote;
import com.qurion.businesslogic.ordering.model.QuoteItem;
import com.qurion.businesslogic.ordering.model.QuoteRole;
import com.qurion.businesslogic.ordering.model.QuoteRoleType;
import com.qurion.businesslogic.ordering.model.QuoteTerm;
import com.qurion.businesslogic.ordering.model.QuoteType;
import com.qurion.businesslogic.ordering.model.RejectionReason;
import com.qurion.businesslogic.ordering.model.Request;
import com.qurion.businesslogic.ordering.model.RequestItem;
import com.qurion.businesslogic.ordering.model.RequestRole;
import com.qurion.businesslogic.ordering.model.RequestRoleType;
import com.qurion.businesslogic.ordering.model.RequestType;
import com.qurion.businesslogic.ordering.model.Requirement;
import com.qurion.businesslogic.ordering.model.RequirementBudgetApplication;
import com.qurion.businesslogic.ordering.model.RequirementRequest;
import com.qurion.businesslogic.ordering.model.RequirementRole;
import com.qurion.businesslogic.ordering.model.RequirementRoleType;
import com.qurion.businesslogic.ordering.model.RequirementStatus;
import com.qurion.businesslogic.ordering.model.RequirementStatusType;
import com.qurion.businesslogic.ordering.model.RequirementType;
import com.qurion.businesslogic.ordering.model.RespondingParty;
import com.qurion.businesslogic.ordering.model.SaleType;
import com.qurion.businesslogic.ordering.model.SalesTaxLookup;
import com.qurion.businesslogic.party.model.CaseRole;
import com.qurion.businesslogic.party.model.CaseRoleType;
import com.qurion.businesslogic.party.model.CaseStatus;
import com.qurion.businesslogic.party.model.Cases;
import com.qurion.businesslogic.party.model.CommunicationEvent;
import com.qurion.businesslogic.party.model.CommunicationEventPurpose;
import com.qurion.businesslogic.party.model.CommunicationEventPurposeType;
import com.qurion.businesslogic.party.model.CommunicationEventRole;
import com.qurion.businesslogic.party.model.CommunicationEventRoleType;
import com.qurion.businesslogic.party.model.CommunicationEventStatus;
import com.qurion.businesslogic.party.model.ContactMechanism;
import com.qurion.businesslogic.party.model.ContactMechanismPurposeType;
import com.qurion.businesslogic.party.model.ContactMechanismType;
import com.qurion.businesslogic.party.model.ElectronicAddress;
import com.qurion.businesslogic.party.model.MarketInterest;
import com.qurion.businesslogic.party.model.Organization;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.party.model.PartyClassification;
import com.qurion.businesslogic.party.model.PartyContactMechanism;
import com.qurion.businesslogic.party.model.PartyContactMechanismPurpose;
import com.qurion.businesslogic.party.model.PartyPostalAddress;
import com.qurion.businesslogic.party.model.PartyRelationship;
import com.qurion.businesslogic.party.model.PartyRelationshipStatus;
import com.qurion.businesslogic.party.model.PartyRelationshipType;
import com.qurion.businesslogic.party.model.PartyRole;
import com.qurion.businesslogic.party.model.PartyRoleType;
import com.qurion.businesslogic.party.model.PartyType;
import com.qurion.businesslogic.party.model.Person;
import com.qurion.businesslogic.party.model.PostalAddress;
import com.qurion.businesslogic.party.model.PostalAddressBoundry;
import com.qurion.businesslogic.party.model.TelecommunicationsNumber;
import com.qurion.businesslogic.product.model.Container;
import com.qurion.businesslogic.product.model.ContainerType;
import com.qurion.businesslogic.product.model.InventoryItem;
import com.qurion.businesslogic.product.model.InventoryItemStatus;
import com.qurion.businesslogic.product.model.InventoryItemType;
import com.qurion.businesslogic.product.model.InventoryItemVariance;
import com.qurion.businesslogic.product.model.Lot;
import com.qurion.businesslogic.product.model.PreferenceType;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.product.model.ProductCategory;
import com.qurion.businesslogic.product.model.ProductCategoryClassification;
import com.qurion.businesslogic.product.model.ProductCategoryRollup;
import com.qurion.businesslogic.product.model.ProductCategoryType;
import com.qurion.businesslogic.product.model.ProductComplement;
import com.qurion.businesslogic.product.model.ProductComponent;
import com.qurion.businesslogic.product.model.ProductCostComponent;
import com.qurion.businesslogic.product.model.ProductCostComponentType;
import com.qurion.businesslogic.product.model.ProductFeature;
import com.qurion.businesslogic.product.model.ProductFeatureApplicability;
import com.qurion.businesslogic.product.model.ProductFeatureApplicabilityType;
import com.qurion.businesslogic.product.model.ProductFeatureCategory;
import com.qurion.businesslogic.product.model.ProductFeatureType;
import com.qurion.businesslogic.product.model.ProductIncompatibility;
import com.qurion.businesslogic.product.model.ProductObsolescence;
import com.qurion.businesslogic.product.model.ProductPriceComponent;
import com.qurion.businesslogic.product.model.ProductPriceComponentType;
import com.qurion.businesslogic.product.model.ProductSubstitute;
import com.qurion.businesslogic.product.model.ProductType;
import com.qurion.businesslogic.product.model.RatingType;
import com.qurion.businesslogic.product.model.Reason;
import com.qurion.businesslogic.product.model.SupplierProduct;
import com.qurion.businesslogic.shipment.model.Document;
import com.qurion.businesslogic.shipment.model.DocumentType;
import com.qurion.businesslogic.shipment.model.ItemIssuance;
import com.qurion.businesslogic.shipment.model.ItemIssuanceRole;
import com.qurion.businesslogic.shipment.model.ItemIssuanceRoleType;
import com.qurion.businesslogic.shipment.model.PackagingContent;
import com.qurion.businesslogic.shipment.model.PickList;
import com.qurion.businesslogic.shipment.model.PickListItem;
import com.qurion.businesslogic.shipment.model.Shipment;
import com.qurion.businesslogic.shipment.model.ShipmentItem;
import com.qurion.businesslogic.shipment.model.ShipmentItemBilling;
import com.qurion.businesslogic.shipment.model.ShipmentItemFeature;
import com.qurion.businesslogic.shipment.model.ShipmentMethodType;
import com.qurion.businesslogic.shipment.model.ShipmentPackage;
import com.qurion.businesslogic.shipment.model.ShipmentReceipt;
import com.qurion.businesslogic.shipment.model.ShipmentReceiptRoleType;
import com.qurion.businesslogic.shipment.model.ShipmentRouteSegment;
import com.qurion.businesslogic.shipment.model.ShipmentStatus;
import com.qurion.businesslogic.shipment.model.ShipmentStatusType;
import com.qurion.businesslogic.shipment.model.ShipmentType;
import com.qurion.businesslogic.shipment.model.ShippmentReceiptRole;
import com.qurion.businesslogic.user.model.SystemUser;
import com.qurion.businesslogic.user.model.UserActivity;
import com.qurion.businesslogic.workeffort.model.Deliverable;
import com.qurion.businesslogic.workeffort.model.DeliverableType;
import com.qurion.businesslogic.workeffort.model.WorkEffort;
import com.qurion.businesslogic.workeffort.model.WorkEffortAssetAssignment;
import com.qurion.businesslogic.workeffort.model.WorkEffortAssetAssignmentStatusType;
import com.qurion.businesslogic.workeffort.model.WorkEffortAssignmentRate;
import com.qurion.businesslogic.workeffort.model.WorkEffortAssociation;
import com.qurion.businesslogic.workeffort.model.WorkEffortAssociationType;
import com.qurion.businesslogic.workeffort.model.WorkEffortBilling;
import com.qurion.businesslogic.workeffort.model.WorkEffortDeliverableProduced;
import com.qurion.businesslogic.workeffort.model.WorkEffortFixedAssetStandard;
import com.qurion.businesslogic.workeffort.model.WorkEffortInventoryAssignment;
import com.qurion.businesslogic.workeffort.model.WorkEffortInventoryProduced;
import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignement;
import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignment;
import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignmentStatusType;
import com.qurion.businesslogic.workeffort.model.WorkEffortProductStandard;
import com.qurion.businesslogic.workeffort.model.WorkEffortPurposeType;
import com.qurion.businesslogic.workeffort.model.WorkEffortRoleType;
import com.qurion.businesslogic.workeffort.model.WorkEffortSkillStandard;
import com.qurion.businesslogic.workeffort.model.WorkEffortStatus;
import com.qurion.businesslogic.workeffort.model.WorkEffortStatusType;
import com.qurion.businesslogic.workeffort.model.WorkEffortType;
import com.qurion.businesslogic.workeffort.model.WorkEffortTypeAssociation;
import com.qurion.businesslogic.workeffort.model.WorkEffortTypeAssociationType;
import com.qurion.businesslogic.workeffort.model.WorkOrderItemFulfillment;
import com.qurion.businesslogic.workeffort.model.WorkRequirementFulfillment;


public class BusinessObjectClasses{

	@SuppressWarnings("rawtypes")
	private Map<String, Class> entityNames = new HashMap<String, Class>();
	
	
	public BusinessObjectClasses()
	{
		entityNames.put("Universe", Universe.class);
		entityNames.put("Container", Container.class);
		entityNames.put("ProductFeatureApplicability", ProductFeatureApplicability.class);
		entityNames.put("ProductObsolescence", ProductObsolescence.class);
		entityNames.put("ProductCategoryRollup", ProductCategoryRollup.class);
		entityNames.put("ProductCategoryClassification", ProductCategoryClassification.class);
		entityNames.put("ProductCostComponent", ProductCostComponent.class);
		entityNames.put("Lot", Lot.class);
		entityNames.put("InventoryItemStatus", InventoryItemStatus.class);
		entityNames.put("ProductComponent", ProductComponent.class);
		entityNames.put("Product", Product.class);
		entityNames.put("ContainerType", ContainerType.class);
		entityNames.put("ProductFeature", ProductFeature.class);
		entityNames.put("SupplierProduct", SupplierProduct.class);
		entityNames.put("ProductPriceComponentType", ProductPriceComponentType.class);
		entityNames.put("ProductComplement", ProductComplement.class);
		entityNames.put("ProductCostComponentType", ProductCostComponentType.class);
		entityNames.put("ProductCategory", ProductCategory.class);
		entityNames.put("ProductFeatureCategory", ProductFeatureCategory.class);
		entityNames.put("ProductCategoryType", ProductCategoryType.class);
		entityNames.put("PreferenceType", PreferenceType.class);
		entityNames.put("ProductFeatureApplicabilityType", ProductFeatureApplicabilityType.class);
		entityNames.put("ProductPriceComponent", ProductPriceComponent.class);
		entityNames.put("InventoryItem", InventoryItem.class);
		entityNames.put("ProductIncompatibility", ProductIncompatibility.class);
		entityNames.put("Reason", Reason.class);
		entityNames.put("ProductSubstitute", ProductSubstitute.class);
		entityNames.put("ProductFeatureType", ProductFeatureType.class);
		entityNames.put("ProductType", ProductType.class);
		entityNames.put("RatingType", RatingType.class);
		entityNames.put("InventoryItemType", InventoryItemType.class);
		entityNames.put("InventoryItemVariance", InventoryItemVariance.class);
		entityNames.put("FacilityType", FacilityType.class);
		entityNames.put("FixedAssetType", FixedAssetType.class);
		entityNames.put("Facility", Facility.class);
		entityNames.put("FixedAsset", FixedAsset.class);
		entityNames.put("TermType", TermType.class);
		entityNames.put("PeriodType", PeriodType.class);
		entityNames.put("Uom", Uom.class);
		entityNames.put("UomConversion", UomConversion.class);
		entityNames.put("GeoBoundry", GeoBoundry.class);
		entityNames.put("GeoBoundryType", GeoBoundryType.class);
		entityNames.put("StandardTimePeriod", StandardTimePeriod.class);
		entityNames.put("CommunicationEventPurpose", CommunicationEventPurpose.class);
		entityNames.put("CommunicationEventRoleType", CommunicationEventRoleType.class);
		entityNames.put("MarketInterest", MarketInterest.class);
		entityNames.put("Person", Person.class);
		entityNames.put("Cases", Cases.class);
		entityNames.put("ContactMechanismType", ContactMechanismType.class);
		entityNames.put("Party", Party.class);
		entityNames.put("PartyRelationshipStatus", PartyRelationshipStatus.class);
		entityNames.put("PartyContactMechanismPurpose", PartyContactMechanismPurpose.class);
		entityNames.put("ContactMechanismPurposeType", ContactMechanismPurposeType.class);
		entityNames.put("PartyType", PartyType.class);
		entityNames.put("PostalAddressBoundry", PostalAddressBoundry.class);
		entityNames.put("CaseRoleType", CaseRoleType.class);
		entityNames.put("PartyRelationshipType", PartyRelationshipType.class);
		entityNames.put("CommunicationEventPurposeType", CommunicationEventPurposeType.class);
		entityNames.put("PartyClassification", PartyClassification.class);
		entityNames.put("CommunicationEventRole", CommunicationEventRole.class);
		entityNames.put("Organization", Organization.class);
		entityNames.put("PartyRole", PartyRole.class);
		entityNames.put("TelecommunicationsNumber", TelecommunicationsNumber.class);
		entityNames.put("CommunicationEvent", CommunicationEvent.class);
		entityNames.put("PartyContactMechanism", PartyContactMechanism.class);
		entityNames.put("ContactMechanism", ContactMechanism.class);
		entityNames.put("CaseRole", CaseRole.class);
		entityNames.put("ElectronicAddress", ElectronicAddress.class);
		entityNames.put("CommunicationEventStatus", CommunicationEventStatus.class);
		entityNames.put("PartyRoleType", PartyRoleType.class);
		entityNames.put("PartyRelationship", PartyRelationship.class);
		entityNames.put("PostalAddress", PostalAddress.class);
		entityNames.put("PartyPostalAddress", PartyPostalAddress.class);
		entityNames.put("CaseStatus", CaseStatus.class);
		entityNames.put("BudgetStatus", BudgetStatus.class);
		entityNames.put("BudgetItem", BudgetItem.class);
		entityNames.put("BudgetRole", BudgetRole.class);
		entityNames.put("BudgetStatusType", BudgetStatusType.class);
		entityNames.put("BudgetScenarioRule", BudgetScenarioRule.class);
		entityNames.put("BudgetRevision", BudgetRevision.class);
		entityNames.put("BudgetItemType", BudgetItemType.class);
		entityNames.put("BudgetType", BudgetType.class);
		entityNames.put("Budget", Budget.class);
		entityNames.put("BudgetScenario", BudgetScenario.class);
		entityNames.put("BudgetRevisionImpact", BudgetRevisionImpact.class);
		entityNames.put("OrganizationGlAccountBalance", OrganizationGlAccountBalance.class);
		entityNames.put("BudgetReviewResultType", BudgetReviewResultType.class);
		entityNames.put("BudgetScenarioApplication", BudgetScenarioApplication.class);
		entityNames.put("AccountingTransactionType", AccountingTransactionType.class);
		entityNames.put("OrganizationGeneralLedgerAccount", OrganizationGeneralLedgerAccount.class);
		entityNames.put("DepreciationMethod", DepreciationMethod.class);
		entityNames.put("BudgetReview", BudgetReview.class);
		entityNames.put("FixedAssetDepreciationMethod", FixedAssetDepreciationMethod.class);
		entityNames.put("GeneralLedgerAccount", GeneralLedgerAccount.class);
		entityNames.put("GlBudgetXref", GlBudgetXref.class);
		entityNames.put("BudgetRoleType", BudgetRoleType.class);
		entityNames.put("GeneralLedgerAccountType", GeneralLedgerAccountType.class);
		entityNames.put("AccountingPeriod", AccountingPeriod.class);
		entityNames.put("AccountingTransaction", AccountingTransaction.class);
		entityNames.put("AccountingTransactionDetail", AccountingTransactionDetail.class);
		entityNames.put("DeliverableType", DeliverableType.class);
		entityNames.put("Deliverable", Deliverable.class);
		entityNames.put("WorkEffortSkillStandard", WorkEffortSkillStandard.class);
		entityNames.put("WorkEffortPartyAssignement", WorkEffortPartyAssignement.class);
		entityNames.put("WorkEffortAssociationType", WorkEffortAssociationType.class);
		entityNames.put("WorkEffortTypeAssociationType", WorkEffortTypeAssociationType.class);
		entityNames.put("WorkEffortDeliverableProduced", WorkEffortDeliverableProduced.class);
		entityNames.put("WorkEffortStatusType", WorkEffortStatusType.class);
		entityNames.put("WorkEffortAssignmentRate", WorkEffortAssignmentRate.class);
		entityNames.put("WorkEffortType", WorkEffortType.class);
		entityNames.put("WorkEffortFixedAssetStandard", WorkEffortFixedAssetStandard.class);
		entityNames.put("WorkEffortStatus", WorkEffortStatus.class);
		entityNames.put("WorkEffortAssetAssignmentStatusType", WorkEffortAssetAssignmentStatusType.class);
		entityNames.put("WorkEffortPartyAssignmentStatusType", WorkEffortPartyAssignmentStatusType.class);
		entityNames.put("WorkRequirementFulfillment", WorkRequirementFulfillment.class);
		entityNames.put("WorkEffortTypeAssociation", WorkEffortTypeAssociation.class);
		entityNames.put("WorkEffortInventoryProduced", WorkEffortInventoryProduced.class);
		entityNames.put("WorkEffortPurposeType", WorkEffortPurposeType.class);
		entityNames.put("WorkEffortPartyAssignment", WorkEffortPartyAssignment.class);
		entityNames.put("WorkEffortAssociation", WorkEffortAssociation.class);
		entityNames.put("WorkEffortInventoryAssignment", WorkEffortInventoryAssignment.class);
		entityNames.put("WorkEffortRoleType", WorkEffortRoleType.class);
		entityNames.put("WorkEffort", WorkEffort.class);
		entityNames.put("WorkEffortAssetAssignment", WorkEffortAssetAssignment.class);
		entityNames.put("WorkOrderItemFulfillment", WorkOrderItemFulfillment.class);
		entityNames.put("WorkEffortProductStandard", WorkEffortProductStandard.class);
		entityNames.put("WorkEffortBilling", WorkEffortBilling.class);
		entityNames.put("RequirementRoleType", RequirementRoleType.class);
		entityNames.put("RequirementRole", RequirementRole.class);
		entityNames.put("QuoteItem", QuoteItem.class);
		entityNames.put("QuoteRoleType", QuoteRoleType.class);
		entityNames.put("QuoteTerm", QuoteTerm.class);
		entityNames.put("RequestRoleType", RequestRoleType.class);
		entityNames.put("OrderItemAssociation", OrderItemAssociation.class);
		entityNames.put("RequirementType", RequirementType.class);
		entityNames.put("OrderType", OrderType.class);
		entityNames.put("RejectionReason", RejectionReason.class);
		entityNames.put("QuoteRole", QuoteRole.class);
		entityNames.put("AgreementTerm", AgreementTerm.class);
		entityNames.put("OrderRequirementCommitment", OrderRequirementCommitment.class);
		entityNames.put("RequirementBudgetApplication", RequirementBudgetApplication.class);
		entityNames.put("SalesTaxLookup", SalesTaxLookup.class);
		entityNames.put("AgreementOrganizationApplicability", AgreementOrganizationApplicability.class);
		entityNames.put("RequirementStatus", RequirementStatus.class);
		entityNames.put("OrderItemContactMechanism", OrderItemContactMechanism.class);
		entityNames.put("RespondingParty", RespondingParty.class);
		entityNames.put("OrderValue", OrderValue.class);
		entityNames.put("Requirement", Requirement.class);
		entityNames.put("OrderTerm", OrderTerm.class);
		entityNames.put("QuoteType", QuoteType.class);
		entityNames.put("OrderItemRoleType", OrderItemRoleType.class);
		entityNames.put("RequestItem", RequestItem.class);
		entityNames.put("AgreementProductApplicability", AgreementProductApplicability.class);
		entityNames.put("AgreementType", AgreementType.class);
		entityNames.put("OrderRole", OrderRole.class);
		entityNames.put("RequestType", RequestType.class);
		entityNames.put("Orders", Orders.class);
		entityNames.put("RequirementStatusType", RequirementStatusType.class);
		entityNames.put("RequestRole", RequestRole.class);
		entityNames.put("OrderItem", OrderItem.class);
		entityNames.put("Quote", Quote.class);
		entityNames.put("OrderAdjustmentType", OrderAdjustmentType.class);
		entityNames.put("AgreementItemType", AgreementItemType.class);
		entityNames.put("RequirementRequest", RequirementRequest.class);
		entityNames.put("OrderContactMechanism", OrderContactMechanism.class);
		entityNames.put("DesiredFeature", DesiredFeature.class);
		entityNames.put("OrderAdjustment", OrderAdjustment.class);
		entityNames.put("OrderRoleType", OrderRoleType.class);
		entityNames.put("Request", Request.class);
		entityNames.put("OrderItemBilling", OrderItemBilling.class);
		entityNames.put("OrderShipment", OrderShipment.class);
		entityNames.put("Addendum", Addendum.class);
		entityNames.put("OrderStatusType", OrderStatusType.class);
		entityNames.put("AgreementRole", AgreementRole.class);
		entityNames.put("SaleType", SaleType.class);
		entityNames.put("OrderStatus", OrderStatus.class);
		entityNames.put("OrderItemRole", OrderItemRole.class);
		entityNames.put("AgreementItem", AgreementItem.class);
		entityNames.put("AgreementRoleType", AgreementRoleType.class);
		entityNames.put("AgreementGeoboundryApplicability", AgreementGeoboundryApplicability.class);
		entityNames.put("QuantityBreak", QuantityBreak.class);
		entityNames.put("OrderItemType", OrderItemType.class);
		entityNames.put("Agreement", Agreement.class);
		entityNames.put("EntityFieldType", EntityFieldType.class);
		entityNames.put("UiComponentAttributeType", UiComponentAttributeType.class);
		entityNames.put("EntityField", EntityField.class);
		entityNames.put("ActivityGroupType", ActivityGroupType.class);
		entityNames.put("UiComponentType", UiComponentType.class);
		entityNames.put("ActivityRelationshipType", ActivityRelationshipType.class);
		entityNames.put("ActivityGrouping", ActivityGrouping.class);
		entityNames.put("Activity", Activity.class);
		entityNames.put("EntityData", EntityData.class);
		entityNames.put("TempRelatedActivity", TempRelatedActivity.class);
		entityNames.put("UiComponentAttribute", UiComponentAttribute.class);
		entityNames.put("ActivityRelationship", ActivityRelationship.class);
		entityNames.put("Module", Module.class);
		entityNames.put("UiComponent", UiComponent.class);
		entityNames.put("ActivityGroup", ActivityGroup.class);
		entityNames.put("ActivityType", ActivityType.class);
		entityNames.put("InvoiceStatus", InvoiceStatus.class);
		entityNames.put("FinancialAccountTransactionType", FinancialAccountTransactionType.class);
		entityNames.put("BillingAccountRoleType", BillingAccountRoleType.class);
		entityNames.put("Payment", Payment.class);
		entityNames.put("Invoice", Invoice.class);
		entityNames.put("InvoiceStatusType", InvoiceStatusType.class);
		entityNames.put("InvoiceRoleType", InvoiceRoleType.class);
		entityNames.put("PaymentBudgetApplication", PaymentBudgetApplication.class);
		entityNames.put("FinancialAccountRole", FinancialAccountRole.class);
		entityNames.put("PaymentMethodType", PaymentMethodType.class);
		entityNames.put("FinancialAccount", FinancialAccount.class);
		entityNames.put("BillingAccountRole", BillingAccountRole.class);
		entityNames.put("InvoiceItem", InvoiceItem.class);
		entityNames.put("InvoiceItemType", InvoiceItemType.class);
		entityNames.put("BillingAccount", BillingAccount.class);
		entityNames.put("FinancialAccountType", FinancialAccountType.class);
		entityNames.put("FinancialAccountTransaction", FinancialAccountTransaction.class);
		entityNames.put("PaymentApplication", PaymentApplication.class);
		entityNames.put("PaymentType", PaymentType.class);
		entityNames.put("FinancialAccountRoleType", FinancialAccountRoleType.class);
		entityNames.put("InvoiceTerm", InvoiceTerm.class);
		entityNames.put("SalaryStep", SalaryStep.class);
		entityNames.put("TimeSheetRole", TimeSheetRole.class);
		entityNames.put("PayGrade", PayGrade.class);
		entityNames.put("QualificationType", QualificationType.class);
		entityNames.put("TrainingClassType", TrainingClassType.class);
		entityNames.put("Deduction", Deduction.class);
		entityNames.put("PartySkill", PartySkill.class);
		entityNames.put("TimeEntry", TimeEntry.class);
		entityNames.put("PerformanceReviewItem", PerformanceReviewItem.class);
		entityNames.put("TimeSheetRoleType", TimeSheetRoleType.class);
		entityNames.put("PositionTypeRate", PositionTypeRate.class);
		entityNames.put("PersonTraining", PersonTraining.class);
		entityNames.put("EmploymentApplication", EmploymentApplication.class);
		entityNames.put("EmploymentApplicationStatusType", EmploymentApplicationStatusType.class);
		entityNames.put("TimeSheet", TimeSheet.class);
		entityNames.put("PositionClassificationType", PositionClassificationType.class);
		entityNames.put("PositionTypeClass", PositionTypeClass.class);
		entityNames.put("UnemploymentClaimStatusType", UnemploymentClaimStatusType.class);
		entityNames.put("UnemploymentClaim", UnemploymentClaim.class);
		entityNames.put("PartyRate", PartyRate.class);
		entityNames.put("PositionReportingStructure", PositionReportingStructure.class);
		entityNames.put("PerformanceNote", PerformanceNote.class);
		entityNames.put("DeductionType", DeductionType.class);
		entityNames.put("PartyBenefit", PartyBenefit.class);
		entityNames.put("PositionType", PositionType.class);
		entityNames.put("RateType", RateType.class);
		entityNames.put("SkillType", SkillType.class);
		entityNames.put("PayrollReference", PayrollReference.class);
		entityNames.put("PerformanceReview", PerformanceReview.class);
		entityNames.put("ResponsibilityType", ResponsibilityType.class);
		entityNames.put("TerminationType", TerminationType.class);
		entityNames.put("Resume", Resume.class);
		entityNames.put("TimeEntryBilling", TimeEntryBilling.class);
		entityNames.put("PerformanceReviewItemType", PerformanceReviewItemType.class);
		entityNames.put("PartyQualification", PartyQualification.class);
		entityNames.put("EmploymentApplicationSourceType", EmploymentApplicationSourceType.class);
		entityNames.put("PositionFulfillment", PositionFulfillment.class);
		entityNames.put("ValidResponsibility", ValidResponsibility.class);
		entityNames.put("TerminationReason", TerminationReason.class);
		entityNames.put("PerformanceNoteType", PerformanceNoteType.class);
		entityNames.put("PositionStatusType", PositionStatusType.class);
		entityNames.put("BenefitType", BenefitType.class);
		entityNames.put("PayHistory", PayHistory.class);
		entityNames.put("PositionResponsibility", PositionResponsibility.class);
		entityNames.put("Position", Position.class);
		entityNames.put("ShipmentItem", ShipmentItem.class);
		entityNames.put("ItemIssuance", ItemIssuance.class);
		entityNames.put("ShipmentStatus", ShipmentStatus.class);
		entityNames.put("PickListItem", PickListItem.class);
		entityNames.put("ShipmentMethodType", ShipmentMethodType.class);
		entityNames.put("ShipmentRouteSegment", ShipmentRouteSegment.class);
		entityNames.put("ShipmentItemBilling", ShipmentItemBilling.class);
		entityNames.put("PickList", PickList.class);
		entityNames.put("ItemIssuanceRole", ItemIssuanceRole.class);
		entityNames.put("Document", Document.class);
		entityNames.put("ShippmentReceiptRole", ShippmentReceiptRole.class);
		entityNames.put("Shipment", Shipment.class);
		entityNames.put("ShipmentStatusType", ShipmentStatusType.class);
		entityNames.put("ShipmentReceipt", ShipmentReceipt.class);
		entityNames.put("PackagingContent", PackagingContent.class);
		entityNames.put("DocumentType", DocumentType.class);
		entityNames.put("ShipmentPackage", ShipmentPackage.class);
		entityNames.put("ShipmentReceiptRoleType", ShipmentReceiptRoleType.class);
		entityNames.put("ShipmentItemFeature", ShipmentItemFeature.class);
		entityNames.put("ItemIssuanceRoleType", ItemIssuanceRoleType.class);
		entityNames.put("ShipmentType", ShipmentType.class);
		entityNames.put("UserActivity", UserActivity.class);
		entityNames.put("SystemUser", SystemUser.class);
	}


	/**
	 * @return the entityNames
	 */
	public Map<String, Class> getEntityNames() {
		return entityNames;
	}


	/**
	 * @param entityNames the entityNames to set
	 */
	public void setEntityNames(Map<String, Class> entityNames) {
		this.entityNames = entityNames;
	}

}