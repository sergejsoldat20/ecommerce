import React from "react";
import "./App.css";
import { BrowserRouter, Route, Router, Routes } from "react-router-dom";
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
import Reports from "./pages/Reports/Reports";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route element={<Products />} exact path={"/products"} />
          <Route element={<Filter />} exact path={"/"} />
          <Route element={<Login />} exact path={"/login"} />
          <Route element={<RegistrationForm />} exact path={"/signup"} />
          <Route element={<UploadPhotoForm />} exact path={"/upload/:id"} />
          <Route element={<ProductForm />} exact path={"/upload-product"} />
          <Route element={<AttributesForm />} exact path={"/attributes/:id"} />
          <Route element={<ProductDetails />} exact path={"/products/:id"} />
          <Route element={<AccountDetails />} exact path={"/users/:id"} />
          <Route element={<Reports />} exact path={"/reports"} />
          {/* <Route element={<ProductsTest />} path={"/test"} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
