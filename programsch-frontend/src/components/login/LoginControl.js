import React, {useEffect, useState} from 'react';
import {
    Button, Center,
    Modal,
    ModalBody,
    ModalCloseButton,
    ModalContent,
    ModalFooter,
    ModalHeader,
    ModalOverlay, Spinner,
    Text,
    useDisclosure
} from '@chakra-ui/react'
import {LockIcon, UnlockIcon} from "@chakra-ui/icons";

export function LoginControl() {

    const [loginUrl, setLoginUrl] = useState('');
    const [isLoggedIn, setLoggedIn] = useState(false);
    const {isOpen, onOpen, onClose} = useDisclosure()

    const reloadPage = () => {
        const reloadCount = sessionStorage.getItem('reloadCount');
        if (reloadCount < 2) {
            sessionStorage.setItem('reloadCount', String(reloadCount + 1));
            window.location.reload();
        } else {
            sessionStorage.removeItem('reloadCount');
        }
    }

    useEffect(() => {
        handleGetLoginState();
    });

    const handleGetLoginState = () => {
        fetch(`http://localhost:8080/api/isLoggedIn`, {
            headers: {
                'Content-Type': 'application/json', 'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setLoggedIn(data))
    }

    const handleLogin = () => {
        fetch(`http://localhost:8080/api/login`, {
            headers: {
                'Content-Type': 'application/json', 'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => {
                window.location.href = data.url;
            });
    }

    const handleLogout = () => {
        fetch(`http://localhost:8080/api/logout`).then(data => console.log("logout"))
        reloadPage()
    }

    const OverlayTwo = () => (
        <ModalOverlay
            bg='none'
            backdropFilter='auto'
            backdropInvert='80%'
            backdropBlur='2px'
        />)

    const OverlayOne = () => (
        <ModalOverlay
            bg='blackAlpha.300'
            backdropFilter='blur(10px) hue-rotate(90deg)'
        />
    )

    const [overlay, setOverlay] = useState(<OverlayOne/>)

    const handleRender = () => {
        if (isLoggedIn) {
            return (<div>
                <Button colorScheme="white" variant="ghost"><a href="/profile">Profil</a></Button>
                <Button colorScheme="red" rightIcon={<LockIcon/>} onClick={() => handleLogout()}
                        boxShadow={'0px 1px 25px -5px rgb(255 0 0 / 40%), 0 10px 10px -5px rgb(255 0 0 / 35%)'}
                >Kijelentkezés</Button>
            </div>);
        } else {
            return (
                <>
                    <Button
                        onClick={() => {
                            setOverlay(<OverlayTwo />);
                            onOpen();
                            handleLogin();
                        }}
                        colorScheme="green" rightIcon={<UnlockIcon/>}
                        boxShadow={'0px 1px 25px -5px rgb(0 255 0 / 40%), 0 10px 10px -5px rgb(0 255 0 / 35%)'}>
                        Bejelentkezés</Button>
                    <Modal isCentered isOpen={isOpen} onClose={onClose}>
                        {overlay}
                        <ModalContent>
                            <ModalHeader>Belépés folyamatban...</ModalHeader>
                            <ModalBody>
                            <Center>
                                <Spinner
                                    style={{marginBottom: '15px'}}
                                    thickness='4px'
                                    speed='0.65s'
                                    emptyColor='gray.200'
                                    color='blue.500'
                                    size='xl'
                                />
                            </Center>
                            </ModalBody>
                        </ModalContent>
                    </Modal>
                </>);
        }
    }

    return handleRender();
}

export default LoginControl;
