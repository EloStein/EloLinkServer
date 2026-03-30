import {login ,getConversation, getSelf, getUser} from "./api.js";


document.addEventListener('DOMContentLoaded', function () {


    // Eventlistener for Friendbox
    const friendboxes = document.querySelectorAll('.friendbox');
    friendboxes.forEach(box => {
        box.addEventListener('click', handleFriendboxClick);
    });
    async function handleFriendboxClick(event) {
        // Get Elements
        const username = event.currentTarget.querySelector('.friendbox-username').textContent;

        // Login
        await login()

        // Get User
        const user = await getUser(username);
        console.log("USERMAIL: " + user.email);

        // Get Self
        const self = await getSelf();
        console.log("SELF: " + self.userName);
        console.log("SELF: " + self.email);


        // Get Conversation
        const chats = await getConversation(self.userName, username)
        clearMessages()
        for (let i = 0; i < chats.length; i++) {
            console.log("Chat number " + i + ":" + chats[i].message);

            //Check Message Sender
            var sender;
            if (chats[i].sender === self.userName) {
                sender = "me"
            } else {
                sender = "other"
            }

            // Add Message to Chatbox
            createMessage(chats[i].message, chats[i].timestamp, sender);
        }
    }

    function clearMessages() {
        document.querySelector(".chatbox").replaceChildren()
    }

    function createMessage(text, time, sender, checked = false) {
        const li = document.createElement("li");

        li.classList.add("message", sender); // sender: "me" oder "other"

        const textSpan = document.createElement("span");
        textSpan.classList.add("message-text");
        textSpan.textContent = text;

        const timeSpan = document.createElement("span");
        timeSpan.classList.add("message-time");
        timeSpan.textContent = time;

        li.appendChild(textSpan);
        li.appendChild(timeSpan);

        if (sender === "me" && checked) {
            const checker = document.createElement("span");
            checker.classList.add("checker");
            checker.innerHTML = "&#10003;&#10003;";
            li.appendChild(checker);
        }

        document.querySelector(".chatbox").appendChild(li);
    }


});