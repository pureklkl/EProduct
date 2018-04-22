import React from 'react';
import PropTypes from 'prop-types';

import { List, ListProductImg, ListLink, ListVal } from './ProductList';
import { range } from '../util';
var cartListHeaders = [
(<th key='Cover'>Cover</th>),
(<th key='Name'>Product Name</th>),
(<th key='TotalPrice'>Total Price</th>),
(<th key='Price'>Price</th>),  
(<th key='Quantity'>Quantity</th>),
(<th key='Delete'></th>)];

var cartListKeys =  [
'id', 
'name',
'totalPrice',
'price',
'quantity',
'cartItemId'];

export const UpdateItem = (handler, value, rowData) => {
  return (
      <select className="custom-select" value={value} onChange={(e)=>handler(e.target.value, rowData.cartItemId)}>
        {range().map((val, index)=><option key={index} value={val}>{val}</option>)}
      </select>
    );
}

const DeleteItem=(handler, id)=>
(<a href="#" onClick={(e)=>{e.preventDefault();handler(id);}}>
  <button type="button" className="btn btn-danger">
    remove
  </button>
  </a>);

var CartListTd = [
ListProductImg,
ListLink,
ListVal,
ListVal];

export const CartList = (props) => {
  var cartList = CartListTd.slice();
  cartList.push(UpdateItem.bind(null, props.changeCartItemQty));
  cartList.push(DeleteItem.bind(null, props.deleteCartItem));
  return props.data.length > 0 ? (<List className="table table-striped table-hover"
          data={props.data} headers={cartListHeaders} tds={cartList} keys={cartListKeys}/>) : (
            <div></div>
          );
}

CartList.propTypes = {
  data: PropTypes.array,
  changeCartItemQty: PropTypes.func,
  deleteCartItem: PropTypes.func
}


