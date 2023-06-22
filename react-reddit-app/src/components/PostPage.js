import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import fetchCommentsForPost from './service/FetchCommentsForPost';
import CreateComment from "./CreateComment";



function PostPage() {
    const { postId } = useParams();
    const [post, setPost] = useState(null);
    const [comments, setComments] = useState([]);
    const jwt = localStorage.getItem("jwt");

    useEffect(() => {
        // Fetch post details
        fetch(`http://localhost:8080/reddit/posts/${postId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => response.json())
            .then((post) => {
                console.log(post);
                setPost(post);


            })
            .catch((error) => console.log(error));

        fetchCommentsForPost(postId, jwt)
            .then((comments) => {
                console.log(comments);
                setComments(comments);
            })
            .catch((error) => console.log(error));
    }, [postId]);

    if (!post) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>{post.title}</h2>
            <p>{post.text}</p>
            <img src={post.image} alt="Post Image" width="600" height="600" />
            <CreateComment postId={postId} subredditId={post.subredditId} />
            <h3>Comments</h3>
            {comments.map((comment) => (
                <div key={comment.id}>
                    <div className="comments">
                        <p>{comment.text}</p>
                        {/* Add any other comment details you want to display */}
                    </div>
                </div>
            ))}
        </div>
    );
}

export default PostPage;