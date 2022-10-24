import React, {useEffect, useState, } from 'react';
import AppNavbar from "../banner/AppNavbar";
import Footer from "../banner/Footer";
import './EventCreator.css';
import {Form, Input} from 'reactstrap';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import {
    FormControl,
    Button,
    Box,
    Alert,
    AlertDescription,
    AlertIcon,
    FormLabel, Heading,
    FormErrorMessage,
    FormHelperText,
    Link,
    ScaleFade,
    Stack,
    useColorModeValue, Textarea, Select
} from "@chakra-ui/react";
import {useHistory} from "react-router-dom";

export function EventCreator() {
    const boxColor = useColorModeValue('white', 'gray.900');
    const textColor = useColorModeValue('gray.700', 'gray.400');
    const [resorts, setResorts] = useState([]);
    const [circles, setCircles] = useState([]);
    const [selectedResort, setSelectedResort] = useState({});
    const [userResort, setUserResort] = useState({});
    const history = useHistory();

    function newEvent() {
        return {
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
    }

    const [event, setEvent] = useState(newEvent);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        const parameter = history.location.pathname.replace('/event/', '');
        if (parameter !== 'new') {
            fetch(`http://localhost:8080/api/event/${parameter}`).then((response) => response.json())
                .then(data => fetchResortByCircleName(data))
        }
        handleGetLoginState();
        fetchResorts();

    }, []);

    const fetchResorts = () => {
        fetch(`http://localhost:8080/api/resort/usermemberships`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setResorts(data));
    }

    const fetchResortByCircleName = (data) => {
        setEvent(data);
        fetch(`http://localhost:8080/api/resort/byCircle?circleName=` + event.circle.displayName, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setUserResort(data));
    }

    const handleSubmit = (e) => {
        e.preventDefault()

        handleApiCreateCall(event)

        const onFinish = () => {
            history.push('/')
            window.location.reload();
        }
        onFinish();
    }

    const handleResortChange = (e) => {
        handleChange(e);
        fetch(`http://localhost:8080/api/resort/` + e.target.value, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setSelectedResort(data));
    }


    const handleActualCircleList = (e) => {
        const circles = selectedResort.circles;
        if (circles !== undefined) {
            return circles.map(circle =>
                <option key={circle.displayName}>{circle.displayName}</option>
            );
        }
    }

    const handleApiCreateCall = (newEvent) => {
        fetch('http://localhost:8080/api/event' + (newEvent.id ? '/' + newEvent.id : ''), {
            method: newEvent.id ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newEvent),
        });
    }

    const handleChange = (e) => {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        setEvent({...event, [name]: value});
    }

   const handleGetLoginState = () =>  {
       fetch(`http://localhost:8080/api/isLoggedIn`, {
           method: 'GET',
           headers: {
               'Content-Type': 'application/json',
               'Accept': 'application/json'
           }
       })
           .then((response) => response.json())
           .then(data => setIsLoggedIn(data));
   }

    const render = () => {
        const resortList = resorts.map(resort =>
            <option key={resort.name}>{resort.name}</option>
        );

        const circleList = handleActualCircleList();

        if (isLoggedIn === false) {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container justify-content-center">
                        <div className="card" id="card">
                            <div className="card-header">Kérlek jelentkezz be!</div>
                        </div>
                    </div>
                </div>

            );
        } else {
            return (
                <div>
                    <AppNavbar/>
                    <Box style={{marginTop: '50px'}} bg={boxColor} className="container" id="eventCardHolder">
                        <Box bg={boxColor} className="card">
                            <Heading color={textColor} className="card-header"><CalendarMonthIcon/> Eseménykezelő</Heading>
                            <div className="card-body">
                                <Form id="form" onSubmit={handleSubmit}>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <FormLabel color={textColor} for="name">Program neve</FormLabel>
                                            <Input type="text" className="form-control" id="name" name="name"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.name || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <FormControl>
                                            <FormLabel color={textColor} className="my-1 mr-2" for="resort">Reszort</FormLabel><br/>
                                            <Select bg={'white'} color={'black'} className="custom-select my-1 mr-sm-2" id="resort"
                                                    name="resort" onChange={handleResortChange}>
                                                <option hidden/>
                                                {resortList}
                                            </Select>
                                        </FormControl>
                                    </div>
                                    <div className="form-row">
                                            <FormLabel color={textColor} className="my-1 mr-2" for="circle">Kör</FormLabel><br/>
                                            <Select bg={'white'} color={'black'} className="custom-select my-1 mr-sm-2" id="circle"
                                                   name="circle" onChange={handleChange}>
                                                <option hidden/>
                                                {circleList}
                                            </Select>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <FormLabel color={textColor} for="start">Esemény kezdete</FormLabel>
                                            <Input type="datetime-local" className="form-control" id="start"
                                                   name="start"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.start || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-6">
                                            <FormLabel color={textColor} for="end">Esemény vége</FormLabel>
                                            <Input type="datetime-local" className="form-control" id="end" name="end"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.end || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-12">
                                            <FormLabel color={textColor} for="place">Helyszín</FormLabel>
                                            <Input type="text" className="form-control" id="place" name="place"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.place || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-12">
                                            <FormLabel color={textColor} for="facebookUrl">Facebook esemény linkje</FormLabel>
                                            <Input type="text" className="form-control" id="facebookUrl"
                                                   name="facebookUrl"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.facebookUrl || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-12">
                                            <FormLabel color={textColor} for="poster">Plakát linkje</FormLabel>
                                            <Input type="text" className="form-control" id="poster" name="poster"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.poster || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-12">
                                            <FormLabel color={textColor} for="tldr">Program TLDR</FormLabel>
                                            <Input type="text" className="form-control" id="tldr" name="tldr"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.tldr || ''}/>
                                        </div>
                                    </div>
                                    <div className="form-row">
                                        <div className="form-group col-md-12">
                                            <FormLabel color={textColor} for="description">Az esemény leírása</FormLabel>
                                            <Textarea id="description" name="description" size='lg' fontSize={16}
                                                      bg={'white'}
                                                      onChange={handleChange}
                                                      value={event.description || ''}>
                                        </Textarea>
                                        </div>
                                    </div>
                                    <Button colorScheme="green"
                                            boxShadow={
                                                '0px 1px 25px -5px rgb(0 255 0 / 40%), 0 10px 10px -5px rgb(0 255 0 / 35%)'
                                            }>Létrehozás</Button>
                                </Form>
                            </div>
                        </Box>
                    </Box>
                    <Footer/>
                </div>
            );
        }
    }

    return render();
}
