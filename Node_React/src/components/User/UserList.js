import React, { Component } from 'react'

export default class UserList extends Component {

    constructor(props) {
        super(props);
        
    }
    
  render() {
    const {memberInfo}=this.props;
    const {memberId,username,password,name,phone,email}=memberInfo;
    return (
      <div>
        <table align='center' border={1} className='table'>
            <tr>
                <td>id</td>
                <td>{memberId}</td>
            </tr>
            <tr>
                <td>帳號</td>
                <td>{username}</td>
            </tr>
            <tr>
                <td>密碼</td>
                <td>{password}</td>
            </tr>
            <tr>
                <td>暱稱</td>
                <td>{name}</td>
            </tr>
            <tr>
                <td>電話</td>
                <td>{phone}</td>
            </tr>
            <tr>
                <td>信箱</td>
                <td>{email}</td>
            </tr>
        </table>
      </div>
    )
  }
}
