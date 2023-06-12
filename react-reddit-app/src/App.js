import './App.css';
import './assets/default.css'
import Header from "./components/Header";
import Footer from "./components/Footer";
import PostContent from "./components/PostContent";
import {BrowserRouter, Route, Routes from "react-router-dom";


function App() {
return (
    <>
      <Header/>
      <div className="content-wrapper">

        <aside>
          {/*<!-- Your aside content goes here -->*/}
          <h2>Aside</h2>
        </aside>
        <BrowserRouter>
          <Routes>
            <Route path="/home" element={<PostContent/>}/>
          </Routes>
        </BrowserRouter>
      </div>


    </>
);

}
export default App;
