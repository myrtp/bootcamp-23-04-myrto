import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";

function SubredditPage() {
    const {subredditId} = useParams();
    const [subreddit, setSubreddit] = useState(null);
    const [posts, setPosts] = useState([]);
    const navigate = useNavigate();
    // localStorage.setItem("jwt", "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJteXJ0b3AxMzMiLCJhIjoiYiIsImlzcyI6ImJvb3RjYW1wLTIzLTA0IiwiZXhwIjoxNjg2ODI4OTIxLCJpYXQiOjE2ODY3NDI1MjEsInVzZXJJZCI6NiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlVTRVJfQVVUSEVOVElDQVRJT04ifV0sInVzZXJuYW1lIjoibXlydG9wMTMzIn0.MS0GT9QPib_qN7IK7w7sjNivdsTh1uk7V-YwiFJjD9bKIreLaf8mNIBpNQm3nGDlVRu6qbxHz8rC6AuuqUTaKzv0c8OhlgpmvV64tA5yhX6hO4zg6mkIkqfvKrhMmIPFDYUb1WEfW07BX31WgIWognl7OVf6LUzJC8qMeBX8u5P49xVgxXcL2UMy8UpdyBYxEXpzdhMN_dNOxtpP1kjzQc7mY-tuhj7tpRvIvqlQ50_ua2VcsxUUNW70Cv5IB7lZSLQIyv0ugAGygd_5kzFyhcd3zRauYFCVf9NkdSn3tQIQWSi8lNLd_HR1RhPkD4XLNU3oaqCQdyWrY59yjVn8HQ");
    let jwt = localStorage.getItem("jwt")

    useEffect(() => {
        // Fetch subreddit details
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

        // Fetch subreddit posts
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
    }, [subredditId, jwt]);

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

    return (

        <main>
            <h1>{subreddit.title}</h1>
            <p>{subreddit.description}</p>

            <h2>Posts</h2>
            {posts.map((post) => (
                <div key={post.id}>
                    <div className="post-content">
                        <h3>{post.title}</h3>
                        <p>{post.text}</p>
                    </div>
                    <div>
                        <img src={post.image} alt="Post Image"
                             width="400" // Decrease the width value to make the image smaller
                             height="400" />
                    </div>
                    <div className="grid-container"></div>
                </div>
            ))}
        </main>
);
}

export default SubredditPage;