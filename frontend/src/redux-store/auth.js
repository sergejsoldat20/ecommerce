import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import api from "../apiInterceptor/api";

export const LOCAL_STORAGE_VALUE = "token";

export const initialState = {
  isLoginProgress: false,
  isAuthenticated: false,
  checkUserInProgress: true,
  userInfo: null,
  loggedOut: false,
};
export const login = createAsyncThunk("login", async (data) => {
  const result = await axios.post(
    "http://localhost:9000/api/auth/authenticate",
    data
  );
  const token = result.data.token;
  // console.log("token");
  localStorage.setItem(LOCAL_STORAGE_VALUE, token);
  const userResult = await api.get("/api/auth/validate");
  console.log(userResult);
  return userResult.data;
});
export const getUser = createAsyncThunk("get-user", async () => {
  const result = await api.get("/api/auth/validate");
  return result.data;
});

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    logOut: (state) => {
      state.isLoginProgress = false;
      state.isAuthenticated = false;
      state.checkUserInProgress = true;
      state.userInfo = null;
      state.loggedOut = true;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(login.pending, (state) => {
        state.isLoginProgress = true;
        state.loggedOut = false;
      })
      .addCase(login.fulfilled, (state, action) => {
        state.isLoginProgress = false;
        state.isAuthenticated = true;
        state.userInfo = action.payload;
      })
      .addCase(login.rejected, (state) => {
        state.isLoginProgress = false;
        state.isAuthenticated = false;
        state.loggedOut = false;
      })
      .addCase(getUser.pending, (state, action) => {})
      .addCase(getUser.fulfilled, (state, action) => {
        state.isAuthenticated = true;
        state.userInfo = action.payload;
      })
      .addCase(getUser.rejected, (state, action) => {
        state.isLoginProgress = false;
        state.isAuthenticated = false;
        state.checkUserInProgress = true;
        state.userInfo = null;
        state.loggedOut = true;
      });
  },
});

export const { logOut } = authSlice.actions;
export default authSlice.reducer;
