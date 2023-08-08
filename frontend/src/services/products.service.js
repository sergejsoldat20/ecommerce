import base from "./base.service";

const instance = base.service(false);

export const getAll = () => {
  return instance.get("/posts");
};

export const getPostById = (id) => {
  return instance.get(`/posts/${id}`);
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  getAll,
};
