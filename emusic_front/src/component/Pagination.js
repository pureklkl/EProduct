import React from 'react'
import PropTypes from 'prop-types'

import { Link } from 'react-router-dom'
const getShowPage = (total, cur) => {
  var pagin = [];
  pagin.push(1);
  var end = Math.min(cur + 1, total - 1);
  for(let page = Math.max(2, cur - 1); page <= end; page++) {
    pagin.push(page);
  }
  if(total != 1) {
    pagin.push(total);
  }
  return pagin;
}

export const Pagin = (label)=> {
  const Pagin = (props)=>{
    var {total, url, cur} = props;
    if(total == 0) return null;
    var showed = getShowPage(total, cur);
    var pagin = [];
    for(let i = 0; i < showed.length; i++) {
      var page = showed[i];
      pagin.push(<li key={i.toString()} className={page == cur ? "page-item active" : "page-item"} >
        <Link className = "page-link" to={url(page)}>{page}</Link>
        </li>);
      if(i < showed.length - 1 && ((page + 1) != showed[i + 1])) {
        pagin.push(<li key={i.toString() + "..."} className="page-item">
          <a className = "page-link">...</a></li>);
      }
    }
    return(
      <nav aria-label={label}> 
        <ul className="pagination justify-content-center">
          {pagin}
        </ul>
      </nav>
      )
  }
  Pagin.propTypes = {
    total : PropTypes.number,
    url: PropTypes.func,
    cur: PropTypes.number,
  }
  return Pagin;
}

