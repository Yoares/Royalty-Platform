package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String primaryArtist;
    private Double producerSplitPercentage;

    // 1. Default constructor required by JPA
    public Track() {
    }

    // 2. Custom constructor for our own use
    public Track(String title, String primaryArtist, Double producerSplitPercentage) {
        this.title = title;
        this.primaryArtist = primaryArtist;
        this.producerSplitPercentage = producerSplitPercentage;
    }

    // 3. Getters and Setters
    public Long getId() { return id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getPrimaryArtist() { return primaryArtist; }
    public void setPrimaryArtist(String primaryArtist) { this.primaryArtist = primaryArtist; }
    
    public Double getProducerSplitPercentage() { return producerSplitPercentage; }
    public void setProducerSplitPercentage(Double producerSplitPercentage) { this.producerSplitPercentage = producerSplitPercentage; }
}