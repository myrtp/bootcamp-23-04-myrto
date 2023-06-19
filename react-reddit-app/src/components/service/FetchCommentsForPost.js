function fetchCommentsForPost(postId, jwt) {
    return fetch(`http://localhost:8080/reddit/comments/${postId}/comments`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + jwt,
        },
    }).then((response) => response.json());
}
export default fetchCommentsForPost;


