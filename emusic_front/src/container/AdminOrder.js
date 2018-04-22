import React, {Component} from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

import { updateOrder, REQUESTING, FAILED } from '../actions'
import { Order } from './Order.js';

class AdminOrderDom extends Component {
  static propTypes = {
    id : PropTypes.number,
    handler : PropTypes.func,
    orderStatusName : PropTypes.string,
    status : PropTypes.string
  }
  render() {
    const {status, orderStatusName, id, handler, ...rest} = this.props;
    return (
        <Order {...rest}>
          <div>
            {status === REQUESTING && (<span>requesting...</span>)}
            {status === FAILED && (<span>failed, please try again</span>)}
            <select className="custom-select" value={orderStatusName} onChange={(e)=>handler(id, e.target.value)}>
              {["ORDER STARTED",
              "PROCESSING",
              "SHIPPING",
              "DELIVERED",
              "CANCELED"].map((val, index)=><option key={index} value={val}>{val}</option>)}
            </select>
          </div>
        </Order>
      );
  }
}
const adminOrderMapStateToProps = (state) => {
  const { id } = state.order.data ? state.order.data : {};
  const { status, orderStatusName } = state.orderStatus;
  return { status, orderStatusName, id };
}

export const AdminOrder = connect(adminOrderMapStateToProps, 
  {handler : updateOrder})(AdminOrderDom);