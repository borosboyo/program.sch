import React from "react";
import {ProfileFilters} from "./ProfileFilters";

export class ProfileData extends React.Component {

    constructor(props) {
        super(props);
        this.state = {userObject: {}};
    }

    componentDidMount() {
        this.handleGetUserObject()
    }

    handleGetUserObject() {
        fetch(`http://localhost:8080/appUserEntity`, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then((response) => response.json())
            .then(data => this.setState({userObject: data}));
    }

    render() {
        const userObject = this.state.userObject;
        return (
            <section>
                <div className="container">
                    <div className="page-header">
                        <h1> Profil <small> - {userObject.name}</small></h1>
                    </div>
                    <div className="row">
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
                        <ProfileFilters/>
                    </div>
                </div>
            </section>
        );
    }
}

