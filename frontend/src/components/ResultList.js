import React from 'react';

const ResultList = () => {

  return(
    <div>
      <h1>Result List</h1>
      <table>
        <thead>
          <tr>
            <th>Country |</th>
            <th>Product Name |</th>
            <th>Currency |</th>
            <th>Price |</th>
            <th>Savings |</th>
            <th>Buy</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{1}</td>
            <td>{2}</td>
            <td>{3}</td>
            <td>{4}</td>
            <td>{5}</td>
            <td>{6}</td>
          </tr>
        </tbody>
      </table>
    </div>
  )
}

export default ResultList;
