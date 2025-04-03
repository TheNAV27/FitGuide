const BASE_URL = "http://localhost:8080";

async function handleResponse(response) {
    if (!response.ok) {
        const error = await response.text();
        throw new Error(error || 'Request failed');
    }
    return response.json();
}

export async function calculateBMR(params) {
    const query = new URLSearchParams(params).toString();
    const response = await fetch(`${BASE_URL}/api/calculateBMR?${query}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    return handleResponse(response);
}

export async function calculateTDEE(bmr, activityLevel) {
    const response = await fetch(`${BASE_URL}/api/calculateTDEE?bmr=${bmr}&activityLevel=${activityLevel}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    return handleResponse(response);
}