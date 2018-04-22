import { combineReducers } from 'redux'

import * as Act from '../actions'


const user = (state = null, action) => {
  if(action.type === Act.LOAD_USER) {
    return {
      status: action.status,
      user : action.res || {},
      info : action.info || ""
    }
  }
  return state || {status:Act.LOAD_USER};
}

const productList = (state, action) => {
  if (action.type === Act.LOAD_BROWSE) {
    return {
      status:action.status, 
      productList: action.res || [],
      info: action.info || ""}
  }
  return state || {};
}

const product = (state, action) => {
  if (action.type === Act.LOAD_PRODUCT) {
    return {
      status:action.status || "", 
      productDetail: action.res || {},
      info: action.info || "",
    }
  }
  return state || {};
}

const addProduct = (state, action) => {
  if (action.type === Act.ADD_PRODUCT) {
    return {
      status:action.status || "", 
      res: action.res || {},
      info: action.info || ""}
  }
  if (action.type === Act.RESET_PRODUCT) {
    let nextState = Object.assign({}, state || {});
    nextState.status="";
    return nextState;
  }
  return state || {};
}

const editProduct = (state, action) => {
  if (action.type === Act.EDIT_PRODUCT) {
    return {
      status:action.status || "", 
      res: action.res || {},
      info: action.info || ""}
  }
  if (action.type === Act.RESET_PRODUCT) {
    let nextState = Object.assign({}, state || {});
    nextState.status="";
    return nextState;
  }
  return state || {};
}

const cart = (state, action) => {
  if (action.type === Act.LOAD_CART) {
    var res = action.res || {};
    var cart = {
      status: action.status || "",
      id : res.id,
      items: res.items || [],
      totalPrice : res.totalPrice || 0,
      info : action.info || "",
    };
    var totalPrice = 0;
    cart.items.map(item => {
      item.price = item.product.price;
      item.name = item.product.name;
      item.id = item.product.id;
      totalPrice += item.totalPrice;
      return item;
    });
    cart.totalPrice = totalPrice;
    return cart;
  }
  return state || {totalPrice: 0, items: []};
}

const buyProduct = (state, action) => {
  if (action.type === Act.LOAD_PRODUCT) {
    return {
      status : "",
    }
  }
  if (action.type === Act.ADD_CARTITEM) {
    return {
      status:action.status || "", 
      res: action.res || {},
      info: action.info || ""}
  }
  return state || {};
}

const orderList = (state, action) => {
  if(action.type === Act.LOAD_ORDER_LIST) {
    var res = action.res || [];
    var orderList = {
      status:action.status, 
      items: res || [],
      info: action.info || ""
    };
    orderList.items.map((item) => {
      item.nameSummary = item.items[0].product.name;
      if(item.items.length > 1) {
        item.nameSummary += " ...etc";
      }
      item.id = item.customerOrderId;
      item.firstItemId = item.items[0].product.id;
      item.statusName = item.status.name;
    });
    return orderList;
  }
  return state || {};
}

const order = (state, action) => {
  if(action.type === Act.LOAD_ORDER_DETAIL) {
    var order = {
      status:action.status, 
      data: action.res || {},
      info: action.info || "",
    };
    order.data.id = order.data.customerOrderId;
    if(order.data.items) {
      order.data.items.map((item)=>{
        item.price = item.product.price;
        item.name = item.product.name;
        item.id = item.product.id;
        return item;
      })
    }
    return order;
  }
  return state || {};
}

const orderStatus = (state, action) => {
  if(action.type === Act.LOAD_ORDER_DETAIL) {
    if(action.status === Act.SUCCESS) {
      let nextState = Object.assign({}, state);
      nextState.orderStatusName = action.res.status.name;
      return nextState;
    }
  }
  if(action.type === Act.UPDATE_ORDER) {
    let nextState = Object.assign({}, state);
    nextState.status = action.status;
    if(action.status === Act.SUCCESS) {
      nextState.orderStatusName = action.nextOrderStatus;
      return nextState;
    }
    return nextState;
  }
  return state || {};
}

const rootReducer = combineReducers({
  user,
  productList,
  product,
  addProduct,
  editProduct,
  cart,
  buyProduct,
  orderList,
  order,
  orderStatus
})

export default rootReducer