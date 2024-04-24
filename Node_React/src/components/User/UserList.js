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
        {memberId !== null && (
            <tr>
                <td>id</td>
                <td>{memberId}</td>
            </tr>
        )}
            <tr>
                <td width={100}>帳號</td>
                <td>{username}</td>
            </tr>
        {password !==null &&(
            <tr>
                <td>密碼</td>
                <td>{password}</td>
            </tr>

        )}
        {name!==null&&(
            <tr>
                <td>暱稱</td>
                <td>{name}</td>
            </tr>
        )}
        {phone!==null&&(
            <tr>
                <td>電話</td>
                <td>{phone}</td>
            </tr>
        )}
        {email!==null&&(
            <tr>
                <td>信箱</td>
                <td>{email}</td>
            </tr>
        )}
            
        </table>
      </div>
    )
  }
}
