import React, {Component} from 'react'
import { connect } from 'react-redux'
import { Link } from 'react-router-dom'
import PropTypes from 'prop-types'

import { loadProductList, deleteProduct, 
  SUCCESS, REQUESTING, FAILED } from '../actions'
import { ProductList, AdminProductList} from '../component/ProductList'
import { Pagin } from '../component/Pagination'
import { shouldUpdateByRoute } from '../util'
import { SearchBar } from '../component/SearchBar'

import './App.css';

// see https://reactjs.org/docs/higher-order-components.html

export const BaseBrowse = (ProductListDom, PaginDom)=>{
  class BaseBrowse extends Component {
    static propTypes = {
      status: PropTypes.string,
      location: PropTypes.object,
      match: PropTypes.object,
      paginUrl: PropTypes.func,
      loadBrowse: PropTypes.func
    }

    componentDidMount() {
      this.props.loadBrowse(this.props);
    }

    componentWillReceiveProps(nextProps) {
      if(shouldUpdateByRoute(this.props, nextProps)) {
        this.props.loadBrowse(nextProps);
      }
    }

    render() {
      const { status, paginUrl } = this.props;
      return (
          <div>
            { status === SUCCESS && 
              (
                <div>
                  <ProductListDom />
                  {PaginDom && (<PaginDom url = {paginUrl} />)}  
                </div>
                ) }
            { status === REQUESTING && <div className="alert alert-info" role="alert">loading...</div> }
            { status === FAILED && <div className="alert alert-danger" role="alert">load failed try refresh</div> }
          </div>

        );
    }
  }
  return BaseBrowse;
}

const paginMapStateToProps = (state) => {
  const {total, cur} = state.productList;
  return {total, cur};
} 
const ProductPagin = connect(paginMapStateToProps)(Pagin("Product List"));

const productListMapStateToProps = (state) => {
  const {productList, isAsc, field, query} = state.productList;
  return {productList, isAsc, field, query}
}
const ConProductList = connect(productListMapStateToProps)(ProductList);

const loadProduct = (props)=>(dispatch) => {
  const params = props.match.params;
  const {field, isAsc, page, query} = params;

  loadProductList(page, query, field, isAsc == "true")(dispatch);
}
const mapStateToProps = (state) => {
  const { status } = state.productList;
  return { status };
}
const ConBrowse = 
connect(mapStateToProps, 
  {loadBrowse: loadProduct})(BaseBrowse(ConProductList, ProductPagin));


const Header = (
    <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
      <h1>Browse Product</h1>
      <p className="lead">find more product!</p>
    </div>
  );
const productPaginUrl = (location)=>(page) => {
  var curUrl = location.pathname;
  var pagePos = curUrl.indexOf('page');
  if(pagePos < 0) {
    return curUrl + "/page/" + page;
  } else {
    return curUrl.substring(0, pagePos) + 'page/' + page; 
  }
}
export const Browse = (props)=>{
  var {location, history, match} = props;
  return (
    <div className = "container">
      {Header}
      <SearchBar
      query = {match.params.query} 
      onSearch={(val)=>{history.push(`/browse/query/${val}/page/1`)}}/>
      <ConBrowse paginUrl = {productPaginUrl(location)} {...props}/>
    </div>);
}
Browse.propTypes = {
  location: PropTypes.object,
  history: PropTypes.object,
  match: PropTypes.object
}
 
const connectAdminList = connect(productListMapStateToProps, {deleteProduct})(AdminProductList);
const ConAdminBrowse = connect(mapStateToProps, 
  { loadBrowse: loadProduct })(BaseBrowse(connectAdminList, ProductPagin));
export const AdminBrowse =  (props)=>{
  var {location, history, match} = props;
  return (
    <div className = "container">
      {Header}
      <SearchBar
      query = {match.params.query} 
      onSearch={(val)=>{history.push(`/browse/query/${val}/page/1`)}}/>
      <div>
        <Link className="btn btn-primary" to="/admin/addProduct">Add Product</Link>
      </div>
      <ConAdminBrowse paginUrl = {productPaginUrl(location)} {...props}/>
    </div>);
}
AdminBrowse.propTypes = {
  location: PropTypes.object,
  history: PropTypes.object,
  match: PropTypes.object
}