package com.example.BuyNothingAPI.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "exchanges")
public class Exchange extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "exchange_generator")
	@SequenceGenerator(
			name = "exchange_generator",
			sequenceName = "exchange_sequence",
			initialValue = 1000
			)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "STATUS", columnDefinition = "text")
	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void prepareForJSON(Exchange exchange) {
		exchange.getOffer().setExchanges(null);
		exchange.getRequest().setExchanges(null);
	}
}
