import React, { Component } from 'react';


class SearchBox extends Component {

    constructor(props) {
        super(props)
        this.state = {
            query: '',
            currency:'',
            results: []
        }

        this.handleInput = this.handleInput.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        
    }

    getInfo = () => {
        //fetch request?
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
        const text = this.state.text.trim();
        const currency = this.state.currency;
        if (!text || !currency) {
            return;
        }

        console.log('text:',text);
        console.log('currency:', currency);
        this.setState({ text: '' , currency: ''})
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <input
                        type="text"
                        placeholder="Search product..."
                        value={this.state.text}
                        onChange={this.handleInput}
                    />
                    <select id='currency-seelctor' value={this.state.currency} onChange={this.handleChange}>
                        <option default value="default">Select currency</option>
                        <option value="GBP">£ - GBP</option>
                        <option value="USD">$ - USD</option>
                        <option value="EURO">€ - EURO</option>
                        <option value="Yen">¥ - JPY</option>
                        <option value="Yuan">¥ - CNY</option>
                        <option value="Real">R$ - BRL</option>
                        <option value="Rupee">₹ - INR</option>
                        <option value="SinDollar">$ - SGD</option>
                        <option value="Lira">₺ - TRY</option>
                        <option value="Peso">Mex$ - MXN</option>
                        <option value="AUDollar">$ - AUD</option>
                        
                    </select>
                    <input type="submit" value="Search" />
                    {/* <p>{this.state.query}</p> */}
                </form>
            
                {/* <select id='currency-seelctor' defaultValue="default">
                    <option disabled value="default">Select currency</option>
                    <option value="GBP">Pound Sterling</option>
                    <option value="USD">US Dolar</option>
                    <option value="EURO">Euro</option>
                    <option value="Yen">Japanese Yen</option>
                    <option value="Yuan">Chinese Yuan</option>
                    <option value="Real">Brazilian Real</option>
                    <option value="Rupee">Indian Rupee</option>
                    <option value="SinDollar">Singapore Dollar</option>
                    <option value="Lira">Turkish Lira</option>
                    <option value="Peso">Mexican Peso</option>
                    <option value="AUDollar">Australian Dollar</option>
                </select> */}
            </div>
        )
    }
}

export default SearchBox;