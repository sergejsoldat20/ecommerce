/* eslint-disable no-unused-vars */
import { Col, Row } from "antd";
import React, { useEffect, useState } from "react";
import "../../static/home.css";
import productService from "../../services/products.service";
import ProductCard from "./ProductCard";
import { PropTypes } from "prop-types";

export default function Products(properties) {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = () => {
    productService.getAll().then((response) => {
      if (response.status === 200) {
        // console.log(response.data.content);
        setProducts(response.data.content);
      } else {
        alert("Can't fetch data");
      }
    });
  };
  return (
    <div style={{ padding: "2%", display: "flex", flexDirection: "column" }}>
      {Array.from({ length: 5 }, (_, rowIndex) => (
        <Row
          style={{
            flex: 1,
            overflowY: "auto",
          }}
          gutter={[5, 5]}
          key={rowIndex}
        >
          {Array.from({ length: 4 }, (_, colIndex) => {
            const productIndex = rowIndex * 4 + colIndex;
            const product = properties.products[productIndex];
            if (product != null) {
              const props = {
                name: product.name,
                description: product.description,
                price: product.price,
                id: product.id,
              };

              return (
                <Col xs={20} sm={12} lg={6} key={colIndex}>
                  <div id={colIndex} className="wrapper">
                    <div id={colIndex} className="cardsWrapper">
                      <ProductCard props={props} />
                    </div>
                  </div>
                </Col>
              );
            }
            return <div></div>;
          })}
        </Row>
      ))}
    </div>
  );
}

Products.propTypes = {
  products: PropTypes.array,
};
