import React, { useState, useEffect } from "react";
import accountService from "../services/account.service";
import { Typography } from "@mui/material";

export default function Navbar() {
  const [account, setAccount] = useState();

  useEffect(() => {
    loadCurrentAccount();
  }, []);

  const loadCurrentAccount = async () => {
    if (localStorage.getItem("token") !== null) {
      accountService.getCurrentUser().then((result) => {
        setAccount(result.data);
      });
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    // navigate("/login");
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
          {accountService.checkIfAuthorized() && (
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

          {accountService.checkIfAuthorized() && (
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
                  href={`/users/${account?.id}`}
                >
                  Profil
                </a>
              </Typography>
            </li>
          )}
          {accountService.checkIfAuthorized() && (
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
                  href={"/administration"}
                >
                  Podrska
                </a>
              </Typography>
            </li>
          )}
        </ul>
      </div>

      {/* <div style={{ width: 40 }}></div> */}
      {accountService.checkIfAuthorized() && (
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
      {!accountService.checkIfAuthorized() && (
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
