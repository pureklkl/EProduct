import React, {Component} from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'

import { editProduct, resetProduct, SUCCESS, REQUESTING, FAILED } from '../actions'
import Product from './Product'
import ProductForm  from '../component/ProductForm'

class ProductPatch extends Component {
  
  static propTypes = {
    
    match: PropTypes.object,
    location: PropTypes.object,

    productDetail: PropTypes.object,
    loadStatus: PropTypes.string,

    editProduct: PropTypes.func,
    resetProduct: PropTypes.func,
    status: PropTypes.string,
    res: PropTypes.object
  }

  componentDidMount() {
    this.props.resetProduct();
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.location !== this.props.location) {
      this.props.resetProduct();
    }
  }

  render() {
    const { status, loadStatus } = this.props;
    return (
        <div>
        <Product match={ this.props.match }/>
        <div className="container">
          <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
            <h1> Edit Product </h1>
          </div>
            { status === SUCCESS && <div className="alert alert-success" role="alert">product id {this.props.res.info} edit sucess</div> }
            { status === REQUESTING && <div className="alert alert-info" role="alert">uploading...</div> }
            { status === FAILED && <div className="alert alert-danger" role="alert">failed try again</div> }
            
            { loadStatus === SUCCESS &&
              <ProductForm submit={this.submit.bind(this)} reset={this.props.resetProduct} defaultVal={ this.props.productDetail } />}
            { loadStatus === REQUESTING && <div className="alert alert-info" role="alert">Loading product info...</div>}
            { loadStatus === FAILED && <div className="alert alert-danger" role="alert">failed try again</div>}
        </div>
        </div>
      );
  }

  submit(value) {
    window.scrollTo(0, 0);
    this.props.editProduct(value);
  }
}

const mapStateToProps = (state)=>{
  const { status, res } = state.editProduct;
  const loadStatus = state.product.status;
  const productDetail = state.product.productDetail;
  return { status, res, loadStatus, productDetail }
}

export default connect(mapStateToProps, { editProduct, resetProduct })(ProductPatch)