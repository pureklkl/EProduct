import React, {Component} from 'react'
import { connect } from 'react-redux'
import { Link } from 'react-router-dom'
import PropTypes from 'prop-types'

import { loadBrowse, deleteProduct, SUCCESS, REQUESTING, FAILED } from '../actions'
import { CustomerProductList, AdminProductList} from '../component/ProductList'
import './App.css';

// see https://reactjs.org/docs/higher-order-components.html

export const BaseBrowse = (ProductListDom)=>{
  class BaseBrowse extends Component {
    static propTypes = {
      status: PropTypes.string,
      productList: PropTypes.array,
      loadBrowse: PropTypes.func,
      location: PropTypes.object
    }

    componentWillMount() {
      this.props.loadBrowse();
    }

    componentWillReceiveProps(nextProps) {
      if (nextProps.location !== this.props.location) {
        this.props.loadBrowse();
      }
    }

    render() {
      const { status, productList } = this.props;
      return (
          <div>
            { status === SUCCESS && <ProductListDom data={ productList } /> }
            { status === REQUESTING && <div className="alert alert-info" role="alert">loading...</div> }
            { status === FAILED && <div className="alert alert-danger" role="alert">load failed try refresh</div> }
          </div>
        );
    }
  }
  return BaseBrowse;
}

const Header = (
    <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
      <h1>Browse Music</h1>
      <p className="lead">find more music!</p>
    </div>
  );

const mapStateToProps = (state) => {
  const { status, productList } = state.productList;
  return { status, productList };
} 

const ConBrowse = connect(mapStateToProps, { loadBrowse })(BaseBrowse(CustomerProductList));
export const Browse = ()=>{
  return (
    <div className = "container">
      {Header}
      <ConBrowse/>
    </div>);
}


const AdminBrowseBtns = ()=>{
  return (
      <div>
      <Link className="btn btn-primary" to="/admin/addProduct">Add Product</Link>
      </div>
    );
}
const connectAdminList = connect(()=>{return {}}, {deleteProduct})(AdminProductList);
const ConAdminBrowse = connect(mapStateToProps, { loadBrowse })(BaseBrowse(connectAdminList));
export const AdminBrowse =  ()=>{
    return (
      <div className = "container">
        {Header}
        {AdminBrowseBtns}
        <ConAdminBrowse />
      </div>);
}