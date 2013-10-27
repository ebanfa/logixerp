/**
 *  Business Logic.
 */
package com.qurion.businesslogic.businessdata.model;

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

import com.qurion.businesslogic.businessdata.model.PeriodType;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.accounting.model.Budget;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * StandardTimePeriod 
 * @author Edward Banfa
 */
@Entity
@Table(name="STANDARD_TIME_PERIOD"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class StandardTimePeriod  extends BaseEntity implements java.io.Serializable {
	private PeriodType periodType;
	private String name;
	private String description;
	private Date fromDt;
	private Date toDt;
	private Set<Budget> budgets;

    public StandardTimePeriod() {
    }

    public StandardTimePeriod(String name, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public StandardTimePeriod(PeriodType periodType, String name, String description, Date fromDt, Date toDt, Set budgets, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.periodType = periodType;
        this.name = name;
        this.description = description;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.budgets = budgets;
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
    @JoinColumn(name="PERIOD_TY_ID")
    @JsonIgnore
    public PeriodType getPeriodType() 
    {
        return this.periodType;
    }
    
    public void setPeriodType(PeriodType periodType)
    {
        this.periodType = periodType;
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
	
    @Column(name="FROM__DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", nullable=false, length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="standardTimePeriod")
    @JsonIgnore
    public Set<Budget> getBudgets() 
    {
        return this.budgets;
    }
    
    public void setBudgets(Set<Budget> budgets) 
    {
        this.budgets = budgets;
    }			
		
    

}
