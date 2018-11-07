import React from 'react';

const ProductInfo = () =>{

    const name = details.productName;
    const img = details.image;
    const rating = details.rating;
    const price = details.price;
    const highestPrice = details.highPrice;
    const lowestPrice = details.lowPrice;
    const savings = details.savings;

    return(
        <div>
            <img src={img} alt="product image" width="50px" height="50px" />
            <ul>
                <h3>{name}</h3>
                <li>Rating: {rating}</li>
                <li>Highest Price: {highestPrice}</li>
                <li>Lowest Price: {lowestPrice}</li>
                <li>Savings: {savings}</li>
            </ul>
        </div>
    )
}

export default ProductInfo;
