import { BROWSE_API, PRODUCT_API } from '../config'

export const REQUESTING = 'REQUESTING';
export const FAILED = 'FAILED';
export const SUCCESS = 'SUCCESS';
/**
 * general api request
 * dispatch requesting upon getting called
 * async dispatch success/failure result when get result
 * itself can be inject by mapDispatchToProps
 * accept a abort signal to function
 * see https://developers.google.com/web/updates/2017/09/abortable-fetch
 * 
 * @param  {string} url     url to be fetched
 * @param  {object} payload some action infomation
 * @param  {object} signal  abort signal
 * @return {promise}        fetch result
 */
const requestApi = (url, payload, signal)=>(dispatch)=>{
  dispatch({status: REQUESTING, ...payload});
  return fetch(url, { signal }).then(res=>res.json())
  .then(res=>dispatch({status:SUCCESS, res:res, ...payload}))
  .catch(e=>dispatch({status:FAILED, info:e, ...payload}));
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

