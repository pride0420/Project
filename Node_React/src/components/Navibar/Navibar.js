import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { MemberContext } from '../MemberContext';

function Navibar() {

    const { restMember } = useContext(MemberContext);

    const RestMember = () => {
        restMember();
        alert("已登出");
    }
    return (
        <nav className="navbar navbar-inverse navbar-fixed-top">
            <div className="container-fluid">
                <div className="navbar-header">
                    <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span className="sr-only">Toggle navigation</span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                    </button>
                    <Link to="/chat" className="navbar-brand" >神龍</Link>
                </div>

                <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul className="nav navbar-nav navbar-right">
                        <li><Link to="/chat">留言板</Link></li>
                        <li><Link to="/about">關於我</Link></li>
                        <li><Link to="/shop">產品目錄</Link></li>
                        <li><Link to="/user">查看帳號</Link></li>
                        <li><Link to="/" onClick={RestMember}>登出</Link></li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default Navibar;