import base from "./base.service";

const instance = base.service(false);

export const getAllCategories = () => {
  return instance.get("/categories/all-categories");
};

export const getAttributesByCategoryId = (id) => {
  return instance.get(`/categories/attributes-by-category/${id}`);
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  getAllCategories,
  getAttributesByCategoryId,
};
