import React from 'react';
import {Home} from './components/Home';
import {LoginRedirect} from './components/LoginRedirect';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {Component} from "react";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/loggedin' component={LoginRedirect}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
