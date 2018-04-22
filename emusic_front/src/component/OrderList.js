import React from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom'

import { List, ListProductImg, ListVal } from './ProductList';

const OrderLink = (id, r) => (<Link to = {"/order/orderid/" + id}>{r.nameSummary}</Link>);

const DateVal = (date) => {
  if(!date) return null;
  var dateObj = new Date(date);
  return (<span>{dateObj.toDateString()}</span>);
}
var orderListTd = [
ListProductImg,
OrderLink,
ListVal,
ListVal,
DateVal,
DateVal];

var orderListHeaders = [
(<th key='Cover'>Cover</th>), 
(<th key='Id'>Summary</th>), 
(<th key='TotalPrice'>Total Price</th>), 
(<th key='Status'>Status</th>),
(<th key='CreatedDate'>Created Date</th>),
(<th key='UpdateDate'>Update Date</th>)];

var orderListKeys =  [
'firstItemId',
'customerOrderId',
'totalPrice',
'statusName',
'startTime',
'updateTime'];

export const OrderList = (props) => {
return props.data.length > 0 ? (<List className="table table-striped table-hover"
  data={props.data} headers={orderListHeaders} tds={orderListTd} keys={orderListKeys}/>) : 
  null;
}
OrderList.propTypes = {
  data: PropTypes.array
}

const AdminOrderLink = (id, r) => (<Link to = {"/admin/order/orderid/" + id}>{r.nameSummary}</Link>);
var adminOrderListTd = [
ListProductImg,
AdminOrderLink,
ListVal,
ListVal,
DateVal,
DateVal];

export const AdminOrderList = (props) => {
return props.data.length > 0 ? (<List className="table table-striped table-hover"
  data={props.data} headers={orderListHeaders} tds={adminOrderListTd} keys={orderListKeys}/>) : 
  null;
}
AdminOrderList.propTypes = {
  data: PropTypes.array
}