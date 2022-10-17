import React, {useEffect, useState} from 'react'
import FullCalendar from '@fullcalendar/react' // must go before plugins
import dayGridPlugin from '@fullcalendar/daygrid' // a plugin!
import timeGridPlugin from '@fullcalendar/timegrid' // a plugin
import {Tooltip} from "bootstrap";

let tooltipInstance = null;

export function Calendar() {

    const [filteredEvents, setFilteredEvents] = useState([]);
    const [events, setEvents] = useState([]);
    const [filtersEnabled, setFiltersEnabled] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        handleGetLoginState();
        handleGetFilterState();
        fetchFilteredEvents();
        fetchEvents();
        //reloadPage();
    });

    const reloadPage = () => {
        const reloadCount = sessionStorage.getItem('reloadCount');
        if (reloadCount < 2) {
            sessionStorage.setItem('reloadCount', String(reloadCount + 1));
            window.location.reload();
        } else {
            sessionStorage.removeItem('reloadCount');
        }
    }

    const handleGetLoginState = () => {
        fetch(`http://localhost:8080/api/isLoggedIn`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setIsLoggedIn(data))
    }

    const handleGetFilterState = () => {
        fetch(`http://localhost:8080/api/filter/filtersEnabled`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setFiltersEnabled(data));
    }

    const fetchFilteredEvents = () => {
        fetch(`http://localhost:8080/api/event/calendar/filtered`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setFilteredEvents(data));
    }

    const fetchEvents = () => {
        fetch(`http://localhost:8080/api/event/calendar`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setEvents(data));
    }

    const handleMouseEnter = (info) => {
        if (info.event.extendedProps.description) {
            tooltipInstance = new Tooltip(info.el, {
                title: info.event.extendedProps.description,
                html: true,
                placement: "top",
                trigger: "hover",
                container: "body"
            });
            tooltipInstance.show();
        }
    }

    const handleMouseLeave = (info) => {
        if (tooltipInstance) {
            tooltipInstance.dispose();
            tooltipInstance = null;
        }
    }

    const render = () => {
        let displayEvents = events;
        if (isLoggedIn) {
            if (filtersEnabled) {
                displayEvents = filteredEvents;
            }
        }

        return (
            <FullCalendar
                plugins={[dayGridPlugin, timeGridPlugin]}
                initialView="timeGridWeek"
                timeGridWeekCount={2}
                slotDuration={'00:30:00'}
                events={displayEvents}
                headerToolbar={{
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                }}
                locale={'hu-HU'}
                eventClick={(info) => {
                    window.location.href = `/eventview/${info.event.id}`
                }}
                navLinks={true}
                navLinkDayClick={(date) => {
                    const day = (date.getMonth() + 1) + "-" + date.getDate();
                    window.location.href = `/dayview/${day}`
                }}
                eventMouseEnter={handleMouseEnter}
                eventMouseLeave={handleMouseLeave}
            />
        )
    }

    return render();
}

export default Calendar;
