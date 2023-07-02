import { useNavigate } from "react-router-dom";

function DeleteUser({ userId }) {
    const navigate = useNavigate();
    const jwt = localStorage.getItem("jwt");

    const handleDelete = () => {
        fetch(`http://localhost:8080/reddit/users/${userId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => response.json())
            .then((data) => {
                console.log("User deleted successfully:", data);
                navigate(`/`);
            })
            .catch((error) => {
                console.error("Error deleting user:", error);
            });
    };

    // return (
    //     // <div>
    //     //     {/*<button onClick={handleDelete}>Delete User</button>*/}
    //     // </div>
    // );
}

export default DeleteUser;
