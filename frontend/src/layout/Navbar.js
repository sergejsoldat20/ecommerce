import React from "react";

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <a className="navbar-brand m-2" href="/">
        <h5>Web shop</h5>
      </a>

      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item active">
            <a className="nav-link" href="/upload-product">
              Objavi proizvod
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="/">
              Podrska
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="/">
              Profil
            </a>
          </li>
        </ul>
        <ul className="navbar-nav ml-auto">
          <li className="nav-item">
            <a className="nav-link" href="/login">
              Login
            </a>
          </li>
        </ul>
      </div>
    </nav>
  );
}
