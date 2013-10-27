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
import com.qurion.businesslogic.hr.model.PayGrade;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PayHistory;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionTypeRate;

/**
 * SalaryStep 
 * @author Edward Banfa
 */
@Entity
@Table(name="SALARY_STEP"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SalaryStep  extends BaseEntity implements java.io.Serializable {
	private PayGrade payGrade;
	private String name;
	private String description;
	private Set<PayHistory> payHistories;
	private Set<PositionTypeRate> positionTypeRates;

    public SalaryStep() {
    }

    public SalaryStep(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public SalaryStep(PayGrade payGrade, String name, String description, Set payHistories, Set positionTypeRates, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.payGrade = payGrade;
        this.name = name;
        this.description = description;
        this.payHistories = payHistories;
        this.positionTypeRates = positionTypeRates;
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
    @JoinColumn(name="PAY_GRADE_ID")
    @JsonIgnore
    public PayGrade getPayGrade() 
    {
        return this.payGrade;
    }
    
    public void setPayGrade(PayGrade payGrade)
    {
        this.payGrade = payGrade;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="salaryStep")
    @JsonIgnore
    public Set<PayHistory> getPayHistories() 
    {
        return this.payHistories;
    }
    
    public void setPayHistories(Set<PayHistory> payHistories) 
    {
        this.payHistories = payHistories;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="salaryStep")
    @JsonIgnore
    public Set<PositionTypeRate> getPositionTypeRates() 
    {
        return this.positionTypeRates;
    }
    
    public void setPositionTypeRates(Set<PositionTypeRate> positionTypeRates) 
    {
        this.positionTypeRates = positionTypeRates;
    }			
		
    

}
