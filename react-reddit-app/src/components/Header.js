import React from "react";
import { Link, useLocation } from "react-router-dom";

function Header() {
    const location = useLocation();

    const hideButtonPaths = ["/", "/auth", "/profile/:userId"];
    const shouldHideButton = hideButtonPaths.includes(location.pathname);

    return (
        <header className="header">
            <div className="header-left">
                <Link to="/home">
                    <img
                        src="https://www.redditinc.com/assets/images/site/reddit-logo.png"
                        alt="RedditLogo"
                        width="60"
                        height="60"
                    />
                </Link>
                    <h1>reddit.</h1>

            </div>

            {!shouldHideButton && (
                <Link to={`/profile/:userId`} className="profile-button">
                    Profile
                </Link>
            )}
        </header>
    );
}

export default Header;