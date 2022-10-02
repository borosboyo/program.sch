import React from 'react';
import AppNavbar from './banner/AppNavbar';
import CalendarHolder from "./calendar/CalendarHolder";
import Footer from "./banner/Footer";

export class Home extends React.Component {

    render() {
        return (
            <div>
                <AppNavbar/>
                <CalendarHolder/>
                <Footer/>
            </div>
        );
    }
}

