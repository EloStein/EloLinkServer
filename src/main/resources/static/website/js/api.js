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
