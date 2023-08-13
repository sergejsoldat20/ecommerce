import React, { useEffect, useState } from "react";
import { Collapse, Image, Tabs } from "antd";
import Paragraph from "antd/es/typography/Paragraph";
import { useParams } from "react-router-dom";
import productsService from "../../services/products.service";
import attributeService from "../../services/attribute.service";

export default function ProductDetails() {
  const [productData, setProductData] = useState();
  const { id } = useParams();
  // const [, setAttributeValues] = useState([]);

  const [collapseData, setCollapseData] = useState([]);
  const [photoUrl, setPhotoUrl] = useState();
  useEffect(() => {
    loadProductData();
    loadPhoto();
  }, []);

  const loadPhoto = () => {
    productsService.getPhotoByProductId(id).then((result) => {
      if (result.data.length > 0) {
        setPhotoUrl(result.data.pop().photoUrl);
      } else {
        setPhotoUrl(
          "https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
        );
      }
    });
  };

  const loadProductData = () => {
    productsService.getProductById(id).then((result) => {
      setProductData(result.data);
      console.log(result.data);
      attributeService.getArributeValuesByProductId(id).then((result) => {
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

  const getContentOfProductTab = () => {
    return (
      <div className="container" style={{ marginTop: "3%" }}>
        <div className="row" style={{ width: "100%" }}>
          <div className="col-sm">
            <Image width={500} src={photoUrl} />
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
                      // onChange={onChange}
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
  };

  const getContentOfPCommentTab = () => {
    return (
      <div>
        <p>Comment</p>
      </div>
    );
  };

  const tabItems = [
    {
      key: "1",
      label: `Proizvod`,
      children: getContentOfProductTab(),
    },
    {
      key: "2",
      label: `Komentari`,
      children: getContentOfPCommentTab(),
    },
  ];

  return (
    <div style={{ margin: "2%" }}>
      <Tabs type="card" defaultActiveKey="1" items={tabItems} />
    </div>
  );
}
