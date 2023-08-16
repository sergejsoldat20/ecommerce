import React, { useEffect, useState } from "react";
import { PropTypes } from "prop-types";
import productsService from "../services/products.service";
import { Avatar, Card, Table } from "antd";
import "../../src/static/products.css";
import { Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function UserProducts(props) {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = () => {
    productsService.getProductsByAccountId(props.accountId).then((result) => {
      const data = result.data.map((product) => {
        if (product.photoUrl === null) {
          product.photoUrl =
            "https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg";
        }
        return product;
      });
      setProducts(data);
    });
  };

  const deleteProduct = (id) => {
    productsService.deleteProduct(id).then((result) => {
      loadProducts();
    });
  };

  const navigateToProduct = (id) => {
    navigate(`/products/${id}`);
  };

  const tableColumns = [
    {
      title: "Slika",
      dataIndex: "photoUrl",
      key: "photoUrl",
      render: (photoUrl) => (
        <Avatar shape="square" size="small" src={photoUrl} />
      ),
    },
    {
      title: "Naziv proizvoda",
      dataIndex: "name",
      key: "name",
      render: (name) => {
        return (
          <Typography
            variant="body1"
            sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
          >
            {name}
          </Typography>
        );
      },
    },
    {
      title: "Cijena",
      dataIndex: "price",
      key: "price",
      render: (price) => (
        <Typography
          variant="body1"
          sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
        >
          {new Intl.NumberFormat("ba", {
            style: "currency",
            currency: "BAM",
          }).format(price)}
        </Typography>
      ),
    },
    {
      title: "",
      dataIndex: "id",
      key: "id",
      render: (id) => (
        <Typography
          variant="body1"
          sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
        >
          <button
            type="submit"
            className="btn btn-main w-100 login-submit"
            onClick={() => navigateToProduct(id)}
          >
            Pogledaj
          </button>
        </Typography>
      ),
    },
    {
      title: "",
      dataIndex: "id",
      key: "id",
      render: (id) => (
        <Typography
          variant="body1"
          sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
        >
          <button
            type="submit"
            className="btn btn-main w-100 login-submit"
            onClick={() => deleteProduct(id)}
          >
            Obrisi
          </button>
        </Typography>
      ),
    },
  ];

  return (
    <div className="listWrapper">
      <div className="cardsWrapper">
        <Card style={{ marginLeft: "5%" }} className="homeCard">
          <Table columns={tableColumns} dataSource={products} />
        </Card>
      </div>
    </div>
  );
}

UserProducts.propTypes = {
  accountId: PropTypes.string,
};
