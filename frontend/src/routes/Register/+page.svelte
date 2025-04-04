<script>
    import Header from "$lib/components/Header.svelte";
    import Footer from "$lib/components/Footer.svelte";
    import {goto} from "$app/navigation";

</script>

<Header/>
<div class="main-container">
<h2>Register</h2>
<form>
    <div class="form-group">
        <label for="email">Email</label>
        <input/>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input/>
    </div>

    <button class="mainButton" type="submit">Register
    </button>

</form>

<p>Already have an account? <a href="/Login">Login here</a></p>
</div>

<Footer />