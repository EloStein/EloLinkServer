import {getConversation, registerUser, login} from "./api.js";

document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.getElementById('registration_section');
    signupForm.style.opacity = 0;
    setTimeout(() => {
      signupForm.style.transition = 'opacity 1s ease-in-out';
      signupForm.style.opacity = 1;
    }, 500);


    const registrationButton = document.getElementById("submit")
    registrationButton.addEventListener('click', async function (event) {
        event.preventDefault();
        console.log("FUNCTION")

        await login()

        const form = document.querySelector("form");
        const username = document.querySelector('input[name="username"]');
        const email = document.querySelector('input[type="email"]');
        const password = document.querySelector('input[type="password"]');
        const passwordcon = document.querySelector('input[type="password"][name="passwordcon"]');

        console.log("PW: " + password.value);
        console.log("PWC: " + passwordcon.value);

        form.reportValidity()

        if (password.value !== passwordcon.value) {
            console.log("Password mismatch");
        } else {
            const response = await registerUser(username.value, email.value, password.value);
            console.log(response)

        }




    });




});