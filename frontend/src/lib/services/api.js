const BASE_URL = "http://localhost:8080/api";

// Handle the response and throw errors if necessary
async function handleResponse(response) {
    if (!response.ok) {
        const error = await response.text();
        console.error("API error:", error);
        throw new Error(error || 'Request failed');
    }
    return response.json();
}

// Function to calculate BMR
export async function calculateBMR(params) {
    const query = new URLSearchParams({
        gender: params.gender,
        weight: params.weight.toString(),
        height: params.height.toString(),
        age: params.age.toString(),
    }).toString();

    const response = await fetch(`${BASE_URL}/calculateBMR?${query}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });

    return handleResponse(response);
}

// Function to calculate TDEE
export async function calculateTDEE(params) {
    const query = new URLSearchParams({
        BMR: params.bmr.toString(),
        activityLevel: params.activityLevel,
    }).toString();

    const response = await fetch(`${BASE_URL}/calculateTDEE?${query}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });

    return handleResponse(response);
}
