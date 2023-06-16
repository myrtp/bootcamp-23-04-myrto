import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

function LoginContent() {

    let [loginForm, setLoginForm] = useState({username: "", password: ""});
    let [registerForm, setRegisterForm] = useState({username: "", password: "", confirmPassword: ""});

    const navigate = useNavigate();
    const handleLoginFormChange = (e) => {
        setLoginForm({ ...loginForm, [e.target.name]: e.target.value });
    };

    const handleRegisterFormChange = (e) => {
        setRegisterForm({ ...registerForm, [e.target.name]: e.target.value });
    };

    useEffect(() => {
        console.log(registerForm)
    }, [registerForm])

    const handleRegistration = (e) => {
        e.preventDefault();

        navigate("/home");
    }

    const handleLogin = (e) => {
        e.preventDefault();
        navigate("/home");
        // Encode the username and password in the Base64 auth format
        const basicAuth = btoa(`${loginForm.username}:${loginForm.password}`);

        fetch(`http://localhost:8080/reddit/auth/login`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    'Authorization': `Basic ${basicAuth}`,
                }
            })
            .then((response) => response.json())
            .then((data) => {
                console.log(data.token);
                localStorage.setItem("jwt", data.token)
            })
            .catch((error) => console.log(error));

    }

    return (
        // create with css grid 2 forms side-by-side for login and register
        <div className="authorization-container">
            <div className="login-form">
                <form className={"form-group"} onSubmit={handleLogin}>
                    <label htmlFor="username">Username</label>
                    <input type="text"
                           id="username"
                           name="username"
                           placeholder="Username"
                           value={loginForm.username}
                           onChange={handleLoginFormChange}
                           required></input>
                    <label htmlFor="password">Password</label>
                    <input type="password"
                           id="password"
                           name="password"
                           placeholder="Password"
                           value={loginForm.password}
                           onChange={handleLoginFormChange}
                           required></input>
                    <button type="submit">Login</button>
                </form>
            </div>
            <div className="registration-form">
                <form className={"form-group"} onSubmit={handleRegistration}>
                    <label htmlFor="email">Email</label>
                    <input type="text"
                           id="email"
                           value={registerForm.email}
                           onChange={handleRegisterFormChange}
                           name="email"
                           placeholder="email"
                           required></input>
                    <label htmlFor="username">Username</label>
                    <input type="text"
                           id="username"
                           value={registerForm.username}
                           onChange={handleRegisterFormChange}
                           name="username"
                           placeholder="Username"
                           required></input>
                    <label htmlFor="password">Password</label>
                    <input type="password"
                           id="password"
                           value={registerForm.password}
                           onChange={handleRegisterFormChange}
                           name="password"
                           placeholder="Password"
                           required></input>
                    <label htmlFor="confirm-password">Confirm Password</label>
                    <input type="password"
                           id="confirm-password"
                           value={registerForm.confirmPassword}
                           onChange={handleRegisterFormChange}
                           name="confirmPassword"
                           placeholder="Password"
                           required></input>
                    <button type="submit">Register</button>
                </form>
            </div>
        </div>

    );
}

export default LoginContent;