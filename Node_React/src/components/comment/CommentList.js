import React, { Component, useState } from 'react'
import './comment.css';
import axios from 'axios';
export default class CommentList extends Component {

    constructor(props) {
        super(props);
    }
    render() {
        const { commentInfo, memberId, queryList } = this.props;
        const { name, firsttime, comment, commentId, chatId, item,button,goodsCommentSum } = commentInfo;

        // 将时间戳转换为 Date 对象
        const date = new Date(firsttime);

        // 获取日期部分
        const dateString = date.toLocaleDateString();

        // 获取时间部分
        const timeString = date.toLocaleTimeString();

        const goodcomment = () => {
            let data = {
                commentId: commentId,
                memberId: memberId,
                chatId: chatId
            }
            axios.post("http://localhost:8080/goodComment/addGoodComment", data, {
                headers: { 'Content-Type': 'application/json' }
            })
                .then(response => {
                    alert(response.data);
                })
        }

        const delteComment=()=>{
            axios.post(`http://localhost:8080/comment/deleteComment?commentId=${commentId}`)
            .then(response=>{
                alert(response.data);
            })
        }
        return (
            <>
                <table border={1} align='center' className='table'>
                    <tr>
                        <td>留言者:{name}</td>
                        <td>{dateString} {timeString}</td>
                    </tr>
                    <tr>
                        <td colSpan={2} className='comment'>
                            {comment}
                        </td>
                    </tr>
                    <tr>
                        <td colSpan={2} >
                            <div className='flex-content'>
                                <div>
                                    <button className='button-left' onClick={() => { goodcomment(memberId, chatId, commentId); queryList(); }}>{item ? "已按讚" : "讚"}</button>
                                    <span className='content'>{goodsCommentSum}</span>
                                </div>
                                <div className='button-right'>{button?<button onClick={()=>{delteComment(commentId); queryList();}}>刪除</button>:""}</div>
                            </div>
                           
                        </td>
                    </tr>
                </table>
            </>
        )
    }
}

