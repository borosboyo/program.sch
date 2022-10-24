import React, {useEffect} from 'react'
import './Calendar.css'
import Calendar from "./Calendar";
import {ScaleFade, useDisclosure} from "@chakra-ui/react";

export function CalendarHolder() {
    const { isOpen, onToggle } = useDisclosure()

    useEffect(() => {
        onToggle();
    }, []);

    return (
        <ScaleFade in={isOpen}>
        <div className="container" id="calendar">
            <Calendar/>
        </div>
        </ScaleFade>
    );

}

export default CalendarHolder;



