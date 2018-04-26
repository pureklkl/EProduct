import React, {Component} from 'react';

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
              <img className="first-slide" src="http://ia.imdb.com/media/imdb/01/I/05/30/21m.jpg" alt="First slide" />
            </div>
            <div className="carousel-item" styleName="carousel-item">
              <img className="second-slide" src="http://tecfa.unige.ch/etu/LME/0001/fernandez-trueba/projet/images/amelie.jpg" alt="Second slide" />
            </div>
            <div className="carousel-item" styleName="carousel-item">
              <img className="third-slide" src="http://www.carosta.com/shrek-dvd.jpg" alt="Third slide" />
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