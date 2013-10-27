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
import com.qurion.businesslogic.hr.model.PositionType;
import com.qurion.businesslogic.hr.model.ResponsibilityType;

import java.util.Date;
import java.util.Date;

/**
 * ValidResponsibility 
 * @author Edward Banfa
 */
@Entity
@Table(name="VALID_RESPONSIBILITY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ValidResponsibility  extends BaseEntity implements java.io.Serializable {
	private PositionType positionType;
	private ResponsibilityType responsibilityType;
	private String comment;
	private Date fromDt;
	private Date toDt;

    public ValidResponsibility() {
    }

    public ValidResponsibility(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ValidResponsibility(PositionType positionType, ResponsibilityType responsibilityType, String comment, Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.positionType = positionType;
        this.responsibilityType = responsibilityType;
        this.comment = comment;
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
    @JoinColumn(name="POSITION_TY_ID")
    @JsonIgnore
    public PositionType getPositionType() 
    {
        return this.positionType;
    }
    
    public void setPositionType(PositionType positionType)
    {
        this.positionType = positionType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESPONSIBILITY_TY_ID")
    @JsonIgnore
    public ResponsibilityType getResponsibilityType() 
    {
        return this.responsibilityType;
    }
    
    public void setResponsibilityType(ResponsibilityType responsibilityType)
    {
        this.responsibilityType = responsibilityType;
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
