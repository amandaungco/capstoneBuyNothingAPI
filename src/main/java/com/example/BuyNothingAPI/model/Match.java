package com.example.BuyNothingAPI.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "matches")

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
	 public Long getId() {
	        return Id;
	    }
	 
	    public void setId(Long Id) {
	        this.Id = Id;
	    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OFFER_ID")  
    private Offer offer;
    public Offer getOffer() {
        return offer;
    }
 
    public void setOffer(Offer offer) {
        this.offer = offer;
    }
 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REQUEST_ID") 
    private Request request;
    public Request getRequest() {
        return request;
    }
 
    public void setRequest(Request request) {
        this.request = request;
    }
    
    @Column(name = "DISTANCE", columnDefinition = "text")
    private int distance;
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
