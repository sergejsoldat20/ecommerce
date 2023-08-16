import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { NavLink } from "react-router-dom";
import { BsFillEyeFill, BsFillEyeSlashFill } from "react-icons/bs";
import { BiError } from "react-icons/bi";
import "../../static/login.css";
import { useNavigate } from "react-router-dom";
import accountService from "../../services/account.service";
import axios from "axios";
import { message } from "antd";

export default function Login() {
  const [showPassword, setShowPassword] = useState(false);

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

  const onsubmit = async (data) => {
    try {
      const response = await axios.post(
        "http://localhost:9000/api/auth/authenticate",
        data
      );
      const jwt = response.data.token;
      localStorage.setItem("token", jwt);

      navigate("/");
    } catch (error) {
      message.error("Niste se uspjesno ulogovali");
    }
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
