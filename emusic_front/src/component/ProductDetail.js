import React, {Component} from 'react'
import PropTypes from 'prop-types';

class ProductDetail extends Component {
  static propTypes = {
    data: PropTypes.object
  }
  render() {
    return (
        <div>
          <div className="px-3 py-3 pt-md-5 pb-md-4 mx-auto">
            <h1>{ this.props.data.name + " - " + this.props.data.manufacotry }</h1>
          </div>
          <div className="row">
            <div className="col-md-5">
              <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder" width="100%" height="300px"/>
            </div>
            <div className="col-md-5 text-center">
              <h3>{ this.props.data.name }</h3>
              <p>{ this.props.data.id }</p>
              <p>{ this.props.data.description }</p>
              <p>{ this.props.data.manufacotry }</p>
              <p>{ this.props.data.category }</p>
              <p>{ this.props.data.status }</p>
              <p>{ this.props.data.price }</p>
            </div>
          </div>
        </div>
      );
  }
}

export default ProductDetail