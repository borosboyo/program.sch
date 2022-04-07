import React from 'react';
import {Home} from "./Home";
//UNUSED!!
export class LoginRedirect extends React.Component {

    constructor(props) {
        super(props);
        this.state = {loginUrl: ''};
        const queryParams = new URLSearchParams(window.location.search);
        const code = queryParams.get('code');
        const state = queryParams.get('state');
        this.handleLoginRequest(code, state);
    }

    componentDidMount() {
    }

    async handleLoginRequest(code, state) {
        console.log(code);
        console.log(state);
        await fetch('http://localhost:8080/api/loggedin/' + code + "/" + state, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    }

    render() {
        return (
            <div>
                <Home/>
            </div>
        )
    }
}