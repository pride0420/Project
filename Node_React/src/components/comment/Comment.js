import React, { useEffect, useContext, useState } from 'react'
import axios from 'axios'
import Navibar from '../Navibar/Navibar'
import Footer from '../Footer/Footer'
import './comment.css';
import CommentItem from './CommentItem';
import { useParams, useLocation, Link } from 'react-router-dom';
import { MemberContext } from '../MemberContext';
import CommentList from './CommentList';
function Comment() {
    const { member } = useContext(MemberContext); // 使用 useContext 获取 Member 数据
    const memberId = member ? member.memberId : null;
    const { chatId } = useParams();
    const [comment, setComment] = useState("");
    const [commentItem, setCommentItem] = useState(null);
    const [commentList, setCommentList] = useState(null);

    const chlickComment = (e) => {
        const { name, value } = e.target;
        if (name === 'comment'&&value.startsWith(" ")) {
            e.target.value=value.trimStart();
        }
        setComment(value);
    }

    const queryItem = () => {
        const url = `http://localhost:8080/chat/queryChat?chatId=${chatId}`;
        axios.get(url, { headers: { 'Content-Type': 'applicato=ion/json' } })
            .then(response => {
                setCommentItem(response.data);
            }).catch(error => {
                console.error('Error fetching commentItem :', error);
            })
    }
    const queryList = () => {
        const url = `http://localhost:8080/comment/queryAllComment?chatId=${chatId}&memberId=${memberId}`;

        axios.get(url, { headers: { 'Content-Type': 'application/json' } })
            .then(response => {
                setCommentList(response.data);
            }).catch(error => {
                console.error('Error fetching commentList :', error);
            })
    }

    useEffect(() => {
        queryItem();
        queryList();
    }, [queryList]);

    const addComment = (e) => {
        e.preventDefault();
        let data = {
            memberId: memberId,
            chatId: chatId,
            comment: comment
        }
        axios.post("http://localhost:8080/comment/addComment", data, {
            headers: { 'Content-Type': 'application/json' }
        })
            .then(response => {
                alert("新增成功");
                document.getElementsByName("comment")[0].value = ""; // 设置 textarea 的值为空
            }).catch(error => {
                console.error('Error adding chat:', error);
                alert("新增失败");
            });
    }
    return (
        <>
            <Navibar />
            <img src="/image/05.jpg" className="img-responsive" id="banner" alt="" />
            {commentItem && <CommentItem commentInfo={commentItem} />}
            <form onSubmit={addComment}>
                <table align='' border={1} className='table'>
                    <tr>
                        <td>
                            <textarea name='comment' rows={5} cols={70} onChange={chlickComment} required></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type='submit' value="ok" />
                            <input type='reset' value='清除' />
                            <Link to="/chat"><button>回首頁</button></Link>
                        </td>
                    </tr>
                </table>
            </form>
            {commentList && commentList.map((commentInfo) => (<CommentList key={commentInfo.commentId} commentInfo={commentInfo} memberId={memberId} queryList={queryList} />))}
            <Footer />
        </>
    )
}
export default Comment;