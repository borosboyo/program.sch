import React, {useEffect, useState} from 'react'
import {ProfileData} from "./ProfileData";
import AppNavbar from "../banner/AppNavbar";

export function Profile() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
       handleGetLoginState();
    });

    const handleGetLoginState = () => {
        fetch(`http://localhost:8080/api/isLoggedIn`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setIsLoggedIn(data));
    }


    const renderProfile = () => {
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
                    <AppNavbar/>
                    <div className="container justify-content-center">
                        <div className="card" id="card">
                            <div className="card-header">Kérlek jelentkezz be!</div>
                        </div>
                    </div>
                </div>
            );
        }
    }

    return renderProfile();
}

export default Profile;
