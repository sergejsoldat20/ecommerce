import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { NavLink } from "react-router-dom";
import { BsFillEyeFill, BsFillEyeSlashFill } from "react-icons/bs";
import { BiError } from "react-icons/bi";
import "../../static/login.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { getAuth } from "../../redux-store/selectors";
import { message } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { getUser, login } from "../../redux-store/auth";
import accountService from "../../services/account.service";

export default function Login() {
  const [showPassword, setShowPassword] = useState(false);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm({
    defaultValues: {
      username: "sega",
      password: "pass",
      // email: "sergej@gmail.com", password: "pass",
    },
  });

  const togglePassword = () => {
    setShowPassword((currValue) => !currValue);
  };

  const onsubmit = (data) => {
    accountService
      .authenticate(data)
      .then((response) => {
        if (response.status === 200) {
          message.success("Uspjesno ste se prijavili!");
          localStorage.setItem("token", response.data.token);
          navigate("/");
        } else {
          message.error("Pogresno ime ili lozinka!");
        }
      })
      .catch((err) => {
        alert("Pogresno ime ili lozinka!");
      });
  };
  return (
    <div className="container position-absolute card-top top-50 start-50 translate-middle mt-4">
      <div className="login-background">
        <div className="container position-absolute card-top top-50 start-50 translate-middle">
          <div className="row justify-content-sm-center">
            <div className="col-xl-4 col-lg-5 col-md-6 col-sm-10 login-card">
              <div className="card">
                <div className="card-body p-3">
                  <form onSubmit={handleSubmit(onsubmit)}>
                    <h4>Prijava</h4>

                    <div className="mb-4">
                      <label
                        className="mb-2 text-white login-label"
                        htmlFor="username"
                      >
                        Email
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"username"}
                        {...register("username", { required: true })}
                      />
                      {errors.email && (
                        <span className="loggin-error">
                          <BiError size={20} color={"red"} /> Ovo polje je
                          obavezno!
                        </span>
                      )}
                    </div>

                    <div className="mb-4">
                      <div className="mb-2 w-100">
                        <label
                          className="text-white login-label"
                          htmlFor="password"
                        >
                          Lozinka
                        </label>
                      </div>
                      <div className="input-group mb-3">
                        <input
                          type={showPassword ? "text" : "password"}
                          className="form-control password-eye-input"
                          name={"password"}
                          {...register("password", { required: true })}
                        />
                        <span
                          className="input-group-text password-eye"
                          id="basic-addon2"
                          onClick={togglePassword}
                        >
                          {showPassword ? (
                            <BsFillEyeSlashFill />
                          ) : (
                            <BsFillEyeFill />
                          )}
                        </span>
                      </div>
                      {errors.password && (
                        <span className="loggin-error">
                          <BiError size={20} color={"red"} /> Ovo polje je
                          obavezno!
                        </span>
                      )}
                    </div>

                    <div className="d-flex align-items-center">
                      <button
                        type="submit"
                        className="btn btn-main w-100 login-submit"
                      >
                        Prijavi se
                      </button>
                    </div>
                    <div className={"entrenceLink"}>
                      <NavLink to={"/signup"} className={"entrenceLink"}>
                        Ako nemas nalog registruj se
                      </NavLink>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
