<script>
    import '$lib/styles/style.css';
    import Header from "$lib/components/Header.svelte";
    import Footer from "$lib/components/Footer.svelte";
    import { authService } from '$lib/services/authService';
    import {goto} from "$app/navigation";

    let email = '';
    let password = '';
    let error = '';
    let isLoading = false;

    async function handleSubmit(){
        isLoading = true;
        error = '';
        try{
            await authService.register(email, password);
            goto('/login');
        }catch(err){
            error = typeof err === 'string' ? err : 'Registration failed';
        }finally{
            isLoading = false;
        }
    }

</script>


<Header />
<div class="main-container">
    <h2>Register</h2>
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
                    minlength="6"
            />
        </div>

        <button type="submit" disabled={isLoading}>
            {isLoading ? 'Registering...' : 'Register'}
        </button>
    </form>

    <p>Already have an account? <a href="/login">Login here</a></p>
</div>



<Footer />