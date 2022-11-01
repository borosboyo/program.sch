import React, {Component, useEffect} from 'react';
import {Home} from './components/Home';
import {EventCreator} from './components/event/EventCreator';
import {Profile} from './components/profile/Profile';
import {EditFilters} from './components/profile/EditFilters';
import {EventViewer} from "./components/event/EventViewer";
import {DayViewer} from "./components/event/DayViewer";
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

function App() {

    useEffect(() => {
        document.title = "Program.sch";
    });

    return (
        <Router>
            <Switch>
                <Route path='/' exact={true} component={Home}/>
                <Route path='/event/:id' exact={true} component={EventCreator}/>
                <Route path='/profile' exact={true} component={Profile}/>
                <Route path='/filters' exact={true} component={EditFilters}/>
                <Route path='/eventview/:id' component={EventViewer}/>
                <Route path='/dayview/:id' component={DayViewer}/>
            </Switch>
        </Router>)
}

export default App;
