package com.example.BuyNothingAPI.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MatchId {
	
	private Offer offer;
    private Request request;

	@ManyToOne
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@ManyToOne
	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchId that = (MatchId) o;

        if (offer != null ? !offer.equals(that.offer) : that.offer != null) return false;
        if (request != null ? !request.equals(that.request) : that.request != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (offer != null ? offer.hashCode() : 0);
        result = 31 * result + (request != null ? request.hashCode() : 0);
        return result;
    }
    

}
