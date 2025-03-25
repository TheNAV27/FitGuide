<script>
    import {getData,postData} from "$lib/api.js";

    let data = [];

    async function loadData(){
        data = await getData();
    }
    async function handleSubmit(){
        await postData({example : 'value'});
        await loadData();
    }
</script>

<button on:click = {loadData}>Load Data</button>
<button on:click = {handleSubmit}>Submit Data</button>
