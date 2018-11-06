import React from 'react';
import ReactLoading from "react-loading";

const LoadingBox = () => (
  <div className="loadingbox">
    <h4>Sit tight we're getting the best prices for you...</h4>
      <div key="bubbles">
        <ReactLoading type="bubbles" color="#000" />
      </div>

  </div>
);


export default LoadingBox;
