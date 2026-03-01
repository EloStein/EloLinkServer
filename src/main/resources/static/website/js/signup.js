import { login } from "./api.js";

document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.getElementById('registration_section');
    signupForm.style.opacity = 0;
  
    setTimeout(() => {
      signupForm.style.transition = 'opacity 1s ease-in-out';
      signupForm.style.opacity = 1;
    }, 500);


    // Eventlistener for Registration Section
    const regButton = document.getElementById("submitX")
    regButton.addEventListener('click', function () {
      const emailInput = document.querySelector('input[type="email"]');
      const passwordInput = document.querySelector('input[type="password"]');
      const confirmPasswordInput = document.querySelector('input[type="password"][name="confirm-password"]');
      // Check for a valid email and password (you can add your validation logic here)
      const isValid = emailInput.checkValidity() && passwordInput.checkValidity() && confirmPasswordInput.checkValidity();
      if (!isValid) {
        signupForm.classList.add('shake');
        setTimeout(() => {
          signupForm.classList.remove('shake');
        }, 1000);
      }
    });


    async function getUser() {
        try{
            await login()
            const userResponse = await fetch('http://localhost:25270/elolink/getuser/Mat', {
                credentials: 'include'
            });
            const userData = await userResponse.json();
            console.log(userData);
        } catch (error){
            console.log(error);
        }
    }

    document.getElementById('submit')
        .addEventListener('click', getUser);





});