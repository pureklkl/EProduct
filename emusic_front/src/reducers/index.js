import { combineReducers } from 'redux'
import * as Act from '../actions'

const user = (state = null, action) => {
  return state || {};
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
      status:action.status, 
      productDetail: action.res || {},
      info: action.info || ""}
  }
  return state || {};
}

const rootReducer = combineReducers({
  user,
  productList,
  product
})

export default rootReducer