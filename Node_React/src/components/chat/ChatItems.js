import React, { Component } from 'react'

import './chat.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
export default class ChatItems extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        const { chatInfo, memberId, queryAll } = this.props;
        const { chatId, name, content, firsttime, item, chatSum,goodsChatSum } = chatInfo;

        // 将时间戳转换为 Date 对象
        const date = new Date(firsttime);

        // 获取日期部分
        const dateString = date.toLocaleDateString();

        // 获取时间部分
        const timeString = date.toLocaleTimeString();

        const addGoodChat = () => {
            let data = {
                chatId: chatId,
                memberId: memberId
            }
            axios.post("http://localhost:8080/goodchat/addGoodChat", data, {
                headers: { 'Content-Type': 'application/json' }
            })
                .then(response => {
                    alert(response.data);
                })
        }

        return (
            <table className='table' align='center' border={1} >
                <tr>
                    <td><p>發文者:{name}</p></td>
                    <td><p>{dateString} {timeString}</p></td>

                </tr>
                <tr>
                    <td colSpan={2} className='chat-content'>{content}</td>
                </tr>
                <tr>
                    <td colSpan={2} >
                        <div className='flex-content'>
                            <button  onClick={() => { addGoodChat(memberId, chatId); queryAll(); }} >{item ? "已按讚" : "讚"}</button>
                            <span className='left'>{goodsChatSum}</span>
                                
                            <Link to={`/comment/${chatId}`}>
                                <button >留言 </button>
                            </Link>
                            <span> {chatSum}筆</span>
                                
                        </div>
                    </td>
                </tr>
            </table>

        )
    }
}
