const BASE_URL = "http://localhost:8080/api"

    //BMR API
export async function calculateBMR(params) {
    const queryString = new URLSearchParams(params).toString();
    const response = await fetch(`${BASE_URL}/calculateBMR?${queryString}`, {
        method: 'GET',
        credentials: 'include', // if using cookies/sessions
        headers: {
            'Accept': 'application/json',
        }
    });
    return handleResponse(response);
}

    //TDEE API
export async function calculateTDEE(bmr, activityLevel) {
    const response = await fetch(`${BASE_URL}/calculateTDEE?bmr=${bmr}&activityLevel=${activityLevel}`, {
        method: 'GET',
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
        }
    });
    return handleResponse(response);
}
export async function postData(endpoint, data) {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return handleResponse(response);
}

