package com.example.BuyNothingAPI.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "matches")
@AssociationOverrides({
	@AssociationOverride(name = "pk.offer", 
		joinColumns = @JoinColumn(name = "OFFER_ID")),
	@AssociationOverride(name = "pk.request", 
		joinColumns = @JoinColumn(name = "REQUEST_ID")) })
public class Match extends AuditModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "match_generator")
    @SequenceGenerator(
            name = "match_generator",
            sequenceName = "match_sequence",
            initialValue = 1000
    )
    @Column(name = "ID", unique = true, nullable = false)
	private Long Id;
	
	private Date createdDate;
    private int distance;
	
	
//	@Transient
//	public Offer getOffer() {
//		return getPk().getOffer();
//	}
//
//	public void setOffer(Offer offer) {
//		getPk().setOffer(offer);
//	}

//	@Transient
//	public Request getRequest() {
//		return getPk().getRequest();
//	}
//
//	public void setRequest(Request request) {
//		getPk().setRequest(request);
//	}
	
	


	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", nullable = false, length = 10)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	 
    
    @Column(name = "DISTANCE", columnDefinition = "text")
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
//        
//    public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//
//		Match that = (Match) o;
//
//		if (getPk() != null ? !getPk().equals(that.getPk())
//				: that.getPk() != null)
//			return false;
//
//		return true;
//	}
//
//	public int hashCode() {
//		return (getPk() != null ? getPk().hashCode() : 0);
//	}

}
