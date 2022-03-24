import React from 'react';
import AppNavbar from './AppNavbar';
import {Container} from "reactstrap";

export class Home extends React.Component {

    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                </Container>
            </div>
        );
    }
}

