import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import './Shop.css';
import axios from 'axios';
export default class ShopItems extends Component {
  constructor(props) {
    super(props);
    
  }
  render() {
    const { shopInfo } = this.props;
    const { porderId, product, mode, explanation,items } = shopInfo;

    return (
      <div  className="list-item">
      <div className="item-content">
      <div className="image-container">
        <img src={`images/${porderId}.jpg`} className="item-image" alt="" />
        <div className="image-info">
          <p>{explanation}</p>
        </div>
        </div>
        <h3>{product}</h3>
        <p>{mode}</p>
        <Link to={`/products/${items}`} className="btn btn-default">查看更多</Link>
      </div>
    </div>
    )
  }
}
