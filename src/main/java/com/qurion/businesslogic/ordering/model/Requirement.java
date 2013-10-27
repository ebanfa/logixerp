/**
 *  Business Logic.
 */
package com.qurion.businesslogic.ordering.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.ordering.model.RequirementType;
import com.qurion.businesslogic.fixedasset.model.FixedAsset;
import com.qurion.businesslogic.ordering.model.Requirement;
import com.qurion.businesslogic.workeffort.model.Deliverable;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.fixedasset.model.Facility;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequirementRole;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequirementStatus;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.DesiredFeature;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkRequirementFulfillment;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequirementRequest;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.Requirement;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequirementBudgetApplication;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderRequirementCommitment;

/**
 * Requirement 
 * @author Edward Banfa
 */
@Entity
@Table(name="REQUIREMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Requirement  extends BaseEntity implements java.io.Serializable {
	private RequirementType requirementType;
	private FixedAsset fixedAsset;
	private Requirement requirement;
	private Deliverable deliverable;
	private Product product;
	private Facility facility;
	private String name;
	private String description;
	private Date requiredByDt;
	private BigDecimal estimatedBudget;
	private Integer quantity;
	private String reason;
	private Set<RequirementRole> requirementRoles;
	private Set<RequirementStatus> requirementStatuses;
	private Set<DesiredFeature> desiredFeatures;
	private Set<WorkRequirementFulfillment> workRequirementFulfillments;
	private Set<RequirementRequest> requirementRequests;
	private Set<Requirement> requirements;
	private Set<RequirementBudgetApplication> requirementBudgetApplications;
	private Set<OrderRequirementCommitment> orderRequirementCommitments;

    public Requirement() {
    }

    public Requirement(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Requirement(RequirementType requirementType, FixedAsset fixedAsset, Requirement requirement, Deliverable deliverable, Product product, Facility facility, String name, String description, Date requiredByDt, BigDecimal estimatedBudget, Integer quantity, String reason, Set requirementRoles, Set requirementStatuses, Set desiredFeatures, Set workRequirementFulfillments, Set requirementRequests, Set requirements, Set requirementBudgetApplications, Set orderRequirementCommitments, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.requirementType = requirementType;
        this.fixedAsset = fixedAsset;
        this.requirement = requirement;
        this.deliverable = deliverable;
        this.product = product;
        this.facility = facility;
        this.name = name;
        this.description = description;
        this.requiredByDt = requiredByDt;
        this.estimatedBudget = estimatedBudget;
        this.quantity = quantity;
        this.reason = reason;
        this.requirementRoles = requirementRoles;
        this.requirementStatuses = requirementStatuses;
        this.desiredFeatures = desiredFeatures;
        this.workRequirementFulfillments = workRequirementFulfillments;
        this.requirementRequests = requirementRequests;
        this.requirements = requirements;
        this.requirementBudgetApplications = requirementBudgetApplications;
        this.orderRequirementCommitments = orderRequirementCommitments;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.versionNo = versionNo;
        this.rowTs = rowTs;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
        this.lastModifiedDt = lastModifiedDt;
        this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REQ_TY_ID")
    @JsonIgnore
    public RequirementType getRequirementType() 
    {
        return this.requirementType;
    }
    
    public void setRequirementType(RequirementType requirementType)
    {
        this.requirementType = requirementType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIXED_ASSET_ID")
    @JsonIgnore
    public FixedAsset getFixedAsset() 
    {
        return this.fixedAsset;
    }
    
    public void setFixedAsset(FixedAsset fixedAsset)
    {
        this.fixedAsset = fixedAsset;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REQ_ID")
    @JsonIgnore
    public Requirement getRequirement() 
    {
        return this.requirement;
    }
    
    public void setRequirement(Requirement requirement)
    {
        this.requirement = requirement;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DELIVERABLE_ID")
    @JsonIgnore
    public Deliverable getDeliverable() 
    {
        return this.deliverable;
    }
    
    public void setDeliverable(Deliverable deliverable)
    {
        this.deliverable = deliverable;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_ID")
    @JsonIgnore
    public Product getProduct() 
    {
        return this.product;
    }
    
    public void setProduct(Product product)
    {
        this.product = product;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FACILITY_ID")
    @JsonIgnore
    public Facility getFacility() 
    {
        return this.facility;
    }
    
    public void setFacility(Facility facility)
    {
        this.facility = facility;
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
	
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @Column(name="REQUIRED_BY_DT", length=10)
    public Date getRequiredByDt() 
    {
        return this.requiredByDt;
    }
    
    public void setRequiredByDt(Date requiredByDt) 
    {
        this.requiredByDt = requiredByDt;
    }
	
    @Column(name="ESTIMATED_BUDGET")
    public BigDecimal getEstimatedBudget() 
    {
        return this.estimatedBudget;
    }
    
    public void setEstimatedBudget(BigDecimal estimatedBudget) 
    {
        this.estimatedBudget = estimatedBudget;
    }
	
    @Column(name="QUANTITY")
    public Integer getQuantity() 
    {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) 
    {
        this.quantity = quantity;
    }
	
    @Column(name="REASON", length=150)
    public String getReason() 
    {
        return this.reason;
    }
    
    public void setReason(String reason) 
    {
        this.reason = reason;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirement")
    @JsonIgnore
    public Set<RequirementRole> getRequirementRoles() 
    {
        return this.requirementRoles;
    }
    
    public void setRequirementRoles(Set<RequirementRole> requirementRoles) 
    {
        this.requirementRoles = requirementRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirement")
    @JsonIgnore
    public Set<RequirementStatus> getRequirementStatuses() 
    {
        return this.requirementStatuses;
    }
    
    public void setRequirementStatuses(Set<RequirementStatus> requirementStatuses) 
    {
        this.requirementStatuses = requirementStatuses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirement")
    @JsonIgnore
    public Set<DesiredFeature> getDesiredFeatures() 
    {
        return this.desiredFeatures;
    }
    
    public void setDesiredFeatures(Set<DesiredFeature> desiredFeatures) 
    {
        this.desiredFeatures = desiredFeatures;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirement")
    @JsonIgnore
    public Set<WorkRequirementFulfillment> getWorkRequirementFulfillments() 
    {
        return this.workRequirementFulfillments;
    }
    
    public void setWorkRequirementFulfillments(Set<WorkRequirementFulfillment> workRequirementFulfillments) 
    {
        this.workRequirementFulfillments = workRequirementFulfillments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirement")
    @JsonIgnore
    public Set<RequirementRequest> getRequirementRequests() 
    {
        return this.requirementRequests;
    }
    
    public void setRequirementRequests(Set<RequirementRequest> requirementRequests) 
    {
        this.requirementRequests = requirementRequests;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirement")
    @JsonIgnore
    public Set<Requirement> getRequirements() 
    {
        return this.requirements;
    }
    
    public void setRequirements(Set<Requirement> requirements) 
    {
        this.requirements = requirements;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirement")
    @JsonIgnore
    public Set<RequirementBudgetApplication> getRequirementBudgetApplications() 
    {
        return this.requirementBudgetApplications;
    }
    
    public void setRequirementBudgetApplications(Set<RequirementBudgetApplication> requirementBudgetApplications) 
    {
        this.requirementBudgetApplications = requirementBudgetApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirement")
    @JsonIgnore
    public Set<OrderRequirementCommitment> getOrderRequirementCommitments() 
    {
        return this.orderRequirementCommitments;
    }
    
    public void setOrderRequirementCommitments(Set<OrderRequirementCommitment> orderRequirementCommitments) 
    {
        this.orderRequirementCommitments = orderRequirementCommitments;
    }			
		
    

}
