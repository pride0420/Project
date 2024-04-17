import React, { Component } from 'react';
import './about.css';
import Footer from '../Footer/Footer';
import Navibar from '../Navibar/Navibar';
import SetUser from './SetUser';
import UserChat from './UserChat';
class About extends Component {
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
            <>
                <Navibar />
                <img src="image/05.jpg" className="img-responsive" id="banner" alt="" />
                <div className='Box'>
                    <div style={{ position: 'relative' }}>
                        <div className='button-con'>
                            <button onClick={this.change.bind(this)}>帳號資料</button>
                            <span> | </span>
                            <button onClick={this.change2.bind(this)}>我的貼文</button>
                        </div>
                        <div>{this.state.value ? <SetUser /> : <UserChat />}</div>
                    </div>
                </div>
                <Footer />
            </>
        );
    }
}

export default About;