import React from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Products from "./pages/Products/Products";
import Filter from "./pages/Filter/Filter";
import Navbar from "./layout/Navbar";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Login from "./pages/Login/Login";
import RegistrationForm from "./pages/Forms/RegistrationForm";
import UploadPhotoForm from "./pages/Forms/UploadPhotoForm";
import ProductForm from "./pages/Forms/ProductForm";
import AttributesForm from "./pages/Forms/AttributesForm";
import ProductDetails from "./pages/Products/ProductDetails";
import AccountDetails from "./pages/Account/AccountDetails";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route element={<Products />} path={"/products"} />
          <Route element={<Filter />} path={"/"} />
          <Route element={<Login />} path={"/login"} />
          <Route element={<RegistrationForm />} path={"/signup"} />
          <Route element={<UploadPhotoForm />} path={"/upload/:id"} />
          <Route element={<ProductForm />} path={"/upload-product"} />
          <Route element={<AttributesForm />} path={"/attributes/:id"} />
          <Route element={<ProductDetails />} path={"/products/:id"} />
          <Route element={<AccountDetails />} path={"/users/:id"} />
          {/* <Route element={<ProductsTest />} path={"/test"} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
