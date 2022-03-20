import React, {Component} from 'react';
import '../css/AppNavbar.css'
import {Redirect} from "react-router-dom";
import {Button} from "reactstrap";
import {responsivePropType} from "react-bootstrap/createUtilityClasses";
import data from "bootstrap/js/src/dom/data";

export class LoginControl extends React.Component {

    constructor(props) {
        super(props);
        this.state = {userObject: {}};
    }

    componentDidMount() {
        this.handleGetJson()
    }

    handleGetJson() {
        fetch(`http://localhost:8080/isLoggedIn`, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }

        })
            .then((response) => response.json())
            .then(data => this.setState({userObject: data}) );
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
                            <a className="nav-link active" href="#" >Kijelentkezés</a>
                        </li>
                    </ul>
                </div>);
        } else {
            return (
                <div className="collapse navbar-collapse" id="rightButtons">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0" id="loginButtons">
                        <li className="nav-item">
                            <Button>Bejelentkezés</Button>
                        </li>
                    </ul>
                </div>);
        }

    }
}