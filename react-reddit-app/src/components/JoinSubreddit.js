import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

function JoinSubredditPage() {
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

    const handleJoin = () => {
        fetch(`http://localhost:8080/reddit/subreddits/${subredditId}/join`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => {
                if (response.ok) {
                    // User successfully joined the community
                    window.alert("Joined community!");
                    navigate(`/home`);
                } else if (response.status === 401 || response.status === 500) {
                    throw new Error("User does not have authority to make changes");
                } else {
                    throw new Error("Failed to join subreddit");
                }
            })
            .catch((error) => {
                console.error("Error joining subreddit:", error.message);
                // Display a pop-up alert with the error message
                window.alert(error.message);
            });
    };

    if (!subreddit) {
        return <div>Loading...</div>;
    }

    return (
        <div className="join-subreddit">
            <h2>Are you sure you want to join this community?</h2>
            <div className="button-container">
                <button className="yes-button" onClick={handleJoin}>Yes</button>
                <Link className="no-button" to={`/home`}>No</Link>
            </div>
        </div>
    );
}

export default JoinSubredditPage;
