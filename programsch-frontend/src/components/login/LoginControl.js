import React, {Component} from 'react';
import '../banner/AppNavbar.css'
import {Button} from "reactstrap";

export class LoginControl extends React.Component {

    constructor(props) {
        super(props);
        this.state = {isLoggedIn: {}, loginUrl: ''};
    }

    reloadPage() {
        const reloadCount = sessionStorage.getItem('reloadCount');
        if (reloadCount < 2) {
            sessionStorage.setItem('reloadCount', String(reloadCount + 1));
            window.location.reload();
        } else {
            sessionStorage.removeItem('reloadCount');
        }
    }

    componentDidMount() {
        this.handleGetLoginState();
    }

    handleGetLoginState() {
        fetch(`http://localhost:8080/api/isLoggedIn`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({isLoggedIn: data}))
    }

    handleLogin(){
        fetch(`http://localhost:8080/api/login`, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({loginUrl: data}));

        return this.state.loginUrl.url;
    }


    handleLogout() {
        fetch(`http://localhost:8080/api/logout`).then(data => console.log("logout"))
        this.reloadPage();
    }

    render() {
        const isLoggedIn = this.state.isLoggedIn;
        if (isLoggedIn) {
            return (
                <div className="collapse navbar-collapse" id="rightButtons">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0" id="loginButtons">
                        <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href="/profile">Profil</a>
                        </li>
                        <li className="nav-item">
                            <Button className="btn btn-danger" onClick={() => this.handleLogout()}>Kijelentkezés</Button>
                        </li>
                    </ul>
                </div>);
        } else {
            return (
                <div className="collapse navbar-collapse" id="rightButtons">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0" id="loginButtons">
                        <li className="nav-item">
                            <Button className="btn btn-success"><a id={"signInButton"} href={this.handleLogin()}>Bejelentkezés</a></Button>
                        </li>
                    </ul>
                </div>);
        }
    }
}
