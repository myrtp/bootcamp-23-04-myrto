import { useEffect, useState } from "react";
import {useParams, useNavigate, Link} from "react-router-dom";
import EditProfilePage from "./UpdateUserProfile";
import { extractUserDetails } from "./service/DecodeJWT";
import DeleteUser from "./DeleteUserProfile";
function UserProfilePage() {
    const { userId } = useParams();
    const [user, setUser] = useState(null);
    const [posts, setPosts] = useState([]);
    const navigate = useNavigate();

    const jwt = localStorage.getItem("jwt");
    const userDetails = extractUserDetails(jwt);
    const decodedUserId = userDetails ? userDetails.userId : null;

    useEffect(() => {
        fetch(`http://localhost:8080/reddit/users/${decodedUserId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => response.json())
            .then((user) => {
                setUser(user);
            })
            .catch((error) => console.log(error));

        fetch(`http://localhost:8080/reddit/posts/user/${decodedUserId}`, {
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
    }, [decodedUserId]);

    // const handleEditProfile = () => {
    //     navigate(`/profile/${userId}/edit`);
    // };
    const navigateToPostPage = (postId) => {
        navigate(`/post/${postId}`);
    };

    const handleEditProfile = () => {
        navigate(`/profile/${decodedUserId}/edit`);
    };

    if (!user) {
        return <div>User not found</div>;
    }

    return (
        <div className="user-profile">
            <div className="profile-info">
                <div className="grid-container">
                    <div className="profile-image-container">
                        <img
                            src={user.profileimage}
                            alt="ProfileImage"
                            className="profile-image"
                        />
                    </div>
                    <div className="username-email-container">
                        <h1>{user.username}</h1>
                        <p>{user.email}</p>
                        <div >
                            <button className="edit-button" onClick={handleEditProfile}>
                                Edit
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            {/*<DeleteUser userId={userId} />*/}

            <h2>Posts</h2>
            {posts.map((post) => (
                <div key={post.id} className="post-content">
                    <Link
                        to={`/post/${post.id}`}
                        className="link-black align-by-first-letter"
                        onClick={() => navigateToPostPage(post.id)}
                    >
                        <h3>{post.title}</h3>
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
                </div>
            ))}
        </div>
    );


}

export default UserProfilePage;
