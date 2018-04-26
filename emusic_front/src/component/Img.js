import React from "react";
import PropTypes from 'prop-types'

import ReactImageFallback from "react-image-fallback";
import { LOCAL } from '../config';

const Img = (props) => {
  const {src, ...rest} = props;
  return (<ReactImageFallback 
    src={src} 
    fallbackImage={ LOCAL + "img/noimg.png" }
    initialImage={ LOCAL + "img/loading.gif" } 
    alt = "image load fail"
    {...rest}
    />);
}
Img.propTypes = {
  src: PropTypes.string
}

export default Img;