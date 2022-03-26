import React from 'react'
import {ProfileData} from "./ProfileData";
import AppNavbar from "./AppNavbar";

export class Profile extends React.Component {
    constructor(props) {
        super(props);
        this.state = {isLoggedIn: {}};
    }

    componentDidMount() {
        this.handleGetLoginState()
    }

    handleGetLoginState() {
        fetch(`http://localhost:8080/isLoggedIn`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({isLoggedIn: data}));
    }


    render() {
        const isLoggedIn = this.state.isLoggedIn;
        if(isLoggedIn) {
            return (
                <div>
                    <AppNavbar/>
                    <div className="container">
                        <ProfileData/>
                    </div>
                </div>
            );
        } else {
            return (
                <div>
                    Kérlek jelentkezz be!
                </div>
            );
        }
    }
}