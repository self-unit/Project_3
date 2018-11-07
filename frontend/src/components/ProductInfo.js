import React from 'react';

const ProductInfo = ({results}) =>{

    const name = results.productName;
    const img = results.imageLink;
    const rating = results.rating;
//TODO: make high prices and low prices from converted price list

    // const highestPrice = results.countryList.map(data => )
    // const lowestPrice = results.countryList.map(data => )
    // const savings = highestPrice - lowestPrice;

    return(
        <div>
            <img src={img} alt="product" width="50px" height="50px" />
            <ul>
                <h4>{name}</h4>
                <li>Rating: {rating}</li>
                {/* <li>Highest Price: {highestPrice}</li>
                <li>Lowest Price: {lowestPrice}</li>
                <li>Savings: {savings}</li> */}
            </ul>
        </div>
    )
}

export default ProductInfo;
