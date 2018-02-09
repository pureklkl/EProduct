import React, {Component} from 'react';


import Carousel from '../component/Carousel'
import Features from '../component/Features'

import './App.css';

class App extends Component {
  
  render() {
    return (
      <main role="main">
        <Carousel />
        <div className="container marketing">
          <Features />
        </div>
      </main>
    );
  }
}

export default App;
