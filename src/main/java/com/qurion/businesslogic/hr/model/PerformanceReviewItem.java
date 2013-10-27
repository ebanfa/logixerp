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
import com.qurion.businesslogic.hr.model.PerformanceReview;
import com.qurion.businesslogic.hr.model.PerformanceReviewItemType;

/**
 * PerformanceReviewItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="PERFORMANCE_REVIEW_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PerformanceReviewItem  extends BaseEntity implements java.io.Serializable {
	private PerformanceReview performanceReview;
	private PerformanceReviewItemType performanceReviewItemType;
	private Integer ratingTyId;
	private String comment;

    public PerformanceReviewItem() {
    }

    public PerformanceReviewItem(Integer ratingTyId, String comment, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.ratingTyId = ratingTyId;
        this.comment = comment;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PerformanceReviewItem(PerformanceReview performanceReview, PerformanceReviewItemType performanceReviewItemType, Integer ratingTyId, String comment, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.performanceReview = performanceReview;
        this.performanceReviewItemType = performanceReviewItemType;
        this.ratingTyId = ratingTyId;
        this.comment = comment;
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
    @JoinColumn(name="REVIEW_ID")
    @JsonIgnore
    public PerformanceReview getPerformanceReview() 
    {
        return this.performanceReview;
    }
    
    public void setPerformanceReview(PerformanceReview performanceReview)
    {
        this.performanceReview = performanceReview;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REVIEW_ITEM_TY_ID")
    @JsonIgnore
    public PerformanceReviewItemType getPerformanceReviewItemType() 
    {
        return this.performanceReviewItemType;
    }
    
    public void setPerformanceReviewItemType(PerformanceReviewItemType performanceReviewItemType)
    {
        this.performanceReviewItemType = performanceReviewItemType;
    }
    
    @Column(name="RATING_TY_ID", nullable=false)
    public Integer getRatingTyId() 
    {
        return this.ratingTyId;
    }
    
    public void setRatingTyId(Integer ratingTyId) 
    {
        this.ratingTyId = ratingTyId;
    }
	
    @Column(name="COMMENT", nullable=false, length=150)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
	
    

}
