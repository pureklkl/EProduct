import React from 'react'
import PropTypes from 'prop-types'

import { Provider } from 'react-redux'
import { BrowserRouter as Router, Route } from 'react-router-dom'

import Header from '../component/Header'
import Footer from '../component/Footer'
import Product from './Product'
import App from './App'
import Browse from './Browse'


import './Root.css'
/*
const history =createBrowserHistory({
  basename: "/emusic"
})*/

const Root = ({ store }) => (
  <Provider store={store}>
    <Router basename="/emusic">
    <div>
      <Header />
      <div>
        <Route exact path="/" component={App} />
        <Route path="/browse" component={Browse} />
        <Route path="/product/:id" component={Product} />
        {/*<Route path="/:login/:name"
               component={RepoPage} />
        <Route path="/:login"
               component={UserPage} />*/}
      </div>
      <hr className="featurette-divider" />
      <Footer />
    </div>
    </Router>
  </Provider>
)

Root.propTypes = {
  store: PropTypes.object.isRequired,
}

export default Root