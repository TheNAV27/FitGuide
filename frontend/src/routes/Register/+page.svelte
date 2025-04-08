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
        <label for="name">username</label>
        <input type="text" id="name" name="name" required>
        <div id="nameError" class="error"></div>
    </div>

    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
        <div id="passwordError" class="error"></div>
    </div>
    <button class="mainButton" type="submit">Register
    </button>

</form>

<p>Already have an account? <a href="/Login">Login here</a></p>
</div>

<Footer />