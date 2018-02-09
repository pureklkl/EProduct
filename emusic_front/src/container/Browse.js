import React, {Component} from 'react';
import { connect } from 'react-redux'
import PropTypes from 'prop-types'

import { loadBrowse, SUCCESS, REQUESTING, FAILED } from '../actions'
import ProductList from '../component/ProductList'
import './App.css';

class Browse extends Component {
  static propTypes = {
    status: PropTypes.string,
    productList: PropTypes.array,
    loadBrowse: PropTypes.func
  }

  componentWillMount() {
    this.props.loadBrowse();
  }

  componentWillReceiveProps(nextProps) {
    if (this.props.status !== REQUESTING && nextProps.status !== REQUESTING) {
      this.props.loadBrowse();
    }
  }

  render() {
    const { status, productList } = this.props;
    return (
        <div className="container">
          <div className="px-3 py-3 pt-md-5 pb-md-4 mx-auto">
            <h1>Browse Music</h1>
            <p className="lead">find more music!</p>
          </div>
          { status === SUCCESS && <ProductList data={productList} /> }
          { status === REQUESTING && <h3 className="text-center py-5"><i>loading music list...</i></h3> }
          { status === FAILED && <h3 className="text-center py-5"><i>load failed try refresh</i></h3> }
        </div>
      );
  }
}

const mapStateToProps = (state) => {
  const { status, productList } = state.productList;
  return {status, productList};
}

export default connect(mapStateToProps, { loadBrowse })(Browse);