import { BROWSE_API, 
  PRODUCT_API, PRODUCT_ADD_API, PRODUCT_EDIT_API, PRODUCT_DELETE_API,
  USER_GETAUTH_API, 
  CART_API, CART_ITEM_API, CART_UPDATE_ITEM_API,
  LOAD_ORDER_LIST_API, LOAD_ORDER_DETAIL_API, 
  UPDATE_ORDER_API, ADMIN_LOAD_ORDER_LIST_API } from '../config'
import {doFetch, jsonToForm} from '../util';

export const REQUESTING = 'REQUESTING';
export const FAILED = 'FAILED';
export const SUCCESS = 'SUCCESS';

export const RES_SUCCESS = 0;

/**
 * general api request
 * dispatch requesting upon getting called
 * async dispatch success/failure result when get result
 * itself can be inject by mapDispatchToProps
 * see https://developers.google.com/web/updates/2017/09/abortable-fetch
 * 
 * @param  {string} url     url to be fetched
 * @param  {object} payload some action infomation
 * @param  {object} signal  abort signal
 * @return {promise}        fetch result
 */
const requestApi = (url, payload, params={})=>(dispatch)=>{
  dispatch({status: REQUESTING, ...payload});
  return doFetch(url, params)
  .then(res=>dispatch({status:SUCCESS, res:res, ...payload}))
  .catch(e=>{
      if(e.json) {
        try {
          e.json().then(eInfo=>dispatch({status:FAILED, info:eInfo, ...payload}));
        } catch(exc) {
          dispatch({status:FAILED, info: e.statusText || 'unknown error', ...payload});
        }
      }
      else
        dispatch({status:FAILED, info:'unknown error', ...payload});
    });
};

export const LOAD_BROWSE = 'LOAD_BROWSE';

export const loadBrowse = (controller = {})=>{
  const signal = controller.signal || {};
  return (dispatch)=>requestApi(BROWSE_API, {type:LOAD_BROWSE}, signal)(dispatch);
}

export const LOAD_PRODUCT = 'LOAD_PRODUCT';

export const loadProduct = (id, controller = {})=>{
  const signal = controller.signal || {};
  return (dispatch)=>requestApi(PRODUCT_API + `${id}`, {type:LOAD_PRODUCT}, signal)(dispatch);
}

export const ADD_PRODUCT = 'ADD_PRODUCT';
/**
 * Since we have binary data, we use form data.
 * @param  {object} value form data
 * @return {object}       react action creator for mapDispatchToProps
 */
export const addProduct = (value)=>{
  return (dispatch)=>
      requestApi(PRODUCT_ADD_API, {type:ADD_PRODUCT}, 
        {method:'post', 
         body: value
       })(dispatch);
}

export const EDIT_PRODUCT = 'EDIT_PRODUCT';

export const editProduct = (value)=>{
  return (dispatch)=>{
      requestApi(PRODUCT_EDIT_API, {type:EDIT_PRODUCT}, 
        {method:'POST', 
         body: value
       })(dispatch)
       .then(()=>loadProduct(value.get('id'))(dispatch))
     };
}

export const DELETE_PRODUCT = 'DELETE_PRODUCT';

export const deleteProduct = (id)=>{
  return (dispatch) => {
        requestApi(PRODUCT_DELETE_API+id, {type:DELETE_PRODUCT}, 
        {method:'delete'})(dispatch)
        .then(()=>loadBrowse()(dispatch))
      };
}

export const RESET_PRODUCT = 'RESET_PRODUCT'

export const resetProduct = ()=>{
  return (dispatch)=>{
    dispatch({type:RESET_PRODUCT})
  }
}

export const LOAD_USER = 'LOAD_USER';

export const loadUser = ()=>{
  return (dispatch) => {
        requestApi(USER_GETAUTH_API, {type:LOAD_USER})(dispatch);
      };
}

export const LOAD_CART = 'LOAD_CART';

export const loadCart = ()=>{
  return (dispatch) => {
        requestApi(CART_API, {type:LOAD_CART})(dispatch);
      };
}

export const ADD_CARTITEM = 'ADD_CARTITEM';

export const addCartItem = (quantity, id)=>{
  return (dispatch) => {
        requestApi(CART_API, 
          {type:ADD_CARTITEM},
          { method:'post',
            body:jsonToForm({
              productId: id,
              quantity: quantity
            })
        })(dispatch);
      };
}

export const CHANGE_CARTITEM_QUANTITY = 'CHANGE_CARTITEM_QUANTITY';

export const changeCartItemQty = (quantity, id)=>{
  return (dispatch) => {
        requestApi(CART_UPDATE_ITEM_API(quantity, id), 
          {type: CHANGE_CARTITEM_QUANTITY },
          {method: 'put'})(dispatch)
          .then(()=>loadCart()(dispatch));
      };
}

export const DELETE_CARTITEM = 'DELETE_CARTITEM';

export const deleteCartItem = (id)=>{
  return (dispatch) => {
        requestApi(CART_ITEM_API + id, 
          {type:DELETE_CARTITEM},
          {method:'delete'})(dispatch)
          .then(()=>loadCart()(dispatch));
      };
}

export const LOAD_ORDER_LIST = 'LOAD_ORDER_LIST';
export const loadOrderList = () => {
  return (dispatch) => {
        requestApi(LOAD_ORDER_LIST_API,
          {type: LOAD_ORDER_LIST},
          {method:'get'})(dispatch);
  }
}

export const LOAD_ORDER_DETAIL = 'LOAD_ORDER_DETAIL';
export const loadOrderDetail = (id) => {
  return (dispatch) => {
        requestApi(LOAD_ORDER_DETAIL_API + id,
          {type: LOAD_ORDER_DETAIL},
          {method: 'get'})(dispatch);
  }
}

export const UPDATE_ORDER = 'UPDATE_ORDER';
export const updateOrder = (id, status) => {
  return (dispatch) => {
        requestApi(UPDATE_ORDER_API(id, status),
          {type: UPDATE_ORDER, nextOrderStatus: status},
          {method: 'PATCH'})(dispatch);
  }
}

export const adminLoadOrderList = () => {
  return (dispatch) => {
        requestApi(ADMIN_LOAD_ORDER_LIST_API,
          {type: LOAD_ORDER_LIST},
          {method:'get'})(dispatch);
  }
}