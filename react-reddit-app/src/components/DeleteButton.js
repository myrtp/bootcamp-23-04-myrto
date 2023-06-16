import React from "react";

const DeleteButton = ({ onDelete }) => {
    const handleDelete = () => {
        // Call the onDelete function passed as a prop
        onDelete();
    };

    return (
        <button onClick={handleDelete}>
            Delete Subreddit
        </button>
    );
};

export default DeleteButton;