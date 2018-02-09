import React, {Component} from 'react'
import PropTypes from 'prop-types'
import {Link} from 'react-router-dom'

class ProductList extends Component {
  static propTypes = {
    data: PropTypes.array
  }
  render() {
    let tp = this.props.data;
    let tpDom = tp.map((p)=>
      <tr key={ p.id }>
        <td><img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder" width="80" height="80"/></td>
        <td><Link to={"product/"+p.id}>{p.name}</Link></td>
        <td>{ p.category }</td>
        <td>{ p.status }</td>
        <td>{ p.price }</td>
      </tr>
    );
    return (
        <table className="table table-striped table-hover">
          <thead>
            <tr>
              <th>Cover</th>
              <th>Name</th>
              <th>Category</th>
              <th>Condition</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            {tpDom}
          </tbody>
        </table>
      );
  }

}

export default ProductList;