import React from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Products from "./pages/Products/Products";
import Filter from "./pages/Filter/Filter";
import Navbar from "./layout/Navbar";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Login from "./pages/Login/Login";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route element={<Products />} path={"/products"} />
          <Route element={<Filter />} path={"/homepage"} />
          <Route element={<Login />} path={"/login"} />
          {/* <Route element={<ProductsTest />} path={"/test"} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
