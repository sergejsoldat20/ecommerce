import { LOCAL_STORAGE_VALUE } from "../redux-store/auth";
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:9000",
});

const interceptor = (store) => {
  api.interceptors.request.use(async (request) => {
    let token = localStorage.getItem(LOCAL_STORAGE_VALUE);

    request.headers.Authorization = `Bearer ${token}`;
    return request;
  });

  api.interceptors.response.use(
    (response) => {
      return response;
    },
    (error) => {
      return Promise.reject(error);
    }
  );
};
export default api;
export { interceptor };
