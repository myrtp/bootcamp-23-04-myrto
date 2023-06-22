import './assets/default.css'
import Header from "./components/Header";
import Footer from "./components/Footer";
import PostContent from "./components/PostContent";
import {BrowserRouter, Route, Routes, useLocation} from "react-router-dom";
import SubredditsByTitle from "./components/SubredditsByTitle";
import SubredditPage from "./components/SubredditPage";
import LoginContent from "./components/LoginContent";
import UserProfilePage from "./components/UserProfilePage";
import WelcomePage from "./components/WelcomePage";
import UpdateUserProfile from "./components/UpdateUserProfile";
import PostPage from "./components/PostPage";
import CreateSubreddit from "./components/CreateSubreddit";
import EditSubredditPage from "./components/UpdateSubredditPage";
import SubredditMembers from "./components/SubredditMembers";
import JoinSubreddit from "./components/JoinSubreddit";


function App() {
    return (
        <>
            <BrowserRouter>
                <Header/>
                <div className="content-wrapper">

                    <SubredditsByTitle/>
                     {/*<SubredditMembers/>*/}

                    <Routes>
                        <Route path="/" element={<WelcomePage/>} />
                        <Route path="/auth" element={<LoginContent/>}/>
                        <Route path="/home" element={<PostContent/>}/>
                        <Route path="/subreddits/:subredditId" element={<SubredditPage/>}/>
                        <Route path="/profile/:userId" element={<UserProfilePage/>}/>
                        <Route path="/profile/:userId/edit" element={<UpdateUserProfile/>}/>
                        <Route path="/post/:postId" element={<PostPage />} />
                        <Route path="/subreddit/create" element={<CreateSubreddit/>} />
                        <Route path="/subreddits/:subredditId/edit" element={<EditSubredditPage/>}/>
                        <Route path="/subreddits/:subredditId/join" element={<JoinSubreddit/>}/>


                    </Routes>

                </div>
                <Footer/>
            </BrowserRouter>
        </>
    );
}

export default App;