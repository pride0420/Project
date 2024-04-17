import React, { Component } from 'react'
import Login from '../Login/Login';
import AddMember from '../Login/AddMember';
export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      value: true
    }
  }

  change = () => {
    this.setState({
      value: true
    })
  }
  change2 = () => {
    this.setState({
      value: false
    })
  }

  render() {
    return (
      <div style={{ position: 'relative' }}>
        <div>{this.state.value ? <Login /> : <AddMember />}</div>
        <div style={{ position: 'absolute', top: '10%', left: '47%' }}>
          <button onClick={this.change.bind(this)}>登入</button> |{' '}
          <button onClick={this.change2.bind(this)}>註冊</button>
        </div>
      </div>
    )
  }
}
