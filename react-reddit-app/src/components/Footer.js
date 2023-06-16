import React from "react";
import { useLocation } from "react-router-dom";
import BackToTopButton from "./BackToTop";

function Footer() {
    const location = useLocation();

    const hideButtonPaths = ["/", "/auth"];

    const shouldHideButton = hideButtonPaths.includes(location.pathname);

    return (
        <footer>
            {!shouldHideButton && <BackToTopButton />}
        </footer>
    );
}

export default Footer;