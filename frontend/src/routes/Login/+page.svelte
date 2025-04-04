<script>
    import '$lib/styles/style.css';
    import Header from "$lib/components/Header.svelte";
    import Footer from "$lib/components/Footer.svelte";
    import {goto} from "$app/navigation";

    async function redirectRegister(){
        goto("/Register")
    }
</script>
<Header/>
<div class="main-container">
        <h2>Login</h2>
        <form>
                <label for="email">Email</label>
                <input>
            <br>
                <label for="password">Password</label>
                <input>
            <br>

            <button class ="mainButton" type="submit">Login
            </button>
        </form>

        <p>Don't have an account? <a href="/Register">Register here</a></p>
    </div>


<Footer />