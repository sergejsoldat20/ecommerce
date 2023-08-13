import React, { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { BiError } from "react-icons/bi";
import "../../static/login.css";
import { useNavigate } from "react-router-dom";
import categoriesService from "../../services/categories.service";
import { Select } from "antd";
import productsService from "../../services/products.service";

export default function ProductForm() {
  const [categories, setCategories] = useState([]);
  const [categoriesWithId, setCategoriesWithId] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");

  useEffect(() => {
    loadCategories();
  });

  const loadCategories = () => {
    categoriesService.getAllCategories().then((result) => {
      const names = result.data.map((item) => item.name);
      setCategories(names);
      setCategoriesWithId(result.data);
    });
  };
  const navigate = useNavigate("/");
  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm({
    defaultValues: {
      name: "Peugeot 207",
      description: "Some Description",
      price: 1000,
      categoryId: null,
    },
  });

  const onsubmit = (data) => {
    data.categoryId = categoriesWithId.find(
      (item) => item.name === selectedCategory
    ).id;

    productsService.insert(data).then((result) => {
      if (result.status === 200) {
        // debugger;
        navigate(`/attributes/${result.data.id}`);
      } else {
        alert("Zahtjev nije uspio!");
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
                        Naziv
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"name"}
                        {...register("name", { required: true })}
                      />
                      {errors.name && (
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
                        Opis
                      </label>
                      <input
                        type="text"
                        className="form-control"
                        name={"description"}
                        {...register("description", { required: true })}
                      />
                      {errors.description && (
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
                        Cijena
                      </label>
                      <input
                        type="number"
                        className="form-control"
                        name={"price"}
                        {...register("price", { required: true })}
                      />
                      {errors.price && (
                        <span className="loggin-error">
                          <BiError size={20} color={"red"} /> Ovo polje je
                          obavezno!
                        </span>
                      )}
                    </div>
                    <div className="mb-4">
                      <label
                        className="mb-2 text-white login-label"
                        htmlFor="category"
                      >
                        Kategorija
                      </label>
                      <Select
                        className="form-control"
                        name="category"
                        value={selectedCategory}
                        onChange={(value) => setSelectedCategory(value)}
                      >
                        {categories.map((category) => (
                          <Select.Option key={category} value={category}>
                            {category}
                          </Select.Option>
                        ))}
                      </Select>
                      {!selectedCategory && (
                        <span className="loggin-error">
                          <BiError size={20} color="red" /> This field is
                          required!
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
