document.getElementById('payoutForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const outputBox = document.getElementById('payoutOutput');
    outputBox.textContent = "Calculating...";

    // 1. Grab the values from the new form
    const trackId = document.getElementById('trackId').value;
    const revenue = document.getElementById('revenue').value;

    try {
        // 2. Build the exact URL dynamically using template literals
        const url = `http://localhost:8080/api/tracks/${trackId}/payout?revenue=${revenue}`;
        
        // 3. Fire the GET request
        const response = await fetch(url);
        
        // 4. Parse and display the result
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