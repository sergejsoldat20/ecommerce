import React, { useEffect, useState } from "react";
import Products from "../Products/Products";
import categoriesService from "../../services/categories.service";
import { useNavigate } from "react-router-dom";
import { WindowSharp } from "@mui/icons-material";

export default function Filter() {
  const [categories, setCategories] = useState();
  const loadCategories = () => {
    categoriesService.getAllCategories().then((response) => {
      if (response.status === 200) {
        setCategories(response.data.content);
      } else {
        alert("Can't fetch data");
      }
    });
  };
  useEffect(() => {
    loadCategories();
  }, []);
  const randomNames = [
    "John",
    "Jane",
    "Michael",
    "Emily",
    "William",
    "Sosphia",
  ];

  const columnStyle = {
    borderRight: "2px solid #e0e0e0",
    paddingRight: "15px", // To adjust for Bootstrap's column padding
  };

  return (
    <div className="row">
      {/* Left side */}
      <div className="col-md-3" style={columnStyle}>
        <ul style={{ listStyle: "none", padding: "4%" }}>
          {randomNames.map((name, index) => (
            <li style={{ backgroundColor: "grey" }} key={index}>
              {name}
            </li>
          ))}
        </ul>
      </div>

      {/* Right side */}
      <div className="col-md-9">
        <Products />
      </div>
    </div>
  );
}
