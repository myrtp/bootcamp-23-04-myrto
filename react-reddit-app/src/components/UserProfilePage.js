import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import EditProfilePage from "./UpdateUserProfile";

function UserProfilePage() {
    const { userId } = useParams();
    const [user, setUser] = useState(null);
    const [posts, setPosts] = useState([]);
    const navigate = useNavigate();

    const jwt = localStorage.getItem("jwt");

    useEffect(() => {
        fetch(`http://localhost:8080/reddit/users/profile`, {
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

        fetch(`http://localhost:8080/reddit/posts/user/${userId}`, {
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
    }, [userId]);

    // const handleEditProfile = () => {
    //     navigate(`/profile/${userId}/edit`);
    // };

    if (!user) {
        return <div>User not found</div>;
    }

    return (
        <div className="user-profile">
            <div className="profile-info">
                <img
                    src={user.profileimage}
                    alt="ProfileImage"
                    className="profile-image"
                />
                <div className="profile-details">
                    <h1>{user.username}</h1>
                    <p>{user.email}</p>

                </div>
            </div>
        </div>
    );
}

export default UserProfilePage;
