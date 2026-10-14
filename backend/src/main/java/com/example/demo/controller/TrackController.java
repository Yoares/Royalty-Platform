package com.example.demo.controller;

import com.example.demo.model.Track;
import com.example.demo.service.TrackService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.TrackCreateDto;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    // Inject the Service, NOT the Repository
    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public Track createTrack(@Valid @RequestBody TrackCreateDto dto) {
        // Map the DTO data into a real database Entity
        Track trackToSave = new Track(
            dto.getTitle(), 
            dto.getPrimaryArtist(), 
            dto.getProducerSplitPercentage()
        );
        
        return trackService.saveTrack(trackToSave);
    }

    @GetMapping
    public List<Track> getAllTracks() {
        return trackService.getAllTracks();
    }

    // New endpoint to calculate the split
    // Example URL: GET /api/tracks/1/payout?revenue=1000
    @GetMapping("/{id}/payout")
    public String getProducerPayout(@PathVariable Long id, @RequestParam double revenue) {
        double payout = trackService.calculateProducerPayout(id, revenue);
        return String.format("Total Revenue: $%.2f | Producer Payout: $%.2f", revenue, payout);
    }
}