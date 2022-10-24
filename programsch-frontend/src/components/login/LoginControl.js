import React, {useEffect, useState} from 'react';
import {Button} from '@chakra-ui/react'
import {LockIcon, UnlockIcon} from "@chakra-ui/icons";


export function LoginControl() {

    const [loginUrl, setLoginUrl] = useState('');
    const [isLoggedIn, setLoggedIn] = useState(false);

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
            .then(data => setLoginUrl(data));

        return loginUrl.url;
    }

    const handleLogout = () => {
        fetch(`http://localhost:8080/api/logout`).then(data => console.log("logout"))
        reloadPage()
    }

    const handleRender = () => {
        if (isLoggedIn) {
            return (<div>
                <Button colorScheme="white" variant="ghost"><a href="/profile">Profil</a></Button>
                <Button colorScheme="red" rightIcon={<LockIcon/>} onClick={() => handleLogout()}
                        boxShadow={
                            '0px 1px 25px -5px rgb(255 0 0 / 40%), 0 10px 10px -5px rgb(255 0 0 / 35%)'
                        }
                >Kijelentkezés</Button>
            </div>);
        } else {
            return (<Button colorScheme="green" rightIcon={<UnlockIcon/>}
                            boxShadow={
                                '0px 1px 25px -5px rgb(0 255 0 / 40%), 0 10px 10px -5px rgb(0 255 0 / 35%)'
                            }><a id={"signInButton"} href={handleLogin()}>Bejelentkezés</a></Button>);
        }
    }

    return handleRender();
}

export default LoginControl;
