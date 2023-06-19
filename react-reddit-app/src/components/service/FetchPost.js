function fetchPostbyId(postId, jwt) {


    fetch(`http://localhost:8080/reddit/posts/${postId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + jwt,
        },
    }).then((response) => response.json());
}
export default fetchPostbyId;
