import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

function PostContent() {
    const [posts, setPosts] = useState([]);
    const [commentsPerPost, setCommentsPerPost] = useState({});
    const [users, setUsers] = useState([]);
    // localStorage.setItem("jwt", "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJteXJ0b3AxMzMiLCJhIjoiYiIsImlzcyI6ImJvb3RjYW1wLTIzLTA0IiwiZXhwIjoxNjg2ODI4OTIxLCJpYXQiOjE2ODY3NDI1MjEsInVzZXJJZCI6NiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlVTRVJfQVVUSEVOVElDQVRJT04ifV0sInVzZXJuYW1lIjoibXlydG9wMTMzIn0.MS0GT9QPib_qN7IK7w7sjNivdsTh1uk7V-YwiFJjD9bKIreLaf8mNIBpNQm3nGDlVRu6qbxHz8rC6AuuqUTaKzv0c8OhlgpmvV64tA5yhX6hO4zg6mkIkqfvKrhMmIPFDYUb1WEfW07BX31WgIWognl7OVf6LUzJC8qMeBX8u5P49xVgxXcL2UMy8UpdyBYxEXpzdhMN_dNOxtpP1kjzQc7mY-tuhj7tpRvIvqlQ50_ua2VcsxUUNW70Cv5IB7lZSLQIyv0ugAGygd_5kzFyhcd3zRauYFCVf9NkdSn3tQIQWSi8lNLd_HR1RhPkD4XLNU3oaqCQdyWrY59yjVn8HQ"
    let jwt = localStorage.getItem("jwt")

    const fetchCommentsForPost = (post) => {
        fetch(`http://localhost:8080/reddit/comments/${post.id}/comments`, {

            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + jwt
            }

        }).then((response) => response.json())
            .then((comments) => {
                post.comments = comments;
                // {1:[], 2:[], 3:[]}
                setCommentsPerPost({...commentsPerPost, [post.id]: comments})
            })
    }

    const setPostsAndCommentsToState= (posts) => {
        console.log(posts);
        posts.forEach( (post) => {
            fetchCommentsForPost(post);
        })
        setPosts(posts);
    }

    function getPostByDateDesc() {
        return fetch(`http://localhost:8080/reddit/posts/home`, {

            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + jwt
            }

        })
            .then((response) => response.json());
    }

    const fetchPosts = () => {
        getPostByDateDesc()
            .then(setPostsAndCommentsToState)
            .catch((error) => console.log(error));
    }

    const fetchUsers = () => {
        fetch('http://localhost:8080/reddit/users', {

            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + jwt
            }
        })
            .then((response) => response.json())

            .then((user) => {
                console.log(user);
                setUsers([user]); // Wrap the user object in an array
            })
            .catch((error) => console.log(error));
    }

    useEffect(() => {

        fetchPosts();
        fetchUsers();

    }, []);