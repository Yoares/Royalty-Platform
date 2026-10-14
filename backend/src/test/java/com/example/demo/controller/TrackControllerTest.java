package com.example.demo.controller;

import com.example.demo.service.TrackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrackController.class)
class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Fakes the database service so the test doesn't crash looking for PostgreSQL
    @MockBean
    private TrackService trackService;

    @Test
    void whenPostInvalidTrack_thenReturn400AndErrorMessages() throws Exception {
        String invalidJson = """
                {
                    "title": "",
                    "primaryArtist": "ARES.thereal",
                    "producerSplitPercentage": -15.0
                }
                """;

        mockMvc.perform(post("/api/tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors.length()").value(2));
    }
}