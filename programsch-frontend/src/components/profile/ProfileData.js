import React, {useEffect, useState} from "react";
import {ProfileFilters} from "./ProfileFilters";
import './ProfileData.css';
import AccountBoxIcon from '@mui/icons-material/AccountBox';

export function ProfileData() {

    const [userObject, setUsersObject] = useState({});

    useEffect(() => {
        handleGetUserObject();
    }, []);

    const handleGetUserObject = () => {
        fetch(`http://localhost:8080/api/appuser`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => setUsersObject(data));
    }

    const render = () =>  {
        return (
            <section id="profilePanel" className="container justify-content-center">
                <div className="container text-center">
                    <div className="page-header">
                        <h1><AccountBoxIcon/>  Profil <small> - {userObject.name}</small></h1>
                    </div>
                    <div className="row justify-content-center" id="profileData">
                        <div className="col-md-6">
                            <div className="panel panel-default">
                                <div className="panel-heading">
                                    <h3 className="panel-title">Adatok</h3>
                                </div>
                                <table className="table">
                                    <tbody>
                                    <tr>
                                        <td>Név</td>
                                        <th className="text-right">{userObject.name}</th>
                                    </tr>
                                    <tr>
                                        <td>E-mail cím</td>
                                        <th className="text-right">{userObject.email}</th>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div className="row justify-content-center">
                        <ProfileFilters/>
                    </div>
                </div>
            </section>
        );
    }

    return render();
}

export default ProfileData;

