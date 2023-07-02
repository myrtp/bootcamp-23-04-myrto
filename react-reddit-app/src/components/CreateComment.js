import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {extractUserDetails} from "./service/DecodeJWT";

function CreateComment({ subredditId, postId }) {

    const [commentForm, setCommentForm] = useState({
        title: "",
        text: "",
        image: "", // Updated to accept a string
    });
    const navigate = useNavigate();
    const jwt = localStorage.getItem("jwt");
    const userDetails = extractUserDetails(jwt);
    const decodedUserId = userDetails ? userDetails.userId : null;
    const handleCommentFormChange = (e) => {
        setCommentForm({
            ...commentForm,
            [e.target.name]: e.target.value,
        });
    };

    const handleCreateComment = (e) => {
        e.preventDefault();

        const newComment = {
            title: commentForm.title,
            text: commentForm.text,
            image: commentForm.image, // Updated to use the string value
            subredditId: subredditId,
            postId: postId,
            userId: decodedUserId
        };

        fetch(`http://localhost:8080/reddit/comments/`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
            body: JSON.stringify(newComment),
        })
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else if (response.status === 401 || response.status === 500) {
                    throw new Error("User does not have authority to make changes");
                } else {
                    throw new Error("Failed to create post");
                }
            })
            .then((data) => {
                console.log(data);
                setCommentForm({
                    title: "",
                    text: "",
                    image: "",
                });
                navigate(`/home`);
            })
            .catch((error) => {
                console.error("Error creating post:", error.message);
                window.alert(error.message);
            });   };

    return (
        <div className="create-post-container login-form">
            <h3>Write your comment...</h3>
            <form className="create-post-form" onSubmit={handleCreateComment}>
                <label htmlFor="title">Title</label>
                <input
                    type="text"
                    id="title"
                    value={commentForm.title}
                    onChange={handleCommentFormChange}
                    name="title"
                    placeholder="Title"
                    required
                />

                <label htmlFor="text">Text</label>
                <textarea
                    id="text"
                    value={commentForm.text}
                    onChange={handleCommentFormChange}
                    name="text"
                    placeholder="Text"
                    required
                />

                <label htmlFor="image">Image URL</label>
                <input
                    type="text"
                    id="image"
                    value={commentForm.image}
                    onChange={handleCommentFormChange}
                    name="image"
                    placeholder="Image URL"
                    required
                />

                <button type="submit" className="create-post-button">Post</button>
            </form>
        </div>
    );
}
export default CreateComment;