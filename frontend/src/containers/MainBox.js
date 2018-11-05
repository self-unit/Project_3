import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import SearchBox from './SearchBox';
import Results from './ResultBox';
import LoadingBox from './LoadingBox';

class Main extends Component {

    constructor(props) {
        super(props)
        this.state = {
            text: '',
            currency: '',
            results: []
        }
        this.handleInput = this.handleInput.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    getInfo = () => {
        //fetch request
    }

    handleInput(event) {
        this.setState({ text: event.target.value })
    }

    handleChange(event) {
        this.setState({ currency: event.target.value })
    }

    handleSubmit(event) {
        event.preventDefault();
        console.log('form submitted');
        const text = this.state.text;
        const currency = this.state.currency;

        console.log('text:', text);
        console.log('currency:', currency);

        //reset form
        this.setState({ text: '', currency: '' })
    }

    render() {
        return (
            <Router>
                <React.Fragment>
                    <Route exact path="/"
                        render={() => <SearchBox handleInput={this.handleInput} handleChange={this.handleChange} handleSubmit={this.handleSubmit} search={this.state.text} currency={this.state.currency} />}
                    />
                    <Route path="/results" render={() => <Results results={this.state.results} />} />
                    <Route path="/loading" component={LoadingBox} />
                </React.Fragment>
            </Router>
        )
    }
}

export default Main;