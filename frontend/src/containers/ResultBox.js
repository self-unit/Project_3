import React from 'react';
import SearchBox from './SearchBox';
import ResultList from '../components/ResultList';

const Results = () => (
    <div className='resultPage'>
        <h1>Result Box</h1>
        <div className="productSearch">
            <h4>Search products: </h4>
            <SearchBox />
        </div>
        <div className='productInfo'>
            <h4>Product Info Box</h4>
        </div>
        <div className='resultList'>
            <h4>Result List:</h4>
            <ResultList />
        </div>

        {/* <div className='productSuggetion'>

        </div> */}
    </div>
);

export default Results;
