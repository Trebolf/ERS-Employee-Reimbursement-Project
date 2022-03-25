
//function that runs when the page loads
/* window.onload = function() { */

document.getElementById("card-body").addEventListener("submit", async function (event) {
    //this is to stop the form from reloading.
    event.preventDefault();

    //get values
    let usernameInputElem = document.getElementById("username");
    let passwordInputElem = document.getElementById("password");

    //store in object
    let user = {
        username: usernameInputElem.value,
        password: passwordInputElem.value
    }

    //send http request using value stored in object
    let response = await fetch(`http://localhost:4242/login`, {
        method: "POST",
        body: JSON.stringify(user)
    })

    //convert the value in response from a string to a json.
    let responseBody = await response.json();

    console.log(responseBody)

    if(responseBody.success == false) {
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
    } else {
        if (responseBody.data.userRoleId == 1) {
            window.location = "./reimbursementlistall/"
        } else {
            //redirect page
            //window.location = "./reimbursementlistpersonal/?username=" + responseBody.data.username
            window.location = "./reimbursementlistpersonal"
            //window.location = "./list/" + responseBody.data.username
        }
    }
})

