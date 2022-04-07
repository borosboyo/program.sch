import React from 'react';
import AppNavbar from './AppNavbar';
import Calendar from "./Calendar";

export class Home extends React.Component {

    render() {
        return (
            <div>
                <AppNavbar/>
                <Calendar/>
            </div>
        );
    }
}

