import './App.css';
import './assets/default.css'
import Header from "./components/Header";
import Footer from "./components/Footer";
import PostContent from "./components/PostContent";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SubredditsByTitle from "./components/SubredditsByTitle";
import SubredditPage from "./components/SubredditPage";

function App() {
return (
    <>
      <Header/>
      <div className="content-wrapper">


          <BrowserRouter>
              <SubredditsByTitle/>

              <Routes>
            <Route path="/home" element={<PostContent/>}/>
            <Route path="/subreddits/:subredditId" element={<SubredditPage />} />

          </Routes>

          </BrowserRouter>
      </div>

        <Footer/>
    </>
);

}
export default App;
