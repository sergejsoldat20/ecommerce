import React from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";

import { BiError } from "react-icons/bi";
import "../../static/login.css";
import { useNavigate } from "react-router-dom";
import { message } from "antd";

export default function BuyProductForm() {
  const navigate = useNavigate();
  // const { username } = useParams();
  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm({
    defaultValues: {
      card: "0000-0000-0000-0000",
    },
  });

  const onsubmit = (data) => {
    message.success("Kupili ste proizvod");
    navigate("/");
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
                        Broj kartice
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"pin"}
                        {...register("pin", { required: true })}
                      />
                      {errors.card && (
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
                        Potvrdi kupovinu
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
