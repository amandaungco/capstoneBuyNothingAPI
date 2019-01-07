package com.example.BuyNothingAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offers")
public class Offer extends AuditModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "offer_generator")
    @SequenceGenerator(
            name = "offer_generator",
            sequenceName = "offer_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;
    
    @Column(columnDefinition = "text")
    private String status;
    
    @Column(columnDefinition = "text")
    private String category;
    
    @Column(columnDefinition = "text")
    private String description;
    
    @Min(1)
    private int quantity;
    
    @Column(name = "DISTANCE")
    private int distance;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Offerer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,targetEntity = Match.class)
    @JoinTable(name = "MATCHES", joinColumns = { @JoinColumn(name = "OFFER_ID") })
    private Set<Match> matches;

	public Set<Match> getMatches() {
		return this.matches;
	}

	public void setMatches(Set<Match> matches) {
		this.matches = matches;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
        
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
