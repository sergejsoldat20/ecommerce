import React from "react";
import { Upload } from "antd";
import "../../static/fileUpload.css";
import ImageUpload from "../../static/images/image.png";
import { useNavigate, useParams } from "react-router-dom";
import productsService from "../../services/products.service";

const { Dragger } = Upload;
export default function UploadPhotoForm() {
  const { id } = useParams();
  const handleFileUpload = (file) => {
    productsService.uploadPhoto(file, id);
  };
  const navigate = useNavigate();
  const props = {
    name: "file",
    action: "",
    multiple: false,
    showUploadList: true,
    beforeUpload: (file) => {
      handleFileUpload(file);
      return false;
    },
  };

  const onsubmit = () => {
    navigate("/");
  };

  return (
    <div className="fileUpolad">
      <Dragger {...props}>
        <p className="ant-upload-drag-icon">
          <img className="fileUploadIcon" src={ImageUpload} alt="" />
        </p>
        <p className="ant-upload-text">Klikni ili prenesi</p>
      </Dragger>
      <div className="d-flex align-items-center">
        <button
          type="submit"
          className="btn btn-main w-100 login-submit"
          onClick={onsubmit}
        >
          Submit
        </button>
      </div>
    </div>
  );
}
