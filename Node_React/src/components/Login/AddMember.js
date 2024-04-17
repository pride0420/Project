import React, { useState } from 'react'
import './login.css';
import axios from 'axios';

export default function AddMember() {

    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [name, setName] = useState();
    const [phone, setPhone] = useState();
    const [email, setEmail] = useState();
    const [emailError, setEmailError] = useState('');
    const [isEmailValid, setIsEmailValid] = useState(false);

    const change = (e) => {
        const { name, value } = e.target;
        if (name === "username") setUsername(value);
        if (name === "password") setPassword(value);
        if (name === "name") setName(value);
        if (name === "phone") setPhone(value);
        if (name === "email") {
            setEmail(value);
            setIsEmailValid(/^\w+@\w+\.\w+$/.test(value));
            setEmailError(/^\w+@\w+\.\w+$/.test(value) ? '' : '信箱格式不正確');
        }
    }

    const addMember = (e) => {
        e.preventDefault(); // 防止表單的預設提交行為
        let data = {
            username: username,
            password: password,
            name: name,
            phone: phone,
            email: email
        }
        axios.post("http://localhost:8080/member/addMember", data, {
            header: { 'Content-Type': 'application/json' }
        })
            .then(response => {
                if (response.data) {
                    alert("新增成功，請前往登入");
                } else {
                    alert("帳號重複");
                }
            }).catch(error => {
                console.error("發生錯誤:", error);
                alert("新增失敗");
            });
    }


    const handleKeyDown = (event) => {
        // 檢查按鍵是否為空格，如果是則阻止默認行為
        if (event.key === ' ') {
            event.preventDefault();
        }
    }

    return (
        <>
            <div className="background-divs">
                <div className="words text-center">
                    <form onSubmit={addMember}>
                        <table align='center' border={1}>
                            <tr>
                                <td>帳號:</td>
                                <td><input type='text' name='username' onChange={change} onKeyDown={handleKeyDown} required /></td>
                            </tr>
                            <tr>
                                <td>密碼:</td>
                                <td><input type='password' name='password' onChange={change} onKeyDown={handleKeyDown} required /></td>
                            </tr>
                            <tr>
                                <td>暱稱:</td>
                                <td><input type='text' name='name' onChange={change} onKeyDown={handleKeyDown} required /></td>
                            </tr>
                            <tr>
                                <td>電話:</td>
                                <td><input type='text' name='phone' pattern="\d*" onChange={change} onKeyDown={handleKeyDown} required /></td>
                            </tr>
                            <tr>
                                <td>信箱:</td>
                                <td>
                                    <input type='text' name='email' onChange={change} onKeyDown={handleKeyDown} required /><br />
                                    {emailError && <span className='error' style={{ color: 'red' }}>{emailError}</span>}
                                </td>
                            </tr>
                            <tr>
                                <td colSpan={2} >
                                    {isEmailValid && <input type='submit' name='ok' />}
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </>
    )
}

