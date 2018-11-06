import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect} from 'react-router-dom';
import SearchBox from './SearchBox';
import Results from './ResultBox';
import LoadingBox from './LoadingBox';

class Main extends Component {

    constructor(props) {
        super(props)
        this.state = {
            text: '',
            currency: '',
            results: null,
            redirectPage: false
        }

        this.handleInput = this.handleInput.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleRedirect = this.handleRedirect.bind(this);
    }

    handleInput(event) {
      this.setState({ text: event.target.value })
    }

    handleChange(event) {
      this.setState({ currency: event.target.value })
    }

    handleSubmit(event) {
        event.preventDefault();

        const text = this.state.text;
        const currency = this.state.currency;
        if (!text || !currency) {
          return
        }
        console.log('text:', text);
        console.log('currency:', currency);
        console.log('form submitted');
        fetch(`/search/${text}`)

          .then( (response) => response.json())
          .then( (jsonData) => this.setState({results: jsonData}))
          .then(() => this.setState({redirectPage: true}))


        //reset form
        this.setState({ text: '' })
    }

    handleRedirect() {
      if (this.state.redirectPage) {
          return <Redirect to='/results'/>
      }
    }

    // handleResultSubmit(newResult) {
    //   newResult.id = Date.now();
    //   const updatedResults= [this.state.results, newResult];
    //   this.setState({results: updatedResults})
    // }

    render() {
        return (

            <Router>
              <Switch>
                <React.Fragment>
                    <Route exact path="/"
                        render={() => <SearchBox handleInput={this.handleInput} handleChange={this.handleChange} handleSubmit={this.handleSubmit} search={this.state.text} currency={this.state.currency} />}
                    />

                    <Route path="/results" render={() => <Results results={this.state.results} />} />
                    <Route path="/loading" component={LoadingBox} />
                     {this.handleRedirect()}
                </React.Fragment>
              </Switch>
            </Router>

        )
    }
}

export default Main;
