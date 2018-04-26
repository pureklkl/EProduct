import React, {Component} from 'react'
import PropTypes from 'prop-types';

import { SUCCESS, REQUESTING, FAILED } from '../actions'
import { range, getBannerUrl } from '../util';

import Img from './Img';
import './ProductDetail.css'

const showDirector = (dirctor) => {
  return (dirctor == null || dirctor === "") ? "unknown" : dirctor;
}

class ProductDetail extends Component {
  static propTypes = {
    data: PropTypes.object,
    buy : PropTypes.func,
    buyStatus : PropTypes.string
  }

  constructor() {
    super();
    this.state = {
      value: 1
    }
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  render() {
    var data = this.props.data;
    var buyStatus = this.props.buyStatus;
    return (
        <div className = "py-3">
          { buyStatus === SUCCESS && <div className="alert alert-success" role="alert">{data.name} added to the cart</div> }
          { buyStatus === REQUESTING && <div className="alert alert-info" role="alert">processing...</div> }
          { buyStatus === FAILED && <div className="alert alert-danger" role="alert">failed</div> }
          <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
            <h3><span>{data.title}</span><span>&nbsp;</span><span>{data.manufactory}</span></h3>
          </div>
          <div className="row">
            <div className="col-md-5">
              <Img src={getBannerUrl(data.id, data.banner_url)} width="100%" height="300px"/>
            </div>
            
            <div className="col-md-5">
              
              <div className="px-3">
                <h3>{ data.title }</h3>
                <p>Director: {showDirector(data.dirctor)}</p>
                <p>Year: {data.year}</p>
                <h6 className="text-secondary"><small>Price</small></h6>
                <h3>{ data.price }</h3>

                <div className="card">
                  <div className="card-body">
                    <div>
                      <label htmlFor="Quantity" className="mr-sm-2">Quantity</label>
                      <select className="custom-select" style = {{width: '100px'}} id = "Quantity" value={data.num} onChange={this.handleChange.bind(this)}>
                        {range().map((val, index)=><option key={index} value={val}>{val}</option>)}
                      </select>
                    </div>
                    <div className = "my-2">
                      <button className = "btn btn-warning btn-block" onClick = {this.props.buy.bind(null, this.state.value, data.id)}>Add to cart</button>
                    </div>
                  </div>
                </div>{/*card*/}

              </div>{/**/}

            </div>
          </div>
          <div styleName="descibe-section">
            <p>{data.description}</p>
            <ul>
              <li>{data.manufactory}</li>
              <li>{data.condition_}</li>
              <li>{data.category}</li>
            </ul>
          </div>
        </div>
      );
  }
}

export default ProductDetail