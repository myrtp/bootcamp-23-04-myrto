import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function CreateSubreddit() {
    const [subredditForm, setSubredditForm] = useState({
        title: "",
        description: "",
    });
    const navigate = useNavigate();
    const jwt = localStorage.getItem("jwt");

    const handleSubredditFormChange = (e) => {
        setSubredditForm({
            ...subredditForm,
            [e.target.name]: e.target.value,
        });
    };

    const handleCreateSubreddit = (e) => {
        e.preventDefault();

        fetch(`http://localhost:8080/reddit/subreddits`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
            body: JSON.stringify(subredditForm), // Pass the subredditForm in the request body
        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
            })
            .catch((error) => console.log(error));
            navigate(`/home`);

    };

    return (
        <div className="create-subreddit-container">
            <h1 className="create-subreddit-title">Create Subreddit</h1>
            <form className="create-subreddit-form"  onSubmit={handleCreateSubreddit}>
                <label htmlFor="title">Title</label>
                <input
                    type="text"
                    id="title"
                    value={subredditForm.title}
                    onChange={handleSubredditFormChange} // Use handleSubredditFormChange as the onChange handler
                    name="title"
                    placeholder="Title"
                    required
                ></input>
                <label htmlFor="description">Description</label>
                <textarea
                    id="description"
                    value={subredditForm.description}
                    onChange={handleSubredditFormChange} // Use handleSubredditFormChange as the onChange handler
                    name="description"
                    placeholder="Description"
                    required
                ></textarea>
                <button type="submit" className="create-button">Create</button>
            </form>
        </div>
    );
}

export default CreateSubreddit;
