import React, {Component} from 'react'
import PropTypes from 'prop-types'

export class SearchBar extends Component {
  static propTypes = {
    onSearch : PropTypes.func,
    query: PropTypes.string
  }
  constructor(props) {
    super(props);
    this.state = {value: props.query || ''};

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleChange(event) {
    this.setState({value: event.target.value});
  }
  handleSubmit() {
    this.props.onSearch(this.state.value);
  }

  render() {
    const {value} = this.state;
    return (
      <div className="form-row justify-content-center mb-5">
        <div className="col-8">
          <div className='input-group'>
            <div className="input-group-prepend">
              <div className="input-group-text"><span className="oi oi-magnifying-glass"></span></div>
            </div>
            <input type="text" value={value} onChange={this.handleChange} className="form-control input-lg" placeholder='Search' /> 
          </div>
        </div>
        <div className="col-auto">
          <button onClick = {this.handleSubmit} 
          className="btn btn-primary" disabled={value.length==0}>Seach</button>
        </div>
      </div>
      );

  }
}