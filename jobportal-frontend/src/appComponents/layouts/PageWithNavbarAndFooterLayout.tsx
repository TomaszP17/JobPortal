import React from 'react';
import Navbar from "@/appComponents/organisms/Navbar.tsx";
import {Footer} from "@/appComponents/organisms/Footer.tsx";

interface PageWithNavbarAndFooterLayoutProps {
    children: React.ReactNode;
}

const PageWithNavbarAndFooterLayout = ({children}: PageWithNavbarAndFooterLayoutProps) => {
    return (
        <div className={"flex flex-col min-h-screen"}>
            <Navbar/>
            <main className={"flex-grow"}>
                {children}
            </main>
            <Footer/>
        </div>
    )
}

export default PageWithNavbarAndFooterLayout;