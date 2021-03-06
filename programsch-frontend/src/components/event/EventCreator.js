import React from 'react';
import AppNavbar from "../banner/AppNavbar";
import Footer from "../banner/Footer";
import './EventCreator.css';
import {Button, Form, Input, Label} from 'reactstrap';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';

export class EventCreator extends React.Component {

    newEvent: {
        name: '',
        resort: '',
        circle: '',
        start: '',
        end: '',
        place: '',
        facebookUrl: '',
        poster: '',
        tldr: '',
        descirption: '',
    }

    constructor(props) {
        super(props);

        this.state = {resorts: [], circles: [], selectedResort: {}, event: this.newEvent};
        this.handleResortChange = this.handleResortChange.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleApiCreateCall = this.handleApiCreateCall.bind(this);

    }

    async componentDidMount() {
        const parameter = this.props.match.url.replace('/event/', '')
        if (parameter !== 'new') {
            const event = await (await (fetch(`http://localhost:8080/api/event/${parameter}`))).json();
            this.setState({event: event});
        }

        this.handleGetLoginState();
        this.fetchResorts();
    }

    fetchResorts() {
        fetch(`http://localhost:8080/api/resort/usermemberships`, {
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

        const onFinish = () => {
            this.props.history.push('/')
            window.location.reload();
        }
        onFinish();
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
        if (circles !== undefined) {
            return circles.map(circle =>
                <option key={circle.displayName}>{circle.displayName}</option>
            );
        }
    }

    handleApiCreateCall(newEvent) {
        fetch('http://localhost:8080/api/event' + (newEvent.id ? '/' + newEvent.id : ''), {
            method: newEvent.id ? 'PUT' : 'POST',
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
        console.log(event.start);
        console.log(event.end);
        this.setState({event});
    }

    handleGetLoginState() {
        fetch(`http://localhost:8080/api/isLoggedIn`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({isLoggedIn: data}));
    }

    render() {
        const resortList = this.state.resorts.map(resort =>
            <option key={resort.name}>{resort.name}</option>
        );

        const circleList = this.handleActualCircleList();

        let currentEvent = {};

        if (this.state.event !== undefined) {
            currentEvent = this.state.event;
        }

        if (this.state.isLoggedIn === false) {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container justify-content-center">
                        <div className="card" id="card">
                            <div className="card-header">K??rlek jelentkezz be!</div>
                        </div>
                    </div>
                </div>

            );
        } else {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container" id="eventCardHolder">
                        <div className="card">
                            <h5 className="card-header"><CalendarMonthIcon/> Esem??nykezel??</h5>
                            <div className="card-body">
                                <Form id="form" onSubmit={this.handleSubmit}>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <Label for="name">Program neve</Label>
                                            <Input type="text" className="form-control" id="name" name="name"
                                                   placeholder="" onChange={this.handleChange}
                                                   value={currentEvent.name || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <Label className="my-1 mr-2" for="resort">Reszort</Label><br/>
                                        <Input type={"select"} className="custom-select my-1 mr-sm-2" id="resort"
                                               name="resort" onChange={this.handleResortChange}
                                               defaultValue={"V??lassz.."}>
                                            <option hidden/>
                                            {resortList}
                                        </Input>
                                    </div>
                                    <div className="form-row">
                                        <Label className="my-1 mr-2" for="circle">K??r</Label><br/>
                                        <Input type={"select"} className="custom-select my-1 mr-sm-2" id="circle"
                                               name="circle" onChange={this.handleChange}>
                                            <option hidden/>
                                            {circleList}
                                        </Input>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <Label for="start">Esem??ny kezdete</Label>
                                            <Input type="datetime-local" className="form-control" id="start"
                                                   name="start"
                                                   placeholder="" onChange={this.handleChange}
                                                   value={currentEvent.start || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <Label for="end">Esem??ny v??ge</Label>
                                            <Input type="datetime-local" className="form-control" id="end" name="end"
                                                   placeholder="" onChange={this.handleChange}
                                                   value={currentEvent.end || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <Label for="place">Helysz??n</Label>
                                            <Input type="text" className="form-control" id="place" name="place"
                                                   placeholder="" onChange={this.handleChange}
                                                   value={currentEvent.place || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <Label for="facebookUrl">Facebook esem??ny linkje</Label>
                                            <Input type="text" className="form-control" id="facebookUrl"
                                                   name="facebookUrl"
                                                   placeholder="" onChange={this.handleChange}
                                                   value={currentEvent.facebookUrl || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <Label for="poster">Plak??t linkje</Label>
                                            <Input type="text" className="form-control" id="poster" name="poster"
                                                   placeholder="" onChange={this.handleChange}
                                                   value={currentEvent.poster || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <Label for="tldr">Program TLDR</Label>
                                            <Input type="text" className="form-control" id="tldr" name="tldr"
                                                   placeholder="" onChange={this.handleChange}
                                                   value={currentEvent.tldr || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <Label for="description">Az esem??ny le??r??sa</Label>
                                            <textarea id="description" name="description" rows="4" cols="50"
                                                      onChange={this.handleChange}
                                                      value={currentEvent.description || ''}>
                                        </textarea>
                                        </div>
                                    </div>
                                    <Button type="submit" className="btn btn-primary">L??trehoz??s</Button>
                                </Form>
                            </div>
                        </div>
                    </div>
                    <Footer/>
                </div>
            );
        }
    }
}
