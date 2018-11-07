import React from 'react';
import SearchBox from './SearchBox';
import ResultList from '../components/ResultList';
import ProductInfo from '../components/ProductInfo';

const ResultBox = ({results}) => {

  for (var key in results) {
    if (results.hasOwnProperty(key)) {
      console.log(key +':'+ results[key]);
    }
  }

  console.log(results);

  return (
    <div className='resultPage'>
        <h1>Result Box</h1>
        <div className='productInfo'>
            <h4>Product Info Box</h4>
            <ProductInfo results={results}/>
        </div>
        <div className='resultList'>
            <h4>Result List:</h4>
            <ResultList countryList={results.countryList}/>
        </div>

        {/* <div className='productSuggetion'>

        </div> */}
    </div>
  )
};

export default ResultBox;
