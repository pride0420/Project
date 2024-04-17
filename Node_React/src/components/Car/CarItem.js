import React, { Component } from 'react'
import axios from 'axios';
import "./car.css"
export default class CarItem extends Component {
    constructor(props) {
        super(props);

        this.handleButtonClick = this.handleButtonClick.bind(this);
    }

    componentDidUpdate(prevProps) {
        // 检查前后 props 是否有变化
        if (prevProps.carInfo !== this.props.carInfo) {
            console.log('CarInfo props updated:', this.props.carInfo);
        }
    }

    render() {
        const { carInfo } = this.props;
        const { id, porderId, product, mode, amount, num, sum } = carInfo;


        return (
            <div>
                <table>
                    <tr>
                        <td className="image-column">
                            <img src={`/images/${porderId}.jpg`} />
                        </td>
                        <td className="content-column">
                            <div>
                                <h3>{product}</h3>
                                <p>{mode}</p>
                                <p>價格: {amount}</p>
                                <p>數量: {num}</p>
                                <p>金額: {sum}</p>
                            </div>
                            <div style={{ display: 'flex' }}>
                                <button style={{ flex: 1 }} className='btn btn-default' onClick={() => this.handleButtonClick('add')}>在一個</button>
                                <button style={{ flex: 1 }} className='btn btn-default' onClick={() => this.handleButtonClick('reduced')}>少一個</button>
                            </div>
                            <div>
                                <button className='btn btn-default' onClick={() => this.handleButtonClick('delete')}>全部不要</button>
                            </div>
                        </td>
                    </tr>

                </table>
            </div>
        )
    }

    handleButtonClick(action) {
        const { id } = this.props.carInfo;
        axios.post(`http://localhost:8080/car/updateCar?id=${id}&item=${action}`)
            .then(response => {
                alert("修改成功");
                this.props.handleCar();
            })
            .catch(error => {
                console.error('Error updating car:', error);
            });
    }
}

