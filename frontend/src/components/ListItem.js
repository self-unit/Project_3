import React from 'react';

const ListItem = ({countryCode, link, originalPrice}) => {

    switch(countryCode){
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

    return(
        <tr>
            <td>{countryCode}</td>
            <td>{originalPrice}</td>
            {/* <td>{convertedPrice}</td> */}
            <td><a href={link} target="_blank" class="button">Go To Listing</a></td>
        </tr>
    )
}

export default ListItem;
