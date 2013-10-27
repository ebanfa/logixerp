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
import com.qurion.businesslogic.workeffort.model.Deliverable;
import com.qurion.businesslogic.workeffort.model.WorkEffort;

/**
 * WorkEffortDeliverableProduced 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_DELIVERABLE_PRODUCED"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortDeliverableProduced  extends BaseEntity implements java.io.Serializable {
	private Deliverable deliverable;
	private WorkEffort workEffort;

    public WorkEffortDeliverableProduced() {
    }

    public WorkEffortDeliverableProduced(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortDeliverableProduced(Deliverable deliverable, WorkEffort workEffort, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.deliverable = deliverable;
        this.workEffort = workEffort;
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
    @JoinColumn(name="WRK_EFFORT_ID")
    @JsonIgnore
    public WorkEffort getWorkEffort() 
    {
        return this.workEffort;
    }
    
    public void setWorkEffort(WorkEffort workEffort)
    {
        this.workEffort = workEffort;
    }
    
    

}
