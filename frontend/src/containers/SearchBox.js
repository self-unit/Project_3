import React from 'react';


const SearchBox = ({search, currency, handleSubmit, handleChange, handleInput}) => {

    return (
        <div>
          <h1>Compare your favourite products</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Search product..."
                    value={search}
                    onChange={handleInput}
                />
                <select id='currency-seelctor' value={currency} onChange={handleChange}>
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
            </form>
        </div>
    )

}

export default SearchBox;
