import React, {useEffect, useState} from 'react';
import AppNavbar from "../banner/AppNavbar";
import moment from "moment";
import {Event} from "./Event";
import {useHistory} from "react-router-dom";
import {
    Alert,
    AlertDescription,
    AlertIcon, Avatar,
    Box, Center, Heading, Image,
    ScaleFade,
    Text,
    useColorModeValue,
    useDisclosure
} from "@chakra-ui/react";


export function DayViewer(props) {
    const boxColor = useColorModeValue('white', 'gray.700');
    const headerColor = useColorModeValue('gray.100', 'gray.900');
    const textColor = useColorModeValue('gray.700', 'gray.400');
    const [dayEvents, setDayEvents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [link, setLink] = useState('');
    const [eventPoster, setEventPoster] = useState({});
    const history = useHistory();
    const id = props.match.params.id;
    const {isOpen, onToggle} = useDisclosure()

    useEffect(() => {
        onToggle();
        setLoading(true);
        fetchDay();
    }, []);


    const fetchDay = () => {
        fetch(`http://localhost:8080/api/event/day?date=${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setDayEvents(data));
        setLoading(false);
    }

    const setImage = () => {
        setLink("https://via.placeholder.com/350");
    }

    const myImage = () => {
        return <Center><Image src={link} alt="event image" onError={() => setImage()}/> </Center>
    }

    const renderEvents = () => {
        const currentDayEvents = dayEvents.map(currentEvent =>
            <Box key={currentEvent.name}>
                <Box>
                    <Box maxW={'500px'}
                         w={'full'}
                         bg={headerColor}
                         boxShadow={'2xl'}
                         rounded={'lg'}
                         p={6}
                         textAlign={'center'}
                         style={{marginTop: '15px'}}>
                        <Heading>{currentEvent.circle.displayName}</Heading>
                    </Box>
                    <Box maxW={'500px'}
                         w={'full'}
                         bg={boxColor}
                         boxShadow={'2xl'}
                         rounded={'lg'}
                         p={6}
                         textAlign={'left'}>
                        {<Event currentEvent={currentEvent}/>}
                    </Box>
                </Box>
            </Box>
        );

        if (dayEvents.length === 0) {
            return (
                <div>
                    <AppNavbar/>
                    <ScaleFade initialScale={0.5} in={isOpen}>
                        <Alert status='error'>
                            <AlertIcon/>
                            <AlertDescription>A mai napra nem találhatóak események. :(</AlertDescription>
                        </Alert>
                    </ScaleFade>
                </div>
            )
        } else {
            return (
                <div>
                    <AppNavbar/>
                    <Center>
                        <ScaleFade initialScale={0.5} in={isOpen}>
                            <Box
                                bg={boxColor}
                                maxW={'100%'}
                                w={'full'}
                                boxShadow={'2xl'}
                                rounded={'md'}
                                p={5}
                                textAlign={'center'}>
                                <Heading color={textColor} fontSize={'2xl'}>
                                    {moment(id).format("MM. DD.")}
                                </Heading>
                                <Text color={textColor} mt={2} fontSize={'sm'}>
                                    {dayEvents.length} esemény
                                </Text>
                            </Box>
                            {currentDayEvents}
                        </ScaleFade>
                    </Center>
                </div>
            );
        }
    }
    return renderEvents(props);
}
