import React, {Component} from 'react';

import { LOCAL_IMG } from '../config';
import Img from './Img';

import './Carousel.css'

class Carousel extends Component {
  render(){
    return (
        <div id="myCarousel" className="slide carousel" styleName="carousel" data-ride="carousel">
          <ol className="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" className="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
          </ol>
          <div className="carousel-inner">
            <div className="carousel-item active" styleName="carousel-item">
              <Img className="first-slide" src={LOCAL_IMG + "slide1.jpg"} />
            </div>
            <div className="carousel-item" styleName="carousel-item">
              <Img className="second-slide" src={LOCAL_IMG + "slide2.jpg"} />
            </div>
            <div className="carousel-item" styleName="carousel-item">
              <Img className="third-slide" src={LOCAL_IMG + "slide3.jpg"} />
            </div>
          </div>
          <a className="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span className="carousel-control-prev-icon" aria-hidden="true"></span>
            <span className="sr-only">Previous</span>
          </a>
          <a className="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span className="carousel-control-next-icon" aria-hidden="true"></span>
            <span className="sr-only">Next</span>
          </a>
        </div>
      );
  }
}

export default Carousel;