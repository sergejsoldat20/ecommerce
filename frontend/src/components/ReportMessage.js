import React, { useEffect, useState } from "react";
import { PropTypes } from "prop-types";
import "../../src/static/comments.css";
import { Button, Card, Input, List } from "antd";
import { Avatar, Typography } from "@mui/material";
import commentService from "../../src/services/comments.service";
import { SendOutlined } from "@mui/icons-material";
import reportmessageService from "../services/reportmessage.service";

export default function ReportMessage() {
  const [message, setMessage] = useState();
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    loadMessages();
  }, []);

  const loadMessages = () => {
    reportmessageService
      .getMessages()
      .then((response) => {
        setMessages(response.data);
      })
      .catch((err) => {
        alert("cant fetch data");
      });
  };

  const handleSubmit = () => {
    const messageRequest = {
      messageText: message,
    };

    reportmessageService
      .insert(messageRequest)
      .then((response) => {
        setMessage("");
        loadMessages();
      })
      .catch((err) => {
        message.error("Neuspjesno slanje poruke");
      });
  };
  return (
    <div className="commentCards">
      <div className="commentCardsWrapper">
        <Card
          style={{ marginLeft: "5%", overflow: "auto" }}
          className="commentCard"
        >
          <div style={{ maxHeight: "500px", overflow: "auto" }}>
            {" "}
            {/* Adjust the maxHeight to your needs */}
            <List
              itemLayout="horizontal"
              dataSource={messages}
              renderItem={(comment, index) => (
                <List.Item>
                  {comment.belongsToAdmin && (
                    <List.Item.Meta
                      avatar={
                        <Avatar
                          style={{
                            backgroundColor: "#00a2ae",
                            verticalAlign: "middle",
                            borderRadius: "50%",
                          }}
                          size="large"
                          gap={2}
                        >
                          Ad
                        </Avatar>
                      }
                      title={"Administrator"}
                      description={comment.messageText}
                    />
                  )}
                  {!comment.belongsToAdmin && (
                    <List.Item.Meta
                      avatar={
                        <Avatar
                          style={{
                            backgroundColor: "#f56a00",
                            verticalAlign: "middle",
                            borderRadius: "50%",
                          }}
                          size="large"
                          gap={1}
                        >
                          {comment.accountUsername.charAt(0).toUpperCase()}
                        </Avatar>
                      }
                      title={comment.accountUsername}
                      description={comment.messageText}
                    />
                  )}
                </List.Item>
              )}
            />
          </div>
          <div style={{ display: "flex" }}>
            <Input
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              onPressEnter={handleSubmit}
            />
            <Button
              icon={<SendOutlined />}
              onClick={() => handleSubmit()}
            ></Button>
          </div>
        </Card>
      </div>
    </div>
  );
}
