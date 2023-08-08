import React from "react";
import { Card } from "antd";
const { Meta } = Card;

export default function ProductCard() {
  return (
    <Card
      hoverable
      // style={{ width: "100%", height: "105%" }}
      className="homeCard"
      cover={
        <img
          alt="example"
          src="https://fastly.picsum.photos/id/866/200/300.jpg?hmac=rcadCENKh4rD6MAp6V_ma-AyWv641M4iiOpe1RyFHeI"
        />
      }
    >
      <Meta title="Products" description="1000" />
    </Card>
  );
}
