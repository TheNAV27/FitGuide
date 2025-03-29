<script lang="ts">
    import '$lib/styles/style.css';


    interface BmrFormData{
        gender : 'male' | 'female';
        weight : number;
        height: number;
        age: number;
    }

    interface resultBMR{
        bmr : number;
    }
    let formData: BmrFormData = {
        gender: 'male',
        weight: 70,
        height: 175,
        age: 30,
    };
    let result: resultBMR | null = null;
    let loading = false;
    let error: string | null = null;

    async function calculateBmr(event : Event){
        event.preventDefault();
        loading = true;
        error = null;

        try{
            const params = new URLSearchParams();
            params.append('gender',formData.gender)
            params.append('weight',formData.weight.toString())
            params.append('height',formData.height.toString())
            params.append('age',formData.age.toString())

            const response = await fetch(`http://localhost:8080/users/calculate?${params.toString()}`);

            if(!response.ok){
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            result = await response.json();
        } catch(err){
            error = err instanceof Error ? err.message : 'Unknown error occurred';
            console.error('Calculation error:', err);
        }finally{
            loading = false;
        }
    }

</script>

<h1>
    Welcome to FitGuide
</h1>
<div class = "bmrcalculator-container">
    <h2>
        Calculate BMR
    </h2>
    <form on:submit={calculateBmr}>
        <label for = "gender">Your gender</label>
        <select id="gender" bind:value={formData.gender}>
            <option value ="male">Male</option>
            <option value ="female">Female</option>
        </select>
        <label for ="weight">Weight(kg): </label>
        <input
            id = "weight"
            type = "number"
            bind:value={formData.weight}
            required
            />
        <label for="height">Height (cm): </label>
        <input
            id = "height"
            type = "number"
            bind:value={formData.height}
            required
            />
        <label for="age">Age: </label>
        <input
            id = "age"
            type = "number"
            bind:value={formData.age}
            required
            />
        <br>
        <button type="submit" disabled={loading}>
            {loading ? 'Calculating...':'Calculate'}
        </button>
    </form>
</div>
{#if error}
    <p>{error}</p>
{/if}
{#if result}
    <h2>Your result</h2>
    <p><strong>BMR:</strong>{result.bmr} kcal/day (calories at rest)</p>
{/if}





