/**
 *  Business Logic.
 */
package com.qurion.businesslogic.party.model;

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
 * Person 
 * @author Edward Banfa
 */
@Entity
@Table(name="PERSON"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Person  extends BaseEntity implements java.io.Serializable {
	private Party party;
	private String firstNm;
	private String middleNm;
	private String lastNm;
	private Character gender;
	private Integer height;
	private Integer weigth;
	private String taxPin;
	private String passportNo;
	private Date passportExpDt;

    public Person() {
    }

    public Person(String firstNm, String lastNm, Character gender, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.firstNm = firstNm;
        this.lastNm = lastNm;
        this.gender = gender;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Person(Party party, String firstNm, String middleNm, String lastNm, Character gender, Integer height, Integer weigth, String taxPin, String passportNo, Date passportExpDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.party = party;
        this.firstNm = firstNm;
        this.middleNm = middleNm;
        this.lastNm = lastNm;
        this.gender = gender;
        this.height = height;
        this.weigth = weigth;
        this.taxPin = taxPin;
        this.passportNo = passportNo;
        this.passportExpDt = passportExpDt;
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
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    
    @Column(name="FIRST_NM", nullable=false, length=35)
    public String getFirstNm() 
    {
        return this.firstNm;
    }
    
    public void setFirstNm(String firstNm) 
    {
        this.firstNm = firstNm;
    }
	
    @Column(name="MIDDLE_NM", length=35)
    public String getMiddleNm() 
    {
        return this.middleNm;
    }
    
    public void setMiddleNm(String middleNm) 
    {
        this.middleNm = middleNm;
    }
	
    @Column(name="LAST_NM", nullable=false, length=35)
    public String getLastNm() 
    {
        return this.lastNm;
    }
    
    public void setLastNm(String lastNm) 
    {
        this.lastNm = lastNm;
    }
	
    @Column(name="GENDER", nullable=false, length=1)
    public Character getGender() 
    {
        return this.gender;
    }
    
    public void setGender(Character gender) 
    {
        this.gender = gender;
    }
	
    @Column(name="HEIGHT")
    public Integer getHeight() 
    {
        return this.height;
    }
    
    public void setHeight(Integer height) 
    {
        this.height = height;
    }
	
    @Column(name="WEIGTH")
    public Integer getWeigth() 
    {
        return this.weigth;
    }
    
    public void setWeigth(Integer weigth) 
    {
        this.weigth = weigth;
    }
	
    @Column(name="TAX_PIN", length=15)
    public String getTaxPin() 
    {
        return this.taxPin;
    }
    
    public void setTaxPin(String taxPin) 
    {
        this.taxPin = taxPin;
    }
	
    @Column(name="PASSPORT_NO", length=15)
    public String getPassportNo() 
    {
        return this.passportNo;
    }
    
    public void setPassportNo(String passportNo) 
    {
        this.passportNo = passportNo;
    }
	
    @Column(name="PASSPORT_EXP_DT", length=10)
    public Date getPassportExpDt() 
    {
        return this.passportExpDt;
    }
    
    public void setPassportExpDt(Date passportExpDt) 
    {
        this.passportExpDt = passportExpDt;
    }
	
    

}
