import base from "./base.service";
import axios from "axios";

const instance = base.service(false);
const authorizedInstance = base.service(true);

export const getAll = () => {
  return instance.get("/products");
};

export const getProductById = (id) => {
  return instance.get(`/products/by-id/${id}`);
};

export const getPhotoByProductId = (id) => {
  return instance.get(`/photos/by-post-id/${id}`);
};

export const insert = (request) => {
  return authorizedInstance.post("/products/insert", request);
};

export const getProductsByAccountId = (id) => {
  return instance.get(`/products/by-account-id/${id}`);
};

export const deleteProduct = (id) => {
  return authorizedInstance.delete(`/products/delete/${id}`);
};

export const uploadPhoto = (file, id) => {
  const formData = new FormData();
  formData.append("file", file);
  const token = localStorage.getItem("token");

  axios
    .post(`http://localhost:9000/photos/upload/${id}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: `Bearer ${token}`,
      },
    })
    .then((response) => {
      // console.log(response.data);
    });
};

export const filterProducts = (filtersList, categoryName, pageSize, page) => {
  if (categoryName === null) {
    return instance.post(`/products/filter?page=${page}&size=${pageSize}`, []);
  }
  return instance.post(
    `/products/filter?category=${categoryName}&page=${page}&size=${pageSize}`,
    filtersList
  );
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  getAll,
  getProductById,
  getPhotoByProductId,
  insert,
  uploadPhoto,
  deleteProduct,
  getProductsByAccountId,
  filterProducts,
};
