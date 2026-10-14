package com.example.demo.service;

import com.example.demo.model.Track;
import com.example.demo.repository.TrackRepository;
import org.springframework.stereotype.Service;
import com.example.demo.exception.TrackNotFoundException;


import java.util.List;

@Service
public class TrackService {

    private final TrackRepository trackRepository;

    // Dependency Injection
    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    // --- Core Business Logic ---
    public double calculateProducerPayout(Long trackId, double totalRevenue) {
        // 1. Fetch the track, throw an exception if the ID doesn't exist
        Track track = trackRepository.findById(trackId)
            .orElseThrow(() -> new TrackNotFoundException(trackId));

        // 2. Calculate the monetary cut based on the percentage
        double percentage = track.getProducerSplitPercentage();
        return totalRevenue * (percentage / 100.0);
    }
}