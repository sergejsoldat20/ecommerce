import React, { useEffect, useState } from "react";
import { PropTypes } from "prop-types";
import { Collapse, Image } from "antd";
import productsService from "../services/products.service";
import Paragraph from "antd/es/typography/Paragraph";
import attributeService from "../services/attribute.service";

export default function ProductDetailsModal(props) {
  const [productData, setProductData] = useState();

  // const [, setAttributeValues] = useState([]);

  const [collapseData, setCollapseData] = useState([]);

  useEffect(() => {
    loadProductData();
  }, []);

  const loadProductData = () => {
    productsService.getProductById(props.productId).then((result) => {
      setProductData(result.data);
      attributeService
        .getArributeValuesByProductId(props.productId)
        .then((result) => {
          const data = result.data.map((attribute, index) => {
            return {
              key: `${index}`,
              label: attribute.attributeName,
              children: <p>{attribute.value}</p>,
            };
          });
          setCollapseData(data);
        });
    });
  };

  const onChange = (key) => {
    console.log(key);
  };

  return (
    <div className="container">
      <div className="row" style={{ width: "100%" }}>
        <div className="col-sm">
          <Image width={400} src={props.photoUrl} />
        </div>
        <div className="col-sm">
          {productData && (
            <div className="container-fluid">
              <div className="row row-color-1">
                <div className="col-md-12">
                  <Paragraph style={{ fontSize: "20px", fontWeight: "bold" }}>
                    {productData.name}
                  </Paragraph>

                  <Paragraph style={{ fontSize: "15px" }}>
                    {productData.description}
                  </Paragraph>
                </div>
              </div>
              <div className="row row-color-2">
                <div className="col-md-12">
                  <Collapse
                    items={collapseData}
                    /// defaultActiveKey={["1"]}
                    onChange={onChange}
                  />
                </div>
              </div>
              {/* <ul>
                {attributeValues.map((attribute, index) => (
                  <li key={index}>
                    <strong>{attribute.attributeName}:</strong>{" "}
                    <Tag bordered={true} color="volcano">
                      {attribute.value}
                    </Tag>
                  </li>
                ))}
              </ul> */}
            </div>
          )}{" "}
          {/* Conditional rendering */}
        </div>
      </div>
    </div>
  );
}

ProductDetailsModal.propTypes = {
  photoUrl: PropTypes.string,
  productId: PropTypes.number,
  closeModal: PropTypes.func,
};
