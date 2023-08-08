import React from "react";
import Products from "../Products/Products";

export default function Filter() {
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
