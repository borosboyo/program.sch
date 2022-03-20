import React, {Component} from 'react';
import '../css/AppNavbar.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {LoginControl} from './LoginControl';

export default class AppNavbar extends Component {

    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    render() {
        return <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <a className="navbar-brand" href="#">Navbar</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="leftButtons">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <a className="nav-link active" aria-current="page" href="#">Új program</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link active" href="#">API</a>
                        </li>
                    </ul>
                </div>
                <LoginControl/>
            </div>
        </nav>;
    }
}
