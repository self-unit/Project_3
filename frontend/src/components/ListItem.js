import React from 'react';
import CurrencyHelper from '../helpers/CurrencyHelper';

const ListItem = ({countryCode, originalPrice, link}) => {

    switch(countryCode) {
        case ".co.uk":
        countryCode = "UK"
        break;
        case ".fr":
        countryCode = "France"
        break;
        case ".it":
        countryCode = "Italy"
        break;
        case ".es":
        countryCode = "Spain"
        break;
        case ".ca":
        countryCode = "Canada"
        break;
        case ".com":
        countryCode = "USA"
        break;
        case ".de":
        countryCode = "Germany"
        break;
        case ".co.jp":
        countryCode = "Japan"
        break;
        case ".com.mx":
        countryCode = "Mexico"
        break;
        case ".com.br":
        countryCode = "Brazil"
        break;
    }

    // const convertedValue = new CurrencyHelper();
    // const returnPrice = convertedValue.convert('.co.jp', 'USD', 5)
    //     .then((returnPrice) => {
    // console.log("return price: ", returnPrice)
    // })

    return (
            <tr>
                <td>{countryCode}</td>
                <td>{originalPrice}</td>
                <td>TBA</td>
                <td><a href={link} target="_blank" className="button">Purchase</a></td>
            </tr>
        )
    
// const ListItem = ({countryCode, link, originalPrice, convertCurrency}) => {

}
export default ListItem;