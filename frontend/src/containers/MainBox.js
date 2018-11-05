import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import SearchBox from './SearchBox';
import Results from './ResultBox';
import LoadingBox from './LoadingBox';

class Main extends Component {

    render() {
        return (
            <Router>
                <React.Fragment>
                    <Route exact path="/" component={SearchBox} />
                    <Route path="/results" component={Results} />
                    <Route path="/loading" component={LoadingBox} />
                </React.Fragment>
            </Router>
        )
    }
}

export default Main;