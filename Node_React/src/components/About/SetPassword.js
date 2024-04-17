import React,{useContext,useState} from 'react'
import Navibar from '../Navibar/Navibar'
import './about.css';
import { MemberContext } from '../MemberContext';
import axios from 'axios';
import Footer from '../Footer/Footer';
export default function SetPassword() {
    const {member,updateMember}=useContext(MemberContext);
    const [password,setPassword]=useState("");
    const [newPassword,setNewPassword]=useState("");
    const [checkPassword,setCheckPassword]=useState("");
    const username=member.username;
    const [isPasswordError,setIsPasswordError]=useState("");

    const change=(e)=>{
        const {name,value}=e.target;
        if(name==='password')setPassword(value);
        if(name==='newPassword'){
            setNewPassword(value);
            setIsPasswordError(value===checkPassword?"":"密碼不一致");
            }
        if(name==='checkPassword'){
            setCheckPassword(value);
            setIsPasswordError(value===newPassword?"":"密碼不一致");
        }
        
    }

    const handleKeyDown = (event) => {
        // 檢查按鍵是否為空格，如果是則阻止默認行為
        if (event.key === ' ') {
            event.preventDefault();
        }
    }

    const updatePassword=(e)=>{
        e.preventDefault();
        if(newPassword!=checkPassword){
            alert("密碼不一致");
            e.preventDefault();
        }else{
            let data={
                username:username,
                password:password,
                newPassword:newPassword
            }
            axios.post("http://localhost:8080/member/updatePassword",data)
            .then(response=>{
                if(response.data){
                    alert("修改成功")
                }else{
                    alert("原密碼錯誤")
                }
                console.log(response.data);
                
            }).catch(error=>{
                console.error("修改失败:", error);
                alert(error);
            })
        }
    }

  return (
    <>
      <Navibar />
        <img src="image/05.jpg" className="img-responsive" id="banner" alt="" />
            <div className='Box'>
                <form onSubmit={updatePassword}>
                    <table align='center' border={1} width="600">
                        <tr>
                            <td>原密碼</td>
                            <td><input type='password' name='password' onChange={change} onKeyDown={handleKeyDown} required/></td>
                        </tr>
                        <tr>
                            <td>新密碼</td>
                            <td><input type='password' name='newPassword' onChange={change} onKeyDown={handleKeyDown} required/></td>
                        </tr>
                        <tr>
                            <td>確認密碼</td>
                            <td>
                                <input type='password' name='checkPassword' onChange={change} onKeyDown={handleKeyDown} required/><br/>
                                {isPasswordError && <span className='error' style={{ color: 'red' }}>{isPasswordError}</span>}
                            </td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <input type='submit' value="ok"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <Footer/>
    </>
  )
}
