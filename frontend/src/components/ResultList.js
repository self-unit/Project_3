import React from 'react';
import ListItem from './ListItem';

const ResultList = ({countryList}) => {


  const CountryList = countryList.map((data, id) =>
    <ListItem key={id} countryCode={data.countryCode} link={data.link} originalPrice={data.price} convertedPrice={data.convertedPrice}/>
)


  return(
    <div>
      <h1>Result List</h1>
      <table>
        <thead>
          <tr>
            <th>Country |</th>
            <th>Price in local currency |</th>
            <th>Converted Price |</th>
            <th>Buy</th>
          </tr>
        </thead>
        <tbody>
            {CountryList}
        </tbody>
      </table>
    </div>
  )
}

export default ResultList;
