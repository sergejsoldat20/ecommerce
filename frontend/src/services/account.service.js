import base from "./base.service";

const authorizedInstance = base.service(true);
const unauthorizedInstance = base.service(false);

export const register = (request) => {
  return unauthorizedInstance.post("/api/auth/register", request);
};

export const authenticate = (request) => {
  return unauthorizedInstance.post("/api/auth/authenticate", request);
};

export const getCurrentUser = () => {
  return authorizedInstance.get("/users/current-user");
};

export const getAccountById = (id) => {
  return unauthorizedInstance.get(`/account/by-id/${id}`);
};

export const checkIfAuthorized = () => {
  if (localStorage.getItem("token") !== null) {
    return true;
  } else {
    return false;
  }
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  authenticate,
  register,
  getAccountById,
  checkIfAuthorized,
  getCurrentUser,
};
