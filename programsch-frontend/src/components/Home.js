import React from 'react';
import CalendarHolder from "./calendar/CalendarHolder";
import Footer from "./banner/Footer";
import AppNavbar from "./banner/AppNavbar";

export function Home() {

    return (
        <div>
            <AppNavbar/>
            <CalendarHolder/>
            <Footer/>
        </div>);
}

export default Home;
