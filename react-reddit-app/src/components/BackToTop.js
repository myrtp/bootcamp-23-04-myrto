import React, { useEffect } from 'react';

const BackToTopButton = () => {
    useEffect(() => {
        const handleScroll = () => {
            const scrollY = window.scrollY;
            const backToTopButton = document.getElementById('backToTopButton');

            if (scrollY > 100) {
                backToTopButton.classList.add('show');
            } else {
                backToTopButton.classList.remove('show');
            }
        };

        window.addEventListener('scroll', handleScroll);
        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    const scrollToTop = () => {
        window.scrollTo({ top: 0, behavior: 'smooth' });
    };

    return (
        <button
            id="backToTopButton"
            className="btn btn-primary back-to-top"
            onClick={scrollToTop}
        >
            Back to Top
        </button>
    );
};

export default BackToTopButton;
