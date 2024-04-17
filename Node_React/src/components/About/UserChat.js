import { MemberContext } from '../MemberContext'
import React, { useContext, useState, useEffect } from 'react'
import axios from 'axios';
import UserChatItem from './UserChatItem';
import { Link } from 'react-router-dom';
export default function UserChat() {

    const { member } = useContext(MemberContext);
    const memberId = member.memberId;
    const [userChatList, setUserChatList] = useState([]);

    const queryUserChat = () => {
        let url = `http://localhost:8080/chat/queryUserChat?memberId=${memberId}`;
        axios.get(url)
            .then(response => {
                if (response && response.data) {
                    // 进行响应数据的有效性检查
                    if (Array.isArray(response.data)) {
                        setUserChatList(response.data);
                    } else {
                        console.error('Invalid response data format:', response.data);
                    }
                } else {
                    console.error('Empty or undefined response received.');
                }
            })
    }



    useEffect(() => {
        queryUserChat();
    }, [memberId, queryUserChat])
    return (
        <div className='Box'>
            {userChatList.length > 0 ? (
                <div>{userChatList.map((userChatListInfo) => (
                    <UserChatItem key={userChatListInfo.userId} userChatListInfo={userChatListInfo} queryUserChat={queryUserChat} />
                ))}</div>
            ) :<div className='div-container'><h3>尚未發文</h3><br/><Link to="/chat"><button>前往發文</button></Link></div>}
        </div>
    )
}
