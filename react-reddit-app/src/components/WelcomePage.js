import React from 'react';

const WelcomePage = () => {
    const redirectToAuth = () => {
        window.location.href = "/auth";
    };

    return (
        <div className="welcome-container">
            <h1 className="welcome-text">Welcome to Reddit</h1>
            <h2 className="welcome-text">Log in</h2>
            <button className="login-button" onClick={redirectToAuth}>
               Log in
            </button>
            <h2 className="welcome-text">Create an account</h2>
            <button className="login-button" onClick={redirectToAuth}>
                Sign up
            </button>
        </div>
    );
};
export default WelcomePage;