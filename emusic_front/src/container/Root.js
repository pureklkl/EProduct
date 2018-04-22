import React from 'react'
import PropTypes from 'prop-types'

import { Provider } from 'react-redux'
import { BrowserRouter as Router, Route } from 'react-router-dom'

import { Header, AdminHeader } from './Header'
import AdminPanel from '../component/AdminPanel'
import Footer from '../component/Footer'

import ProductAddition from './ProductAddition'
import Product from './Product'
import ProductPatch from './ProductPatch'
import { Cart } from './Cart'
import { OrderListBrowse } from './OrderBrowse'
import { Order } from './Order'
import App from './App'
import { Browse, AdminBrowse } from './Browse'
import { AdminOrderListBrowse } from './AdminOrderBrowse';
import { AdminOrder } from './AdminOrder';


import './Root.css'

const BaseRoots=({ children }) => (
    <div>
      <Route exact path="/" component={ App } />
      <Route path="/browse" component={ Browse } />
      <Route path="/product/:id" component={ Product } />
      <Route path="/cart" component={ Cart } />
      <Route exact path="/order" component={ OrderListBrowse } />
      <Route path="/order/orderid/:id" component={ Order } />
      {children}
    </div>
)
BaseRoots.propTypes = {
  children: PropTypes.node
}


export const Root = ({ store }) => (
  <Provider store={store}>
    <Router basename="/emusic">
    <div>
      <Header />
        <BaseRoots />
      <hr styleName="featurette-divider" />
      <Footer />
    </div>
    </Router>
  </Provider>
)

Root.propTypes = {
  store: PropTypes.object.isRequired,
}

export const AdminRoot = ({ store }) => (
  <Provider store={store}>
    <Router basename="/emusic/">
    <div>
      <AdminHeader />
        <BaseRoots>
          <Route exact path="/admin" component={ AdminPanel } />
          <Route path="/admin/adminBrowse" component={ AdminBrowse } />
          <Route path="/admin/addProduct" component={ ProductAddition } />
          <Route path='/admin/editProduct/:id' component = { ProductPatch } />
          <Route path = '/admin/orderlist' component = { AdminOrderListBrowse } />
          <Route path = '/admin/order/orderid/:id' component = { AdminOrder } />
        </BaseRoots>
      <hr styleName="featurette-divider" />
      <Footer />
    </div>
    </Router>
  </Provider>
)

AdminRoot.propTypes = {
  store: PropTypes.object.isRequired
}
