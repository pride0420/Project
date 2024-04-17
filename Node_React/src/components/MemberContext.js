import axios from 'axios';
import React, { createContext, useState ,useEffect} from 'react';
import { jwtDecode } from 'jwt-decode';

export const MemberContext = createContext();
export const MemberProvider = ({ children }) => {
  const [member, setMember] = useState(null);
  const [isLoggin, setIsLoggin] = useState(false); // 添加 isLoggedIn 状态

  useEffect(() => {
    // 檢查 token 是否存在並且未過期
    const storedToken = localStorage.getItem('accessToken');
    if (storedToken) {
      const decodedToken = jwtDecode(storedToken);
      const currentTime = Date.now() / 1000;
      if (decodedToken.exp > currentTime) {
        refresh();
      } else {
        // 如果 token 已過期，則執行登出邏輯
        restMember();
      }
    }
  }, []);

  const refresh=()=>{
    axios.post("http://localhost:8080/member/login/refresh-token")
    .then(response=>{
      setMember(response.data);
    })
  }

  const updateMember = (newMember) => {
    setMember(newMember);
    setIsLoggin(true);
  };

  const restMember = () => {
    setMember(null);
    setIsLoggin(false); // 在登出时设置为 false
    localStorage.removeItem('accessToken'); // 移除本地存储中的令牌
    console.log(localStorage.getItem('accessToken')); // 输出本地存储中的令牌
  }

  return (
    <MemberContext.Provider value={{ member, updateMember, restMember, isLoggin }}>
      {children}
    </MemberContext.Provider>
  );
};