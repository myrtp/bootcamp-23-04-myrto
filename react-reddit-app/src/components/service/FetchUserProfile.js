import {useEffect} from "react";
import fetchPostbyId from "./FetchPost";

function fetchUserProfileId(userId, jwt) {

        fetch(`http://localhost:8080/reddit/users/${userId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        }).then((response) => response.json())

    }
export default fetchUserProfileId;
