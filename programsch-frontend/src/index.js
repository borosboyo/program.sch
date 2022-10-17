import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'font-awesome/css/font-awesome.min.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import {Provider} from "react-redux";
import store from "./store";

// As of React 18
const root = ReactDOM.createRoot(document.getElementById('root'))

root.render(
    <Provider store={store}>
        <App />
    </Provider>
)

reportWebVitals();
