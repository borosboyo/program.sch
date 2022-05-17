import React from 'react';
import AppNavbar from "../banner/AppNavbar";
import GroupsIcon from '@mui/icons-material/Groups';
import './EventViewer.css';
import {Event} from "./Event";

export class EventViewer extends React.Component {

    constructor(props) {
        super(props);
        this.state = {currentEvent: {}, currentCircle: {}};
    }

    componentDidMount() {
        this.fetchEvent();
    }

    fetchEvent() {
        fetch(`http://localhost:8080/api/event/${this.props.match.params.id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({currentEvent: data, currentCircle: data.circle}))
    }

    handleDelete() {
        fetch(`http://localhost:8080/api/event/${this.props.match.params.id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
        })
        this.props.history.push("/");
    }

    render() {
        const currentEvent = this.state.currentEvent;
        const currentCircle = this.state.currentCircle;
        return (
            <div>
                <AppNavbar/>
                <div className="container justify-content-center">
                    <div className="card" id="card">
                        <div className="card-header"><GroupsIcon/> {currentCircle.displayName}</div>
                        <img src={currentEvent.poster} className="card-img-top" alt="..."/>
                        <Event currentEvent={currentEvent} onClick={() => this.handleDelete()}/>
                    </div>
                </div>
            </div>
        );
    }

}
