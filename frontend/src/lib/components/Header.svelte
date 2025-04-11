<script lang="js">

    import {goto} from "$app/navigation";

    function redirectHome(){
        goto('/')
    }

</script>


<div class = "mainhome-container">
    <div class="header">
        <h1 class = "fitguide-home">Welcome to FitGuide!</h1>
        <button class="mainButton" on:click={redirectHome}>Home</button>
    </div>

</div>