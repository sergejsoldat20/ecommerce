import React, { useEffect, useState } from "react";
import { Card } from "antd";
import { PropTypes } from "prop-types";
import productsService from "../../services/products.service";
import { useNavigate } from "react-router-dom";

const { Meta } = Card;

export default function ProductCard({ props }) {
  const [photoUrl, setPhotoUrl] = useState();
  const navigate = useNavigate();
  // const [isModalOpen, setIsModalOpen] = useState(false);

  const routeToProduct = () => {
    navigate(`/products/${props.id}`);
  };

  useEffect(() => {
    loadPhoto();
  });

  const loadPhoto = () => {
    productsService.getPhotoByProductId(props.id).then((result) => {
      if (result.data.length > 0) {
        setPhotoUrl(result.data.pop().photoUrl);
      } else {
        setPhotoUrl(
          "https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
        );
      }
    });
  };
  return (
    <Card
      hoverable
      // style={{ width: "100%", height: "105%" }}
      className="homeCard"
      cover={
        <img alt="example" src={photoUrl} onClick={() => routeToProduct()} />
      }
    >
      <Meta title={props.name} description={props.price + " BAM"} />
    </Card>
  );
}
ProductCard.propTypes = {
  name: PropTypes.string,
  description: PropTypes.string,
  price: PropTypes.number,
  id: PropTypes.number,
};
