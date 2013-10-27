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
import com.qurion.businesslogic.workeffort.model.WorkEffortAssociationType;
import com.qurion.businesslogic.workeffort.model.WorkEffort;
import com.qurion.businesslogic.workeffort.model.WorkEffort;

import java.util.Date;
import java.util.Date;

/**
 * WorkEffortAssociation 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_ASSOCIATION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortAssociation  extends BaseEntity implements java.io.Serializable {
	private WorkEffortAssociationType workEffortAssociationType;
	private WorkEffort workEffortByToWrkEffortId;
	private WorkEffort workEffortByFromWrkEffortId;
	private Date effectiveFromDt;
	private Date effectiveThruDt;

    public WorkEffortAssociation() {
    }

    public WorkEffortAssociation(Date effectiveFromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.effectiveFromDt = effectiveFromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortAssociation(WorkEffortAssociationType workEffortAssociationType, WorkEffort workEffortByToWrkEffortId, WorkEffort workEffortByFromWrkEffortId, Date effectiveFromDt, Date effectiveThruDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.workEffortAssociationType = workEffortAssociationType;
        this.workEffortByToWrkEffortId = workEffortByToWrkEffortId;
        this.workEffortByFromWrkEffortId = workEffortByFromWrkEffortId;
        this.effectiveFromDt = effectiveFromDt;
        this.effectiveThruDt = effectiveThruDt;
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
    @JoinColumn(name="ASSOC_TY_ID")
    @JsonIgnore
    public WorkEffortAssociationType getWorkEffortAssociationType() 
    {
        return this.workEffortAssociationType;
    }
    
    public void setWorkEffortAssociationType(WorkEffortAssociationType workEffortAssociationType)
    {
        this.workEffortAssociationType = workEffortAssociationType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_WRK_EFFORT_ID")
    @JsonIgnore
    public WorkEffort getWorkEffortByToWrkEffortId() 
    {
        return this.workEffortByToWrkEffortId;
    }
    
    public void setWorkEffortByToWrkEffortId(WorkEffort workEffortByToWrkEffortId)
    {
        this.workEffortByToWrkEffortId = workEffortByToWrkEffortId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_WRK_EFFORT_ID")
    @JsonIgnore
    public WorkEffort getWorkEffortByFromWrkEffortId() 
    {
        return this.workEffortByFromWrkEffortId;
    }
    
    public void setWorkEffortByFromWrkEffortId(WorkEffort workEffortByFromWrkEffortId)
    {
        this.workEffortByFromWrkEffortId = workEffortByFromWrkEffortId;
    }
    
    @Column(name="EFFECTIVE_FROM_DT", nullable=false, length=10)
    public Date getEffectiveFromDt() 
    {
        return this.effectiveFromDt;
    }
    
    public void setEffectiveFromDt(Date effectiveFromDt) 
    {
        this.effectiveFromDt = effectiveFromDt;
    }
	
    @Column(name="EFFECTIVE_THRU_DT", length=10)
    public Date getEffectiveThruDt() 
    {
        return this.effectiveThruDt;
    }
    
    public void setEffectiveThruDt(Date effectiveThruDt) 
    {
        this.effectiveThruDt = effectiveThruDt;
    }
	
    

}
