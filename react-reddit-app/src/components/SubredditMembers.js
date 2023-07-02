import { useEffect, useState } from "react";
import fetchCommentsForPost from "./service/FetchCommentsForPost";
import fetchSubredditbyId from "./service/FetchSubredditbyId";
import {useLocation, useParams} from "react-router-dom";
let jwt = localStorage.getItem("jwt")

function SubredditMembers({subredditId}) {

    const [members, setMembers] = useState([]);
    const [subreddit, setSubreddit] = useState(null);
    const location = useLocation();

    useEffect(() => {

            fetch(`http://localhost:8080/reddit/subreddits/${subredditId}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + jwt,
                },
            })
                .then((response) => response.json())
                .then((subreddit) => {
                    console.log(subreddit);
                    setSubreddit(subreddit);
                })
                .catch((error) => console.log(error));



            fetch(`http://localhost:8080/reddit/subreddits/${subredditId}/members`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + jwt,
            },
        })
            .then((response) => response.json())
            .then((members) => {
                console.log(members);
                setMembers(members);
            })
            .catch((error) => console.log(error));
    }, [subredditId]);

    // if (location.pathname !== "/subreddits/:subredditId") {
    //     return ( <div className="side-element">
    //     </div>);
    // }

    return (
        <div className="subreddit-list-container">
            <h3>Members:</h3>
            {members.map((member) => (
                <div key={member.id}>
                    <p>{member.username}</p>
                </div>
            ))}
        </div>
    );
}

export default SubredditMembers;
