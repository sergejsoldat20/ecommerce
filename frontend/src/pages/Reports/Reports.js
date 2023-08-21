import React from "react";
import ReportMessage from "../../components/ReportMessage";
import { Paper } from "@mui/material";

export default function Reports() {
  return (
    <Paper
      elevation={3}
      sx={{
        backgroundColor: "#f4f4f4",
        width: "calc(100% - 96px)",
        height: "auto",
        m: 6,
      }}
    >
      <ReportMessage />
    </Paper>
  );
}
