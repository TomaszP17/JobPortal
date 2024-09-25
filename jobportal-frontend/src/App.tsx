import React from "react";
import "./App.css";
import {Routes, Route} from 'react-router-dom';
import RegisterPage from "@/appComponents/pages/RegisterPage.tsx";

function App() {

    return (
        <Routes>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/ciopa" element={<ProfilePage/>}/>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/complete-profile" element={<CompleteProfilePage/>}/>
            <Route path={"/oauth2/redirect"} element={<OAuth2RedirectHandler/>}/>
        </Routes>
    )
}

export default App;
