import React from 'react';
import AppNavbar from "./AppNavbar";
import Footer from "./Footer";
import '../css/EventCreator.css';
import {Button, Form, Input, Label, Select} from 'reactstrap';
export class EventCreator extends React.Component {

    newEvent : {
        name :  '',
        resort :  '',
        circle :  '',
        start :  '',
        end :  '',
        place :  '',
        facebookUrl :  '',
        poster :  '',
        tldr :  '',
        descirption :  '',
    }

    constructor(props) {
        super(props);

        this.state = {resorts: [], circles: [], selectedResort: {}, event: this.newEvent};
        this.handleResortChange= this.handleResortChange.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleApiCreateCall = this.handleApiCreateCall.bind(this);

    }

    componentDidMount() {
        this.fetchResorts();
    }

    fetchResorts(){
        fetch(`http://localhost:8080/api/resort`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({resorts: data}));
    }

    handleSubmit(e) {
        e.preventDefault()
        const {event} = this.state;

        this.handleApiCreateCall(event)

        this.props.history.push('/');
    }

    handleResortChange(e) {
        this.handleChange(e);
        fetch(`http://localhost:8080/api/resort/` + e.target.value, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({selectedResort: data}));
    }

    handleActualCircleList() {
        const circles = this.state.selectedResort.circles;
        if(circles !== undefined) {
            return circles.map(circle =>
                <option key={circle.displayName} >{circle.displayName}</option>
            );
        }
    }

    handleApiCreateCall(newEvent) {
        console.log(newEvent);
        fetch('http://localhost:8080/api/event/', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newEvent),
        });
    }

    handleChange(e) {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        let event = {...this.state.event};
        event[name] = value;
        this.setState({event});
    }

    render() {
        const resortList = this.state.resorts.map(resort =>
            <option key={resort.name}>{resort.name}</option>
        );

        const circleList = this.handleActualCircleList();

        return (
            <div>
                <AppNavbar/>
                <div className="container" id="eventCardHolder">
                    <div className="card">
                        <h5 className="card-header">Új program létrehozása</h5>
                        <div className="card-body">
                            <Form id="form" onSubmit={this.handleSubmit}>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <Label for="name">Program neve</Label>
                                        <Input type="text" className="form-control" id="name" name="name"
                                               placeholder="" onChange={this.handleChange}/>
                                    </div>
                                </div>
                                <div className="form-row">
                                        <Label className="my-1 mr-2" for="resort">Reszort</Label><br/>
                                        <Input type={"select"} className="custom-select my-1 mr-sm-2" id="resort" name="resort" onChange={this.handleResortChange} defaultValue={"Válassz.."}>
                                            <option hidden/>
                                            {resortList}
                                        </Input>
                                </div>
                                <div className="form-row">
                                    <Label className="my-1 mr-2" for="circle">Kör</Label><br/>
                                    <Input type={"select"} className="custom-select my-1 mr-sm-2" id="circle" name="circle" onChange={this.handleChange}>
                                        <option hidden/>
                                        {circleList}
                                    </Input>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <Label for="start">Esemény kezdete</Label>
                                        <Input type="datetime-local" className="form-control" id="start" name="start"
                                               placeholder="" onChange={this.handleChange}/>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <Label for="end">Esemény vége</Label>
                                        <Input type="datetime-local" className="form-control" id="end" name="end"
                                               placeholder="" onChange={this.handleChange}/>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <Label for="place">Helyszín</Label>
                                        <Input type="text" className="form-control" id="place" name="place"
                                               placeholder="" onChange={this.handleChange}/>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <Label for="facebookUrl">Facebook esemény linkje</Label>
                                        <Input type="text" className="form-control" id="facebookUrl" name="facebookUrl"
                                               placeholder="" onChange={this.handleChange}/>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <Label for="poster">Plakát linkje</Label>
                                        <Input type="text" className="form-control" id="poster" name="poster"
                                               placeholder="" onChange={this.handleChange}/>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <Label for="tldr">Program TLDR</Label>
                                        <Input type="text" className="form-control" id="tldr" name="tldr"
                                               placeholder="" onChange={this.handleChange}/>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <Label for="description">Az esemény leírása</Label>
                                        <textarea id="description" name="description" rows="4" cols="50" onChange={this.handleChange}>
                                        </textarea>
                                    </div>
                                </div>
                                <Button type="submit" className="btn btn-primary">Létrehozás</Button>
                            </Form>
                        </div>
                    </div>
                </div>
                <Footer/>
            </div>
        );
    }
}
