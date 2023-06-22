import {useEffect, useState} from "react";
import {Link, useNavigate, useParams} from "react-router-dom";
import CreatePost from "./CreatePost";
function SubredditPage() {
    const {subredditId} = useParams();
    const [subreddit, setSubreddit] = useState(null);
    const [posts, setPosts] = useState([]);
    const navigate = useNavigate();
    let jwt = localStorage.getItem("jwt")

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

        fetch(`http://localhost:8080/reddit/posts/subreddits/${subredditId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => response.json())
            .then((posts) => {
                console.log(posts);
                setPosts(posts);
            })
            .catch((error) => console.log(error));
    }, [subredditId]);

    const handleDelete = () => {
        // Delete subreddit by subredditId
        fetch(`http://localhost:8080/reddit/subreddits/${subredditId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                // Include any necessary authorization headers
            },
        })
            .then((response) => {
                if (response.ok) {
                    // Redirect the user to another page after deletion (e.g., homepage)
                    navigate("/home");
                } else {
                    throw new Error("Failed to delete subreddit");
                }
            })
            .catch((error) => console.log(error));
    };


    console.log(subreddit); // Debugging statement
    console.log(posts); // Debugging statement

    if (!subreddit) {
        return <div>Loading...</div>;
    }

    const navigateToPostPage = (postId) => {
        navigate(`/post/${postId}`);
    };

    return (

        <main>
            <h1>{subreddit.title}</h1>
            <p>{subreddit.description}</p>
            <CreatePost subredditId={subredditId} />
            <h2>Posts</h2>
            {posts.map((post) => (
                <div key={post.id}>
                    <div className="post-content">
                        <Link
                            to={`/post/${post.id}`}
                            className="link-black align-by-first-letter"
                            onClick={() => navigateToPostPage(post.id)}
                        >
                            <h3>{post.title}</h3>
                        </Link>

                        <p>{post.text}</p>
                    <div>
                        <img src={post.image} alt="Post Image"
                             width="400" // Decrease the width value to make the image smaller
                             height="400" />
                    </div>
                    <div className="grid-container"></div>
                    </div>

                </div>
            ))}
        </main>
);
}

export default SubredditPage;