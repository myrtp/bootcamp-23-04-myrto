import { useEffect, useState } from "react";
import {useLocation, Link, useNavigate, useParams} from "react-router-dom";
import CreateSubreddit from "./CreateSubreddit";
function SubredditsByTitle() {
    const [subreddits, setSubreddits] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();
    let jwt = localStorage.getItem("jwt")

    useEffect(() => {
        fetch(`http://localhost:8080/reddit/subreddits/title`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => response.json())
            .then((subreddits) => {
                console.log(subreddits);
                setSubreddits(subreddits);
            })
            .catch((error) => console.log(error));
    }, []);

    console.log(subreddits); // Debugging statement

    if (location.pathname !== "/home") {
        return ( <div className="side-element">
        </div>);
    }

    const navigateToSubredditPage = (subredditId) => {
        navigate(`/subreddits/${subredditId}`);
    };

    return (
        <div className="subreddit-list-container">
            <h1>Communities</h1>
            {jwt && (
                <div>
                    <Link
                        to="/subreddit/create"
                        className="link-black align-by-first-letter create-button"                    >
                        <h3> + Create Community</h3>
                    </Link>
                </div>
            )}
            {location.pathname === "/subreddit/create" ? (
                <CreateSubreddit />
            ) : (
                subreddits.map((subreddit) => (
                    <div key={subreddit.id}>
                        <h3>
                            <Link
                                to={`/subreddits/${subreddit.id}`}
                                className="link-black align-by-first-letter"
                                onClick={() => navigateToSubredditPage(subreddit.id)}
                            >
                                {subreddit.title}
                            </Link>
                        </h3>
                    </div>
                ))
            )}
        </div>
    );
}

export default SubredditsByTitle;