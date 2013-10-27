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

import com.qurion.businesslogic.hr.model.PayrollReference;

import java.util.Set;

import com.qurion.businesslogic.hr.model.Deduction;

/**
 * DeductionType 
 * @author Edward Banfa
 */
@Entity
@Table(name="DEDUCTION_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DeductionType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<PayrollReference> payrollReferences;
	private Set<Deduction> deductions;

    public DeductionType() {
    }

    public DeductionType(String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
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

    public DeductionType(String name, String description, Set payrollReferences, Set deductions, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.payrollReferences = payrollReferences;
        this.deductions = deductions;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="deductionType")
    @JsonIgnore
    public Set<PayrollReference> getPayrollReferences() 
    {
        return this.payrollReferences;
    }
    
    public void setPayrollReferences(Set<PayrollReference> payrollReferences) 
    {
        this.payrollReferences = payrollReferences;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="deductionType")
    @JsonIgnore
    public Set<Deduction> getDeductions() 
    {
        return this.deductions;
    }
    
    public void setDeductions(Set<Deduction> deductions) 
    {
        this.deductions = deductions;
    }			
		
    

}
