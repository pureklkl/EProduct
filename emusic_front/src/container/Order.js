import React, {Component} from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

import { SUCCESS, REQUESTING, FAILED, loadOrderDetail } from '../actions';
 
import  OrderDetail  from '../component/OrderDetail';



class OrderDom extends Component {
  static propTypes = {
    match: PropTypes.object,
    data: PropTypes.object,
    status: PropTypes.string,
    loadHanlder: PropTypes.func,
    children : PropTypes.node
  }
  componentWillMount() {
    this.props.loadHanlder(this.props.match.params.id);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.match.params.id !== nextProps.match.params.id) {
      this.props.loadHanlder(nextProps.match.params.id);
    }
  }
  render() {
    const {data, status, children} = this.props;
    return (
       <div className="container">
        { status === SUCCESS && <OrderDetail data={ data }>{children}</OrderDetail> }
        { status === REQUESTING && <h3 className="text-center py-5"><i>loading...</i></h3> }
        { status === FAILED && <h3 className="text-center py-5"><i>load failed try refresh</i></h3> }
      </div>
      );
  }
}
 

const orderMapStateToProps = (state) => {
  const { data, status, info } = state.order;
  const { orderStatusName } = state.orderStatus;
  if(data) {
    data.statusName = orderStatusName;
  }
  return { data, status, info };
}

export const Order = connect(orderMapStateToProps, 
  { loadHanlder : loadOrderDetail })(OrderDom);

