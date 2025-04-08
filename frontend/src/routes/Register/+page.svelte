<script>
    import Header from "$lib/components/Header.svelte";
    import Footer from "$lib/components/Footer.svelte";
    import { goto } from "$app/navigation";
    import { authStore, authService } from "$lib/services/api";

    let username = '';
    let password = '';
    let email = '';
    let errors = {
        username: '',
        password: '',
        email: '',
        general: ''
    };

    async function handleSubmit(event) {
        event.preventDefault();

        // Reset errors
        errors = { username: '', password: '', email: '', general: '' };

        // Basic validation
        if (!username.trim()) errors.username = 'Username is required';
        if (!password) errors.password = 'Password is required';
        if (!email.includes('@')) errors.email = 'Valid email is required';

        if (errors.username || errors.password || errors.email) {
            return;
        }

        authStore.update(state => ({ ...state, loading: true }));

        try {
            await authService.register(username, password);
            goto('/'); // Redirect after successful registration
        } catch (error) {
            errors.general = error.message || 'Registration failed';
            authStore.update(state => ({ ...state, loading: false }));
        }
    }

    function redirectToLogin(event) {
        event.preventDefault();
        goto("/Login");
    }
</script>

<Header />

<div class="main-container">
    <h2>Register</h2>

    {#if errors.general}
        <div class="error-message">{errors.general}</div>
    {/if}

    <form on:submit={handleSubmit}>
        <div class="form-group">
            <label for="email">Email</label>
            <input
                    type="email"
                    id="email"
                    bind:value={email}
                    class:error={errors.email}
            />
            {#if errors.email}
                <div class="error">{errors.email}</div>
            {/if}
        </div>

        <div class="form-group">
            <label for="username">Username</label>
            <input
                    type="text"
                    id="username"
                    bind:value={username}
                    class:error={errors.username}
            />
            {#if errors.username}
                <div class="error">{errors.username}</div>
            {/if}
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input
                    type="password"
                    id="password"
                    bind:value={password}
                    class:error={errors.password}
            />
            {#if errors.password}
                <div class="error">{errors.password}</div>
            {/if}
        </div>

        <button class="mainButton" type="submit" disabled={$authStore.loading}>
            {#if $authStore.loading}
                Registering...
            {:else}
                Register
            {/if}
        </button>
    </form>

    <p>Already have an account? <a href="/Login" on:click|preventDefault={redirectToLogin}>Login here</a></p>
</div>

<Footer />