import React, {useEffect, useState} from 'react'
import {ProfileData} from "./ProfileData";
import AppNavbar from "../banner/AppNavbar";
import {
    Alert,
    AlertIcon,
    AlertDescription,
} from '@chakra-ui/react'
import {ScaleFade, useDisclosure} from "@chakra-ui/react";

export function Profile() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const {isOpen, onToggle} = useDisclosure()

    useEffect(() => {
        onToggle();
        handleGetLoginState();
    }, []);  // eslint-disable-line react-hooks/exhaustive-deps

    const handleGetLoginState = () => {
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


    const renderProfile = () => {
        if (isLoggedIn) {
            return (
                <div>
                    <AppNavbar/>
                    <div style={{marginTop: '50px'}}>
                        <ScaleFade initialScale={0.5} in={isOpen}>
                            <ProfileData/>
                        </ScaleFade>
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    <AppNavbar/>
                    <ScaleFade initialScale={0.5} in={isOpen}>
                        <Alert status='error'>
                            <AlertIcon/>
                            <AlertDescription>A profilod megtekintéséhez kérlek jelentkezz be.</AlertDescription>
                        </Alert>
                    </ScaleFade>
                </div>
            );
        }
    }

    return renderProfile();
}

export default Profile;
