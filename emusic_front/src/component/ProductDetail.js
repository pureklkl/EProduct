import React, {Component} from 'react'
import PropTypes from 'prop-types';

import { SUCCESS, REQUESTING, FAILED } from '../actions'
import { range } from '../util';

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
            <h1>{ data.name + " - " + data.manufactory }</h1>
          </div>
          <div className="row">
            <div className="col-md-5">
              <img src={`http://localhost:8080/emusic/img/${data.id}.png`} alt="Generic placeholder" width="100%" height="300px"/>
            </div>
            <div className="col-md-5 text-center">
              <h3>{ data.name }</h3>
              <p>{ data.id }</p>
              <p>{ data.description }</p>
              <p>{ data.manufactory }</p>
              <p>{ data.category }</p>
              <p>{ data.status }</p>
              <p>{ data.price }</p>
              <div>
                <div>
                  <label htmlFor="Quantity" className="mr-sm-2">Quantity</label>
                  <select className="custom-select" style = {{width: '100px'}} id = "Quantity" value={data.num} onChange={this.handleChange.bind(this)}>
                    {range().map((val, index)=><option key={index} value={val}>{val}</option>)}
                  </select>
                </div>
                <div className = "my-2">
                  <button className = "btn btn-warning" onClick = {this.props.buy.bind(null, this.state.value, data.id)}>Add to cart</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      );
  }
}

export default ProductDetail