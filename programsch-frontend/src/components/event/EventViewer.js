import React, {useEffect, useState} from 'react';
import AppNavbar from "../banner/AppNavbar";
import {Event} from "./Event";
import {useHistory} from "react-router-dom";
import {Box, Center, Heading, Image, ScaleFade, useColorModeValue, useDisclosure} from "@chakra-ui/react";

export function EventViewer(props) {

    const boxColor = useColorModeValue('white', 'gray.700');
    const headerColor = useColorModeValue('gray.100', 'gray.900');
    const textColor = useColorModeValue('gray.700', 'gray.400');
    const [currentEvent, setCurrentEvent] = useState({});
    const [currentCircle, setCurrentCircle] = useState({});
    const id = props.match.params.id;
    const history = useHistory();
    const {isOpen, onToggle} = useDisclosure()

    useEffect(() => {
        onToggle();
        fetchEvent();
    }, []);

    const fetchEvent = () => {
        fetch(`http://localhost:8080/api/event/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then((data) => {
                setCurrentEvent(data);
                setCurrentCircle(data.circle);
            })
    }

    const render = () => {
        return (
            <div>
                <AppNavbar/>
                <Center>
                    <ScaleFade initialScale={0.5} in={isOpen}>
                        <Box key={currentEvent.name}>
                            <Box maxW={'500px'}
                                 w={'full'}
                                 bg={headerColor}
                                 boxShadow={'2xl'}
                                 rounded={'lg'}
                                 p={6}
                                 textAlign={'center'}
                                 style={{marginTop: '15px'}}>
                                <Heading>{currentCircle.displayName}</Heading>
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
                    </ScaleFade>
                </Center>
            </div>
        );
    }

    return render();
}
