export async function login() {
    try {
        const loginResponse = await fetch('http://192.168.178.56:25270/elolink/login', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                username: 'Elo4',
                password: 'test'
            })
        });
    } catch (err) {
        console.error(err);
    }
}


export async function getRelations(username) {
    try {
        const chat = await fetch(`http://192.168.178.56:25270/elolink/api/relation/getfriend`, {
            credentials: 'include'
        });
        return await chat.json();
    } catch (error) {
        console.log(error);
    }
}

export async function getUser(username) {
    console.log("FRIEND NAME: " + username);
    try {
        const userResponse = await fetch(`http://192.168.178.56:25270/elolink/api/user/getuser/${username}`, {
            credentials: 'include'
        });
        return await userResponse.json();
    } catch (error) {
        console.log(error);
    }
}

export async function getSelf() {
    try {
        const userResponse = await fetch(`http://192.168.178.56:25270/elolink/api/user/getself`, {
            credentials: 'include'
        });
        return await userResponse.json();
    } catch (error) {
        console.log(error);
    }
}

export async function getConversation(username1, username2) {
    try {
        const chat = await fetch(`http://192.168.178.56:25270/elolink/api/chat/getconversation/${username1}/${username2}`, {
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
            const response = await fetch("http://192.168.178.56:25270/elolink/signup", {
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
                return await response.text();
            } catch (error) {
                console.log(error)
            }
        } catch (error) {
            console.log(error);
        }

}
