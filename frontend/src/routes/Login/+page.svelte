<script>
    import '$lib/styles/style.css';
    import Header from "$lib/components/Header.svelte";
    import Footer from "$lib/components/Footer.svelte";
    import {goto} from "$app/navigation";
    import {authStore,authService} from "$lib/services/api.js";

    async function redirectRegister(){
        goto("/Register")
    }
    let username = '';
    let password = '';
    let error = '';

    async function handleLogin(event){
        event.preventDefault();
        try{
            await authService.login(username,password);
            goto('/');
        }catch(error){
            error = err.message || 'Login failed';
        }
    }
</script>
<Header/>
<div class="main-container">
    {#if error}
        <div class="error-message">{error}</div>
    {/if}
<form on:submit|preventDefault={handleLogin}>
    <label for="username">Username</label>
    <input
            id="username"
            type="text"
            bind:value={username}
            required
            placeholder="Enter your username"
    >
    <br>

    <label for="password">Password</label>
    <input
            id="password"
            type="password"
            bind:value={password}
            required
            placeholder="Enter your password"
    >
    <br>

    <button class="mainButton" type="submit" disabled={$authStore.loading}>
        {#if $authStore.loading}
            Logging in...
        {:else}
            Login
        {/if}
    </button>
</form>

<p>Don't have an account? <a href="/Register" on:click|preventDefault={redirectRegister}>Register here</a></p>
</div>

<Footer />



<Footer />