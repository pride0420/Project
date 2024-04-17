import React, { useContext, useState } from 'react'
import { MemberContext } from '../MemberContext'
import './about.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import SetPassword from './SetPassword';
import { Link } from 'react-router-dom';
export default function SetUser() {
    const { member, updateMember ,restMember} = useContext(MemberContext);
    const memberId = member.memberId;
    const username = member.username;
    const [name, setName] = useState(member.name);
    const [phone, setPhone] = useState(member.phone);
    const [email, setEmail] = useState(member.email);
    const [isEmailValid, setIsEmailValid] = useState(true);
    const [emailError, setEmailError] = useState('');
    const navigate = useNavigate();

    const changeUser = (e) => {
        const { name, value } = e.target;
        //禁止第一個字輸入空格
        if (name === 'name'&&value.startsWith(" ")) {
            e.target.value=value.trimStart();
        }else if(name==='name'){
            setName(value);
        }
        if (name === 'phone') {
            setPhone(value);
        }
        if (name === 'email') {
            setEmail(value);
            setIsEmailValid(/^\w+@\w+\.\w+$/.test(value));
            setEmailError(/^\w+@\w+\.\w+$/.test(value) ? '' : '信箱格式不正確');
        }
    }

    const handleKeyDown = (event) => {
        // 檢查按鍵是否為空格，如果是則阻止默認行為
        if (event.key === ' ') {
            event.preventDefault();
        }
    }

    const checkSubmit = (e) => {
        e.preventDefault();
            let data = {
                username: username,
                name: name,
                phone: phone,
                email: email
            }
            console.log(data.name);
            axios.post("http://localhost:8080/member/updateMember", data, {
                headers: { 'Content-Type': 'application/json' }
            })
                .then(response => {
                    updateMember(response.data);
                    alert("修改成功");
                })
                .catch(error => {
                    // 处理错误
                    console.error("修改失败:", error);
                });
        }

    const handleReset = () => {
        // 将输入框的值恢复为 member 原来的值
        setName(member.name);
        setPhone(member.phone);
        setEmail(member.email);
        setIsEmailValid(true); // 重置邮箱验证状态
    }

    const btnDelete = () => {
        let Click = window.confirm("是否確定註銷");
        if (Click) {
            axios.post(`http://localhost:8080/member/deleteMember?memberId=${memberId}`)
                .then(response => {
                    restMember();
                    navigate("/");
                })
        }
    }
    return (

        <div className='Box'>
            <form onSubmit={checkSubmit}>
                <table align='center' border={1} width="600">
                    <tr>
                        <td>暱稱</td>
                        <td><input type='text' name='name' value={name} placeholder={member.name} onChange={changeUser} required /></td>
                    </tr>
                    <tr>
                        <td>電話</td>
                        <td><input type='text' name='phone' pattern="\d*" value={phone} placeholder={member.phone} onChange={changeUser} onKeyDown={handleKeyDown} required /></td>
                    </tr>
                    <tr>
                        <td>信箱</td>
                        <td><input type='text' name='email' value={email} placeholder={member.email} onChange={changeUser} onKeyDown={handleKeyDown} required /><br />
                            {emailError && <span className='error' style={{ color: 'red' }}>{emailError}</span>}
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2}>
                            <input type='submit' value="更新" disabled={!isEmailValid} />
                            <input type='reset' value='復原' onClick={handleReset} />
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2}>
                           <Link to='/setPassword'><button>修改密碼</button></Link>
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2}><button onClick={btnDelete}>註銷帳號</button></td>
                    </tr>
                </table>
            </form>
        </div>
    )
}

