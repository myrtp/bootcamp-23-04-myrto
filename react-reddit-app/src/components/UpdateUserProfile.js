import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EditProfilePage() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [profileImage, setProfileImage] = useState("");
    const navigate = useNavigate();
    const { userId } = useParams();

    useEffect(() => {
        fetch(`http://localhost:8080/reddit/users/${userId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((response) => response.json())
            .then((user) => {
                setUsername(user.username);
                setEmail(user.email);
                setProfileImage(user.profileimage);
            })
            .catch((error) => console.log(error));
    }, [userId]);

    const handleUpdate = () => {
        const updatedUser = {
            id: userId,
            username: username,
            email: email,
            profileimage: profileImage,
        };

        fetch(`http://localhost:8080/reddit/users/${userId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedUser),
        })
            .then((response) => response.json())
            .then((updatedUser) => {
                console.log("User updated successfully:", updatedUser);
                navigate(`/profile/${userId}`);
            })
            .catch((error) => {
                console.error("Error updating user:", error);
            });
    };

    return (
        <div className="edit-profile">
            <h1>Edit Profile</h1>
            <label>
                Profile Image:
                <input
                    type="text"
                    value={profileImage}
                    onChange={(e) => setProfileImage(e.target.value)}
                />
            </label>
            <label>
                Username:
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
            </label>
            <label>
                Email:
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </label>
            <button onClick={handleUpdate}>Save</button>
        </div>
    );
}

export default EditProfilePage;
