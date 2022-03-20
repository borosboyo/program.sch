import React from 'react';
import Home from './components/Home';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import {Component} from "react";

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
          </Switch>
        </Router>
    )
  }
}

export default App;
