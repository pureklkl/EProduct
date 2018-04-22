import React from 'react'
import { Link } from 'react-router-dom'

const AdminPanel = ()=>{
  return (
      <div className="container">
        <div className="page-header py-3 pt-md-5 pb-md-4 mx-auto">
          <h1>Administrator Page</h1>
        </div>
        <h3><Link to="/admin/adminBrowse">Product Inventory</Link></h3>
        <h3><Link to="/admin/orderlist">Order</Link></h3>
      </div>
    );
}

export default AdminPanel;