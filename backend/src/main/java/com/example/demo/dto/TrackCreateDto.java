package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TrackCreateDto {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Primary artist cannot be empty")
    private String primaryArtist;

    @NotNull(message = "Producer split percentage is required")
    @Min(value = 0, message = "Percentage cannot be negative")
    @Max(value = 100, message = "Percentage cannot exceed 100")
    private Double producerSplitPercentage;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getPrimaryArtist() { return primaryArtist; }
    public void setPrimaryArtist(String primaryArtist) { this.primaryArtist = primaryArtist; }
    
    public Double getProducerSplitPercentage() { return producerSplitPercentage; }
    public void setProducerSplitPercentage(Double producerSplitPercentage) { this.producerSplitPercentage = producerSplitPercentage; }
}