import React from "react";
import { PropTypes } from "prop-types";

export default function Comments(props) {
  const [comments, setComments] = useState([]);

  return <div>Comments</div>;
}

Comments.propTypes = {
  photoUrl: PropTypes.string,
  productId: PropTypes.number,
  closeModal: PropTypes.func,
};
