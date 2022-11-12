import React, {useEffect, useState,} from 'react';
import AppNavbar from "../banner/AppNavbar";
import Footer from "../banner/Footer";
import {Form} from 'reactstrap';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import {
    FormControl,
    Button,
    Box,
    Alert,
    AlertDescription,
    AlertIcon,
    FormLabel,
    Heading,
    ScaleFade,
    Input,
    useColorModeValue,
    Textarea,
    Select,
    useDisclosure,
    FormHelperText,
    FormErrorMessage
} from "@chakra-ui/react";
import {useHistory} from "react-router-dom";
import moment from "moment";

export function EventCreator() {
    const boxColor = useColorModeValue('white', 'gray.900');
    const textColor = useColorModeValue('gray.700', 'gray.400');
    const [resorts, setResorts] = useState([]);
    const [circles, setCircles] = useState([]);
    const [selectedResort, setSelectedResort] = useState({});
    const [userResort, setUserResort] = useState({});
    const history = useHistory();
    const {isOpen, onToggle} = useDisclosure()

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
        onToggle();

    }, []); // eslint-disable-line react-hooks/exhaustive-deps

    const fetchResorts = () => {
        fetch(`http://localhost:8080/api/resort/usermemberships`, {
            method: 'GET', headers: {
                'Content-Type': 'application/json', 'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setResorts(data));
    }

    const fetchResortByCircleName = (data) => {
        setEvent(data);
        fetch(`http://localhost:8080/api/resort/byCircle?circleName=` + event.circle.displayName, {
            method: 'GET', headers: {
                'Content-Type': 'application/json', 'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setUserResort(data));
    }

    const handleSubmit = (event) => {

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
            method: 'GET', headers: {
                'Content-Type': 'application/json', 'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setSelectedResort(data));
    }


    const handleActualCircleList = (e) => {
        const circles = selectedResort.circles;
        if (circles !== undefined) {
            return circles.map(circle => <option key={circle.displayName}>{circle.displayName}</option>);
        }
    }

    const handleApiCreateCall = (newEvent) => {
        newEvent.start = moment(newEvent.start).format("YYYY-MM-DD HH:mm");
        newEvent.end = moment(newEvent.end).format("YYYY-MM-DD HH:mm");
        fetch('http://localhost:8080/api/event' + (newEvent.id ? ('/' + newEvent.id) : ''), {
            method: newEvent.id ? 'PUT' : 'POST', headers: {
                'Accept': 'application/json', 'Content-Type': 'application/json'
            }, body: JSON.stringify(newEvent),
        });
    }

    const handleChange = (e) => {
        const target = e.target;
        const value = target.value;
        const name = target.name;
        setEvent({...event, [name]: value});
    }

    const handleGetLoginState = () => {
        fetch(`http://localhost:8080/api/isLoggedIn`, {
            method: 'GET', headers: {
                'Content-Type': 'application/json', 'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setIsLoggedIn(data));
    }

    const createButton = () => {
        if (event.name === '') {
            return <p>Létrehozás</p>
        }
        return <p>Módosítás</p>
    }

    const isNameError = event.name === '';
    const isResortError = event.resort === '';
    const isCircleError = event.circle === '';

    const isStartError = () => {
        return event.start === '';
    }

    const isStartDateBeforeCurrentDate = () => {
        return moment(event.start).isBefore(moment());
    }
    const isEndDateBeforeCurrentDate = () => {
        return moment(event.end).isBefore(moment()) || moment(event.end).isBefore(event.start);

    }
    const isEndError = () => {
        return event.end === '';

    }
    const isPlaceError = event.place === '';

    const evaluateStartInputText = () => {
        if (!(isStartError() || isStartDateBeforeCurrentDate())) {
            return <FormHelperText>
                Az időpont, amikor kezdődik az esemény.
            </FormHelperText>
        }
        if (isStartError()) {
            return <FormErrorMessage>
                Az esemény kezdési ideje kötelező.
            </FormErrorMessage>
        }
        if (isStartDateBeforeCurrentDate()) {
            return <FormErrorMessage>
                Az esemény kezdési ideje nem lehet korábbi időpont.
            </FormErrorMessage>
        }
    }

    const evaluateEndInputText = () => {
        if (!(isEndError() || isEndDateBeforeCurrentDate())) {
            return <FormHelperText>
                Az időpont, amikor véget ér az esemény.
            </FormHelperText>
        }
        if (isEndError()) {
            return <FormErrorMessage>
                Az esemény befejezési ideje kötelező.
            </FormErrorMessage>
        }
        if (isEndDateBeforeCurrentDate()) {
            return <FormErrorMessage>
                Az esemény befejezési ideje nem lehet korábbi időpont.
            </FormErrorMessage>
        }
    }

    const render = () => {
        const resortList = resorts.map(resort => <option key={resort.name}>{resort.name}</option>);
        const circleList = handleActualCircleList();
        if (isLoggedIn === false) {
            return (<div>
                    <AppNavbar/>
                    <ScaleFade initialScale={0.5} in={isOpen}>
                        <Alert status='error'>
                            <AlertIcon/>
                            <AlertDescription>Esemény létrehozásához kérlek jelentkezz be.</AlertDescription>
                        </Alert>
                    </ScaleFade>
                </div>

            );
        } else {
            return (<div>
                <AppNavbar/>
                <Box style={{marginTop: '50px'}} bg={boxColor} className="container" id="eventCardHolder"
                     maxW={"50%"}>
                    <Box bg={boxColor} className="card">
                        <Heading color={textColor}
                                 className="card-header"><CalendarMonthIcon/> Eseménykezelő</Heading>
                        <div className="card-body">
                            <Form id="form" onSubmit={() => handleSubmit(event)}>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <FormControl isRequired={true} mb={'10px'} isInvalid={isNameError}>
                                            <FormLabel color={textColor} for="name">Program neve</FormLabel>
                                            <Input type="text" className="form-control" id="name" name="name"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.name}/>
                                            {!isNameError ? (<FormHelperText>
                                                Az esemény neve, ahogyan szerepelni fog a naptárban.
                                            </FormHelperText>) : (<FormErrorMessage>Kötelező nevet adni az
                                                eseménynek.</FormErrorMessage>)}
                                        </FormControl>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <FormControl isRequired={true} mb={'10px'} isInvalid={isResortError}>
                                        <FormLabel color={textColor} className="my-0 mr-2"
                                                   for="resort">Reszort</FormLabel><br/>
                                        <Select bg={'white'} color={'black'} className="custom-select my-0 mr-sm-2"
                                                id="resort"
                                                name="resort" onChange={handleResortChange}>
                                            <option hidden/>
                                            {resortList}
                                        </Select>
                                        {!isResortError ? (<FormHelperText>
                                            A reszort, ahová az esemény tartozik.
                                        </FormHelperText>) : (<FormErrorMessage>Kötelező reszorthoz rendelni
                                            az eseményt.</FormErrorMessage>)}
                                    </FormControl>
                                </div>
                                <div className="form-row">
                                    <FormControl isRequired={true} mb={'10px'} isInvalid={isCircleError}>
                                        <FormLabel color={textColor} className="my-0 mr-2"
                                                   for="circle">Kör</FormLabel><br/>
                                        <Select bg={'white'} color={'black'} className="custom-select my-0 mr-sm-2"
                                                id="circle"
                                                name="circle" onChange={handleChange}>
                                            <option hidden/>
                                            {circleList}
                                        </Select>
                                        {!isCircleError ? (<FormHelperText>
                                            A kör, ahová az esemény tartozik.
                                        </FormHelperText>) : (<FormErrorMessage>Kötelező körhöz rendelni
                                            az eseményt.</FormErrorMessage>)}
                                    </FormControl>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <FormControl isRequired={true} mb={'10px'}
                                                     isInvalid={isStartError() || isStartDateBeforeCurrentDate()}>
                                            <FormLabel color={textColor} for="start">Esemény kezdete</FormLabel>
                                            <Input type="datetime-local" className="form-control" id="start"
                                                   name="start"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.start || ''}/>
                                            {evaluateStartInputText()}
                                        </FormControl>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-6">
                                        <FormControl isRequired={true} mb={'10px'}
                                                     isInvalid={isEndError() || isEndDateBeforeCurrentDate()}>
                                            <FormLabel color={textColor} for="end">Esemény vége</FormLabel>
                                            <Input type="datetime-local" className="form-control" id="end"
                                                   name="end"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.end || ''}/>
                                            {evaluateEndInputText()}
                                        </FormControl>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-12">
                                        <FormControl isRequired={true} mb={'10px'} isInvalid={isPlaceError}>
                                            <FormLabel color={textColor} for="place">Helyszín</FormLabel>
                                            <Input type="text" className="form-control" id="place" name="place"
                                                   placeholder="" onChange={handleChange}
                                                   value={event.place || ''}/>
                                            {!isPlaceError ? (<FormHelperText>
                                                A helyszín, ahol megrendezésre kerül az esemény.
                                            </FormHelperText>) : (<FormErrorMessage>Kötelező helyszínhez rendelni az
                                                eseményt.</FormErrorMessage>)}
                                        </FormControl>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-12">
                                        <FormControl mb={'10px'}>
                                            <FormLabel color={textColor} for="facebookUrl">Facebook esemény
                                                linkje</FormLabel>
                                            <Input type="text" className="form-control" id="facebookUrl"
                                                   name="facebookUrl"
                                                   placeholder='' onChange={handleChange}
                                                   value={event.facebookUrl || ''}/>
                                        </FormControl>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-12">
                                        <FormControl mb={'10px'}>
                                            <FormLabel color={textColor} for="poster">Plakát linkje</FormLabel>
                                            <Input type="text" className="form-control" id="poster" name="poster"
                                                   placeholder={''} onChange={handleChange}
                                                   value={event.poster || ''}/>
                                        </FormControl>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-12">
                                        <FormControl mb={'10px'}>
                                            <FormLabel color={textColor} for="tldr">Program TLDR</FormLabel>
                                            <Input type="text" className="form-control" id="tldr" name="tldr"
                                                   placeholder={''} onChange={handleChange}
                                                   value={event.tldr || ''}/>
                                        </FormControl>
                                    </div>
                                </div>
                                <div className="form-row">
                                    <div className="form-group col-md-12">
                                        <FormControl mb={'10px'}>
                                            <FormLabel color={textColor} for="description">Az esemény
                                                leírása</FormLabel>
                                            <Textarea id="description" name="description" size='lg' fontSize={16}
                                                      bg={'white'}
                                                      placeholder={''}
                                                      onChange={handleChange}
                                                      value={event.description || ''}>
                                            </Textarea>
                                        </FormControl>
                                    </div>
                                </div>
                                <Button colorScheme="green"
                                        type={"submit"}
                                        boxShadow={'0px 1px 25px -5px rgb(0 255 0 / 40%), 0 10px 10px -5px rgb(0 255 0 / 35%)'}>{createButton()}</Button>
                            </Form>
                        </div>
                    </Box>
                </Box>
                <Footer/>
            </div>);
        }
    }

    return render();
}

