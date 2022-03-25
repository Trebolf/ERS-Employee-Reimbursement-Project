
let user;

window.onload = async function () {
    let response = await fetch("http://localhost:4242/check-session");

    let responseBody = await response.json();

    if(responseBody.success == false) {
        window.location = "../";
    }

    if (responseBody.data.userRoleId == 1) {
        window.location = "../reimbursementlistall/"
    }
    
    user = responseBody.data;

    console.log(user)

    getName();
    getAllListsGivenUsername();
}

async function getName() {

    let name = document.createElement("div");
    name.className = "name"

    name.innerHTML = `Welcome, ${user.firstName} ${user.lastName}!`

    document.getElementById("welcome").appendChild(name);
}

async function getAllListsGivenUsername(){
    
    let response = await fetch(`http://localhost:4242/list/${user.username}`);

    let responseBody = await response.json();

    console.log(responseBody)

    let lists = responseBody.data;

    lists.forEach(list => {
        createList(list)
    });
}

function createList(list){

    //let container = document.getElementsByClassName("list-container");

    let li = document.createElement("li");
    li.className = "list-group-item"

    let li2 = document.createElement("li");
    li2.className = "list-group-item disabled"

    let li3 = document.createElement("li");
    li3.className = "list-item"


    li.innerHTML = `${list.status} || $${list.amount} || ${list.type} || ${list.description}`
    li2.innerHTML = `${list.status} || $${list.amount} || ${list.type} || ${list.description}`
    li3.innerHTML = `<button id="delete-item" aria-setsize="4">X</button>`

    if((list.status == 'APPROVED') || list.status == 'DENIED') {
        document.getElementsByClassName("list-group2")[0].appendChild(li2);
    } else {
        document.getElementsByClassName("list-group")[0].appendChild(li);
        document.getElementById("list-delete-group").appendChild(li3);
    }
}

/* async function deleteItem(event){
    //declare variable
    let elem;

    if(event.target.className != "delete-btn"){
        elem = event.target.parentNode
    }else{   
        elem = event.target
    }

    let listId = elem.id.substring("delete-btn-".length)


    let response = await fetch(`${domain}/list/${listId}`,{
        method: "DELETE"
    })

    let responseBody = await response.json()

    let listElem = elem.parentNode.parentNode;
    listElem.remove()

    console.log(responseBody)
} */

async function deleteList(event){
    //declare variable
    let elem;

    if(event.target.className != "delete-btn"){
        elem = event.target.parentNode
    }else{   
        elem = event.target
    }

    let listId = elem.id.substring("delete-btn-".length)

    let response = await fetch(`${domain}/list/${listId}`,{
        method: "DELETE"
    })

    let responseBody = await response.json()

    let listElem = elem.parentNode.parentNode;
    listElem.remove()

    console.log(responseBody)
}

async function logout(){

    let response = await fetch(`http://localhost:4242/logout`, {
        method: "DELETE",
    })

    let responseBody = await response.json();

    console.log(responseBody)

    window.location = "../";
}


function myFunction() {
    var x = document.getElementById("list-past");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function myFunction2() {
    var x = document.getElementById("list-delete-item");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}