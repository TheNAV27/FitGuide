import { error } from "@sveltejs/kit";
import { writable } from 'svelte/store';
import { browser } from '$app/environment';

const BASE_URL = "http://localhost:8080/api";

// Auth Store and Service
export const authStore = writable({
    user: null,
    isAuthenticated: false,
    loading: false,
    error: null
});

export const authService = {
    register: async (username, password) => {
        try {
            const response = await fetch(`${BASE_URL}/auth/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: new URLSearchParams({ username, password }),
                credentials: 'include'
            });
            const data = await handleResponse(response);
            authStore.update(state => ({ ...state, isAuthenticated: true, loading: false }));
            return data;
        } catch (error) {
            authStore.update(state => ({ ...state, error: error.message, loading: false }));
            throw error;
        }
    },
    login: async (username, password) => {
        authStore.update(state => ({ ...state, loading: true, error: null }));
        try {
            const response = await fetch(`${BASE_URL}/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: new URLSearchParams({ username, password }),
                credentials: 'include'
            });
            const data = await handleResponse(response);
            authStore.update(state => ({ ...state, isAuthenticated: true, loading: false }));
            return data;
        } catch (error) {
            authStore.update(state => ({ ...state, error: error.message, loading: false }));
            throw error;
        }
    },
    logout: async () => {
        authStore.update(state => ({ ...state, loading: true }));
        try {
            const response = await fetch(`${BASE_URL}/auth/logout`, {
                method: 'GET',
                credentials: 'include'
            });
            await handleResponse(response);
            authStore.update(state => ({
                user: null,
                isAuthenticated: false,
                loading: false
            }));
        } catch (error) {
            authStore.update(state => ({ ...state, loading: false }));
            throw error;
        }
    }
};

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

    const response = await fetch(`${BASE_URL}/calculate/calculateBMR?${query}`, {
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

    const response = await fetch(`${BASE_URL}/calculate/calculateTDEE?${query}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
    });

    return handleResponse(response);
}