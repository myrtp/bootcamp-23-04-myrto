# Reddit-like App


## Overview
This project is a web application that provides a REST API for managing users, posts, and comments. The backend is developed using Java with the Spring Boot framework, while the frontend is built with React.
The objectives of this web application are:
- Handling resources. To manage the available data of the resources provided the crude operations put, delete and post can be executed for each. It is worth noting that log in and sign up actions for the user are supported.
- Visualise data. In order to view the contents of each resource the crude operation get is executed, to fetch data on the frontend part of the app.


## Technologies
The project uses the following technologies:

- **Backend**:
    - Java - v17
    - Spring Boot - v3.0.6
    - Maven (for dependency management)
    - PostgreSQL - v15

- **Frontend**:
    - React - v6
    - Node.js - v18
    - html
    - css

## Prerequisites
Before running the application, ensure that you have the following software installed:
- npm
- Node.js: It can be installed via terminal via the command: `npm install node.js`
- postgreSQL


## Usage
To run the application, follow these steps:
- Open a new terminal.
- Navigate to: `cd restapi`
- Run the command: `mvn spring-boot:run`
- 
1. Set up the database locally: 
    - Navigate to path:`database/src/main/resources`
    - Run the migrations in PostgreSQL console: `V1__init_reddit_database.sql` and `V2__init_reddit_database.sql`
2. Start the backend server:
   Run ReastApiApplication

3. Start the frontend development server:
    - Open a new terminal.
    - Navigate to: `cd react-reddit-app`
    - Run the command: `npm start`

3. Access the application in your web browser at: `http://localhost:3000`



## API Documentation Example
The API provides the following endpoints:

- `GET /users`: Retrieve a list of users.
- `GET /users/{userId}`: Retrieve details of a specific user.
- `POST /users`: Create a new user.
- `PUT /users/{userId}`: Update a user.
- `DELETE /users/{userId}`: Delete a user.


- `GET /posts`: Retrieve a list of posts.
- `GET /posts/{postId}`: Retrieve details of a specific post.
- `POST /posts`: Create a new post.
- `PUT /posts/{postId}`: Update a post.
- `DELETE /posts/{postId}`: Delete a post.
- 

- `GET /subreddits`: Retrieve a list of subreddits.
- `GET /subreddits/{subredditId}`: Retrieve details of a specific subreddits.
- `POST /subreddits`: Create a new subreddit.
- `PUT /subreddits/{subredditId}`: Update a subreddits.
- `DELETE /subreddits/{subredditId}`: Delete a subreddits.
- 

- `GET /comments`: Retrieve a list of comments.
- `GET /comments/{commentId}`: Retrieve details of a specific comments.
- `POST /comments`: Create a new comment.
- `PUT /comments/{commentId}`: Update a comments.
- `DELETE /comments/{commentId}`: Delete a comments.

## Screenshots
<img src="login_signup.png" alt="login_signup" width="600"/>\
Log in or Sign up Page with authentication

<img src="main_page.png" alt="mainpage" width="600"/>\
Main Page: Showcases posts, available communities and create community action.

<img src="profile_pg.png" alt="profile" width="600"/>\
User profile page.

<img src="edit_profile.png" alt="edit profile" width="600"/>\
Edit profile page action.

<img src="subreddit_pg.png" alt="subreddit" width="600"/>\
Community page: Features community posts, description, create post action and join community button.

