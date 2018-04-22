import React, {Component} from 'react';
import { connect } from 'react-redux'
import PropTypes from 'prop-types'

import { loadProduct, addCartItem, 
  SUCCESS, REQUESTING, FAILED } from '../actions'
import ProductDetail from '../component/ProductDetail'
import './App.css';

class Product extends Component {
  static propTypes = {
    match: PropTypes.object,
    status: PropTypes.string,
    buyStatus: PropTypes.string,
    productDetail: PropTypes.object,
    loadProduct: PropTypes.func,
    buy : PropTypes.func
  }

  componentWillMount() {
    this.props.loadProduct(this.props.match.params.id);
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.match.params.id !== nextProps.match.params.id) {
      this.props.loadProduct(nextProps.match.params.id);
    }
  }

  render() {
    const { status, productDetail, buyStatus, buy } = this.props;
    return (
        <div className="container">
          { status === SUCCESS && <ProductDetail data={ productDetail } buyStatus = {buyStatus} buy = {buy}/> }
          { status === REQUESTING && <h3 className="text-center py-5"><i>loadings...</i></h3> }
          { status === FAILED && <h3 className="text-center py-5"><i>load failed try refresh</i></h3> }
        </div>
      );
  }
}

const mapStateToProps = (state) => {
  const { status, productDetail } = state.product;
  const buyStatus = state.buyProduct.status;
  return { status, productDetail, buyStatus };
}

export default connect(mapStateToProps, 
  { loadProduct: loadProduct, 
    buy: addCartItem
  })(Product);