import React from 'react';
// import ReactLoading from "react-loading";
import ReactLoading from "react-spinkit";

const LoadingBox = () => (
  <div className="loadingbox">
    <h4>Sit tight, we're finding the best prices for you...</h4>
    <div className="loadingLogo" >
      <ReactLoading name="ball-scale-ripple" color="coral" size="200"/>
    </div>
  </div>
);


export default LoadingBox;
