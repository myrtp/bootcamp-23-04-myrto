import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

function EditSubredditPage() {
    const { subredditId } = useParams();
    const [subreddit, setSubreddit] = useState(null);
    const navigate = useNavigate();
    let jwt = localStorage.getItem("jwt");

    useEffect(() => {
        fetch(`http://localhost:8080/reddit/subreddits/${subredditId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => response.json())
            .then((subreddit) => {
                console.log(subreddit);
                setSubreddit(subreddit);
            })
            .catch((error) => console.log(error));
    }, [subredditId]);

    const handleUpdate = () => {
        const updatedSubreddit = {
            id: subreddit.id,
            title: subreddit.title,
            description: subreddit.description,
        };

        fetch(`http://localhost:8080/reddit/subreddits/${subredditId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
            body: JSON.stringify(updatedSubreddit),
        })
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else if (response.status === 401 || response.status === 500) {
                    throw new Error("User does not have authority to make changes");
                } else {
                    throw new Error("Failed to update subreddit");
                }
            })
            .then((updatedSubreddit) => {
                console.log("Subreddit updated successfully:", updatedSubreddit);
                setSubreddit(updatedSubreddit);
                navigate(`/home`);
            })
            .catch((error) => {
                console.error("User not authorized to edit subreddit:", error.message);
                // Display a pop-up alert with the error message
                window.alert(error.message);
            });
    };

    if (!subreddit) {
        return <div>Loading...</div>;
    }




    return (
        <div className="login-form">
            <h1 className="update-subreddit-title">Edit Profile</h1>
            <div className="form-field">
            <label>
                Title  </label>
                <input
                    type="text"
                    value={subreddit.title}
                    onChange={(e) => setSubreddit({ ...subreddit, title: e.target.value })}
                />

            </div>

            <div className="form-field">
                <label>
                Description</label>
                <textarea
                    type="text"
                    value={subreddit.description}
                    onChange={(e) => setSubreddit({ ...subreddit, description: e.target.value })}
                />

            </div>
            {/* id and dob fields are not displayed */}
            <button  className="update-button" onClick={handleUpdate}>Save</button>
        </div>
    );
}
export default EditSubredditPage;