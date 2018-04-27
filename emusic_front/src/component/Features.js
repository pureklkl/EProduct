import React, {Component} from 'react';
import {Link} from 'react-router-dom'

import { LOCAL_IMG } from '../config';
import Img from './Img';

import './Features.css'

class Features extends Component {
  render() {
    return (
        <div className="row">
          <div className="col-lg-4 text-center">
            <Img className="rounded-circle" src={LOCAL_IMG + "feat1.jpg"} width="140" height="140" />
            <h2>James Cameron</h2>
            <p><Link className="btn btn-secondary" to="/browse/query/James%20Cameron/page/1" role="button">View more &raquo;</Link></p>
          </div> 
          <div className="col-lg-4 text-center">
            <Img className="rounded-circle" src={LOCAL_IMG + "feat2.jpg"} width="140" height="140" />
            <h2>Men in Black II</h2>
            <p><Link className="btn btn-secondary" to="/product/671014" role="button">View details &raquo;</Link></p>
          </div> 
          <div className="col-lg-4 text-center">
            <Img className="rounded-circle" src={LOCAL_IMG + "feat3.jpg"} width="140" height="140" />
            <h2>1995</h2>
            <p><Link className="btn btn-secondary" to="/browse/query/1995/page/1" role="button">View details &raquo;</Link></p>
          </div> 
        </div> 
      );
  }
}

export default Features;