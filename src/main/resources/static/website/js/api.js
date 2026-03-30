export async function login() {
    try {
        const loginResponse = await fetch('http://localhost:25270/elolink/login', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                username: 'Mat',
                password: 'test'
            })
        });

        if (!loginResponse.ok) {
            throw new Error('Login fehlgeschlagen');
        }
    } catch (err) {
        console.error(err);
    }
}


export async function getProfiles() {
    const profiles = await ipcRenderer.invoke("getProfiles");
    const friendlist = document.getElementById("friendlist");
    console.log(profiles);

    profiles.forEach(p => {
        const li = document.createElement("li");
        li.className = "friend";
        li.innerHTML = `
            <img src="C:/Users/stein/Desktop/EloLinkClient_Maven/friends/profile_pictures/profile.png" class="friend-pfp">
            <div class="friend-info">
                <span id="friend-name">${p.username}</span>
                <span class="friend-status">${p.description || 'Offline'}</span>
            </div>
        `;
        li.addEventListener("click", () => doSomethingFetch(p.username));
        friendlist.appendChild(li);
    });
}

export async function getUser(username) {
    console.log("FRIEND NAME: " + username);
    try {
        const userResponse = await fetch(`http://localhost:25270/elolink/api/user/getuser/${username}`, {
            credentials: 'include'
        });
        return await userResponse.json();
    } catch (error) {
        console.log(error);
    }
}

export async function getSelf() {
    try {
        const userResponse = await fetch(`http://localhost:25270/elolink/api/user/getself`, {
            credentials: 'include'
        });
        return await userResponse.json();
    } catch (error) {
        console.log(error);
    }
}

export async function getConversation(username1, username2) {
    console.log("FRIEND NAME: " + username2);
    try {
        const chat = await fetch(`http://localhost:25270/elolink/api/chat/getconversation/${username1}/${username2}`, {
            credentials: 'include'
        });
        return await chat.json();
    } catch (error) {
        console.log(error);
    }
}

export async function registerUser(username, email, password) {
    console.log("Trying to reg user: " + username);
        try {
            const response = await fetch("http://localhost:25270/elolink/signup", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                credentials: "include",
                body: JSON.stringify({
                    username: username,
                    email: email,
                    password: password
                })
            });
            try {
                const responseData = await response.text();
                console.log("RESPONSE: " + responseData)
                return await response.json();
            } catch (error) {
                console.log(error)
            }
        } catch (error) {
            console.log(error);
        }

}
