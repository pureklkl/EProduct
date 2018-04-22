import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

import { SUCCESS, adminLoadOrderList } from '../actions';
import { AdminOrderList } from '../component/OrderList';
import { BaseBrowse } from './Browse';
 
const orderBrowseMapStateToProps = (state) => {
  const { status, items } = state.orderList;
  return { status: status, productList: items };
}

 const OrderBrowse = 
 connect(orderBrowseMapStateToProps, 
        { loadBrowse: adminLoadOrderList })(BaseBrowse(AdminOrderList));

const Header = (
    <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
      <h1>Orders</h1>
      <p className="lead">View orders</p>
    </div>
);

 const OrderBrowseDom = (props)=>{
  return (
      <div className = "container">
        {Header}
        <OrderBrowse />
        { props.status === SUCCESS && props.items.length == 0 && 
          (<p>There is no order yet.</p>)
        }
      </div>
    );
}

OrderBrowseDom.propTypes = {
  items: PropTypes.array,
  status : PropTypes.string
}

const orderBrowseDomMapStateToProps = (state) => {
  const { items, status } = state.orderList;
  return { items, status };
}

export const AdminOrderListBrowse = connect(orderBrowseDomMapStateToProps)(OrderBrowseDom);