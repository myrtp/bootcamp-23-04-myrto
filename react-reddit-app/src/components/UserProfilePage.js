import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function UserProfilePage() {
    const { userId } = useParams();
    const [user, setUser] = useState(null);
    const [userPosts, setUserPosts] = useState([]);

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
                console.log(user);
                setUser(user);
            })
            .catch((error) => console.log(error));
    },  [ jwt]);

    if (!user) {
        return <div>!User does not exist!</div>;
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