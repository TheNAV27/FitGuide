<script lang="js">

    import {goto} from "$app/navigation";

    function redirectHome(){
        goto('/')
    }
    function redirectLogin(){
        goto('/Login')
    }
</script>


<div class = "mainhome-container">
    <div class="header">
        <h1 class = "fitguide-home" on:click={redirectHome}>Welcome to FitGuide!</h1>
        <button class="mainButton" on:click={redirectLogin}>Login</button>
    </div>

</div>