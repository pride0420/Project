import React, { useState, useEffect, useContext } from 'react'
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './login.css';
import { MemberContext } from '../MemberContext';
import ForgetPassword from './ForgetPassword';
function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [Member, setMember] = useState("");
    const navigate = useNavigate();
    const { member, updateMember } = useContext(MemberContext);
    

    const changeHandler = (e) => {
        const { name, value } = e.target;
        if (name === "username") setUsername(value);
        if (name === "password") setPassword(value);
    }

    useEffect(() => {
        if (Member !== "") {

            // Member 状态更新后执行页面导航
            navigate("/chat", { state: { Member: Member } });
        }
    }, [Member, navigate]);

    const login = async (e) => {
        e.preventDefault();
        let data = {
            username: username,
            password: password
        };

try {
    const response = await axios.post("http://localhost:8080/member/login", data, {
        headers: {
            'Content-Type': 'application/json'
        }
    });
    if (response.data != null && response.data != '') {
        updateMember(response.data);
        setMember(response.data);
        console.log(response.data);
    } else{
        setUsername(""); // 清空用户名
        setPassword(""); // 清空密码
        alert("帳號或密碼錯誤");
    }
} catch (error) {
    if (error.response) {
        console.log('Server responded with a non-2xx status:', error.response.status);
        setUsername(""); // 清空用户名
        setPassword(""); // 清空密码
        alert("帳號或密碼錯誤");
    } else if (error.request) {
        console.log('No response received from the server');
        
    } else {
        console.log('Error sending the request:', error.message);
    }
}
}



    return (
        <>
            <div className="background-div">
                <div className="word text-center">
                    <form name='user' onSubmit={login}>
                        <table align='center' border={1}>
                            <tr>
                                <td>帳號:</td>
                                <td><input type='text' name='username' value={username} onChange={changeHandler} required /></td>
                            </tr>
                            <tr>
                                <td>密碼:</td>
                                <td><input type='password' name='password' value={password} onChange={changeHandler} required /></td>
                            </tr>
                            <tr>
                                <td colSpan={2} align='center'>
                                    <input type='submit' value="ok" />
                                </td>
                            </tr>
                        </table>
                    </form>
                    <div><Link to='/forget'><button>忘記密碼</button></Link></div>
                </div>
            </div>
            
        </>
    );
}
export default Login;
