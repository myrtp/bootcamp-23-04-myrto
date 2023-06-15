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
                    </Routes>

                </div>
                <Footer/>
            </BrowserRouter>
        </>
    );
}

export default App;