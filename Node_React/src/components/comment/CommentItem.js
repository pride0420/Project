import React, { Component } from 'react'

export default class CommentItem extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    const { commentInfo } = this.props;
    const { name, firsttime, content } = commentInfo;

    // 将时间戳转换为 Date 对象
    const date = new Date(firsttime);

    // 获取日期部分
    const dateString = date.toLocaleDateString();

    // 获取时间部分
    const timeString = date.toLocaleTimeString();
    return (
      <>
        <div className='chatBox'>
          <table border={1} align='center' className='table'>
            <tr>
              <td>留言者:{name}</td>
              <td>{dateString} {timeString}</td>
            </tr>
            <tr>
              <td colSpan={2} className='comment'>
                {content}
              </td>
            </tr>
          </table>
        </div>
      </>
    )
  }
}
