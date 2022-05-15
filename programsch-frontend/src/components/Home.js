import React from 'react';
import AppNavbar from './AppNavbar';
import CalendarHolder from "./CalendarHolder";
import Footer from "./Footer";

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

