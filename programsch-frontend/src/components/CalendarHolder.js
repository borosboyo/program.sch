import React from 'react'
import '../css/Calendar.css'
import Calendar from "./Calendar";

export default class CalendarHolder extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    render() {
        return (
            <div className="container" id="calendar">
                <Calendar/>
            </div>
        );
    }
}



