import React, {Component} from 'react'
import PropTypes from 'prop-types';
 
import { OrderItemList } from '../component/OrderItemList';

const Header = (
    <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
      <h1>Order Detail</h1>
      <p className="lead">View product in your order</p>
    </div>
);

class OrderDetail extends Component {
  static propTypes = {
    data : PropTypes.object,
    children : PropTypes.node
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
    var {id, statusName, totalPrice, shippingAddress, billingAddress, items } = this.props.data;
    var children = this.props.children;
    return (
        <div className = "container">
          {Header}
          <p>id: {id}</p>
          <p>status:{statusName}</p>
          {children}
          <div className="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 co-md-offset-3">
            <div className="row">
              <div className="col-xs-6 col-sm-6 col-md-6">
                <address>
                  <strong>Shipping Address</strong><br />
                  {shippingAddress.streetName}<br />
                  {shippingAddress.apartmentNumber}<br />
                  {shippingAddress.city},{shippingAddress.state}<br />
                  {shippingAddress.country},{shippingAddress.zipCode}<br/>
                </address>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 text-right">
                <address>
                  <strong>Billing Address</strong><br />
                  {billingAddress.streetName}<br />
                  {billingAddress.apartmentNumber}<br />
                  {billingAddress.city},{billingAddress.state}<br />
                  {billingAddress.country},{billingAddress.zipCode}<br/>
                </address>
              </div>
            </div>
          </div>
          <OrderItemList data = {items} />
          <div className = "py-1">
            <div className = "d-sm-flex float-sm-right h-100">
              <div className = "d-sm-inline-block mx-sm-2 display-4">Total Price: {totalPrice}</div>
            </div>
          </div>
        </div>
      );
  }
}

export default OrderDetail;