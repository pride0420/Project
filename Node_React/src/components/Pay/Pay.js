import React, { useContext, useEffect, useState } from 'react';
import Navibar from '../Navibar/Navibar';
import { MemberContext } from '../MemberContext';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import Footer from '../Footer/Footer';
export default function Pay() {
    const { member } = useContext(MemberContext);
    const memberId = member.memberId;
    const [email, setEmail] = useState(member.email);
    const [isEmailValid, setIsEmailValid] = useState(true);
    const [sum, setSum] = useState(0);
    const [emailError, setEmailError] = useState('');
    const navigate = useNavigate();

    const changeEmail = (e) => {
        const { name, value } = e.target;
        if (name === 'email') {
            setEmail(value);
            setIsEmailValid(/^\w+@\w+\.\w+$/.test(value));
            setEmailError(/^\w+@\w+\.\w+$/.test(value) ? '' : '信箱格式不正確');
        }
    }

    const querySum = () => {
        axios.get(`http://localhost:8080/shop/querySum?memberId=${memberId}`)
            .then(response => {
                setSum(response.data);
            }).catch(error => {
                console.error('Error fetching sum:', error);

            });
    }

    const checkSubmit = (e) => {
        e.preventDefault();
        if (!isEmailValid) {
            alert("信箱錯誤");
            return;
        } else {
            let Click = window.confirm("總金額:" + sum + "元，是否確定結帳");
            if (Click) {
            axios.post(`http://localhost:8080/car/checkCar?memberId=${memberId}&email=${email}&sum=${sum}`)
                .then(response => {
                    alert("購買成功");
                    navigate("/shop");
                })
                }
        }
    }
    const handleKeyDown = (event) => {
        // 檢查按鍵是否為空格，如果是則阻止默認行為
        if (event.key === ' ') {
            event.preventDefault();
        }
    }

    const resetEmail = () => {
        setEmail(member.email);
        setIsEmailValid(true);
        setEmailError(true);
    }

    useEffect(() => {
        querySum();
    },[])
    return (
        <>
            <Navibar />
            <img src="image/05.jpg" className="img-responsive" id="banner" alt="" />
            <table align='center' border={1} width="500">
                <caption><h2>訂單資訊</h2></caption>
                <tr>
                    <td>商家:</td>
                    <td>神龍</td>
                </tr>
                <tr>
                    <td>購買者:</td>
                    <td>{member.name}</td>
                </tr>
                <td>商品金額:</td>
                <td>{sum}</td>
                <tr align="right">
                    <td colspan="2">實際付款金額:&nbsp;&nbsp;&nbsp;NT$<b>{sum}</b></td>
                </tr>
                <tr>
                    <td colSpan={2}>
                        <Link to="/shop"><button>回商城</button></Link>
                    </td>
                </tr>
            </table>

            <hr />
            <form onSubmit={checkSubmit}>
                <table width="610" border="1" align="center">
                    <tr align="center">
                        <td>信用卡號:</td>
                        <td align="left"><input type="text" maxlength="4" minlength="4" required size="4" pattern="\d*" onKeyDown={handleKeyDown}/> -
                            <input type="text" maxlength="4" minlength="4" required size="4" pattern="\d*" onKeyDown={handleKeyDown}/> -
                            <input type="text" maxlength="4" minlength="4" required size="4" pattern="\d*" onKeyDown={handleKeyDown}/> -
                            <input type="text" maxlength="4" minlength="4" required size="4" pattern="\d*" onKeyDown={handleKeyDown}/></td>
                    </tr>
                    <tr align="center">
                        <td>有效年月:</td>
                        <td align="left"><input type="text" placeholder="MM" maxlength="2" minlength="2" size="3" pattern="\d*" onKeyDown={handleKeyDown} required />&nbsp;
                            <input type="text" placeholder="YY" maxlength="2" minlength="2" size="3" pattern="\d*" onKeyDown={handleKeyDown} required /></td>
                    </tr>
                    <tr align="center">
                        <td>安全碼:</td>
                        <td align="left"><input type="text" maxlength="3" minlength="3" size="3" pattern="\d*" onKeyDown={handleKeyDown} required /></td>
                    </tr>
                    <tr align="center">
                        <td>持卡人姓名:</td>
                        <td align="left"><input type="text" size="5" onKeyDown={handleKeyDown} required /></td>
                    </tr>
                    <tr align="center">
                        <td>送貨地址:</td>
                        <td align="left"><input type="text" onKeyDown={handleKeyDown} required /></td>
                    </tr>
                    <tr align="center">
                        <td>信箱:</td>
                        <td align="left"><input type="text" name="email" value={email} placeholder={member.email} onChange={changeEmail} onKeyDown={handleKeyDown} required /><br />
                            {emailError && <span className='error' style={{ color: 'red' }}>{emailError}</span>}
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="2">
                            <input type="submit" value="立即付款" disabled={!isEmailValid} />
                            <input type='reset' value="重設" onClick={resetEmail} />
                        </td>
                    </tr>
                </table>
            </form>
            <Footer />
        </>
    )
}

