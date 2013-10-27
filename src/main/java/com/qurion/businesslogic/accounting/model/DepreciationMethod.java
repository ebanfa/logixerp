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

import java.util.Set;

import com.qurion.businesslogic.accounting.model.FixedAssetDepreciationMethod;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * DepreciationMethod 
 * @author Edward Banfa
 */
@Entity
@Table(name="DEPRECIATION_METHOD"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DepreciationMethod  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private String formula;
	private Set<FixedAssetDepreciationMethod> fixedAssetDepreciationMethods;

    public DepreciationMethod() {
    }

    public DepreciationMethod(String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.description = description;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public DepreciationMethod(String name, String description, String formula, Set fixedAssetDepreciationMethods, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.formula = formula;
        this.fixedAssetDepreciationMethods = fixedAssetDepreciationMethods;
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
	
    @Column(name="FORMULA", length=35)
    public String getFormula() 
    {
        return this.formula;
    }
    
    public void setFormula(String formula) 
    {
        this.formula = formula;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="depreciationMethod")
    @JsonIgnore
    public Set<FixedAssetDepreciationMethod> getFixedAssetDepreciationMethods() 
    {
        return this.fixedAssetDepreciationMethods;
    }
    
    public void setFixedAssetDepreciationMethods(Set<FixedAssetDepreciationMethod> fixedAssetDepreciationMethods) 
    {
        this.fixedAssetDepreciationMethods = fixedAssetDepreciationMethods;
    }			
		
    

}
