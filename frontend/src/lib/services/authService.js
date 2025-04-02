import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth';

export const authService = {
    async register(email, password) {
        try {
            const response = await axios.post(`${API_URL}/register`, {email, password});
            return response.data;
        } catch (error) {
            throw error.response?.data || error.message;
        }
    },
    async login(email, password) {
        try {
            const response = await axios.post(`${API_URL}/login`, {email, password});
            if (response.data.token) {
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data
        } catch (error) {
            throw error.response?.data || error.message;
        }
    },
    logout() {
        localStorage.removeItem('user');
    },
    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }
};
