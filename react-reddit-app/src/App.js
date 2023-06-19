import './assets/default.css'
import Header from "./components/Header";
import Footer from "./components/Footer";
import PostContent from "./components/PostContent";
import {BrowserRouter, Route, Routes, useLocation} from "react-router-dom";
import SubredditsByTitle from "./components/SubredditsByTitle";
import SubredditPage from "./components/SubredditPage";
import SubredditFunctions from "./components/SubredditFunctions";
import LoginContent from "./components/LoginContent";
import UserProfilePage from "./components/UserProfilePage";
import WelcomePage from "./components/WelcomePage";
import UpdateUserProfile from "./components/UpdateUserProfile";
import PostPage from "./components/PostPage";
import CreateSubreddit from "./components/CreateSubreddit";


function App() {
    return (
        <>
            <BrowserRouter>
                <Header/>
                <div className="content-wrapper">

                    <SubredditsByTitle/>

                    <Routes>
                        <Route path="/" element={<WelcomePage/>} />
                        <Route path="/auth" element={<LoginContent/>}/>
                        <Route path="/home" element={<PostContent/>}/>
                        <Route path="/subreddits/:subredditId" element={<SubredditPage/>}/>
                        <Route path="/profile/:userId" element={<UserProfilePage/>}/>
                        <Route path="/profile/:userId/edit" element={<UpdateUserProfile/>}/>
                        <Route path="/post/:postId" element={<PostPage />} />
                        <Route path="/subreddit/create" element={<CreateSubreddit/>} />

                    </Routes>

                </div>
                <Footer/>
            </BrowserRouter>
        </>
    );
}

export default App;