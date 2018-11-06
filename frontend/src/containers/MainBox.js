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
            results: {
              productName: "Call of Duty: Black Ops 4 (PS4)",
              image: "https://images-eu.ssl-images-amazon.com/images/I/51dKoETIm1L._AC_US436_FMwebp_QL65_.jpg",
              rating: 3.3,
              highPrice: "£46.75",
              lowPrice: "$36.75",
              countryList: [
                { country: "UK", price: "£46.75", url: "http://amazon.co.uk/url2buy" },
                { country: "USA", price: "$56.75", url: "http://amazon.com/url2buy" }
            ]
          }
        }

        this.handleInput = this.handleInput.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
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

        fetch('https://localhost/search')
          .then( (response) => {
            return response.json();
          })
          .then( (jsonData) =>{
            this.setState({results: jsonData})
          });

        //reset form
        this.setState({ text: '' })
    }

    handleResultSubmit(newResult) {
      newResult.id = Date.now();
      const updatedResults= [this.state.results, newResult];
      this.setState({results: updatedResults})
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
