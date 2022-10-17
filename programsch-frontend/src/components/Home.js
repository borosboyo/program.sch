import React from 'react';
import AppNavbar from './banner/AppNavbar';
import CalendarHolder from "./calendar/CalendarHolder";
import Footer from "./banner/Footer";

export function Home() {

    return (
        <div>
            <AppNavbar/>
            <CalendarHolder/>
            <Footer/>
        </div>);
}

export default Home;
