import React, {useEffect, useState} from 'react';
import '../banner/AppNavbar.css'
import {Button} from "reactstrap";


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
            return (<div className="collapse navbar-collapse" id="rightButtons">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0" id="loginButtons">
                    <li className="nav-item">
                        <a className="nav-link active" aria-current="page" href="/profile">Profil</a>
                    </li>
                    <li className="nav-item">
                        <Button className="btn btn-danger" onClick={() => handleLogout()}>Kijelentkezés</Button>
                    </li>
                </ul>
            </div>);
        } else {
            return (<div className="collapse navbar-collapse" id="rightButtons">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0" id="loginButtons">
                    <li className="nav-item">
                        <Button className="btn btn-success"><a id={"signInButton"} href={handleLogin()}>Bejelentkezés</a></Button>                    </li>
                </ul>
            </div>);
        }
    }

    return handleRender();
}

export default LoginControl;
