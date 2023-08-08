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

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  authenticate,
  register,
};
