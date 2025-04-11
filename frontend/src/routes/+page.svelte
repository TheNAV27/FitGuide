<script lang="ts">
    import {json} from "@sveltejs/kit";
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";
    import '$lib/styles/style.css';
    import Header from "$lib/components/Header.svelte";
    import Footer from "$lib/components/Footer.svelte"




    async function redirectToCalculator(){
        goto('/main');
    }

</script>
<svelte:head>
   <title>FitGuide</title>
</svelte:head>

<Header />

    <div class="main-container">
        <h2>About the application</h2>
        <p>This web application is made by Nguyen Anh Vy. This is the perfect web application for tracking the fitness progress, calculating
            daily calories intake, and building your own training plan. </p>
    </div>
<div class="main-container">
    <h2>
        BMR and TDEE
    </h2>
    <p>
        BMR (Basal Metabolic Rate) is the calories your body burns at rest to maintain basic functions like breathing and digestion. It depends on age, gender, weight, and muscle mass.
        TDEE (Total Daily Energy Expenditure) is your total calorie burn in a day, including BMR, exercise, and daily activities. It’s calculated as BMR × Activity Level (sedentary, active, etc.).
        BMR = Minimum calories needed to survive.
        TDEE = Total calories burned in a day.
        To lose weight, eat below TDEE; to maintain, match TDEE; to gain, eat above TDEE. Both help manage diet and fitness goals effectively.
    </p>
    <button class="mainButton" on:click={redirectToCalculator}>BMR and TDEE calculator</button>
</div>

<Footer />








