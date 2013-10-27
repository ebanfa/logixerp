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
import com.qurion.businesslogic.workeffort.model.WorkEffortType;
import com.qurion.businesslogic.workeffort.model.WorkEffortTypeAssociationType;
import com.qurion.businesslogic.workeffort.model.WorkEffortType;

/**
 * WorkEffortTypeAssociation 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_TYPE_ASSOCIATION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortTypeAssociation  extends BaseEntity implements java.io.Serializable {
	private WorkEffortType workEffortTypeByFromWrkEffortTyId;
	private WorkEffortTypeAssociationType workEffortTypeAssociationType;
	private WorkEffortType workEffortTypeByToWrkEffortTyId;

    public WorkEffortTypeAssociation() {
    }

    public WorkEffortTypeAssociation(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortTypeAssociation(WorkEffortType workEffortTypeByFromWrkEffortTyId, WorkEffortTypeAssociationType workEffortTypeAssociationType, WorkEffortType workEffortTypeByToWrkEffortTyId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.workEffortTypeByFromWrkEffortTyId = workEffortTypeByFromWrkEffortTyId;
        this.workEffortTypeAssociationType = workEffortTypeAssociationType;
        this.workEffortTypeByToWrkEffortTyId = workEffortTypeByToWrkEffortTyId;
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
    @JoinColumn(name="FROM_WRK_EFFORT_TY_ID")
    @JsonIgnore
    public WorkEffortType getWorkEffortTypeByFromWrkEffortTyId() 
    {
        return this.workEffortTypeByFromWrkEffortTyId;
    }
    
    public void setWorkEffortTypeByFromWrkEffortTyId(WorkEffortType workEffortTypeByFromWrkEffortTyId)
    {
        this.workEffortTypeByFromWrkEffortTyId = workEffortTypeByFromWrkEffortTyId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ASSOC_TY_ID")
    @JsonIgnore
    public WorkEffortTypeAssociationType getWorkEffortTypeAssociationType() 
    {
        return this.workEffortTypeAssociationType;
    }
    
    public void setWorkEffortTypeAssociationType(WorkEffortTypeAssociationType workEffortTypeAssociationType)
    {
        this.workEffortTypeAssociationType = workEffortTypeAssociationType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_WRK_EFFORT_TY_ID")
    @JsonIgnore
    public WorkEffortType getWorkEffortTypeByToWrkEffortTyId() 
    {
        return this.workEffortTypeByToWrkEffortTyId;
    }
    
    public void setWorkEffortTypeByToWrkEffortTyId(WorkEffortType workEffortTypeByToWrkEffortTyId)
    {
        this.workEffortTypeByToWrkEffortTyId = workEffortTypeByToWrkEffortTyId;
    }
    
    

}
