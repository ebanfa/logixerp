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
import com.qurion.businesslogic.party.model.PartyRole;
import com.qurion.businesslogic.hr.model.PayHistory;
import com.qurion.businesslogic.party.model.PartyRole;
import com.qurion.businesslogic.invoice.model.Payment;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.hr.model.PerformanceReviewItem;

/**
 * PerformanceReview 
 * @author Edward Banfa
 */
@Entity
@Table(name="PERFORMANCE_REVIEW"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PerformanceReview  extends BaseEntity implements java.io.Serializable {
	private Position position;
	private PartyRole partyRoleByForRoleId;
	private PayHistory payHistory;
	private PartyRole partyRoleByFromRoleId;
	private Payment payment;
	private String comments;
	private Date fromDt;
	private Date toDt;
	private Set<PerformanceReviewItem> performanceReviewItems;

    public PerformanceReview() {
    }

    public PerformanceReview(Date fromDt, Date toDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PerformanceReview(Position position, PartyRole partyRoleByForRoleId, PayHistory payHistory, PartyRole partyRoleByFromRoleId, Payment payment, String comments, Date fromDt, Date toDt, Set performanceReviewItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.position = position;
        this.partyRoleByForRoleId = partyRoleByForRoleId;
        this.payHistory = payHistory;
        this.partyRoleByFromRoleId = partyRoleByFromRoleId;
        this.payment = payment;
        this.comments = comments;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.performanceReviewItems = performanceReviewItems;
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
    @JoinColumn(name="POSITION_ID")
    @JsonIgnore
    public Position getPosition() 
    {
        return this.position;
    }
    
    public void setPosition(Position position)
    {
        this.position = position;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FOR_ROLE_ID")
    @JsonIgnore
    public PartyRole getPartyRoleByForRoleId() 
    {
        return this.partyRoleByForRoleId;
    }
    
    public void setPartyRoleByForRoleId(PartyRole partyRoleByForRoleId)
    {
        this.partyRoleByForRoleId = partyRoleByForRoleId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="HISTORY_ID")
    @JsonIgnore
    public PayHistory getPayHistory() 
    {
        return this.payHistory;
    }
    
    public void setPayHistory(PayHistory payHistory)
    {
        this.payHistory = payHistory;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ROLE_ID")
    @JsonIgnore
    public PartyRole getPartyRoleByFromRoleId() 
    {
        return this.partyRoleByFromRoleId;
    }
    
    public void setPartyRoleByFromRoleId(PartyRole partyRoleByFromRoleId)
    {
        this.partyRoleByFromRoleId = partyRoleByFromRoleId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAYCHECK_ID")
    @JsonIgnore
    public Payment getPayment() 
    {
        return this.payment;
    }
    
    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }
    
    @Column(name="COMMENTS", length=150)
    public String getComments() 
    {
        return this.comments;
    }
    
    public void setComments(String comments) 
    {
        this.comments = comments;
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
	
    @Column(name="TO_DT", nullable=false, length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="performanceReview")
    @JsonIgnore
    public Set<PerformanceReviewItem> getPerformanceReviewItems() 
    {
        return this.performanceReviewItems;
    }
    
    public void setPerformanceReviewItems(Set<PerformanceReviewItem> performanceReviewItems) 
    {
        this.performanceReviewItems = performanceReviewItems;
    }			
		
    

}
