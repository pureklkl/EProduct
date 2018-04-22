import React from 'react';
import PropTypes from 'prop-types';

import { List, ListProductImg, ListLink, ListVal } from './ProductList';
 
var OrderItemHeaders = [
(<th key='Cover'>Cover</th>),
(<th key='Name'>Product Name</th>),
(<th key='TotalPrice'>Total Price</th>),
(<th key='Price'>Price</th>),  
(<th key='Quantity'>Quantity</th>)];

var OrderItemListKeys =  [
'id', 
'name',
'totalPrice',
'price',
'quantity'];

var OrderItemListTd = [
ListProductImg,
ListLink,
ListVal,
ListVal,
ListVal];

export const OrderItemList = (props) => {
  return props.data.length > 0 ? (<List className="table table-striped table-hover"
          data={props.data} 
          headers={OrderItemHeaders} 
          tds={OrderItemListTd} 
          keys={OrderItemListKeys}/>) : null;
}

OrderItemList.propTypes = {
  data: PropTypes.array,
}