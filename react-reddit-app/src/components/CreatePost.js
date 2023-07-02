import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {extractUserDetails} from "./service/DecodeJWT";

function CreatePost({ subredditId }) {

    const [postForm, setPostForm] = useState({
        title: "",
        text: "",
        image: "", // Updated to accept a string
    });
    const navigate = useNavigate();
    const jwt = localStorage.getItem("jwt");
    const userDetails = extractUserDetails(jwt);
    const decodedUserId = userDetails ? userDetails.userId : null;
    const handlePostFormChange = (e) => {
        setPostForm({
            ...postForm,
            [e.target.name]: e.target.value,
        });
    };

    const handleCreatePost = (e) => {
        e.preventDefault();

        const newPost = {
            title: postForm.title,
            text: postForm.text,
            image: postForm.image, // Updated to use the string value
            subredditId: subredditId,
            userId: decodedUserId
        };

        fetch(`http://localhost:8080/reddit/posts`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
            body: JSON.stringify(newPost),
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
            setPostForm({
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
            <h3>create post...</h3>
            <form className="create-post-form" onSubmit={handleCreatePost}>
                <label htmlFor="title">Title</label>
                <input
                    type="text"
                    id="title"
                    value={postForm.title}
                    onChange={handlePostFormChange}
                    name="title"
                    placeholder="Title"
                    required
                />

                <label htmlFor="text">Text</label>
                <textarea
                    id="text"
                    value={postForm.text}
                    onChange={handlePostFormChange}
                    name="text"
                    placeholder="Text"
                    required
                />

                <label htmlFor="image">Image Base64 Code</label>
                <input
                    type="text"
                    id="image"
                    value={postForm.image}
                    onChange={handlePostFormChange}
                    name="image"
                    placeholder="Image Base64 encoding"
                    required
                />

                <button type="submit" className="create-post-button">Create</button>
            </form>
        </div>
    );
}
export default CreatePost;