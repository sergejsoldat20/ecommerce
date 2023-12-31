import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { NavLink, useParams } from "react-router-dom";
import { BsFillEyeFill, BsFillEyeSlashFill } from "react-icons/bs";
import { BiError } from "react-icons/bi";
import "../../static/login.css";
import { useNavigate } from "react-router-dom";
import { message } from "antd";

import accountService from "../../services/account.service";

export default function ConfirmPasswordForm() {
  const navigate = useNavigate();
  const { username } = useParams();
  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm({
    defaultValues: {
      pin: "0000",
    },
  });

  const onsubmit = (data) => {
    accountService
      .confirmPin(data, username)
      .then((response) => {
        navigate("/login");
      })
      .catch((err) => {
        alert("cant confirm email");
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
                    <h4>Konfirmacija naloga</h4>

                    <div className="mb-4">
                      <label
                        className="mb-2 text-white login-label"
                        htmlFor="pin"
                      >
                        Pin
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"pin"}
                        {...register("pin", { required: true })}
                      />
                      {errors.pin && (
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
                        Dalje
                      </button>
                    </div>
                    <div className={"entrenceLink"}></div>
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
