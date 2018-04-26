import React, {Component} from 'react'
import PropTypes from 'prop-types'
import { Form, Text, TextArea, Radio, RadioGroup, Select } from 'react-form'

import { jsonToForm } from '../util'

const categoryOptions = [
  {
    label:"single",
    value:"single"
  },
  {
    label:"commercial",
    value:"commercial"
  },
  {
    label:"other",
    value:"other"
  }
];

const yearOptions = (()=> {
  var end = (new Date()).getFullYear();
  var years = [];
  for(let year = end; year >= 1850 ; year-- ) {
    years.push({label:year.toString(), value:year});
  }
  return years;
})();

const errorValidator = (values) => {
  return {
    title:   !values.title ||
            values.title.trim() == '' ? 'Product title should not be empty.' : null,
    price:  !values.price ||
            isNaN(values.price)  ||
            values.price < 0 ? 'Price must not be a non-negative number.' : null,
    unit:  !values.unit ||
            isNaN(values.unit) ||
            values.unit < 0 ? 'Unit must not be a non-negative number.' : null
  };
};

class ProductForm extends Component {
  
  static propTypes = {
    submit: PropTypes.func,
    reset: PropTypes.func,    
    defaultVal: PropTypes.object
  }

  componentDidMount() {
    this.form = new FormData();
    this.setDefaultVal();
  }

  reset() {
    this.setDefaultVal();
    this.props.reset();
  }

  submit(value) {
    value.price = parseInt(value.price);
    value.unit = parseInt(value.unit);
    value.year = parseInt(value.year);
    this.props.submit(jsonToForm(value, this.form));
  }

  setDefaultVal() {
    if (this.props.defaultVal !== null) {
      for(let key in this.props.defaultVal) {
        this.formApi.setValue(key, this.props.defaultVal[key]);
      }
    }    
  }

  render(){
    return (
          <Form 
            validateError={errorValidator}
            onSubmit={ (val)=>this.submit(val) }>
          { formApi => {
            this.formApi = formApi;
            let errors = formApi.errors;
            return (
            <form onSubmit={formApi.submitForm} id="product-addtion-form" encType='multipart/form-data'>
              
              <div className="form-group">
              <label htmlFor="title">Product title</label>
              <Text className="form-control" field="title" id="title" />
              { errors.title != null && <div className="alert alert-danger" role="alert">{errors.title}</div> }
              </div>
              
              
              <div className="form-group">
              <label htmlFor="price">Price</label>
              <Text className="form-control" field="price" id="price" />
              { errors.price != null && <div className="alert alert-danger" role="alert">{errors.price}</div> }
              </div>
              
              <div className="form-group">
              <label htmlFor="unit">Unit</label>
              <Text className="form-control" field="unit" id="unit" />
              { errors.unit != null && <div className="alert alert-danger" role="alert">{errors.unit}</div> }
              </div>
              
              <div className="form-group">
              <label htmlFor="manufactory">Manufactory</label>
              <Text className="form-control" field="manufactory" id="manufactory" />
              </div>
              
              <div className="form-group">
              <label htmlFor="dirctor">Director</label>
              <Text className="form-control" field="dirctor" id="dirctor" />
              </div>
              
              <div className="form-group">
              <label htmlFor="banner_url">Banner Url</label>
              <Text className="form-control" field="banner_url" id="banner_url" />
              </div>

              <div className="form-group">
              <label htmlFor="trailer">Trailer Url</label>
              <Text className="form-control" field="trailer" id="trailer" />
              </div>

              <div className="form-group">
              <RadioGroup field='status'>
              { group =>  (
                  <div>
                    <label className="pr-5">Status</label>
                    <div className="form-check form-check-inline">
                      <Radio className="form-check-input" group={group} id="active" value="active" />
                      <label className="form-check-label" htmlFor="active">Active</label>
                    </div>
                    <div className="form-check form-check-inline">
                      <Radio className="form-check-input" group={group} id="inactive" value="inactive" />
                      <label className="form-check-label" htmlFor="inactive">Inactive</label>  
                    </div>
                  </div>
                )
              }
              </RadioGroup>
              </div>
              
              <div className="form-group">
              <RadioGroup field="condition_">
              { group => (
                  <div>
                  <label className="pr-5">Condition</label>
                  <div className="form-check form-check-inline">
                    <Radio className="form-check-input" group={group} id="new" value='new' />
                    <label className="form-check-label" htmlFor='new'>New</label>
                  </div>
                  <div className="form-check form-check-inline"> 
                    <Radio className="form-check-input" group={group} id='used' value='used' />
                    <label className="form-check-label" htmlFor='used'>Used</label>  
                  </div>
                  </div>)
              }
              </RadioGroup>
              </div>
              
              <div className="form-group">
              <label htmlFor="category">Category</label>
              <Select  className="custom-select form-control" field="category" id="category" options={categoryOptions} />
              </div>
              
              <div className="form-group">
              <label htmlFor="year">year</label>
              <Select  className="custom-select form-control" field="year" id="year" options={yearOptions} />
              </div>

              <div className="form-group">
              <label htmlFor="description">Description</label>
              <TextArea className="form-control" field="description" id="description" />
              </div>
              
              <div className="form-group">
                <label htmlFor="productImage">Product Image</label>
                <input className="form-control-file" id="productImage" type="file" 
                 onChange={e=>this.form.set('image', e.target.files[0]) }/>
              </div>
              
              <button type="submit" className="py-2 mb-4 btn btn-primary">Submit</button>
            </form>
          )}
          }
          </Form>
      );
  }
}

export default ProductForm;