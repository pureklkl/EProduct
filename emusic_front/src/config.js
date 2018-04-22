//put api here
const LOCAL = "http://localhost:8080/emusic/";

export const BROWSE_API = LOCAL + "product/api/browse";
export const PRODUCT_API = LOCAL + "product/api/";//{id}

export const PRODUCT_ADD_API = LOCAL + "admin/api/product";
export const PRODUCT_EDIT_API = LOCAL + "admin/api/editproduct/";//{id}
export const PRODUCT_DELETE_API = LOCAL + "admin/api/product/";//{id}
export const ADMIN_LOAD_ORDER_LIST_API = LOCAL + "admin/api/orderlist";
export const UPDATE_ORDER_API = (id, status) => {
  return LOCAL + `admin/api/orderid/${id}/status/${status}`;
}

export const USER_GETAUTH_API = LOCAL + "user/api/getuserauth";
export const CART_API = LOCAL + "cart/api";
export const CART_ITEM_API = LOCAL + "cart/api/cartitem/";
export const CART_UPDATE_ITEM_API = (quantity, id) => {
    return CART_ITEM_API + `${id}/quantity/${quantity}`;
  }

export const CHECK_ORDER_API = LOCAL + "order/cartid/";

export const ORDER_API = LOCAL + "order/api/";
export const LOAD_ORDER_LIST_API = ORDER_API + "user";
export const LOAD_ORDER_DETAIL_API = ORDER_API + "orderid/"


