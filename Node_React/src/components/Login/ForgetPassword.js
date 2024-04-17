import React ,{useState}from 'react'
import { Link } from 'react-router-dom';
import axios from 'axios';
export default function ForgetPassword() {

  const [username,setUsername]=useState("");
  const [email,setEmail]=useState("");
  const [emailError, setEmailError] = useState('');
  const [isEmailValid, setIsEmailValid] = useState(false);

  const change=(e)=>{
    const {name,value}=e.target;
    if(name==='username')setUsername(value);
    if(name==='email'){
      setEmail(value);
      setIsEmailValid(/^\w+@\w+\.\w+$/.test(value));
      setEmailError(/^\w+@\w+\.\w+$/.test(value)?'':'信箱格式不正確');
    }
  }

  const handleKeyDown = (event) => {
    // 檢查按鍵是否為空格，如果是則阻止默認行為
    if (event.key === ' ') {
        event.preventDefault();
    }
}

const forget=(e)=>{
  //axios.post(`http://localhost:8080/member/forget?username=${username}&email=${email}`)
  e.preventDefault();
  let data={
    username:username,
    email:email
  }
  axios.post("http://localhost:8080/member/forget",data)
  .then(resonse=>{
    alert("已送新密碼至信箱，請重新登入");
  }).catch(error => {
    console.error('Error adding chat:', error);
    alert("失敗");
});
}

  return (
    <>
      <div className="background-divs">
        <div style={{ position: 'absolute', top: '20%', left: '42%' }}>
            <form onSubmit={forget}>
                <table align='center' border={1} width={600}>
                    <tr>
                        <td>帳號</td>
                        <td><input type='text' name="username" onChange={change} onKeyDown={handleKeyDown} required/></td>
                    </tr>
                    <tr>
                        <td>信箱</td>
                        <td>
                          <input type='text' name="email" onChange={change} onKeyDown={handleKeyDown} required/><br/>
                          {emailError && <span style={{ color: 'red' }}>{emailError}</span>}
                        </td>
                    </tr>
                    <tr align="center">
                        <td colSpan={2}>
                          {isEmailValid && <input type='submit' name='ok' />}
                          <Link to='/'><button>回首頁</button></Link>
                        </td>
                    </tr>
                </table>
            </form>
            </div>
        </div>
    </>
  )
}

