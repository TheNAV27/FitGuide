const BASE_URL = "http://localhost:8080"

export async function getData (){
    const response = await fetch(`${BASE_URL}/users`);
    return await response.json();
}
export async function postData(data){
    const response = await fetch (`${BASE_URL}/users`,{
        method : 'POST',
        headers: {
            'Content-Type':'application/json',
        },
        body: JSON.stringify(data)});
    return await response.json();

}

