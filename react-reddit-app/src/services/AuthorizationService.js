const AuthorizationService = {



    login: (username, password) => {
        const basicAuth = btoa(`${username}:${password}`);


        return fetch(`http://localhost:8080/reddit/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Basic ${basicAuth}`
            }
        })
            .then((response) =>  response.json());
    }

}

export default AuthorizationService;