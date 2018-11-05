import React from 'react';

const ProductInfo = () =>{

    const name = props.details.name;
    const img = props.details.image;
    const rating = props.details.rating;
    const price = props.details.price;
    const highestPrice = props.details.highestPrice;
    const lowestPrice = props.details.lowestPrice;
    const savings = props.details.savings;

    //should be returned from the API
    // let lowestPrice = (price) => {
    //     let minPrice = 0;

    //     for (let i=0; i < price.length; i++){
    //         let price = arr[i].y;
    //         minPrice = (price < minPrice) ? price : minPrice;
    //     }
        
    //     return minPrice;
    // }

    // let highestPrice = () => {

    // }

    // let savings = () =>{
    //     return highestPrice - lowestPrice;
    // }

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