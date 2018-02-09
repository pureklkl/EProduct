import React, {Component} from 'react';

class Footer extends Component {
  render(){
    return (
        <footer className="container">
          <p className="float-right"><a href="#root">Back to top</a></p>
          <p>&copy; 2017-2018 Company, Inc. &middot; <a href="./privacy">Privacy</a> &middot; <a href="./terms">Terms</a></p>
        </footer>
      );
  }
}

export default Footer;