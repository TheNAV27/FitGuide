<script>
    import { authService } from '$lib/services/authService';
    import { goto } from '$app/navigation';
    import '$lib/styles/style.css';
    import Header from "$lib/components/Header.svelte";
    import Footer from "$lib/components/Footer.svelte";

    let email = '';
    let password = '';
    let error = '';
    let isLoading = false;

    async function handleSubmit() {
        isLoading = true;
        error = '';
        try {
            await authService.login(email, password);
            goto('/main');
        } catch (err) {
            error = typeof err === 'string' ? err : 'Login failed';
        } finally {
            isLoading = false;
        }
    }
</script>
<Header/>
<div class="main-container">
    <h1>Login</h1>

    {#if error}
        <div class="error">{error}</div>
    {/if}

    <form on:submit|preventDefault={handleSubmit}>
        <div class="form-group">
            <label for="email">Email</label>
            <input
                    type="email"
                    id="email"
                    bind:value={email}
                    required
            />
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input
                    type="password"
                    id="password"
                    bind:value={password}
                    required
            />
        </div>

        <button type="submit" disabled={isLoading}>
            {isLoading ? 'Logging in...' : 'Login'}
        </button>
    </form>

    <p>Don't have an account? <a href="/register">Register here</a></p>
</div>

<Footer/>