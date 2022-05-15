import React, {Component} from 'react';
import {Home} from './components/Home';
import {EventCreator} from './components/EventCreator';
import {Profile} from './components/Profile';
import {EditFilters} from './components/EditFilters';
import {EventViewer} from "./components/EventViewer";
import {LoginRedirect} from './components/LoginRedirect';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/newevent'exact={true} component={EventCreator}/>
                    <Route path='/loggedin' exact={true} component={LoginRedirect}/>
                    <Route path='/profile' exact={true} component={Profile}/>
                    <Route path='/filters' exact={true} component={EditFilters}/>
                    <Route path='/event/:id' component={EventViewer}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
