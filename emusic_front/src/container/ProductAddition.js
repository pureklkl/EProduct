import React, {Component} from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'

import { addProduct, resetProduct, SUCCESS, REQUESTING, FAILED } from '../actions'
import ProductForm from '../component/ProductForm'

const addProductDefaultVal = {
    status: 'active',
    condition_: 'new',
    category: 'single',
    price: '0',
    unit: '0'
}

class ProductAddition extends Component {
  
  static propTypes = {
    addProduct: PropTypes.func,
    status: PropTypes.string,
    res: PropTypes.object,
    location: PropTypes.object,
    resetProduct: PropTypes.func,
    info: PropTypes.object
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
    const { status } = this.props;
    return (
        <div className="container">
          <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
            <h1> Add A New Product </h1>
          </div>
            { status === SUCCESS && <div className="alert alert-success" role="alert">product id {this.props.res.info} upload sucess</div> }
            { status === REQUESTING && <div className="alert alert-info" role="alert">uploading...</div> }
            { status === FAILED && <div className="alert alert-danger" role="alert">failed try again {this.props.info.info}</div> }
            <ProductForm submit={this.submit.bind(this)} reset={this.props.resetProduct} defaultVal={addProductDefaultVal} />
        </div>
      );
  }

  submit(value) {
    window.scrollTo(0, 0);
    this.props.addProduct(value);
  }
}

const mapStateToProps = (state)=>{
  const { status, res, info } = state.addProduct;
  return { status, res, info }
}

export default connect(mapStateToProps, { addProduct, resetProduct })(ProductAddition)