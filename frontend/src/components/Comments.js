import React, { useEffect, useState } from "react";
import { PropTypes } from "prop-types";
import "../../src/static/home.css";
import { UserOutlined } from "@ant-design/icons";
import { Avatar, Card, List } from "antd";
import commentService from "../../src/services/comments.service";

export default function Comments(props) {
  const [comments, setComments] = useState([]);

  useEffect(() => {
    loadComments();
  }, []);

  const loadComments = () => {
    commentService.getCommentsByProductId(props.productId).then((result) => {
      console.log(result.data);
      setComments(result.data);
    });
  };
  return (
    <div className="wrapper">
      <div className="cardsWrapper">
        <Card style={{ width: "50%" }} className="homeCard">
          <List
            itemLayout="horizontal"
            dataSource={comments}
            renderItem={(comment, index) => (
              <List.Item>
                <List.Item.Meta
                  avatar={
                    <Avatar
                      style={{
                        backgroundColor: "#7265e6",
                        verticalAlign: "middle",
                      }}
                      size="large"
                      gap={1}
                    >
                      {comment.username.charAt(0).toUpperCase()}
                    </Avatar>
                  }
                  title={comment.username}
                  description={comment.text}
                />
              </List.Item>
            )}
          />
        </Card>
      </div>
    </div>
  );
}

Comments.propTypes = {
  productId: PropTypes.number,
};
