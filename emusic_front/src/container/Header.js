import{Header as _Header, AdminHeader as _AdminHeader} from '../component/Header';
import{loadUser} from '../actions'

import { connect } from 'react-redux';

const mapStateToProps = (state) => {
  const {user} = state.user
  return {user};
}

export const Header = connect(mapStateToProps, {loadUser})(_Header);
export const AdminHeader = connect(mapStateToProps, {loadUser})(_AdminHeader);
