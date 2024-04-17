import React, { useContext, useEffect, useState } from 'react'
import ShopItems from './ShopItems';
import axios from 'axios';
import './Shop.css';
import Footer from '../Footer/Footer';
import { Link } from 'react-router-dom';
import Navibar from '../Navibar/Navibar';
import { MemberContext } from '../MemberContext';
export default function Shop() {
    const { member } = useContext(MemberContext);
    const memberId = member.memberId;
    const [shopList, setShopList] = useState(null);
    const [sum, setSum] = useState(0);

    const queryPorder = () => {
        axios.get("http://localhost:8080/porder/queryId")
            .then(response => {
                setShopList(response.data);
            })
    }

    const querySum = () => {
        let url = `http://localhost:8080/shop/querySum?memberId=${memberId}`;
        axios.get(url)
            .then(response => {
                if (response && response.data) {
                    setSum(response.data);
                } else {
                    console.error('Response data is undefined:', response);
                }
            }).catch(error => {
                console.error('Error fetching sum:', error);
            });
    }

    useEffect(() => {
        queryPorder();
        querySum();
    }, [])

    return (
        <>
            <Navibar />
            <img src="image/05.jpg" className="img-responsive" id="banner" alt="" />
            <div id="content" className="container">
                <div className='shop-con'>
                    <h3 className="title">| 產品介紹<small>Product</small></h3>
                    <div className="right">
                        <span>購物車金額: $<span>{sum}</span>
                            <Link to="/car"><button className="btn btn-default">查看購物車</button></Link></span>
                    </div>
                </div>
                <div className="list-container">
                    {shopList !== null && shopList.map((shopInfo) => <ShopItems key={shopInfo.shopId} shopInfo={shopInfo} />)}
                </div>
            </div>
            <Footer />
        </>
    )
}
