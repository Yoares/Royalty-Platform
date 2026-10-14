package com.example.demo.exception;

public class TrackNotFoundException extends RuntimeException {
    public TrackNotFoundException(Long id) {
        super("Track not found with ID: " + id);
    }
}