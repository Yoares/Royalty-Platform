package com.example.demo.controller;

import com.example.demo.dto.TrackCreateDto;
import com.example.demo.model.Track;
import com.example.demo.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // <-- ADD THIS EXACT LINE
import java.util.Map;

@RestController
@RequestMapping("/api/tracks")
@CrossOrigin(origins = "*") // <-- THIS UNLOCKS THE BROWSER SECURITY
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<Track> createTrack(@Valid @RequestBody TrackCreateDto trackDto) {
        Track track = new Track();
        track.setTitle(trackDto.getTitle());
        track.setPrimaryArtist(trackDto.getPrimaryArtist());
        track.setProducerSplitPercentage(trackDto.getProducerSplitPercentage());

        return ResponseEntity.ok(trackService.saveTrack(track));
    }

    @GetMapping("/{id}/payout")
    public ResponseEntity<Map<String, String>> calculatePayout(
            @PathVariable Long id,
            @RequestParam double revenue) {
        
        double payout = trackService.calculateProducerPayout(id, revenue);
        
        return ResponseEntity.ok(Map.of(
                "Total Revenue", "$" + String.format("%.2f", revenue),
                "Producer Payout", "$" + String.format("%.2f", payout)
        ));
    }

    @GetMapping
    public ResponseEntity<List<Track>> getAllTracks() {
        return ResponseEntity.ok(trackService.getAllTracks());
    }
}