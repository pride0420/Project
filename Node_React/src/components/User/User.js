import React ,{useState,useEffect,useContext}from 'react'
import axios from 'axios'
import Navibar from '../Navibar/Navibar';
import UserList from './UserList';
import Footer from '../Footer/Footer';
import { MemberContext } from '../MemberContext';
import './user.css'
export default function User() {
    const [memberList,setMemberList]=useState(null);
    const {member}=useContext(MemberContext);
    const token=member.accessToken;
    const [errorMag,setErrorMag]=useState(null);


// 设置响应拦截器
axios.interceptors.response.use(
    (response) => {
      // 对响应数据做些什么
      return response;
    },
    (error) => {
      // 对响应错误做些什么
      if (error.response) {
        // 如果有响应，但是状态码为403，表示权限不足
        if (error.response.status === 403) {
          // 显示权限不足的提示信息
          setErrorMag('权限不足，请联系管理员获取访问权限！');
          // 标记错误为已处理
          error.isHandled = true;
        }else{
          console.log(error.response.staus);
        }
      } 
      return Promise.reject(error);
    }
  );
  

    const queryAllMember=()=>{
        axios.get("http://localhost:8080/member/queryAll",{headers:{'Authorization': `Bearer ${token}`}})
        .then(response=>{
            setMemberList(response.data);
        }).catch(error => {
            // 如果错误未被标记为已处理，则重新抛出错误
            console.log(error.response.status); // 打印錯誤的響應狀態碼
            console.log(error.response.data); // 打印錯誤的響應數據
            if (!error.isHandled) {
              throw error;
            }
          });
    }
    useEffect(()=>{
        queryAllMember();
    },[])

    const download = () => {
        axios.get("http://localhost:8080/download/downloadExcel", { responseType: 'blob' })
            .then(response => {
                const blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = '帳號資料.xlsx';
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
            })
            .catch(error => {
                console.error('Error downloading Excel file:', error);
            });
    }

    const downJasp = (format) => {

        //axios(請求網址,data,response的類型)
        axios.post(`http://localhost:8080/jasp/generateReport?format=${format}`, null, { responseType: 'blob' })
            .then(response => {
                    //檢查狀態碼
                if (response.status !== 200) {
                    throw new Error("錯誤");
                }
                let contentType = 'application/pdf';
                let fileExtension = 'pdf';
                if (format === 'xlsx') {
                    contentType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
                    fileExtension = 'xlsx';
                }
                //blob是二進制數據的JS對象 接收數據跟配置
                const blob = new Blob([response.data], { type: contentType });
                //創建一個URL
                const url = window.URL.createObjectURL(blob);
                //創建一個<a>
                const a = document.createElement('a');
                //將href設置為創建的url  對應到文件
                a.href = url;
                //設定下載的檔名
                a.download = `帳號資料.${fileExtension}`;
                //將文檔加入到body中
                document.body.appendChild(a);
                //模擬點擊
                a.click();
                //釋放數據
                window.URL.revokeObjectURL(url);
            })
            .catch(error => {
                console.error('There was a problem with your fetch operation:', error);
            });
    }
    
  return (
    <>
       <Navibar />
            <img src="image/05.jpg" className="img-responsive" id="banner" alt="" />
            {errorMag!==null?(<div className='errrorMag'>{errorMag}</div>) :(
        <div className='Box'>
            <div className='button'>
                <button onClick={download}>匯出(excel)</button>
                <button onClick={() => downJasp("pdf")}>匯出(jasper pdf)</button>
                <button onClick={() => downJasp("xlsx")}>匯出(jasper xlsx)</button>
            </div>
            {memberList&&memberList.map((memberInfo)=>(<UserList key={memberInfo.memberId} memberInfo={memberInfo}/>))}
        </div>
         )}
        <Footer/>
   
    </>
  )
}
