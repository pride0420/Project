import React, { useState, useEffect } from 'react'
import Navibar from '../Navibar/Navibar'
import Footer from '../Footer/Footer'
import { useParams, useLocation } from 'react-router-dom';
import axios from 'axios';
import './about.css';
import { useNavigate } from 'react-router-dom';

export default function SetChat() {
    const { chatId } = useParams();
    const [chat, setChat] = useState(null);
    const [content, setContent] = useState(chat && chat.content ? chat.content : '');
    const [loaded, setLoaded] = useState(false); // 新增一个状态用于表示数据是否已加载
    const navigate = useNavigate();

    const queryChat = (chatId) => {
        axios.get(`http://localhost:8080/chat/queryChat?chatId=${chatId}`)
            .then(response => {
                setChat(response.data);
                setContent(response.data.content);
                console.log(response.data);
                setLoaded(true); // 设置 loaded 为 true 表示数据已加载
            })
    }

    useEffect(() => {
        queryChat(chatId);
    }, [])

    const chageComtent = (e) => {
        const { name, value } = e.target;
        if (name === 'content') setContent(value);
    }

    const resetComtemt = () => {
        setContent(chat.content);
    }

    const updateChat = (e) => {
        e.preventDefault();
        let data = {
            chatId: chatId,
            content: content
        }
        axios.post('http://localhost:8080/chat/updateChat', data, {
            headers: { 'Content-Type': 'application/json' }
        })
            .then(response => {
                alert("已修改");
                navigate("/about");
            })
    }

    const goBack = () => {
        navigate("/about");
    }
    
    return (
        <>
            <Navibar />
            <img src="../image/05.jpg" className="img-responsive" id="banner" alt="" />
            {loaded && (
                <div className='Box'>
                    <form onSubmit={updateChat}>
                        <table className='table' border={1}>
                            <tr>
                                <td className='setChat'>
                                    <textarea name='content' value={content} placeholder={chat.content} rows={5} cols={70} onChange={chageComtent} required />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type='submit' value="修改" />
                                    <input type='reset' value="重設" onClick={resetComtemt} />
                                    <button onClick={goBack}>回上頁</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            )}
            <Footer />
        </>
    )
}
