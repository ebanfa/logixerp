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
import com.qurion.businesslogic.party.model.Party;

import java.util.Date;

/**
 * Resume 
 * @author Edward Banfa
 */
@Entity
@Table(name="RESUME"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Resume  extends BaseEntity implements java.io.Serializable {
	private Party party;
	private Date resumeDt;
	private String resumeTxt;

    public Resume() {
    }

    public Resume(Date resumeDt, String resumeTxt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.resumeDt = resumeDt;
        this.resumeTxt = resumeTxt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Resume(Party party, Date resumeDt, String resumeTxt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.party = party;
        this.resumeDt = resumeDt;
        this.resumeTxt = resumeTxt;
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
    @JoinColumn(name="PERSON_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    
    @Column(name="RESUME_DT", nullable=false, length=10)
    public Date getResumeDt() 
    {
        return this.resumeDt;
    }
    
    public void setResumeDt(Date resumeDt) 
    {
        this.resumeDt = resumeDt;
    }
	
    @Column(name="RESUME_TXT", nullable=false)
    public String getResumeTxt() 
    {
        return this.resumeTxt;
    }
    
    public void setResumeTxt(String resumeTxt) 
    {
        this.resumeTxt = resumeTxt;
    }
	
    

}
