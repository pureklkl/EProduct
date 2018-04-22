import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import PropTypes from 'prop-types'
import { doFetch } from '../util';

export class Header extends Component {
  static propTypes = {
    children: PropTypes.node,
    user : PropTypes.object,
    loadUser: PropTypes.func
  }
  componentDidMount(){
    this.props.loadUser();
  }
  render() {
    const user = this.props.user;
    const logInfo = (user != null && user.email != null) ? (
      <div className="mt-2 mt-md-0">
        <a href="#" className="mr-sm-2">{user.email}</a>
        <Link className="mr-sm-2" to="/cart">Cart</Link>
        <Link className="mr-sm-2" to="/order">Order</Link>
        <button className="btn btn-primary" onClick={doFetch.bind(null, '/emusic/logout', {method: 'POST'})}>logout</button>
      </div>) : (
      <div className="mt-2 mt-md-0">
        <a href="/emusic/user/registration" className="mr-sm-2"><button className="btn btn-success">Create Account</button></a>
        <a href="/emusic/login"><button className="btn btn-primary">Login</button></a>
      </div>);

    return (
      <header>
        <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
          <Link className="navbar-brand" to="/">E-Music</Link>
          <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarCollapse">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item">
                <Link className="highligh-focus nav-link" to="/" autoFocus>Home <span className="sr-only">(current)</span></Link>
              </li>
              <li className="nav-item">
                <Link className="highligh-focus nav-link" to="/browse">Browse</Link>
              </li>
              {this.props.children}
            </ul>
            {logInfo}
          </div>
        </nav>
      </header>
    );
  }
}



export class AdminHeader extends Component {
  static propTypes = {
    user : PropTypes.object,
    loadUser: PropTypes.func
  }
  render() {
    return (
        <Header user={this.props.user} loadUser={this.props.loadUser}>
          <li className="nav-item">
            <Link className="highligh-focus nav-link" to="/admin">Admin</Link>
          </li>
        </Header>
      );
  }
}
