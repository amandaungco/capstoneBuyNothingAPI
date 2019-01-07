package com.example.BuyNothingAPI.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "requests")
public class Request extends AuditModel {
	

    @Id
    @GeneratedValue(generator = "request_generator")
    @SequenceGenerator(
            name = "request_generator",
            sequenceName = "request_sequence",
            initialValue = 1000
    )
    @Column(name = "REQUEST_ID", unique = true, nullable = false)
    private Long requestId;
    
    public Long getrequestId() {
        return requestId;
    }

    public void setrequestId(Long requestId) {
        this.requestId = requestId;
    }

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "TITLE", nullable = false)
    private String title;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name = "STATUS", nullable = false, columnDefinition = "text")
    private String status;
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name = "CATEGORY", columnDefinition = "text")
    private String category;
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    @Min(1)
    @Column(name = "QUANTITY")
    private int quantity;
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Column(name = "DISTANCE", columnDefinition = "text")
    private int distance;
    
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
       
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requester_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,targetEntity = Match.class)
    @JoinTable(name = "MATCHES", joinColumns = { @JoinColumn(name = "REQUEST_ID") })
    private Set<Match> matches;
    
	public Set<Match> getMatches() {
		return this.matches;
	}

	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}

}
