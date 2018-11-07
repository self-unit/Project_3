import React from 'react';

const ListItem = ({countryCode, link, originalPrice, convertedPrice}) => {
    return(
        <tr>
            <th>{countryCode} |</th>
            <th>{originalPrice} |</th>
            <th>{convertedPrice} |</th>
            <th>{link}</th>
        </tr>
    )
}

export default ListItem;