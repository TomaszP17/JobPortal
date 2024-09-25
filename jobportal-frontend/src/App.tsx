import React from "react";
import "./App.css";
import {Routes, Route} from 'react-router-dom';
import RegisterPage from "@/appComponents/pages/RegisterPage.tsx";
import ProfilePage from "@/appComponents/pages/ProfilePage.tsx";

function App() {

    return (
        <Routes>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/ciopa" element={<ProfilePage/>}/>
        </Routes>
    )
}

export default App;
