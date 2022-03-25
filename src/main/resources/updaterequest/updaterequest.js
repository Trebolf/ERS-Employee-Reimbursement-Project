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

    getAllLists();
}

async function getAllLists() {
    
    let response = await fetch(`http://localhost:4242/list/all`);

    let responseBody = await response.json();

    console.log(responseBody)

    let lists = responseBody.data;

    lists.forEach(list => {
        createList(list)
    });
}

function createList(list) {
    let li = document.createElement("option");
    li.className = "list-dropdown-option"
    li.value = `${list.id}`

    let li2 = document.createElement("option")
    li.className = "list-dropdown-option2"
    li2.value = `${list.id}`

    li.innerHTML = `$${list.amount} || ${list.firstName} ${list.lastName} || ${list.type} || ${list.description}`;
    li2.innerHTML = `$${list.amount} || ${list.status} || ${list.firstName} ${list.lastName} || ${list.type} || ${list.description}`;

    if(list.status == 'PENDING') {
        document.getElementById("select-box").appendChild(li);
    }

    if(list.status == 'APPROVED' || list.status == 'DENIED') {
        document.getElementById("select-box3").appendChild(li2);
    }
}

async function updateListGivenUsername() {

    //grab html element where the form is
    let updateListSelection = document.getElementById("select-box")
    let updateListStatus = document.getElementById("select-box2")

    let selection = {
        id: updateListSelection.value,
        statusId: updateListStatus.value,
        resolverId: user.id
    }

    console.log(selection)

    if(selection.statusId == 2) {
        let response = await fetch(`http://localhost:4242/approve/${selection.id}`,{
            method: "PATCH",
            body: JSON.stringify(selection)
        })

        let responseBody = await response.json();

        console.log(responseBody)

        if(responseBody.success) {
            let messageElem = document.getElementById("message")
            messageElem.innerText = `Reimbursement request was approved!`
        }
    }
    
    if(selection.statusId == 3) {
        let response = await fetch(`http://localhost:4242/deny/${selection.id}`,{
            method: "PATCH",
            body: JSON.stringify(selection)
        })

        let responseBody = await response.json();

        console.log(responseBody)

        if(responseBody.success) {
            let messageElem = document.getElementById("message")
            messageElem.innerText = `Reimbursement request was denied!`
        } else {
            let messageElem = document.getElementById("message")
            messageElem.innerText = `Please select "APPROVED" or "DENIED" from the dropdown menu`
        }
    }
}

async function updateListGivenUsernamePast() {

    //grab html element where the form is
    let updateListSelection = document.getElementById("select-box3")
    let updateListStatus = document.getElementById("select-box4")

    let selection = {
        id: updateListSelection.value,
        statusId: updateListStatus.value,
        resolverId: user.id
    }
    
    console.log(selection)

    if(selection.statusId == 2) {
        let response = await fetch(`http://localhost:4242/approve/${selection.id}`,{
            method: "PATCH",
            body: JSON.stringify(selection)
        })

        let responseBody = await response.json();

        console.log(responseBody)

        if(responseBody.success) {
            let messageElem = document.getElementById("message2")
            messageElem.innerText = `Reimbursement request is now approved!`
        }
    }
    
    if(selection.statusId == 3) {
        let response = await fetch(`http://localhost:4242/deny/${selection.id}`,{
            method: "PATCH",
            body: JSON.stringify(selection)
        })

        let responseBody = await response.json();

        console.log(responseBody)

        if(responseBody.success) {
            let messageElem = document.getElementById("message2")
            messageElem.innerText = `Reimbursement request is now denied!`
        }
    }
}

function myFunction() {
    var x = document.getElementById("card-container-past");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}