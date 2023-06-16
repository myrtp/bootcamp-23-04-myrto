import React from "react";

const EditButton = ({ onDelete }) => {
    const handleEdit = () => {

        onDelete();
    };

    return (
        <button onClick={handleEdit}>
            Edit Subreddit
        </button>
    );
};

export default EditButton;