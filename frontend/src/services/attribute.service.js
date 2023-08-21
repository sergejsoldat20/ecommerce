import base from "./base.service";

const instance = base.service(true);
const unauthorizedInstance = base.service(false);

export const insertAttributeValue = (request) => {
  return instance.post("/attribute-values/insert", request);
};

export const getArributeValuesByProductId = (id) => {
  return unauthorizedInstance.get(`/attribute-values/by-product-id/${id}`);
};

export const getAttributesByCategoryId = (id) => {
  return unauthorizedInstance.get(`/attributes/by-category-id/${id}`);
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  insertAttributeValue,
  getAttributesByCategoryId,
  getArributeValuesByProductId,
};
