import React, { useEffect, useState } from "react";
import Products from "../Products/Products";
import categoriesService from "../../services/categories.service";
import { Button, Select } from "antd";
import attributeService from "../../services/attribute.service";
import { useForm } from "react-hook-form";
import attributeHelper from "../../helpers/attribute.helper";
import productsService, {
  filterProducts,
} from "../../services/products.service";
import { Grid } from "@mui/material";
import { current } from "@reduxjs/toolkit";

export default function Filter() {
  const [categories, setCategories] = useState([]);
  const [categoriesWithId, setCategoriesWithId] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [products, setProducts] = useState([]);
  const [price, setPrice] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  const [pageSize, setPageSize] = useState(8);
  const [postsSize, setPostsSize] = useState(0);
  const [attributes, setAttributes] = useState([]);
  const [listOfFilters, setListOfFilters] = useState([]);

  const loadCategories = () => {
    categoriesService.getAllCategories().then((result) => {
      const names = result.data.map((item) => item.name);
      setCategories(names);
      setCategoriesWithId(result.data);
    });
  };

  const loadProducts = () => {
    /*productsService.getAll().then((response) => {
      if (response.status === 200) {
        // console.log(response.data.content);
        setProducts(response.data.content);
      } else {
        alert("Can't fetch data");
      }
    });*/
    productsService
      .filterProducts(listOfFilters, selectedCategory, pageSize, currentPage)
      .then((response) => {
        setProducts(response.data.content);
        setPostsSize(response.data.totalElements);
      });
  };

  const loadAttributes = (id) => {
    attributeService.getAttributesByCategoryId(id).then((response) => {
      setAttributes(response.data);
      // console.log(response.data);
    });
  };

  const onChangeCategory = (categoryName) => {
    setSelectedCategory(categoryName);

    // find category id from categoriesWithId using selectedCategory
    const categoryId = categoriesWithId.find(
      (item) => item.name === categoryName
    )?.id;

    loadAttributes(categoryId);
  };

  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm();

  useEffect(() => {
    loadCategories();
    loadProducts();
  }, [currentPage, postsSize, pageSize, listOfFilters]);

  const columnStyle = {
    borderRight: "2px solid #e0e0e0",
    paddingRight: "15px",
  };

  const onsubmit = (data) => {
    const requestData = attributeHelper.createListOfFilters(attributes, data);
    setListOfFilters(requestData);
  };

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
    window.scrollTo(0, 0);
  };

  return (
    <div className="row">
      <div className="col-md-3" style={columnStyle}>
        <div className="mb-4" style={{ marginLeft: "7%", marginRight: "7%" }}>
          <label className="mb-2 text-white login-label" htmlFor="category">
            Kategorija
          </label>
          <Select
            className="form-control"
            name="category"
            value={selectedCategory}
            onChange={(value) => onChangeCategory(value)}
          >
            {categories?.map((category) => (
              <Select.Option key={category} value={category}>
                {category}
              </Select.Option>
            ))}
          </Select>
        </div>
        {selectedCategory != null && (
          <div className="mb-4" style={{ marginLeft: "7%", marginRight: "7%" }}>
            <label className="mb-2 text-white login-label" htmlFor="price">
              Cijena
            </label>
            <input
              type="price"
              className="form-control"
              name={"price"}
              onChange={(e) => setPrice(e.target.value)}
              placeholder="razdvojiti unos sa - "
            />
          </div>
        )}
        {/* Add attributes */}
        <div style={{ marginLeft: "7%", marginRight: "7%" }}>
          <form onSubmit={handleSubmit(onsubmit)} name="Registracija">
            {attributes.map((attr) =>
              attr.type === "Integer" || attr.type === "Double" ? (
                <div className="mb-4" key={attr.name}>
                  <label
                    className="mb-2 text-white login-label"
                    htmlFor={attr.name}
                  >
                    {attr.name}
                  </label>
                  <input
                    type={attr.type}
                    className="form-control"
                    name={attr.name}
                    placeholder="razdvojiti unos sa - "
                    {...register(attr.name, { required: false })}
                  />
                </div>
              ) : (
                attr.type === "String" && (
                  <div className="mb-4" key={attr.name}>
                    <label
                      className="mb-2 text-white login-label"
                      htmlFor={attr.name}
                    >
                      {attr.name}
                    </label>
                    <input
                      type={attr.type}
                      className="form-control"
                      name={attr.name}
                      {...register(attr.name, { required: false })}
                    />
                  </div>
                )
              )
            )}

            <div
              className="d-flex align-items-center"
              style={{ marginLeft: "20%", marginRight: "20%" }}
            >
              <button type="submit" className="btn btn-main w-100 login-submit">
                Pretrazi
              </button>
            </div>
          </form>
        </div>
      </div>

      {/* Right side */}
      <div className="col-md-9">
        <Products products={products} />
        <Grid
          container
          item
          sx={{ justifyContent: "center", paddingLeft: "20%" }}
        >
          <Button
            disabled={currentPage === 0}
            onClick={() => handlePageChange(currentPage - 1)}
          >
            Previous
          </Button>
          <Button
            disabled={(currentPage + 1) * pageSize >= postsSize}
            onClick={() => handlePageChange(currentPage + 1)}
          >
            Next
          </Button>
        </Grid>
      </div>
    </div>
  );
}
