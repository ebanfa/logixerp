/**
 *  Business Logic.
 */
package com.qurion.businesslogic.hr.model;

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

import java.util.Set;

import com.qurion.businesslogic.hr.model.PartyBenefit;

/**
 * BenefitType 
 * @author Edward Banfa
 */
@Entity
@Table(name="BENEFIT_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BenefitType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Integer emplPaidPercentage;
	private Set<PartyBenefit> partyBenefits;

    public BenefitType() {
    }

    public BenefitType(String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
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

    public BenefitType(String name, String description, Integer emplPaidPercentage, Set partyBenefits, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.emplPaidPercentage = emplPaidPercentage;
        this.partyBenefits = partyBenefits;
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
	
    @Column(name="EMPL_PAID_PERCENTAGE")
    public Integer getEmplPaidPercentage() 
    {
        return this.emplPaidPercentage;
    }
    
    public void setEmplPaidPercentage(Integer emplPaidPercentage) 
    {
        this.emplPaidPercentage = emplPaidPercentage;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="benefitType")
    @JsonIgnore
    public Set<PartyBenefit> getPartyBenefits() 
    {
        return this.partyBenefits;
    }
    
    public void setPartyBenefits(Set<PartyBenefit> partyBenefits) 
    {
        this.partyBenefits = partyBenefits;
    }			
		
    

}
