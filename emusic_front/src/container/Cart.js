import React from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

import { SUCCESS } from '../actions';
import { CartList } from '../component/CartList';
import { BaseBrowse } from './Browse';
import { loadCart, deleteCartItem, changeCartItemQty } from '../actions';
import { CHECK_ORDER_API } from '../config';

// see https://reactjs.org/docs/higher-order-components.html

const cartListMapStateToProps = (state) => {
  const { items } = state.cart;
  return { data : items };
}
const ConnectedCartList = connect(cartListMapStateToProps, {deleteCartItem, changeCartItemQty})(CartList);

const cartBrowseMapStateToProps = (state) => {
  const { status, items } = state.cart;
  return { status: status, productList: items };
}

const Header = (
    <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
      <h1>Shopping cart</h1>
      <p className="lead">View product in your shoppingcart</p>
    </div>
);


const CartBrowse = connect(cartBrowseMapStateToProps, { loadBrowse: loadCart })(BaseBrowse(ConnectedCartList));

const CartDom = (props)=>{
  return (
      <div className = "container">
        {Header}
        <CartBrowse {...props} />
        { props.status === SUCCESS && (props.items.length > 0 ? 
          (<div>
            <div className = "py-1">
              <div className = "d-sm-flex float-sm-right h-100">
                <div className = "d-sm-inline-block mx-sm-2 display-4">Total Price: {props.totalPrice}</div>
                <a href = {CHECK_ORDER_API + props.id.toString()}><button className = "btn btn-warning mr-sm-5 btn-lg align-middle">CheckOut</button></a>
              </div>
            </div>
          </div>) : 
          (<p>Your shopping cart is empty</p>))
        }
      </div>
    );
}
CartDom.propTypes = {
  id : PropTypes.number,
  totalPrice : PropTypes.number,
  items: PropTypes.array,
  status : PropTypes.string
}

const cartMapStateToProps = (state) => {
  const { totalPrice, items, status, id } = state.cart;
  return { totalPrice, items, status, id };
}

export const Cart = connect(cartMapStateToProps)(CartDom);


