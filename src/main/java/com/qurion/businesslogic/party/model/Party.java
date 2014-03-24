/**
 *  Business Logic.
 */
package com.qurion.businesslogic.party.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qurion.businesslogic.accounting.model.AccountingPeriod;
import com.qurion.businesslogic.accounting.model.AccountingTransaction;
import com.qurion.businesslogic.accounting.model.BudgetReview;
import com.qurion.businesslogic.accounting.model.BudgetRole;
import com.qurion.businesslogic.accounting.model.OrganizationGeneralLedgerAccount;
import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.hr.model.EmploymentApplication;
import com.qurion.businesslogic.hr.model.PartyQualification;
import com.qurion.businesslogic.hr.model.PartyRate;
import com.qurion.businesslogic.hr.model.PartySkill;
import com.qurion.businesslogic.hr.model.PayrollReference;
import com.qurion.businesslogic.hr.model.PersonTraining;
import com.qurion.businesslogic.hr.model.Position;
import com.qurion.businesslogic.hr.model.PositionFulfillment;
import com.qurion.businesslogic.hr.model.Resume;
import com.qurion.businesslogic.hr.model.TimeSheetRole;
import com.qurion.businesslogic.invoice.model.BillingAccountRole;
import com.qurion.businesslogic.invoice.model.FinancialAccountRole;
import com.qurion.businesslogic.invoice.model.FinancialAccountTransaction;
import com.qurion.businesslogic.invoice.model.Invoice;
import com.qurion.businesslogic.ordering.model.AgreementOrganizationApplicability;
import com.qurion.businesslogic.ordering.model.AgreementRole;
import com.qurion.businesslogic.ordering.model.OrderItemRole;
import com.qurion.businesslogic.ordering.model.OrderRole;
import com.qurion.businesslogic.ordering.model.QuoteRole;
import com.qurion.businesslogic.ordering.model.RequestRole;
import com.qurion.businesslogic.ordering.model.RequirementRole;
import com.qurion.businesslogic.ordering.model.RespondingParty;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.product.model.ProductCostComponent;
import com.qurion.businesslogic.product.model.ProductPriceComponent;
import com.qurion.businesslogic.product.model.SupplierProduct;
import com.qurion.businesslogic.shipment.model.ItemIssuanceRole;
import com.qurion.businesslogic.shipment.model.ShipmentRouteSegment;
import com.qurion.businesslogic.shipment.model.ShippmentReceiptRole;
import com.qurion.businesslogic.user.model.SystemUser;
import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignement;
import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignment;

/**
 * Party 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Party  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private PartyType partyType;
	private Set<PartyQualification> partyQualifications;
	private Set<RequirementRole> requirementRoles;
	private Set<AccountingTransaction> accountingTransactionsForToPartyId;
	private Set<AccountingPeriod> accountingPeriods;
	private Set<AccountingTransaction> accountingTransactionsForFromPartyId;
	private Set<WorkEffortPartyAssignement> workEffortPartyAssignements;
	private Set<PersonTraining> personTrainings;
	private Set<FinancialAccountTransaction> financialAccountTransactions;
	private Set<CommunicationEventRole> communicationEventRoles;
	private Set<SystemUser> systemUsers;
	private Set<Resume> resumes;
	private Set<AgreementOrganizationApplicability> agreementOrganizationApplicabilities;
	private Set<PartyClassification> partyClassifications;
	private Set<PartyRole> partyRoles;
	private Set<ShippmentReceiptRole> shippmentReceiptRoles;
	private Set<PayrollReference> payrollReferencesForEmployeeId;
	private Set<PayrollReference> payrollReferencesForOrganizationId;
	private Set<CaseRole> caseRoles;
	private Set<OrderRole> orderRoles;
	private Set<ProductPriceComponent> productPriceComponents;
	private Set<Invoice> invoicesForFromPartyId;
	private Set<WorkEffortPartyAssignment> workEffortPartyAssignments;
	private Set<Invoice> invoicesForToPartyId;
	private Set<RespondingParty> respondingParties;
	private Set<FinancialAccountRole> financialAccountRoles;
	private Set<BudgetReview> budgetReviews;
	private Set<OrderItemRole> orderItemRoles;
	private Set<PartySkill> partySkills;
	private Set<Product> products;
	private Set<BillingAccountRole> billingAccountRoles;
	private Set<Organization> organizations;
	private Set<ShipmentRouteSegment> shipmentRouteSegments;
	private Set<RequestRole> requestRoles;
	private Set<BudgetRole> budgetRoles;
	private Set<SupplierProduct> supplierProducts;
	private Set<Person> persons;
	private Set<QuoteRole> quoteRoles;
	private Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccountsForRefPartyId;
	private Set<TimeSheetRole> timeSheetRoles;
	private Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccountsForOrganizationId;
	private Set<ItemIssuanceRole> itemIssuanceRoles;
	private Set<Position> positions;
	private Set<AgreementRole> agreementRoles;
	private Set<PartyPostalAddress> partyPostalAddresses;
	private Set<PartyContactMechanism> partyContactMechanisms;
	private Set<EmploymentApplication> employmentApplicationsForApplicantId;
	private Set<PositionFulfillment> positionFulfillments;
	private Set<ProductCostComponent> productCostComponents;
	private Set<EmploymentApplication> employmentApplicationsForReferrerId;
	private Set<PartyRate> partyRates;

    public Party() {
    }

    public Party(String name, String description) 
    {
        this.name = name;
        this.description = description;
    }

    public Party(String name, String description, Set partyQualifications, Set requirementRoles, Set accountingTransactionsForToPartyId, Set accountingPeriods, Set accountingTransactionsForFromPartyId, Set workEffortPartyAssignements, Set personTrainings, Set financialAccountTransactions, Set communicationEventRoles, Set systemUsers, Set resumes, Set agreementOrganizationApplicabilities, Set partyClassifications, Set partyRoles, Set shippmentReceiptRoles, Set payrollReferencesForEmployeeId, Set payrollReferencesForOrganizationId, Set caseRoles, Set orderRoles, Set productPriceComponents, Set invoicesForFromPartyId, Set workEffortPartyAssignments, Set invoicesForToPartyId, Set respondingParties, Set financialAccountRoles, Set budgetReviews, Set orderItemRoles, Set partySkills, Set products, Set billingAccountRoles, Set organizations, Set shipmentRouteSegments, Set requestRoles, Set budgetRoles, Set supplierProducts, Set persons, Set quoteRoles, Set organizationGeneralLedgerAccountsForRefPartyId, Set timeSheetRoles, Set organizationGeneralLedgerAccountsForOrganizationId, Set itemIssuanceRoles, Set positions, Set agreementRoles, Set partyPostalAddresses, Set partyContactMechanisms, Set employmentApplicationsForApplicantId, Set positionFulfillments, Set productCostComponents, Set employmentApplicationsForReferrerId, Set partyRates) 
    {
        this.name = name;
        this.description = description;
        this.partyQualifications = partyQualifications;
        this.requirementRoles = requirementRoles;
        this.accountingTransactionsForToPartyId = accountingTransactionsForToPartyId;
        this.accountingPeriods = accountingPeriods;
        this.accountingTransactionsForFromPartyId = accountingTransactionsForFromPartyId;
        this.workEffortPartyAssignements = workEffortPartyAssignements;
        this.personTrainings = personTrainings;
        this.financialAccountTransactions = financialAccountTransactions;
        this.communicationEventRoles = communicationEventRoles;
        this.systemUsers = systemUsers;
        this.resumes = resumes;
        this.agreementOrganizationApplicabilities = agreementOrganizationApplicabilities;
        this.partyClassifications = partyClassifications;
        this.partyRoles = partyRoles;
        this.shippmentReceiptRoles = shippmentReceiptRoles;
        this.payrollReferencesForEmployeeId = payrollReferencesForEmployeeId;
        this.payrollReferencesForOrganizationId = payrollReferencesForOrganizationId;
        this.caseRoles = caseRoles;
        this.orderRoles = orderRoles;
        this.productPriceComponents = productPriceComponents;
        this.invoicesForFromPartyId = invoicesForFromPartyId;
        this.workEffortPartyAssignments = workEffortPartyAssignments;
        this.invoicesForToPartyId = invoicesForToPartyId;
        this.respondingParties = respondingParties;
        this.financialAccountRoles = financialAccountRoles;
        this.budgetReviews = budgetReviews;
        this.orderItemRoles = orderItemRoles;
        this.partySkills = partySkills;
        this.products = products;
        this.billingAccountRoles = billingAccountRoles;
        this.organizations = organizations;
        this.shipmentRouteSegments = shipmentRouteSegments;
        this.requestRoles = requestRoles;
        this.budgetRoles = budgetRoles;
        this.supplierProducts = supplierProducts;
        this.persons = persons;
        this.quoteRoles = quoteRoles;
        this.organizationGeneralLedgerAccountsForRefPartyId = organizationGeneralLedgerAccountsForRefPartyId;
        this.timeSheetRoles = timeSheetRoles;
        this.organizationGeneralLedgerAccountsForOrganizationId = organizationGeneralLedgerAccountsForOrganizationId;
        this.itemIssuanceRoles = itemIssuanceRoles;
        this.positions = positions;
        this.agreementRoles = agreementRoles;
        this.partyPostalAddresses = partyPostalAddresses;
        this.partyContactMechanisms = partyContactMechanisms;
        this.employmentApplicationsForApplicantId = employmentApplicationsForApplicantId;
        this.positionFulfillments = positionFulfillments;
        this.productCostComponents = productCostComponents;
        this.employmentApplicationsForReferrerId = employmentApplicationsForReferrerId;
        this.partyRates = partyRates;
    }
    
    @Column(name="NAME", nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
	
    @Column(name="DESCRIPTION", nullable=false, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    /**
	 * @return the partyType
	 */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_TY_ID")
    @JsonIgnore
	public PartyType getPartyType() {
		return partyType;
	}

	/**
	 * @param partyType the partyType to set
	 */
	public void setPartyType(PartyType partyType) {
		this.partyType = partyType;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyQualification> getPartyQualifications() 
    {
        return this.partyQualifications;
    }
    
    public void setPartyQualifications(Set<PartyQualification> partyQualifications) 
    {
        this.partyQualifications = partyQualifications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<RequirementRole> getRequirementRoles() 
    {
        return this.requirementRoles;
    }
    
    public void setRequirementRoles(Set<RequirementRole> requirementRoles) 
    {
        this.requirementRoles = requirementRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByToPartyId")
    @JsonIgnore
    public Set<AccountingTransaction> getAccountingTransactionsForToPartyId() 
    {
        return this.accountingTransactionsForToPartyId;
    }
    
    public void setAccountingTransactionsForToPartyId(Set<AccountingTransaction> accountingTransactionsForToPartyId) 
    {
        this.accountingTransactionsForToPartyId = accountingTransactionsForToPartyId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<AccountingPeriod> getAccountingPeriods() 
    {
        return this.accountingPeriods;
    }
    
    public void setAccountingPeriods(Set<AccountingPeriod> accountingPeriods) 
    {
        this.accountingPeriods = accountingPeriods;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByFromPartyId")
    @JsonIgnore
    public Set<AccountingTransaction> getAccountingTransactionsForFromPartyId() 
    {
        return this.accountingTransactionsForFromPartyId;
    }
    
    public void setAccountingTransactionsForFromPartyId(Set<AccountingTransaction> accountingTransactionsForFromPartyId) 
    {
        this.accountingTransactionsForFromPartyId = accountingTransactionsForFromPartyId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<WorkEffortPartyAssignement> getWorkEffortPartyAssignements() 
    {
        return this.workEffortPartyAssignements;
    }
    
    public void setWorkEffortPartyAssignements(Set<WorkEffortPartyAssignement> workEffortPartyAssignements) 
    {
        this.workEffortPartyAssignements = workEffortPartyAssignements;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PersonTraining> getPersonTrainings() 
    {
        return this.personTrainings;
    }
    
    public void setPersonTrainings(Set<PersonTraining> personTrainings) 
    {
        this.personTrainings = personTrainings;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<FinancialAccountTransaction> getFinancialAccountTransactions() 
    {
        return this.financialAccountTransactions;
    }
    
    public void setFinancialAccountTransactions(Set<FinancialAccountTransaction> financialAccountTransactions) 
    {
        this.financialAccountTransactions = financialAccountTransactions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<CommunicationEventRole> getCommunicationEventRoles() 
    {
        return this.communicationEventRoles;
    }
    
    public void setCommunicationEventRoles(Set<CommunicationEventRole> communicationEventRoles) 
    {
        this.communicationEventRoles = communicationEventRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<SystemUser> getSystemUsers() 
    {
        return this.systemUsers;
    }
    
    public void setSystemUsers(Set<SystemUser> systemUsers) 
    {
        this.systemUsers = systemUsers;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<Resume> getResumes() 
    {
        return this.resumes;
    }
    
    public void setResumes(Set<Resume> resumes) 
    {
        this.resumes = resumes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<AgreementOrganizationApplicability> getAgreementOrganizationApplicabilities() 
    {
        return this.agreementOrganizationApplicabilities;
    }
    
    public void setAgreementOrganizationApplicabilities(Set<AgreementOrganizationApplicability> agreementOrganizationApplicabilities) 
    {
        this.agreementOrganizationApplicabilities = agreementOrganizationApplicabilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyClassification> getPartyClassifications() 
    {
        return this.partyClassifications;
    }
    
    public void setPartyClassifications(Set<PartyClassification> partyClassifications) 
    {
        this.partyClassifications = partyClassifications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyRole> getPartyRoles() 
    {
        return this.partyRoles;
    }
    
    public void setPartyRoles(Set<PartyRole> partyRoles) 
    {
        this.partyRoles = partyRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<ShippmentReceiptRole> getShippmentReceiptRoles() 
    {
        return this.shippmentReceiptRoles;
    }
    
    public void setShippmentReceiptRoles(Set<ShippmentReceiptRole> shippmentReceiptRoles) 
    {
        this.shippmentReceiptRoles = shippmentReceiptRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByEmployeeId")
    @JsonIgnore
    public Set<PayrollReference> getPayrollReferencesForEmployeeId() 
    {
        return this.payrollReferencesForEmployeeId;
    }
    
    public void setPayrollReferencesForEmployeeId(Set<PayrollReference> payrollReferencesForEmployeeId) 
    {
        this.payrollReferencesForEmployeeId = payrollReferencesForEmployeeId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByOrganizationId")
    @JsonIgnore
    public Set<PayrollReference> getPayrollReferencesForOrganizationId() 
    {
        return this.payrollReferencesForOrganizationId;
    }
    
    public void setPayrollReferencesForOrganizationId(Set<PayrollReference> payrollReferencesForOrganizationId) 
    {
        this.payrollReferencesForOrganizationId = payrollReferencesForOrganizationId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<CaseRole> getCaseRoles() 
    {
        return this.caseRoles;
    }
    
    public void setCaseRoles(Set<CaseRole> caseRoles) 
    {
        this.caseRoles = caseRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<OrderRole> getOrderRoles() 
    {
        return this.orderRoles;
    }
    
    public void setOrderRoles(Set<OrderRole> orderRoles) 
    {
        this.orderRoles = orderRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<ProductPriceComponent> getProductPriceComponents() 
    {
        return this.productPriceComponents;
    }
    
    public void setProductPriceComponents(Set<ProductPriceComponent> productPriceComponents) 
    {
        this.productPriceComponents = productPriceComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByFromPartyId")
    @JsonIgnore
    public Set<Invoice> getInvoicesForFromPartyId() 
    {
        return this.invoicesForFromPartyId;
    }
    
    public void setInvoicesForFromPartyId(Set<Invoice> invoicesForFromPartyId) 
    {
        this.invoicesForFromPartyId = invoicesForFromPartyId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<WorkEffortPartyAssignment> getWorkEffortPartyAssignments() 
    {
        return this.workEffortPartyAssignments;
    }
    
    public void setWorkEffortPartyAssignments(Set<WorkEffortPartyAssignment> workEffortPartyAssignments) 
    {
        this.workEffortPartyAssignments = workEffortPartyAssignments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByToPartyId")
    @JsonIgnore
    public Set<Invoice> getInvoicesForToPartyId() 
    {
        return this.invoicesForToPartyId;
    }
    
    public void setInvoicesForToPartyId(Set<Invoice> invoicesForToPartyId) 
    {
        this.invoicesForToPartyId = invoicesForToPartyId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<RespondingParty> getRespondingParties() 
    {
        return this.respondingParties;
    }
    
    public void setRespondingParties(Set<RespondingParty> respondingParties) 
    {
        this.respondingParties = respondingParties;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<FinancialAccountRole> getFinancialAccountRoles() 
    {
        return this.financialAccountRoles;
    }
    
    public void setFinancialAccountRoles(Set<FinancialAccountRole> financialAccountRoles) 
    {
        this.financialAccountRoles = financialAccountRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<BudgetReview> getBudgetReviews() 
    {
        return this.budgetReviews;
    }
    
    public void setBudgetReviews(Set<BudgetReview> budgetReviews) 
    {
        this.budgetReviews = budgetReviews;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<OrderItemRole> getOrderItemRoles() 
    {
        return this.orderItemRoles;
    }
    
    public void setOrderItemRoles(Set<OrderItemRole> orderItemRoles) 
    {
        this.orderItemRoles = orderItemRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartySkill> getPartySkills() 
    {
        return this.partySkills;
    }
    
    public void setPartySkills(Set<PartySkill> partySkills) 
    {
        this.partySkills = partySkills;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<Product> getProducts() 
    {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) 
    {
        this.products = products;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<BillingAccountRole> getBillingAccountRoles() 
    {
        return this.billingAccountRoles;
    }
    
    public void setBillingAccountRoles(Set<BillingAccountRole> billingAccountRoles) 
    {
        this.billingAccountRoles = billingAccountRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<Organization> getOrganizations() 
    {
        return this.organizations;
    }
    
    public void setOrganizations(Set<Organization> organizations) 
    {
        this.organizations = organizations;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<ShipmentRouteSegment> getShipmentRouteSegments() 
    {
        return this.shipmentRouteSegments;
    }
    
    public void setShipmentRouteSegments(Set<ShipmentRouteSegment> shipmentRouteSegments) 
    {
        this.shipmentRouteSegments = shipmentRouteSegments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<RequestRole> getRequestRoles() 
    {
        return this.requestRoles;
    }
    
    public void setRequestRoles(Set<RequestRole> requestRoles) 
    {
        this.requestRoles = requestRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<BudgetRole> getBudgetRoles() 
    {
        return this.budgetRoles;
    }
    
    public void setBudgetRoles(Set<BudgetRole> budgetRoles) 
    {
        this.budgetRoles = budgetRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<SupplierProduct> getSupplierProducts() 
    {
        return this.supplierProducts;
    }
    
    public void setSupplierProducts(Set<SupplierProduct> supplierProducts) 
    {
        this.supplierProducts = supplierProducts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<Person> getPersons() 
    {
        return this.persons;
    }
    
    public void setPersons(Set<Person> persons) 
    {
        this.persons = persons;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<QuoteRole> getQuoteRoles() 
    {
        return this.quoteRoles;
    }
    
    public void setQuoteRoles(Set<QuoteRole> quoteRoles) 
    {
        this.quoteRoles = quoteRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByRefPartyId")
    @JsonIgnore
    public Set<OrganizationGeneralLedgerAccount> getOrganizationGeneralLedgerAccountsForRefPartyId() 
    {
        return this.organizationGeneralLedgerAccountsForRefPartyId;
    }
    
    public void setOrganizationGeneralLedgerAccountsForRefPartyId(Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccountsForRefPartyId) 
    {
        this.organizationGeneralLedgerAccountsForRefPartyId = organizationGeneralLedgerAccountsForRefPartyId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<TimeSheetRole> getTimeSheetRoles() 
    {
        return this.timeSheetRoles;
    }
    
    public void setTimeSheetRoles(Set<TimeSheetRole> timeSheetRoles) 
    {
        this.timeSheetRoles = timeSheetRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByOrganizationId")
    @JsonIgnore
    public Set<OrganizationGeneralLedgerAccount> getOrganizationGeneralLedgerAccountsForOrganizationId() 
    {
        return this.organizationGeneralLedgerAccountsForOrganizationId;
    }
    
    public void setOrganizationGeneralLedgerAccountsForOrganizationId(Set<OrganizationGeneralLedgerAccount> organizationGeneralLedgerAccountsForOrganizationId) 
    {
        this.organizationGeneralLedgerAccountsForOrganizationId = organizationGeneralLedgerAccountsForOrganizationId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<ItemIssuanceRole> getItemIssuanceRoles() 
    {
        return this.itemIssuanceRoles;
    }
    
    public void setItemIssuanceRoles(Set<ItemIssuanceRole> itemIssuanceRoles) 
    {
        this.itemIssuanceRoles = itemIssuanceRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<Position> getPositions() 
    {
        return this.positions;
    }
    
    public void setPositions(Set<Position> positions) 
    {
        this.positions = positions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<AgreementRole> getAgreementRoles() 
    {
        return this.agreementRoles;
    }
    
    public void setAgreementRoles(Set<AgreementRole> agreementRoles) 
    {
        this.agreementRoles = agreementRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyPostalAddress> getPartyPostalAddresses() 
    {
        return this.partyPostalAddresses;
    }
    
    public void setPartyPostalAddresses(Set<PartyPostalAddress> partyPostalAddresses) 
    {
        this.partyPostalAddresses = partyPostalAddresses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyContactMechanism> getPartyContactMechanisms() 
    {
        return this.partyContactMechanisms;
    }
    
    public void setPartyContactMechanisms(Set<PartyContactMechanism> partyContactMechanisms) 
    {
        this.partyContactMechanisms = partyContactMechanisms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByApplicantId")
    @JsonIgnore
    public Set<EmploymentApplication> getEmploymentApplicationsForApplicantId() 
    {
        return this.employmentApplicationsForApplicantId;
    }
    
    public void setEmploymentApplicationsForApplicantId(Set<EmploymentApplication> employmentApplicationsForApplicantId) 
    {
        this.employmentApplicationsForApplicantId = employmentApplicationsForApplicantId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PositionFulfillment> getPositionFulfillments() 
    {
        return this.positionFulfillments;
    }
    
    public void setPositionFulfillments(Set<PositionFulfillment> positionFulfillments) 
    {
        this.positionFulfillments = positionFulfillments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<ProductCostComponent> getProductCostComponents() 
    {
        return this.productCostComponents;
    }
    
    public void setProductCostComponents(Set<ProductCostComponent> productCostComponents) 
    {
        this.productCostComponents = productCostComponents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="partyByReferrerId")
    @JsonIgnore
    public Set<EmploymentApplication> getEmploymentApplicationsForReferrerId() 
    {
        return this.employmentApplicationsForReferrerId;
    }
    
    public void setEmploymentApplicationsForReferrerId(Set<EmploymentApplication> employmentApplicationsForReferrerId) 
    {
        this.employmentApplicationsForReferrerId = employmentApplicationsForReferrerId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="party")
    @JsonIgnore
    public Set<PartyRate> getPartyRates() 
    {
        return this.partyRates;
    }
    
    public void setPartyRates(Set<PartyRate> partyRates) 
    {
        this.partyRates = partyRates;
    }			
		
    

}
