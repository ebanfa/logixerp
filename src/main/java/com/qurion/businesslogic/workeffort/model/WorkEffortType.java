/**
 *  Business Logic.
 */
package com.qurion.businesslogic.workeffort.model;

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
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.fixedasset.model.FixedAssetType;
import com.qurion.businesslogic.workeffort.model.WorkEffortType;
import com.qurion.businesslogic.workeffort.model.DeliverableType;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortProductStandard;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortFixedAssetStandard;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffort;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortTypeAssociation;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortTypeAssociation;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortType;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortSkillStandard;

/**
 * WorkEffortType 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortType  extends BaseEntity implements java.io.Serializable {
	private Product product;
	private FixedAssetType fixedAssetType;
	private WorkEffortType workEffortType;
	private DeliverableType deliverableType;
	private String name;
	private String description;
	private Set<WorkEffortProductStandard> workEffortProductStandards;
	private Set<WorkEffortFixedAssetStandard> workEffortFixedAssetStandards;
	private Set<WorkEffort> workEfforts;
	private Set<WorkEffortTypeAssociation> workEffortTypeAssociationsForToWrkEffortTyId;
	private Set<WorkEffortTypeAssociation> workEffortTypeAssociationsForFromWrkEffortTyId;
	private Set<WorkEffortType> workEffortTypes;
	private Set<WorkEffortSkillStandard> workEffortSkillStandards;

    public WorkEffortType() {
    }

    public WorkEffortType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortType(Product product, FixedAssetType fixedAssetType, WorkEffortType workEffortType, DeliverableType deliverableType, String name, String description, Set workEffortProductStandards, Set workEffortFixedAssetStandards, Set workEfforts, Set workEffortTypeAssociationsForToWrkEffortTyId, Set workEffortTypeAssociationsForFromWrkEffortTyId, Set workEffortTypes, Set workEffortSkillStandards, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.product = product;
        this.fixedAssetType = fixedAssetType;
        this.workEffortType = workEffortType;
        this.deliverableType = deliverableType;
        this.name = name;
        this.description = description;
        this.workEffortProductStandards = workEffortProductStandards;
        this.workEffortFixedAssetStandards = workEffortFixedAssetStandards;
        this.workEfforts = workEfforts;
        this.workEffortTypeAssociationsForToWrkEffortTyId = workEffortTypeAssociationsForToWrkEffortTyId;
        this.workEffortTypeAssociationsForFromWrkEffortTyId = workEffortTypeAssociationsForFromWrkEffortTyId;
        this.workEffortTypes = workEffortTypes;
        this.workEffortSkillStandards = workEffortSkillStandards;
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
    @JoinColumn(name="PRODUCT_ID")
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
    @JoinColumn(name="FIXED_ASSET_TY_ID")
    @JsonIgnore
    public FixedAssetType getFixedAssetType() 
    {
        return this.fixedAssetType;
    }
    
    public void setFixedAssetType(FixedAssetType fixedAssetType)
    {
        this.fixedAssetType = fixedAssetType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_EFFORT_TY_ID")
    @JsonIgnore
    public WorkEffortType getWorkEffortType() 
    {
        return this.workEffortType;
    }
    
    public void setWorkEffortType(WorkEffortType workEffortType)
    {
        this.workEffortType = workEffortType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DELIVERABLE_TY_ID")
    @JsonIgnore
    public DeliverableType getDeliverableType() 
    {
        return this.deliverableType;
    }
    
    public void setDeliverableType(DeliverableType deliverableType)
    {
        this.deliverableType = deliverableType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortType")
    @JsonIgnore
    public Set<WorkEffortProductStandard> getWorkEffortProductStandards() 
    {
        return this.workEffortProductStandards;
    }
    
    public void setWorkEffortProductStandards(Set<WorkEffortProductStandard> workEffortProductStandards) 
    {
        this.workEffortProductStandards = workEffortProductStandards;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortType")
    @JsonIgnore
    public Set<WorkEffortFixedAssetStandard> getWorkEffortFixedAssetStandards() 
    {
        return this.workEffortFixedAssetStandards;
    }
    
    public void setWorkEffortFixedAssetStandards(Set<WorkEffortFixedAssetStandard> workEffortFixedAssetStandards) 
    {
        this.workEffortFixedAssetStandards = workEffortFixedAssetStandards;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortType")
    @JsonIgnore
    public Set<WorkEffort> getWorkEfforts() 
    {
        return this.workEfforts;
    }
    
    public void setWorkEfforts(Set<WorkEffort> workEfforts) 
    {
        this.workEfforts = workEfforts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortTypeByToWrkEffortTyId")
    @JsonIgnore
    public Set<WorkEffortTypeAssociation> getWorkEffortTypeAssociationsForToWrkEffortTyId() 
    {
        return this.workEffortTypeAssociationsForToWrkEffortTyId;
    }
    
    public void setWorkEffortTypeAssociationsForToWrkEffortTyId(Set<WorkEffortTypeAssociation> workEffortTypeAssociationsForToWrkEffortTyId) 
    {
        this.workEffortTypeAssociationsForToWrkEffortTyId = workEffortTypeAssociationsForToWrkEffortTyId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortTypeByFromWrkEffortTyId")
    @JsonIgnore
    public Set<WorkEffortTypeAssociation> getWorkEffortTypeAssociationsForFromWrkEffortTyId() 
    {
        return this.workEffortTypeAssociationsForFromWrkEffortTyId;
    }
    
    public void setWorkEffortTypeAssociationsForFromWrkEffortTyId(Set<WorkEffortTypeAssociation> workEffortTypeAssociationsForFromWrkEffortTyId) 
    {
        this.workEffortTypeAssociationsForFromWrkEffortTyId = workEffortTypeAssociationsForFromWrkEffortTyId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortType")
    @JsonIgnore
    public Set<WorkEffortType> getWorkEffortTypes() 
    {
        return this.workEffortTypes;
    }
    
    public void setWorkEffortTypes(Set<WorkEffortType> workEffortTypes) 
    {
        this.workEffortTypes = workEffortTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortType")
    @JsonIgnore
    public Set<WorkEffortSkillStandard> getWorkEffortSkillStandards() 
    {
        return this.workEffortSkillStandards;
    }
    
    public void setWorkEffortSkillStandards(Set<WorkEffortSkillStandard> workEffortSkillStandards) 
    {
        this.workEffortSkillStandards = workEffortSkillStandards;
    }			
		
    

}
