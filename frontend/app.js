// ==========================================
// 1. ADD NEW TRACK LOGIC (POST REQUEST)
// ==========================================
document.getElementById('trackForm').addEventListener('submit', async function(event) {
    // Stop the browser from reloading the page
    event.preventDefault();

    const outputBox = document.getElementById('output');
    outputBox.textContent = "Sending request to Spring Boot...";

    // Gather the data from the HTML inputs
    const trackData = {
        title: document.getElementById('title').value,
        primaryArtist: document.getElementById('artist').value,
        producerSplitPercentage: parseFloat(document.getElementById('percentage').value)
    };

    try {
        // Fire the asynchronous POST request
        const response = await fetch('http://localhost:8080/api/tracks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(trackData)
        });

        const responseData = await response.json();

        // Update the UI based on success or failure
        if (response.ok) {
            outputBox.style.color = "#00ff88";
            outputBox.textContent = JSON.stringify(responseData, null, 2);
            
            // Trigger the auto-refresh immediately after saving!
            loadTracks(); 
            
            // Clear the form inputs so it's ready for the next track
            document.getElementById('trackForm').reset();
        } else {
            outputBox.style.color = "#ff4444";
            outputBox.textContent = "Error: " + JSON.stringify(responseData.errors || responseData, null, 2);
        }
    } catch (error) {
        outputBox.style.color = "#ff4444";
        outputBox.textContent = "Network Error: Blocked by CORS or API is down.";
        console.error(error);
    }
});

// ==========================================
// 2. CALCULATE PAYOUT LOGIC (GET REQUEST)
// ==========================================
document.getElementById('payoutForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const outputBox = document.getElementById('payoutOutput');
    outputBox.textContent = "Calculating...";

    // Grab the values from the new form
    const trackId = document.getElementById('trackId').value;
    const revenue = document.getElementById('revenue').value;

    try {
        // Build the exact URL dynamically using template literals
        const url = `http://localhost:8080/api/tracks/${trackId}/payout?revenue=${revenue}`;
        
        // Fire the GET request
        const response = await fetch(url);
        
        // Parse and display the result
        if (response.ok) {
            const responseData = await response.json();
            outputBox.style.color = "#00aaff";
            outputBox.textContent = JSON.stringify(responseData, null, 2);
        } else {
            outputBox.style.color = "#ff4444";
            outputBox.textContent = "Error: Track ID not found.";
        }
    } catch (error) {
        outputBox.style.color = "#ff4444";
        outputBox.textContent = "Network Error: Is the API running?";
        console.error(error);
    }
});

// ==========================================
// 3. TRACK CATALOG LOGIC (GET ALL REQUEST)
// ==========================================

// Create a reusable function to fetch and draw the list
async function loadTracks() {
    const trackList = document.getElementById('trackList');
    trackList.innerHTML = "<li>Loading tracks...</li>";

    try {
        const response = await fetch('http://localhost:8080/api/tracks');
        const tracks = await response.json();
        
        // Clear the "Loading..." text
        trackList.innerHTML = "";

        if (tracks.length === 0) {
            trackList.innerHTML = "<li>No tracks found. Add one above!</li>";
            return;
        }

        // Loop through the array and create HTML elements dynamically
        tracks.forEach(track => {
            const li = document.createElement('li');
            li.style.background = "#2a2a2a";
            li.style.margin = "0.5rem 0";
            li.style.padding = "1rem";
            li.style.borderRadius = "4px";
            li.innerHTML = `
                <strong>ID: ${track.id}</strong> - ${track.title} 
                <br>
                <small>Artist: ${track.primaryArtist} | Split: ${track.producerSplitPercentage}%</small>
            `;
            trackList.appendChild(li);
        });
    } catch (error) {
        trackList.innerHTML = "<li style='color: #ff4444;'>Error loading tracks. Is the API running?</li>";
        console.error(error);
    }
}

// Keep the manual refresh button working just in case
document.getElementById('loadTracksBtn').addEventListener('click', loadTracks);

// Automatically load the list as soon as the page opens!
loadTracks();