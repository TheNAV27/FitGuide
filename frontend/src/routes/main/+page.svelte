<script lang="ts">
    import '$lib/styles/style.css';
    import Header from "$lib/components/Header.svelte";
    import Footer from "$lib/components/Footer.svelte";
    import { calculateBMR, calculateTDEE } from '$lib/services/api';

    interface BmrFormData {
        gender: 'male' | 'female';
        weight: number;
        height: number;
        age: number;
    }

    interface TdeeFormData {
        bmr: number;
        activityLevel: 'Sedentary' | 'Lightly active' | 'Moderately active' | 'Very active' | 'Extremely active';
    }

    interface TdeeResult {
        tdee: number;
        activityLevel: string;
    }

    let formData: BmrFormData = {
        gender: 'male',
        weight: 70,
        height: 175,
        age: 30,
    };

    let tdeeData: TdeeFormData = {
        bmr: 1500,
        activityLevel: 'Sedentary'
    };

    let bmrResult: { bmr: number };
    let tdeeResult: TdeeResult | null = null;
    let bmrLoading = false;
    let tdeeLoading = false;
    let error: string | null = null;

    // Calculate BMR
    async function calculateBmr(event: Event) {
        event.preventDefault();
        bmrLoading = true;
        error = null;

        try {
            bmrResult = await calculateBMR(formData);
            tdeeData.bmr = bmrResult.bmr; // Set the BMR for TDEE calculation
        } catch (err) {
            error = err instanceof Error ? err.message : 'Unknown error occurred';
            console.error('BMR calculation error:', err);
        } finally {
            bmrLoading = false;
        }
    }

    // Calculate TDEE
    async function calculateTdee(event: Event) {
        event.preventDefault();
        tdeeLoading = true;
        error = null;

        try {
            tdeeResult = await calculateTDEE(tdeeData);
        } catch (err) {
            error = err instanceof Error ? err.message : 'Unknown error occurred';
            console.error('TDEE calculation error:', err);
        } finally {
            tdeeLoading = false;
        }
    }
</script>

<!-- BMR calculator -->
<Header />

<div class="main-container vertical-form">
    <h2>Calculate BMR</h2>
    <form on:submit={calculateBmr}>
        <div class="form-group">
            <label for="gender">Your gender</label>
            <select id="gender" bind:value={formData.gender}>
                <option value="male">Male</option>
                <option value="female">Female</option>
            </select>
        </div>

        <div class="form-group">
            <label for="weight">Weight (kg)</label>
            <input
                    id="weight"
                    type="number"
                    bind:value={formData.weight}
                    required
            />
        </div>

        <div class="form-group">
            <label for="height">Height (cm)</label>
            <input
                    id="height"
                    type="number"
                    bind:value={formData.height}
                    required
            />
        </div>

        <div class="form-group">
            <label for="age">Age</label>
            <input
                    id="age"
                    type="number"
                    bind:value={formData.age}
                    required
            />
        </div>

        <button class="mainButton" type="submit" disabled={bmrLoading}>
            {bmrLoading ? 'Calculating...' : 'Calculate'}
        </button>
    </form>
</div>

{#if error}
    <p>{error}</p>
{/if}
{#if bmrResult}
    <div class="main-container">
        <h2>Your result</h2>
        <p><strong>BMR:</strong> {bmrResult.bmr} kcal/day (calories at rest)</p>
    </div>
{/if}

<!-- TDEE calculator -->

<div class="main-container vertical-form">
    <h2>Calculate TDEE</h2>
    <div class="form group">
        <label for="BMR">Your BMR: </label>
        <input
                id="BMR"
                type="number"
                bind:value={tdeeData.bmr}
                required
        />
    </div>

    <form on:submit={calculateTdee}>
        <div class="form-group">
            <label for="activityLevel">Activity Level</label>
            <select id="activityLevel" bind:value={tdeeData.activityLevel}>
                <option value="Sedentary">Sedentary</option>
                <option value="Lightly active">Lightly active</option>
                <option value="Moderately active">Moderately active</option>
                <option value="Very active">Very active</option>
                <option value="Extremely active">Extremely active</option>
            </select>
        </div>

        <button class="mainButton" type="submit" disabled={tdeeLoading}>
            {tdeeLoading ? 'Calculating...' : 'Calculate'}
        </button>
    </form>
</div>

{#if error}
    <p>{error}</p>
{/if}
{#if tdeeResult}
    <div class="main-container">
        <h2>Your TDEE Result</h2>
        <p><strong>Activity Level:</strong> {tdeeResult.activityLevel}</p>
        <p><strong>TDEE:</strong> {tdeeResult.tdee.toFixed(2)} kcal/day (maintenance calories)</p>
    </div>
{/if}

<Footer />
