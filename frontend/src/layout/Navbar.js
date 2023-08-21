import React, { useEffect, useState } from "react";
import { Typography } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getAuth } from "../redux-store/selectors";
import { LOCAL_STORAGE_VALUE, logOut } from "../redux-store/auth";
import accountService, { validate } from "../services/account.service";

export default function Navbar() {
  const navigate = useNavigate();
  const [account, setAccount] = useState();

  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const logout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  useEffect(() => {
    validateUser();
  });

  const validateUser = () => {
    accountService
      .validate()
      .then((response) => {
        if (response.status === 200) {
          setAccount(response.data);
          setIsAuthenticated(true);
        } else {
          setIsAuthenticated(false);
        }
      })
      .catch((err) => {
        console.log("nije validate");
      });
  };

  return (
    <nav
      className="navbar navbar-expand-lg navbar-light bg-light  "
      style={{
        fontSize: 25,
        paddingRight: 30,
      }}
    >
      <a className="navbar-brand" href="/">
        <Typography
          variant="h5"
          sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
        >
          WebShop
        </Typography>
      </a>
      <div className="collapse navbar-collapse " id="navbarSupportedContent">
        <ul
          className="navbar-nav"
          //={{ paddingLeft: "3rem", paddingRight: "1rem" }}
        >
          <li className="nav-item active">
            <a className="nav-link" href="/">
              <Typography
                variant="h6"
                sx={{
                  marginTop: "2%",
                  m: 1,
                  ml: 1,
                  overflowWrap: "break-word",
                }}
              >
                Početna
              </Typography>
            </a>
          </li>
          {isAuthenticated && (
            <li className="nav-item active">
              <Typography
                variant="h6"
                sx={{
                  marginTop: "2%",
                  m: 1,
                  ml: 1,
                  overflowWrap: "break-word",
                }}
              >
                <a className="nav-link" href="/upload-product">
                  Dodaj oglas
                </a>
              </Typography>
            </li>
          )}

          {isAuthenticated && (
            <li className="nav-item active">
              <Typography
                variant="h6"
                sx={{
                  marginTop: "2%",
                  m: 1,
                  ml: 1,
                  overflowWrap: "break-word",
                }}
              >
                <a
                  className="nav-link"
                  // style={{ paddingInline: "1rem" }}
                  href={`/users/${account.id}`}
                >
                  Profil
                </a>
              </Typography>
            </li>
          )}
          {isAuthenticated && (
            <li className="nav-item active">
              <Typography
                variant="h6"
                sx={{
                  marginTop: "2%",
                  m: 1,
                  ml: 1,
                  overflowWrap: "break-word",
                }}
              >
                <a
                  className="nav-link"
                  // style={{ paddingInline: "1rem" }}
                  href={"/reports"}
                >
                  Podrska
                </a>
              </Typography>
            </li>
          )}
        </ul>
      </div>

      {/* <div style={{ width: 40 }}></div> */}
      {isAuthenticated && (
        <ul
          className="navbar-nav"
          //={{ paddingLeft: "3rem", paddingRight: "1rem" }}
        >
          <li className="nav-item active">
            <Typography
              variant="h6"
              sx={{
                marginTop: "2%",
                m: 1,
                ml: 1,
                overflowWrap: "break-word",
              }}
            >
              <a className="nav-link" href="/login" onClick={() => logout()}>
                Odjavi se
              </a>
            </Typography>
          </li>
        </ul>
      )}
      {!isAuthenticated && (
        <ul
          className="navbar-nav"
          //={{ paddingLeft: "3rem", paddingRight: "1rem" }}
        >
          <li className="nav-item active">
            <a className="nav-link" href="/login">
              <Typography
                variant="h6"
                sx={{
                  marginTop: "2%",
                  m: 1,
                  ml: 1,
                  overflowWrap: "break-word",
                }}
              >
                Prijavi se
              </Typography>
            </a>
          </li>
        </ul>
      )}
    </nav>
  );
}
