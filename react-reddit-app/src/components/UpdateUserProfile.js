import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {extractUserDetails} from "./service/DecodeJWT";

function EditProfilePage() {
    const [user, setUser] = useState(null);
    const navigate = useNavigate();
    const { userId } = useParams();
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
    }, [decodedUserId]);

    const handleUpdate = () => {
        const updatedUser = {
            id: decodedUserId,
            username: user.username,
            email: user.email,
            password: user.password,
            profileimage: user.profileimage,
            dob: user.dob,
        };

        fetch(`http://localhost:8080/reddit/users/${decodedUserId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,

            },
            body: JSON.stringify(updatedUser),
        })
            .then((response) => response.json())
            .then((updatedUser) => {
                console.log("User updated successfully:", updatedUser);
                setUser(updatedUser);
                navigate(`/profile/${decodedUserId}`);
            })
            .catch((error) => {
                console.error("Error updating user:", error);
            });
    };

    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <div className="edit-profile login-form">
            <h1>Edit Profile</h1>
            <label>
                Username:
                <input
                    type="text"
                    value={user.username}
                    onChange={(e) => setUser({ ...user, username: e.target.value })}
                />
            </label>
            <label>
                Email:
                <input
                    type="email"
                    value={user.email}
                    onChange={(e) => setUser({ ...user, email: e.target.value })}
                />
            </label>
            <label>
             Password:
            <input
                type="password"
                value={user.password}
                onChange={(e) => setUser({ ...user, password: e.target.value })}
            />
            </label>

            <label>Profile Image:
            <input
                type="text"
                value={user.profileimage}
                onChange={(e) => setUser({ ...user, profileimage: e.target.value })}
            />
            </label>

            {/* id and dob fields are not displayed */}
            <button className="update-button" onClick={handleUpdate}>Save</button>
        </div>
    );
}

export default EditProfilePage;