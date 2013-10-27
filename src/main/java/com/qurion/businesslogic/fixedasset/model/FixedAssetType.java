/**
 *  Business Logic.
 */
package com.qurion.businesslogic.fixedasset.model;

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
import com.qurion.businesslogic.fixedasset.model.FixedAssetType;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortFixedAssetStandard;

import java.util.Set;

import com.qurion.businesslogic.fixedasset.model.FixedAssetType;

import java.util.Set;

import com.qurion.businesslogic.fixedasset.model.FixedAsset;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortType;

/**
 * FixedAssetType 
 * @author Edward Banfa
 */
@Entity
@Table(name="FIXED_ASSET_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FixedAssetType  extends BaseEntity implements java.io.Serializable {
	private FixedAssetType fixedAssetType;
	private String name;
	private String description;
	private Set<WorkEffortFixedAssetStandard> workEffortFixedAssetStandards;
	private Set<FixedAssetType> fixedAssetTypes;
	private Set<FixedAsset> fixedAssets;
	private Set<WorkEffortType> workEffortTypes;

    public FixedAssetType() {
    }

    public FixedAssetType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public FixedAssetType(FixedAssetType fixedAssetType, String name, String description, Set workEffortFixedAssetStandards, Set fixedAssetTypes, Set fixedAssets, Set workEffortTypes, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.fixedAssetType = fixedAssetType;
        this.name = name;
        this.description = description;
        this.workEffortFixedAssetStandards = workEffortFixedAssetStandards;
        this.fixedAssetTypes = fixedAssetTypes;
        this.fixedAssets = fixedAssets;
        this.workEffortTypes = workEffortTypes;
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
    @JoinColumn(name="PARENT_TY_ID")
    @JsonIgnore
    public FixedAssetType getFixedAssetType() 
    {
        return this.fixedAssetType;
    }
    
    public void setFixedAssetType(FixedAssetType fixedAssetType)
    {
        this.fixedAssetType = fixedAssetType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAssetType")
    @JsonIgnore
    public Set<WorkEffortFixedAssetStandard> getWorkEffortFixedAssetStandards() 
    {
        return this.workEffortFixedAssetStandards;
    }
    
    public void setWorkEffortFixedAssetStandards(Set<WorkEffortFixedAssetStandard> workEffortFixedAssetStandards) 
    {
        this.workEffortFixedAssetStandards = workEffortFixedAssetStandards;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAssetType")
    @JsonIgnore
    public Set<FixedAssetType> getFixedAssetTypes() 
    {
        return this.fixedAssetTypes;
    }
    
    public void setFixedAssetTypes(Set<FixedAssetType> fixedAssetTypes) 
    {
        this.fixedAssetTypes = fixedAssetTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAssetType")
    @JsonIgnore
    public Set<FixedAsset> getFixedAssets() 
    {
        return this.fixedAssets;
    }
    
    public void setFixedAssets(Set<FixedAsset> fixedAssets) 
    {
        this.fixedAssets = fixedAssets;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAssetType")
    @JsonIgnore
    public Set<WorkEffortType> getWorkEffortTypes() 
    {
        return this.workEffortTypes;
    }
    
    public void setWorkEffortTypes(Set<WorkEffortType> workEffortTypes) 
    {
        this.workEffortTypes = workEffortTypes;
    }			
		
    

}
