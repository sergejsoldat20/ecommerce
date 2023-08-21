import base from "./base.service";

const instance = base.service(true);

export const getMessages = () => {
  return instance.get("/report-messages");
};

export const insert = (request) => {
  return instance.post("/report-messages/insert", request);
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  getMessages,
  insert,
};
