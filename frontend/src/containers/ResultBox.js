import React from 'react';
import SearchBox from './SearchBox';
import ResultList from '../components/ResultList';
import ProductInfo from '../components/ProductInfo';

const ResultBox = ({results, currency, newPrice}) => {

  for (var key in results) {
    if (results.hasOwnProperty(key)) {
      console.log(key +':'+ results[key]);
    }
  }

  return (
    <div className='resultPage'>
        <div className='productInfo'>
            <h4>Product details</h4>
            <ProductInfo results={results} currency={currency}/>
        </div>
        <div className='resultList'>
            <h4>International results</h4>
            <ResultList countryList={results.countryList} currency={currency} newPrice={newPrice} />
        </div>

        {/* <div className='productSuggetion'>

        </div> */}
    </div>
  )
};

export default ResultBox;
