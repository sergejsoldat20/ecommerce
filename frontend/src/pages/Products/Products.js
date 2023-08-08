/* eslint-disable no-unused-vars */
import { Col, Row } from "antd";
import React, { useEffect, useState } from "react";
import "../../static/home.css";
import productService from "../../services/products.service";
import ProductCard from "./ProductCard";

export default function Products() {
  /* const [products, setProducts] = useState([]);

  useEffect(() => {
    // loadProducts();
  });

  const loadProducts = () => {
    productService.getAll().then((response) => {
      if (response.status === 200) {
        setProducts(response.data);
      } else {
        alert("Can't fetch data");
      }
    });
  };*/
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
          {Array.from({ length: 4 }, (_, colIndex) => (
            <Col xs={20} sm={12} md={6} lg={6} key={colIndex}>
              <div id={colIndex} className="wrapper">
                <div id={colIndex} className="cardsWrapper">
                  <ProductCard />
                </div>
              </div>
            </Col>
          ))}
        </Row>
      ))}
    </div>
  );
}
