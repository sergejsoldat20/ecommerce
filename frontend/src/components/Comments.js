import React, { useEffect, useState } from "react";
import { PropTypes } from "prop-types";
import "../../src/static/comments.css";
import { Button, Card, Input, List } from "antd";
import { Avatar } from "@mui/material";
import commentService from "../../src/services/comments.service";
import { SendOutlined } from "@mui/icons-material";
import accountService from "../services/account.service";

export default function Comments(props) {
  const [comments, setComments] = useState([]);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  const [singleComment, setSingleComment] = useState();

  useEffect(() => {
    validateUser();
    loadComments();
  }, []);

  const validateUser = () => {
    accountService
      .validate()
      .then((response) => {
        if (response.status === 200) {
          // setAccount(response.data);
          setIsAuthenticated(true);
        } else {
          setIsAuthenticated(false);
        }
      })
      .catch((err) => {
        console.log("nije validate");
      });
  };

  const loadComments = () => {
    commentService
      .getCommentsByProductId(props.productId)
      .then((result) => {
        console.log(result.data);
        setComments(result.data);
      })
      .catch((error) => {});
  };

  const handleInsertComment = () => {
    const commentRequest = {
      productId: props.productId,
      text: singleComment,
    };

    commentService.insertComment(commentRequest).then((result) => {
      console.log(result.data);
      loadComments();
    });
    setSingleComment("");
  };
  return (
    <div className="commentCards">
      <div className="commentCardsWrapper">
        <Card style={{ marginLeft: "5%" }} className="commentCard">
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
                        borderRadius: "50%",
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
          {isAuthenticated && (
            <div style={{ display: "flex" }}>
              <Input
                value={singleComment}
                onChange={(e) => setSingleComment(e.target.value)}
                onPressEnter={handleInsertComment}
              />
              <Button
                icon={<SendOutlined />}
                onClick={() => handleInsertComment()}
              ></Button>
            </div>
          )}
        </Card>
      </div>
    </div>
  );
}

Comments.propTypes = {
  productId: PropTypes.string,
};
