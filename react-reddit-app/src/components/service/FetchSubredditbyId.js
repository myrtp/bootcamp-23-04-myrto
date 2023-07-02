function fetchSubredditbyId(subredditId, jwt) {
    fetch(`http://localhost:8080/reddit/subreddits/${subredditId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + jwt,
        },
    }).then((response) => response.json());
}
export default fetchSubredditbyId;