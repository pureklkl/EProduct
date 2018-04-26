import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

import { getBannerUrl } from '../util';
import Img from './Img';

export class List extends Component {
  static propTypes = {
    headers: PropTypes.arrayOf(PropTypes.node),
    tds: PropTypes.array,
    data: PropTypes.array,
    keys: PropTypes.array,
  }
  render() {
    const {
      headers,
      tds,
      data,
      keys,
      ...rest
    } = this.props;
    let listDom = null;
    if (data) {
      listDom = data.map(r=>(
        <tr key= { r.id }>
          {
            tds.map((td, index)=>(
              <td key={r.id + "-" + index}>{td(r[keys[index]], r)}</td>
            ))
          }
        </tr>)
      );
    }
    return (
        <table {...rest}>
          <thead>
            <tr>
              {headers}
            </tr>
          </thead>
          <tbody>
            {listDom}
          </tbody>
        </table>
      )
  }  
}

export const ListProductImg = (id, r)=>{
  
  return  (
    <Img src={getBannerUrl(id, r.banner_url)} width="80" height="80"/>);
}

export const ListLink = (val, r)=>(<Link to={"/product/"+r.id}>{val}</Link>);

export const ListVal = val=>(<span>{val}</span>);

var productListTd = [
ListProductImg,
ListLink,
ListVal,
ListVal,
ListVal];

var productListKeys =  [
'id', 
'title',
'year',
'dirctor',
'price'];

var sortFieldKeys =  {
'id': false, 
'title': true,
'year': true,
'dirctor': true,
'price': true};

var productListHeaderName = {
  id: 'Cover',
  title: 'Title',
  year: 'Year',
  dirctor: 'Director',
  price: 'Price'
};

const LinkHeader = (name, url) =>
(<Link to={url}>{name}</Link>)

//router url for react-router
const productSortUrl = (field, isAsc, query) => {
  if(query) {
    return `/browse/query/${query}/orderby/${field}/asc/${isAsc}/page/1`;
  } else {
    return `/browse/orderby/${field}/asc/${isAsc}/page/1`;
  }
}

const getProductListHeader = (field, isAsc, query) => {
  var productListHeader = [];
  productListKeys.forEach((key) => {
    if(sortFieldKeys[key]) {
      if(field === key) {
        productListHeader.push(
          <th key = {key}>
            {LinkHeader(productListHeaderName[key], productSortUrl(key, !isAsc, query))}
            {isAsc ? (<span className="oi oi-arrow-top"></span>) 
                  : (<span className="oi oi-arrow-bottom"></span>)}
          </th>
          )
      } else {
        productListHeader.push(
            <th key = {key}>
              {LinkHeader(productListHeaderName[key], productSortUrl(key, true, query))}
            </th>
          )
      }
    } else {//the field is not sortable
      productListHeader.push(
          <th key = {key}>
            {productListHeaderName[key]}
          </th>
        )
    }
  })
  return productListHeader;
}

/*'&#8593' upper arrow, '&#8595' lower arrow*/
export const ProductList = (props) => {
  var {field, isAsc, query, productList} = props;
  var productListHeader = getProductListHeader(field, isAsc, query);
  if(!productList || productList.length == 0) {
    return (<p>no result found</p>);
  } else {
    return (<List className="table table-striped table-hover"
    data={productList} headers={productListHeader} tds={productListTd} keys={productListKeys}/>);
  }
}
ProductList.propTypes = {
  productList: PropTypes.array,
  field: PropTypes.string,
  isAsc: PropTypes.bool,
  query: PropTypes.string
}

const edit=(id)=>(<Link to={"editProduct/"+id}>edit</Link>);

const deleteCol=(handler, id)=>(<a href="#" onClick={(e)=>{e.preventDefault();handler(id);}}>delete</a>);

var adminListKeys = productListKeys.slice();
adminListKeys.push('id');
adminListKeys.push('id');

export const AdminProductList = (props) => {
  var {field, isAsc, query, productList} = props;
  var productListHeader = getProductListHeader(field, isAsc, query);
  productListHeader.push(<th colSpan="2" key='Operation'>Operation</th>);
  var adminListTd = productListTd.slice();
  adminListTd.push(edit);
  adminListTd.push(deleteCol.bind(null, props.deleteProduct));
  return (<List className="table table-striped table-hover"
          data={productList} headers={productListHeader} tds={adminListTd} keys={adminListKeys}/>);
}

AdminProductList.propTypes = {
  productList: PropTypes.array,
  deleteProduct: PropTypes.func,
  field: PropTypes.string,
  isAsc: PropTypes.bool,
  query: PropTypes.string
}



