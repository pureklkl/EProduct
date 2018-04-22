import React, {Component} from 'react'
import PropTypes from 'prop-types'
import {Link} from 'react-router-dom'

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
    let listDom = data.map(r=>(
      <tr key= { r.id }>
        {
          tds.map((td, index)=>(
            <td key={r.id + "-" + index}>{td(r[keys[index]], r)}</td>
          ))
        }
      </tr>)
    );
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

export const ListProductImg = id=>
(<img src={`http://localhost:8080/emusic/img/${id}.png`} alt="Generic placeholder" width="80" height="80"/>);

export const ListLink = (val, r)=>(<Link to={"/product/"+r.id}>{val}</Link>);

export const ListVal = val=>(<span>{val}</span>);

var customerListTd = [
ListProductImg,
ListLink,
ListVal,
ListVal,
ListVal];

var customerListKeys =  [
'id', 
'name',
'category',
'condition_',
'price'];

var customerListHeaders = [
(<th key='Cover'>Cover</th>), 
(<th key='Name'>Name</th>), 
(<th key='Category'>Category</th>), 
(<th key='Condition'>Condition</th>),
(<th key='Price'>Price</th>)];

export const CustomerProductList = (props) => 
(<List className="table table-striped table-hover"
  data={props.data} headers={customerListHeaders} tds={customerListTd} keys={customerListKeys}/>);
CustomerProductList.propTypes = {
  data: PropTypes.array
}

const edit=(id)=>(<Link to={"editProduct/"+id}>edit</Link>);

const deleteCol=(handler, id)=>(<a href="#" onClick={(e)=>{e.preventDefault();handler(id);}}>delete</a>);

var adminListHeaders = customerListHeaders.slice();
adminListHeaders.push((<th colSpan="2" key='Operation'>Operation</th>))

var adminListKeys = customerListKeys.slice();
adminListKeys.push('id');
adminListKeys.push('id');

export const AdminProductList = (props) => {
  var adminListTd = customerListTd.slice();
  adminListTd.push(edit);
  adminListTd.push(deleteCol.bind(null, props.deleteProduct));
  return (<List className="table table-striped table-hover"
          data={props.data} headers={adminListHeaders} tds={adminListTd} keys={adminListKeys}/>);
}

AdminProductList.propTypes = {
  data: PropTypes.array,
  deleteProduct: PropTypes.func
}



