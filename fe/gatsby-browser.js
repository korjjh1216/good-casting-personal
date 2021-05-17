import React from "react";
import { GlobalProvider } from "./src/context/GlobalContext";
import { CartProvider } from "./src/context/CartContext";
import Layout from "./src/components/Layout";
import { Provider } from "react-redux";
import store from "./src/state/store"

export const wrapPageElement = ({ element, props }) => {
  // props provide same data to Layout as Page element will get
  // including location, data, etc - you don't need to pass it
  return <Layout {...props}>{element}</Layout>;
};

export const wrapRootElement = ({ element }) => {

  return (
    <GlobalProvider>
      <CartProvider>
        <Provider store={store}>
          {element}
        </Provider>
      </CartProvider>
    </GlobalProvider>
  );
}
