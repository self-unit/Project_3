import React from 'react';

const ProductInfo = ({results}) =>{

    const name = results.productName;
    const img = results.imageLink;
    let rating = results.rating;
    if (rating === 0) {
        rating = "N/A";
    }
    
//TODO: make high prices and low prices from converted price list

    // const highestPrice = results.countryList.map(data => )
    // const lowestPrice = results.countryList.map(data => )
    // const savings = highestPrice - lowestPrice;

    return(
        <div className="productInfo">
            <img src={img} alt="product" width="200px" height="200px" />
            <ul>
                <li><b>Product name:</b> {name}</li>
                <li><b>Rating:</b> {rating}</li>
                {/* <li>Highest Price: {highestPrice}</li>
                <li>Lowest Price: {lowestPrice}</li>
                <li>Savings: {savings}</li> */}
            </ul>
        </div>
    )
}

export default ProductInfo;
