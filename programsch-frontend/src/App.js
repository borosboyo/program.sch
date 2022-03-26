import React from 'react';
import {Home} from './components/Home';
import {Profile} from './components/Profile';
import {EditFilters} from './components/EditFilters';
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
                    <Route path='/profile' component={Profile}/>
                    <Route path='/filters' exact={true} component={EditFilters}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
