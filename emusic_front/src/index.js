import 'babel-polyfill'
import React from 'react'
import { render } from 'react-dom'
import { BrowserRouter as Router } from 'react-router-dom'

import Root from './container/Root'
import configureStore from './store/configureStore'

// import registerServiceWorker from './registerServiceWorker';

import './index.css';

const store = configureStore()

render((<Router><Root store={store} /></Router>),
  document.getElementById('root')
)

/**
 * The service worker is a web API that helps you caching your assets and other files
 *
 * Well, it caused trouble when developing web on chrome because it will cache all things
 * and hide errors
 * 
 * should disable on dev
 * if enabled before, unregister from chrome devtool->application->serviceworker->unregister
 */
// registerServiceWorker();
