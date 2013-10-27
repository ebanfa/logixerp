/**
 *  Business Logic.
 */
package com.qurion.businesslogic.accounting.model;

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

import com.qurion.businesslogic.fixedasset.model.FixedAsset;
import com.qurion.businesslogic.accounting.model.DepreciationMethod;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.util.Date;
import java.util.Date;

/**
 * FixedAssetDepreciationMethod 
 * @author Edward Banfa
 */
@Entity
@Table(name="FIXED_ASSET_DEPRECIATION_METHOD"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FixedAssetDepreciationMethod  extends BaseEntity implements java.io.Serializable {
	private FixedAsset fixedAsset;
	private DepreciationMethod depreciationMethod;
	private Date fromDt;
	private Date toDt;

    public FixedAssetDepreciationMethod() {
    }

    public FixedAssetDepreciationMethod(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public FixedAssetDepreciationMethod(FixedAsset fixedAsset, DepreciationMethod depreciationMethod, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.fixedAsset = fixedAsset;
        this.depreciationMethod = depreciationMethod;
        this.fromDt = fromDt;
        this.toDt = toDt;
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
    @JoinColumn(name="DEP_METHOD_ID")
    @JsonIgnore
    public DepreciationMethod getDepreciationMethod() 
    {
        return this.depreciationMethod;
    }
    
    public void setDepreciationMethod(DepreciationMethod depreciationMethod)
    {
        this.depreciationMethod = depreciationMethod;
    }
    
    @Column(name="FROM_DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    

}
