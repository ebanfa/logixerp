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
import com.qurion.businesslogic.hr.model.Position;
import com.qurion.businesslogic.hr.model.Position;

import java.util.Date;
import java.util.Date;

/**
 * PositionReportingStructure 
 * @author Edward Banfa
 */
@Entity
@Table(name="POSITION_REPORTING_STRUCTURE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PositionReportingStructure  extends BaseEntity implements java.io.Serializable {
	private Position positionByFromPositionId;
	private Position positionByToPositionId;
	private String comment;
	private Character primaryFg;
	private Date fromDt;
	private Date toDt;

    public PositionReportingStructure() {
    }

    public PositionReportingStructure(Character primaryFg, Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.primaryFg = primaryFg;
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PositionReportingStructure(Position positionByFromPositionId, Position positionByToPositionId, String comment, Character primaryFg, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.positionByFromPositionId = positionByFromPositionId;
        this.positionByToPositionId = positionByToPositionId;
        this.comment = comment;
        this.primaryFg = primaryFg;
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
    @JoinColumn(name="FROM_POSITION_ID")
    @JsonIgnore
    public Position getPositionByFromPositionId() 
    {
        return this.positionByFromPositionId;
    }
    
    public void setPositionByFromPositionId(Position positionByFromPositionId)
    {
        this.positionByFromPositionId = positionByFromPositionId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_POSITION_ID")
    @JsonIgnore
    public Position getPositionByToPositionId() 
    {
        return this.positionByToPositionId;
    }
    
    public void setPositionByToPositionId(Position positionByToPositionId)
    {
        this.positionByToPositionId = positionByToPositionId;
    }
    
    @Column(name="COMMENT", length=150)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
	
    @Column(name="PRIMARY_FG", nullable=false, length=1)
    public Character getPrimaryFg() 
    {
        return this.primaryFg;
    }
    
    public void setPrimaryFg(Character primaryFg) 
    {
        this.primaryFg = primaryFg;
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
