import { Card, Col, Row } from "antd";
import React from "react";
import "../../static/home.css";

const { Meta } = Card;
export default function Products() {
  return (
    <div style={{ padding: "2%", display: "flex", flexDirection: "column" }}>
      {Array.from({ length: 5 }, (_, rowIndex) => (
        <Row
          style={{
            flex: 1,
            overflowY: "auto",
          }}
          gutter={[4, 4]}
          key={rowIndex}
        >
          {Array.from({ length: 4 }, (_, colIndex) => (
            <Col xs={24} sm={12} md={8} lg={6} key={colIndex}>
              {/*<div id="administrationContainer" className="wrapper">*/}
              <div id="administrationContainer" className="cardsWrapper">
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
              </div>
            </Col>
          ))}
        </Row>
      ))}
    </div>
  );
}
