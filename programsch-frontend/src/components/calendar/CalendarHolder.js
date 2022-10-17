import React from 'react'
import './Calendar.css'
import Calendar from "./Calendar";

export function CalendarHolder() {
    return (
        <div className="container" id="calendar">
            <Calendar/>
        </div>
    );

}

export default CalendarHolder;



