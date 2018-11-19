import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect} from 'react-router-dom';
import SearchBox from './SearchBox';
import ResultBox from './ResultBox';
import LoadingBox from './LoadingBox';

class Main extends Component {

    constructor(props) {
        super(props)
        this.state = {
            text: '',
            currency: '',
            results: null,
            redirectPage: null
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
        this.setState({redirectPage: 'loading'});
        const text = this.state.text;
        const currency = this.state.currency;
        if (!text || !currency) {
          return
        }
        // console.log('text:', text);
        // console.log('currency:', currency);
        // console.log('form submitted');
        fetch(`/search/${text}`)
          .then( (response) => response.json())
          .then( (jsonData) => this.setState({results: jsonData, redirectPage: 'results'}))
          // .then(() => this.setState({redirectPage: 'results'}))
        // console.log(this.state.results)
        this.setState({ text: '' , currency: ''})
    }

    handleRedirect() {
      if (this.state.redirectPage === 'results') {
          return <Redirect to='/results'/>
      } else if (this.state.redirectPage === 'loading') {
        return <Redirect to='/loading' />
      } else {}
    }

    render() {
        return (
            <Router>
              <Switch>
                <React.Fragment>
                  <Route exact path="/"
                      render={() => <SearchBox handleInput={this.handleInput} handleChange={this.handleChange} handleSubmit={this.handleSubmit} search={this.state.text} currency={this.state.currency} />}
                  />
                  {this.handleRedirect()}
                  <Route path="/results" render={() => <ResultBox currency={this.state.currency} results={this.state.results} />} />
                  <Route path="/loading" component={LoadingBox} />
                </React.Fragment>
              </Switch>
            </Router>

        )
    }
}

export default Main;
