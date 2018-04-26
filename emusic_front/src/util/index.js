import $ from 'jquery'; 

import {LOCAL} from '../config'

export const jsonToForm = (json, form=new FormData()) => {
  for(let key in json) {
    if (json[key] !== null && json[key] !== undefined) {
      form.set(key, json[key]);
    }
  }
  return form;
}

export const isRedirected = (status) => {
  return status / 100 == 3;
}

export const doFetch = (url, params) => {
  params['credentials'] = "same-origin";
  params['redirect'] = "follow";
  var token = $("meta[name='_csrf']").attr("content");
  var csrfHeader = $("meta[name='_csrf_header']").attr("content");
  params['headers'] = {};
  params.headers[csrfHeader] = token;
  return fetch(url, params).then(
    res=>{
        if(res.ok) {
            if (res.redirected) {
              window.location.href = res.url;
            }
            return res.json().then(jsonObj=>jsonObj).catch(()=>{return {};});
        }
        throw res;
      }
    )
}

export const range = (start = 1, end = 20) => {
  return Array.apply(null, Array(end - start)).map(function (_, i) {return i + start;});
}

export const getBannerUrl = (id, banner_url) => {
  return banner_url && banner_url.length != 0 ? banner_url : (LOCAL + `img/${id}.png`);
}

export const shouldUpdateByRoute = (props, nextProps = null) => {
  var shouldUpdate = false;
  if(props.location != nextProps.location) {
    shouldUpdate = true;
  } else if(props.match.params || nextProps.match.params){
    const params = props.match.params;
    const nextParams = nextProps.match.params;
    for(let key in params) {
      if(params[key] !== nextParams[key]) {
        shouldUpdate = true;
        break;
      }
    }
  } else {
    return false;
  }
  return shouldUpdate;
}