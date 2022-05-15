import React from 'react'
import FullCalendar from '@fullcalendar/react' // must go before plugins
import dayGridPlugin from '@fullcalendar/daygrid' // a plugin!
import timeGridPlugin from '@fullcalendar/timegrid' // a plugin

export default class Calendar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {events: []};
    }

    componentDidMount() {
        this.fetchEvents();
    }

    fetchEvents(){
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

    render() {
        return (
            <FullCalendar
                plugins={[ dayGridPlugin, timeGridPlugin ]}
                initialView="timeGridWeek"
                timeGridWeekCount={2}
                slotDuration={'00:30:00'}
                events={
                    this.state.events
                }
                headerToolbar={{
                    left: 'prev,next today',
                    center: 'title',
                    right: 'dayGridMonth,timeGridWeek,timeGridDay'
                }}
                locale={'hu-HU'}
                eventClick={(info) => {
                    window.location.href = `/event/${info.event.id}`
                }}
            />
        )
    }
}
