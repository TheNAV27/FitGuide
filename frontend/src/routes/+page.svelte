<script lang="ts">
    import {json} from "@sveltejs/kit";
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";

    interface User{
        id : number;
        name : string;
        email: string;
    }

    let users : User[] = [];
    let isLoading = true;
    let error : string| null=null;
    let selectedUser: User | null = null;

    onMount(async () => {
        await fetchUsers();
    });

    async function fetchUsers(){

        try{
            const response = await fetch('http://localhost:8080/users');
            if(!response.ok){
                throw new Error('HTTP error! status: $(response.status)');
            }
            users = await response.json();
        }
        catch (err) {
            error = err instanceof Error ? err.message : 'Unknown error';
            console.error('Error fetching data:',err);
        }finally{
            isLoading = false;
        }
    }
    async function fetchUserByID(id : number){
        try{
            const response = await fetch(`http://localhost:8080/users/${id}`)
            if(!response.ok){
                throw new Error(`HTTP error! status: ${response.status}`);
                selectedUser = await response.json();

            }
        }catch(err){
            error = err instanceof Error ? err.message : 'Unknown error';
            console.error(err);
        }
    }
    async function redirectToHome(){
        goto('/main')
    }

</script>

{#if isLoading}
    <p>Loading data...</p>

    {:else if error}
    <p> Error :{error}</p>

    {:else}
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>ID</th>
            <th>email</th>
            <th>
                <button on:click={redirectToHome}>Home page</button>
            </th>
        </tr>
        </thead>
        <tbody>
        {#each users as user}
            <tr>
                <td>{user.name}</td>
                <td>{user.id}</td>
                <td>{user.email}</td>
            </tr>
        {/each}
        </tbody>
    </table>
    {#if selectedUser}
        <div class="user-details">
            <h2>User Details</h2>
            <div>
                <p><strong>ID:</strong> {selectedUser.id}</p>
                <p><strong>Name:</strong> {selectedUser.name}</p>
                <p><strong>Email:</strong> {selectedUser.email}</p>
            </div>
        </div>
    {/if}

    {/if}
