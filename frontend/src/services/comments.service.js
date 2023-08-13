import base from "./base.service";

const instance = base.service(true);

export const getCommentsByProductId = (id) => {
  return instance.get(`/comments/by-product-id/${id}`);
};

export const insertComment = (comment) => {
  return instance.post("/comments/insert", comment);
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  getCommentsByProductId,
  insertComment,
};
