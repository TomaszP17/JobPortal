import React from "react";
import "./App.css";
import {Routes, Route} from 'react-router-dom';
import RegisterPage from "@/appComponents/pages/RegisterPage.tsx";
import ProfilePage from "@/appComponents/pages/ProfilePage.tsx";
import {CompleteProfilePage} from "@/appComponents/pages/CompleteProfilePage.tsx";
import {OAuth2RedirectHandler} from "@/appComponents/molecules/OAuth2RedirectHandler.tsx";
import {OffersPage} from "@/appComponents/pages/OffersPage.tsx";
import LoginPage from "@/appComponents/pages/LoginPage.tsx";

function App() {

    return (
        <Routes>
            <Route path="/" element={<OffersPage/>}/>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/profile" element={<ProfilePage/>}/>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/complete-profile" element={<CompleteProfilePage/>}/>
            <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler/>}/>
            <Route path="/login" element={<LoginPage/>}/>
        </Routes>
    )
}

export default App;
