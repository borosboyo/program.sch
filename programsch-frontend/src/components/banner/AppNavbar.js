import React, {Component, useState} from 'react';
import './AppNavbar.css'
import {LoginControl} from '../login/LoginControl';

export function AppNavbar() {

    return <nav className="navbar navbar-expand-lg navbar-light bg-light" id="navbar">
        <div className="container-fluid">
            <a className="navbar-brand" href="/">Program.sch</a>
            <div className="collapse navbar-collapse" id="leftButtons">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                        <a className="nav-link active" aria-current="page" href="/event/new">Új program</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link active" href="http://localhost:8080/swagger-ui/index.html">API</a>
                    </li>
                </ul>
            </div>
            <LoginControl/>
        </div>
    </nav>;
}

export default AppNavbar;
