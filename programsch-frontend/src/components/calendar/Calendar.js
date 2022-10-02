import React from 'react'
import FullCalendar from '@fullcalendar/react' // must go before plugins
import dayGridPlugin from '@fullcalendar/daygrid' // a plugin!
import timeGridPlugin from '@fullcalendar/timegrid' // a plugin
import {Tooltip} from "bootstrap";

let tooltipInstance = null;
export default class Calendar extends React.Component {

    constructor(props) {
        super(props);
        this.state = {filteredEvents: [], events: [], filtersEnabled: false, isLoggedIn: false};
        this.fetchFilteredEvents = this.fetchFilteredEvents.bind(this);
        this.fetchEvents = this.fetchEvents.bind(this);
        this.handleGetLoginState = this.handleGetLoginState.bind(this);
        this.handleMouseEnter = this.handleMouseEnter.bind(this);
        this.handleMouseLeave = this.handleMouseLeave.bind(this);
    }

    componentDidMount() {
        this.handleGetLoginState();
        this.handleGetFilterState();
        this.fetchFilteredEvents();
        this.fetchEvents();
        this.reloadPage();
    }

    reloadPage() {
        const reloadCount = sessionStorage.getItem('reloadCount');
        if (reloadCount < 2) {
            sessionStorage.setItem('reloadCount', String(reloadCount + 1));
            window.location.reload();
        } else {
            sessionStorage.removeItem('reloadCount');
        }
    }

    handleGetLoginState() {
        fetch(`http://localhost:8080/api/isLoggedIn`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({isLoggedIn: data}))
    }

    handleGetFilterState() {
        fetch(`http://localhost:8080/api/filter/filtersEnabled`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({filtersEnabled: data}));
    }

    fetchFilteredEvents() {
        fetch(`http://localhost:8080/api/event/calendar/filtered`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({filteredEvents: data}))
    }

    fetchEvents() {
        fetch(`http://localhost:8080/api/event/calendar`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({events: data}))
    }

    handleMouseEnter(info) {
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

    handleMouseLeave() {
        if (tooltipInstance) {
            tooltipInstance.dispose();
            tooltipInstance = null;
        }
    }

    render() {
        let events = this.state.events;
        if (this.state.isLoggedIn) {
            if (this.state.filtersEnabled) {
                events = this.state.filteredEvents;
            }
        }

        return (
            <FullCalendar
                plugins={[dayGridPlugin, timeGridPlugin]}
                initialView="timeGridWeek"
                timeGridWeekCount={2}
                slotDuration={'00:30:00'}
                events={events}
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
                eventMouseEnter={this.handleMouseEnter}
                eventMouseLeave={this.handleMouseLeave}
            />
        )
    }
}
