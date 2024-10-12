import React from "react";
import {StrictMode} from "react";
import {createRoot} from "react-dom/client";
import App from "./App";
import "./index.css";
import {Provider} from "react-redux";
import {store} from "@/state/store.ts";
import {BrowserRouter} from "react-router-dom";

createRoot(document.getElementById("root") as HTMLElement).render(
    <Provider store={store}>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </Provider>
);
