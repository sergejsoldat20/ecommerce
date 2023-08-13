import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { BsFillEyeFill, BsFillEyeSlashFill } from "react-icons/bs";
import { BiError } from "react-icons/bi";
import "../../static/login.css";
import { useNavigate } from "react-router-dom";
import accountService from "../../services/account.service";

export default function RegistrationForm() {
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();
  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm({
    defaultValues: {
      email: "user@gmail.com",
      phoneNumber: "31243123123",
      location: "BL",
      firstName: "Sergej",
      lastName: "Soldat",
      username: "test4@email.com",
      password: "test",
      // email: "sergej@gmail.com", password: "pass",
    },
  });

  const togglePassword = () => {
    setShowPassword((currValue) => !currValue);
  };

  const onsubmit = (data) => {
    accountService.register(data).then((result) => {
      if (result.status === 200) {
        navigate("/login");
      } else {
        alert("Korisnicko ime ili email su zauzeti!");
      }
    });
  };
  return (
    <div className="container position-absolute card-top top-50 start-50 translate-middle mt-5">
      <div className="login-background">
        <div className="container position-absolute card-top top-50 start-50 translate-middle">
          <div className="row justify-content-sm-center">
            <div className="col-xl-4 col-lg-5 col-md-6 col-sm-10 login-card">
              <div className="card">
                <div className="card-body p-3">
                  <form onSubmit={handleSubmit(onsubmit)} name="Registracija">
                    <div className="mb-4">
                      <label
                        className="mb-2 text-white login-label"
                        htmlFor="username"
                      >
                        Ime
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"firstName"}
                        {...register("firstName", { required: true })}
                      />
                      {errors.firstName && (
                        <span className="loggin-error">
                          <BiError size={20} color={"red"} /> Ovo polje je
                          obavezno!
                        </span>
                      )}
                    </div>
                    <div className="mb-4">
                      <label
                        className="mb-2 text-white login-label"
                        htmlFor="username"
                      >
                        Prezime
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"lastName"}
                        {...register("lastName", { required: true })}
                      />
                      {errors.lastName && (
                        <span className="loggin-error">
                          <BiError size={20} color={"red"} /> Ovo polje je
                          obavezno!
                        </span>
                      )}
                    </div>
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
                        name={"email"}
                        {...register("email", { required: true })}
                      />
                      {errors.email && (
                        <span className="loggin-error">
                          <BiError size={20} color={"red"} /> Ovo polje je
                          obavezno!
                        </span>
                      )}
                    </div>
                    <div className="mb-4">
                      <label
                        className="mb-2 text-white login-label"
                        htmlFor="username"
                      >
                        Korisnicko ime
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"username"}
                        {...register("username", { required: true })}
                      />
                      {errors.username && (
                        <span className="loggin-error">
                          <BiError size={20} color={"red"} /> Ovo polje je
                          obavezno!
                        </span>
                      )}
                    </div>
                    <div className="mb-4">
                      <label
                        className="mb-2 text-white login-label"
                        htmlFor="username"
                      >
                        Lokacija
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"location"}
                        {...register("location", { required: true })}
                      />
                      {errors.location && (
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
                        Registruj se
                      </button>
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
