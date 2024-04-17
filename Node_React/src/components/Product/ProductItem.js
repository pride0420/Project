import axios from 'axios';
import React, { useContext } from 'react'
import { MemberContext } from '../MemberContext';
export default function ProductItem(props) {

  const { member } = useContext(MemberContext);
  const memberId = member.memberId;
  const { productInfo,updateProductList } = props;
  const { porderId, product, mode,explanation, amount } = productInfo;

  const addShop = () => {

    const x = axios.post(`http://localhost:8080/shop/addShop?porderId=${porderId}&memberId=${memberId}`);
    x.then(response => {
      alert("新增成功");
      // 触发父组件的更新函数
      updateProductList();
    }).catch(function (error) {
      if (error.response) {
        console.log('Server responded with a non-2xx status:', error.response.status);
      } else if (error.request) {
        // 请求被发送，但没有收到响应
        console.log('No response received from the server');
      } else {
        // 发送请求时发生错误
        console.log('Error sending the request:', error.message);
      }
    })
  }

  return (
    <div className="list-item">
      <div className="item-content">
      <div className="image-container">
        <img src={`/images/${porderId}.jpg`} className="item-image" />
        <div className="image-info">
          <p>{explanation}</p>
        </div>
      </div>
        <h3>{product}</h3>
        <p>{mode}</p>
        <p>價格:{amount}</p>
        <button className="btn btn-default" onClick={addShop}>加入購物車</button>
      </div>
    </div>
  )

}
