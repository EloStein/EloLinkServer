import {login ,getConversation, getSelf, getUser, getRelations, paramLogin} from "./api.js";


document.addEventListener('DOMContentLoaded', async function () {

    // Login
    await login()

    // Get Self
    const self = await getSelf();
    console.log("Logged in as: " + self.userName);

    await paramLogin("elo","test")
    const self2 = await getSelf();
    console.log("Logged in asss: " + self2.userName);

    // Get & Add Relations
    const relations = await getRelations()
    console.log("Loaded Relations: " + relations.length);
    for (let i = 0; i < relations.length; i++) {
        createRelation("Freund", relations[i].friend);
    }


    // Eventlistener for Friendbox
    const relationboxes = document.querySelectorAll('.friendbox');
    relationboxes.forEach(box => {
        box.addEventListener('click', friendboxClick);
    });

    async function friendboxClick(event) {
        loadChat(event)
    }

    async function loadChat(event){
        console.log("Loading Conversation");
        // Get Elements
        const username = event.currentTarget.querySelector('.friendbox-username').textContent;

        // Login
        await paramLogin("elo","test")

        // Get User
        const user = await getUser(username);

        // Get Self
        const self = await getSelf();


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

    function createRelation(name, status){
        const friendbox = document.createElement("div");
        friendbox.classList.add("friendbox");

        const img = document.createElement("img");
        img.src = "/website/images/profile_picture_default.png"
        img.classList.add("friendbox-pfp");

        const infoDiv = document.createElement("div");
        infoDiv.classList.add("friendbox-info");

        const nameSpan = document.createElement("span");
        nameSpan.classList.add("friendbox-name");
        nameSpan.textContent = name;

        const statusSpan = document.createElement("span");
        statusSpan.classList.add("friendbox-username");
        statusSpan.textContent = status;

        infoDiv.appendChild(nameSpan);
        infoDiv.appendChild(statusSpan);

        friendbox.appendChild(img);
        friendbox.appendChild(infoDiv);

        document.querySelector("#profile_section > div").appendChild(friendbox);
    }

});