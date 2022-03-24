import React, {Component} from 'react';
import '../css/AppNavbar.css'
import {Button} from "reactstrap";

export class LoginControl extends React.Component {

    constructor(props) {
        super(props);
        this.state = {userObject: {}, loginUrl: ''};
    }

    reloadPage(){
        const reloadCount = sessionStorage.getItem('reloadCount');
        if(reloadCount < 2) {
            sessionStorage.setItem('reloadCount', String(reloadCount + 1));
            window.location.reload();
        } else {
            sessionStorage.removeItem('reloadCount');
        }
    }

    componentDidMount() {
        this.handleGetLoginState()
    }

    handleGetLoginState() {
        fetch(`http://localhost:8080/isLoggedIn`, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({userObject: data}) );
        this.reloadPage();
    }

    handleLogin(){
        fetch(`http://localhost:8080/login`, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({loginUrl: data}));

        return this.state.loginUrl.url;
    }

    handleLogout(){
        fetch(`http://localhost:8080/logout`).then(data => console.log(data))
        this.reloadPage();
    }


    render() {
        const user = this.state.userObject;
        if (user.name !== undefined) {
            return (
                <div className="collapse navbar-collapse" id="rightButtons">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0" id="loginButtons">
                        <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href="#">Profil</a>
                        </li>
                        <li className="nav-item">
                            <Button onClick={() => this.handleLogout()}>Kijelentkezés</Button>
                        </li>
                    </ul>
                </div>);
        } else {
            return (
                <div className="collapse navbar-collapse" id="rightButtons">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0" id="loginButtons">
                        <li className="nav-item">
                            <Button><a id={"signInButton"} href={this.handleLogin()}>Bejelentkezés</a></Button>
                        </li>
                    </ul>
                </div>);
        }

    }
}