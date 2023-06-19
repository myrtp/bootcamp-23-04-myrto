import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function PostContent() {
    const [posts, setPosts] = useState([]);
    const [commentsPerPost, setCommentsPerPost] = useState({});
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();
    const jwt = localStorage.getItem("jwt");

    function fetchCommentsForPost(postId) {
        return fetch(`http://localhost:8080/reddit/comments/${postId}/comments`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        }).then((response) => response.json());
    }

    const setPostsAndCommentsToState = (posts) => {
        console.log(posts);
        const fetchCommentsPromises = posts.map((post) =>
            fetchCommentsForPost(post.id).then((comments) => {
                post.comments = comments;
                setCommentsPerPost((prevComments) => ({
                    ...prevComments,
                    [post.id]: comments,
                }));
            })
        );

        Promise.all(fetchCommentsPromises).then(() => {
            setPosts(posts);
        });
    };

    const getPostByDateDesc = () => {
        return fetch("http://localhost:8080/reddit/posts/home", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        }).then((response) => response.json());
    };

    const fetchPosts = () => {
        getPostByDateDesc()
            .then(setPostsAndCommentsToState)
            .catch((error) => console.log(error));
    };

    const fetchUsers = () => {
        fetch("http://localhost:8080/reddit/users", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => response.json())
            .then((users) => {
                console.log(users);
                setUsers(users);
            })
            .catch((error) => console.log(error));
    };

    useEffect(() => {
        fetchPosts();
        fetchUsers();
    }, []);

    const navigateToPostPage = (postId) => {
        navigate(`/post/${postId}`);
    };

    return (
        <main>
            {posts.map((post) => (
                <div key={post.id}>
                    <div className="post-content">
                        <Link
                            to={`/post/${post.id}`}
                            className="link-black align-by-first-letter"
                            onClick={() => navigateToPostPage(post.id)}
                        >
                            <h2>{post.title}</h2>
                        </Link>

                        <p>{post.text}</p>
                        <div>
                            <img
                                src={post.image}
                                alt="Post Image"
                                width="400"
                                height="400"
                            />
                        </div>
                        <div className="grid-container">
                            <div className="post_metadata">
                                <div>{post.createdAt}</div>
                            </div>
                            <div className="comments">
                                {commentsPerPost?.[post.id]?.map((comment) => (
                                    <p key={comment.id}>{comment.text}</p>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            ))}
        </main>
    );
}

export default PostContent;
