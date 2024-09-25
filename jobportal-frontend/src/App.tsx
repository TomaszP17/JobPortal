import React from "react";
import "./App.css";
import {Routes, Route} from 'react-router-dom';
import RegisterPage from "@/appComponents/pages/RegisterPage.tsx";

function App() {

    return (
        <Routes>
          <Route path="/register" element={<RegisterPage/>}/>
        </Routes>
    )
}

export default App;
