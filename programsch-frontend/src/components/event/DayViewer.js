import './EventViewer.css';
import React from 'react';
import AppNavbar from "../banner/AppNavbar";
import GroupsIcon from "@mui/icons-material/Groups";
import SentimentDissatisfiedIcon from '@mui/icons-material/SentimentDissatisfied';
import moment from "moment";
import {Event} from "./Event";


export class DayViewer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {dayEvents: [], loading: false}
    }

    componentDidMount() {
        this.setState({loading: true});
        this.fetchDay();
    }

    fetchDay() {
        fetch(`http://localhost:8080/api/event/day?date=${this.props.match.params.id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({dayEvents: data, loading: false}))
    }

    handleDelete(event) {
        fetch(`http://localhost:8080/api/event/${event.id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
        })
        this.props.history.push("/");
    }

    render() {
        console.log(this.state.dayEvents);
        const dayEvents = this.state.dayEvents.map(currentEvent =>
            <div className="container justify-content-center" key={currentEvent.name}>
                <div className="card" id="card">
                    <div className="card-header"><GroupsIcon/> {currentEvent.circle.displayName}</div>
                    <img src={currentEvent.poster} className="card-img-top" alt="..."/>
                    <Event currentEvent={currentEvent} onClick={() => this.handleDelete()}/>
                </div>
            </div>
        );

        if (this.state.loading === true) {
            return <div>Loading...</div>
        }

        if (this.state.dayEvents.length === 0) {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container justify-content-center">
                        <div className="card" id="card">
                            <div className="card-header">Hoppá!</div>
                            <div className="card-body">
                                <h5 className="card-title text-center"> A mai napra nem találhatóak
                                    események! <SentimentDissatisfiedIcon/></h5>
                            </div>
                        </div>
                    </div>
                </div>
            )
        } else {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container justify-content-center">
                        <div className="card" id="card">
                            <div className="card-header">{moment(this.props.match.params.id).format("MM. DD.")}</div>
                        </div>
                    </div>
                    {dayEvents}
                </div>
            );
        }
    }
}
