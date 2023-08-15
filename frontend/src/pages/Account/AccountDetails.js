import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import accountService from "../../services/account.service";
import { Paper, Typography } from "@mui/material";
import { Tabs } from "antd";

export default function AccountDetails() {
  const { id } = useParams();
  const [account, setAccount] = useState();

  useEffect(() => {
    loadAccount();
  }, []);

  const loadAccount = () => {
    accountService.getAccountById(id).then((response) => {
      console.log(response.data);
      setAccount(response.data);
    });
  };

  const getContentOfAccountTab = () => {
    return (
      <div class="container">
        <div class="row">
          <div class="col-3">
            <div class="bg-primary p-3">Left Column (25%)</div>
          </div>
          <div class="col-9">
            <div class="bg-secondary p-3">Right Column (75%)</div>
          </div>
        </div>
      </div>
    );
  };
  const getContentOfProductsTab = () => {
    return <div>sole</div>;
  };
  const getContentOfBoughtProductsTab = () => {
    return <div>sole</div>;
  };
  const tabItems = [
    {
      key: "1",
      label: <Typography variant="body1">Podaci o korisniku</Typography>,
      children: getContentOfAccountTab(),
    },
    {
      key: "2",
      label: <Typography variant="body1">Proizvodi</Typography>,
      children: getContentOfProductsTab(),
    },
  ];

  return (
    <Paper
      elevation={3}
      sx={{ width: "calc(100% - 96px)", height: "auto", m: 6 }}
    >
      <Tabs type="card" defaultActiveKey="1" items={tabItems} />
    </Paper>
  );
}
