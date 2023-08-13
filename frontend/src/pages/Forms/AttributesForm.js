import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import categoriesService from "../../services/categories.service";
import { BiError } from "react-icons/bi";
import productsService from "../../services/products.service";
import attributeService from "../../services/attribute.service";

export default function AttributesForm() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [attributesByCategory, setAttributesByCategory] = useState([]);
  const [currentProduct, setCurrentProduct] = useState();

  useEffect(() => {
    loadAttributesByCategory();
  }, []);

  const loadAttributesByCategory = () => {
    productsService.getProductById(id).then((result) => {
      if (result.status === 200) {
        setCurrentProduct(result.data);
        // debugger;
        console.log(result.data);
        categoriesService
          .getAttributesByCategoryId(result.data.categoryId)
          .then((result) => {
            // console.log(result.data);
            setAttributesByCategory(result.data);
          });
      }
    });
  };

  const findAttributeIdByName = (name) => {
    return attributesByCategory.find((item) => item.name === name).id;
  };

  const {
    handleSubmit,
    register,
    formState: { errors },
  } = useForm();

  const onsubmit = (data) => {
    // loop through data and send request for each attibute, request is defined above
    // debugger;
    for (const [key, val] of Object.entries(data)) {
      attributeService.insertAttributeValue({
        attributeId: findAttributeIdByName(key),
        productId: currentProduct.id,
        value: val,
      });
    }
    navigate(`/upload/${currentProduct.id}`);
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
                    {attributesByCategory.map((attr) => (
                      <div className="mb-4" key={attr.name}>
                        <label
                          className="mb-2 text-white login-label"
                          htmlFor={attr.name}
                        >
                          {attr.name}
                        </label>
                        <input
                          type={attr.type}
                          className="form-control"
                          name={attr.name}
                          {...register(attr.name, { required: true })}
                        />
                        {errors[attr.name] && (
                          <span className="loggin-error">
                            <BiError size={20} color={"red"} /> Ovo polje je
                            obavezno!
                          </span>
                        )}
                      </div>
                    ))}

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
