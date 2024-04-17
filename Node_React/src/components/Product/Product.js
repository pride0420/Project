import axios from 'axios';
import React, { useState, useEffect, useContext } from 'react';
import { useParams, useLocation } from 'react-router-dom';
import ProductItem from './ProductItem';
import Footer from '../Footer/Footer';
import { Link } from 'react-router-dom';
import Navibar from '../Navibar/Navibar';
import { MemberContext } from '../MemberContext';
import './product.css';
function Product() {
    
    const { items } = useParams();
    const [productList, setProductList] = useState(null);
    const [sum, setSum] = useState(null);
    const { member } = useContext(MemberContext);
    const memberId = member.memberId;

    //初始頁數
    const [currentPage, setCurrentPage] = useState(1);
    //顯示的數量
    const productsPerPage = 3;
    //儲存顯示的商品
    const [currentProducts, setCurrentProducts] = useState([]);
    //總數量
    const [totalPages,setTotalPage]=useState(0);
    
    useEffect(() => {
        if (productList) {
            //紀錄最後一個產品的位置
            const lastProduct = currentPage * productsPerPage;
            //紀錄第一個產品的位置
            const firstProduct = lastProduct - productsPerPage;
            //儲存這之間的資料
            //slice從陣列中提取子陣列 slice(起始,結束(不包含))
            const productsToShow = productList.slice(firstProduct, lastProduct);
           
            setCurrentProducts(productsToShow);
        }
        
    }, [currentPage, productList]);
    
    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    }

    useEffect(()=>{
        queryItems(items);
        querySum();
    },[items]);

    const queryItems=(items)=>{
        axios.get(`http://localhost:8080/porder/queryItems?items=${items}`)
        .then(response=>{
            setProductList(response.data);
            //math.ceil返回大於或=0的最小數 計算出總頁數
            setTotalPage(Math.ceil(response.data.length / productsPerPage));
            setCurrentPage(1); // 將 currentPage 設置回1
            console.log(response);
        })
    }

    const querySum = () => {
        axios.get(`http://localhost:8080/shop/querySum?memberId=${memberId}`)
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

    const updateProductList = () => {
        // 更新 productList 数据
        querySum();
    }
   
    return (
        <>
            <Navibar />
            <img src="/image/05.jpg" className="img-responsive" id="banner" alt="" />
            <div id="content" className="container">
                <div className='shop-con'>
                    <h3 className="title">| 產品介紹<small>Product</small></h3>
                    <span><Link to='/shop'><button className='btn btn-default'>回上一頁</button></Link></span>

                    <div className="right">
                        <span>購物車金額: $<span>{sum !== null ? sum : '0'}</span>
                            <Link to="/car"><button className="btn btn-default">查看購物車</button></Link></span>
                    </div>
                </div>
                <div className="list-container">
                {productList && productList.length > 0 && (
                    currentProducts.map((productInfo) => (
                    <ProductItem key={productInfo.productId} productInfo={productInfo} updateProductList={updateProductList} />
                    ))
                )}
                </div>
            </div>
           <div className='flex-button'>
           <button onClick={() => setCurrentPage(currentPage - 1)} disabled={currentPage===1}>上一页</button>
           {/*_是當前元素值(undefined) index是元素索引  key生成個別元素索引 */}
           {[...Array(totalPages)].map((_, index) => (
                    <button key={index + 1} onClick={() => handlePageChange(index + 1)} disabled={currentPage === index + 1}>{index + 1}</button>
                ))}
            <button onClick={() => setCurrentPage(currentPage + 1)} disabled={currentPage === totalPages}>下一页</button>
           </div>
            
            <div className='butoon-shop'>
                <button className="btn btn-default" onClick={() => { queryItems("手機") }}>手機</button>
                <button className="btn btn-default" onClick={() => { queryItems("電腦") }}>電腦</button>
                <button className="btn btn-default" onClick={() => { queryItems("電動") }}>電動</button>
                <button className="btn btn-default" onClick={() => { queryItems("其他") }}>其他</button>
            </div>
            <Footer />
        </>
    );
}

export default Product;