<script lang="ts">

    import {goto} from "$app/navigation";

    function redirectHome(){
        goto('/')
    }
    function redirectRegister(){
        goto('/')
    }
</script>


<div class = "mainhome-container">
    <div class="header">
        <h1 class = "fitguide-home" on:click={redirectHome}>Welcome to FitGuide!</h1>
    </div>

</div>