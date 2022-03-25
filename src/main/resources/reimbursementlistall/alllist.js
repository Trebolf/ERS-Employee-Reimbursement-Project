let user;

window.onload = async function () {
    let response = await fetch("http://localhost:4242/check-session");

    let responseBody = await response.json();

    if(responseBody.success == false) {
        window.location = "../";
    }

    if (responseBody.data.userRoleId == 2) {
        window.location = "../reimbursementlistpersonal/"
    }

    user = responseBody.data;

    console.log(user)

    getName();
    getAllLists();
}

async function getName() {

    let name = document.createElement("div");
    name.className = "name"

    name.innerHTML = `Welcome, ${user.firstName} ${user.lastName}!`

    document.getElementById("welcome").appendChild(name);
}

async function getAllLists(){
    
    let response = await fetch(`http://localhost:4242/list/all`);

    let responseBody = await response.json();

    console.log(responseBody)

    let lists = responseBody.data;

    lists.forEach(list => {
        createList(list)
    });
}

async function logout(){

    let response = await fetch(`http://localhost:4242/logout`, {
        method: "DELETE",
    })

    let responseBody = await response.json();

    console.log(responseBody)

    window.location = "../";
}

function createList(list){

    //let container = document.getElementsByClassName("list-container");

    let li = document.createElement("li");
    li.className = "list-group-item"

    let li2 = document.createElement("li");
    li2.className = "list-group-item disabled"

    li.innerHTML = `${list.status} || ${list.firstName} ${list.lastName} || $${list.amount} || ${list.type} || ${list.description}`
    li2.innerHTML = `${list.status} || ${list.firstName} ${list.lastName} || $${list.amount} || ${list.type} || ${list.description}`

    if((list.status == 'APPROVED') || list.status == 'DENIED') {
        document.getElementsByClassName("list-group2")[0].appendChild(li2);
    } else {
        document.getElementsByClassName("list-group")[0].appendChild(li);
    }
}

function myFunction() {
    var x = document.getElementById("list-past");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}