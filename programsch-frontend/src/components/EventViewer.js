import React from 'react';
import AppNavbar from "./AppNavbar";
import Footer from "./Footer";
import '../css/EventViewer.css';

export class EventViewer extends React.Component {

    constructor(props){
        super(props);
        this.state = {currentEvent: {}, currentCircle: {}};
    }

    componentDidMount(){
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

    render() {
        const currentEvent = this.state.currentEvent;
        console.log(currentEvent);
        const currentCircle = this.state.currentCircle;
        return (
            <div>
                <AppNavbar/>
                <div className="container justify-content-center">
                    <div className="card" id="card">
                        <div className="card-header">{currentCircle.displayName}</div>
                        <img src="https://program.sch.bme.hu/storage/posters/836_78615017d6d1591c85831ad43a427180aac8f80d.png" className="card-img-top" alt="..."/>
                        <div className="card-body">
                            <h5 className="card-title">{currentEvent.name}</h5>
                            <p className="card-text">Some quick example text to build on the card title and make up the
                                bulk of the card's content.</p>
                            <a href="#" className="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}
