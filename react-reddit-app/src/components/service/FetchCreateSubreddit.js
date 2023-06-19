fetch(`http://localhost:8080/reddit/subreddits`, {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + jwt,
    },
    body: JSON.stringify(subredditForm), // Pass the subredditForm in the request body
})