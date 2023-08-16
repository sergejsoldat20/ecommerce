import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import accountService from "../../services/account.service";
import {
  Divider,
  Grid,
  Avatar,
  Box,
  Paper,
  Stack,
  Typography,
} from "@mui/material";
import { Tabs } from "antd";
import {
  EmailOutlined,
  PersonOutlineOutlined,
  PhoneOutlined,
} from "@mui/icons-material";
import UserProducts from "../../components/UserProducts";

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
      <div className="container" style={{ marginBottom: "2%" }}>
        <div className="row">
          <div className="col-3">
            <Grid container spacing={1}>
              <Grid
                item
                xs={12}
                sm={12}
                md={12}
                lg={12}
                style={{ marginBottom: "2%" }}
              >
                <Box sx={{ height: "100%", mt: 2 }}>
                  <Stack sx={{ p: 3 }} rowGap={1}>
                    <Stack direction="row" columnGap={2}>
                      {account && (
                        <Avatar
                          style={{
                            backgroundColor: "#7265e6",
                            verticalAlign: "middle",
                            borderRadius: "100%",
                          }}
                          sx={{ width: 70, height: 70 }}
                          gap={1}
                        >
                          <Typography sx={{ fontSize: 30 }} variant="body1">
                            {account?.username.charAt(0).toUpperCase()}
                          </Typography>
                        </Avatar>
                      )}

                      <Typography sx={{ mt: 1 }} variant="body1">
                        {account?.username}
                      </Typography>
                    </Stack>
                  </Stack>
                </Box>
              </Grid>
            </Grid>
          </div>
          <div className="col-9">
            <Grid container spacing={1} sx={{ marginTop: "2%" }}>
              <Grid container item xs={12} sm={12} md={8} lg={9}>
                <Grid item xs={12} sm={6} md={6} lg={4}>
                  <Box sx={{ height: "100%", m: 1 }}>
                    <Paper elevation={10}>
                      <Stack direction="column">
                        <Stack
                          direction="row"
                          columnGap={2}
                          sx={{ m: 1, overflowWrap: "anywhere" }}
                        >
                          <PersonOutlineOutlined />
                          <Typography variant="body1">
                            {"Korisničko ime"}
                          </Typography>
                        </Stack>
                        <Divider />
                        <Typography
                          variant="body1"
                          sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                        >
                          {account?.username}
                        </Typography>
                      </Stack>
                    </Paper>
                  </Box>
                </Grid>
                <Grid item xs={12} sm={6} md={6} lg={4}>
                  <Box sx={{ height: "100%", m: 1 }}>
                    <Paper elevation={10}>
                      <Stack direction="column">
                        <Stack
                          direction="row"
                          columnGap={2}
                          sx={{ m: 1, overflowWrap: "anywhere" }}
                        >
                          <PersonOutlineOutlined />
                          <Typography variant="body1">Ime i prezime</Typography>
                        </Stack>
                        <Divider />
                        <Typography
                          variant="body1"
                          sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                        >
                          {account?.firstName + " " + account?.lastName}
                        </Typography>
                      </Stack>
                    </Paper>
                  </Box>
                </Grid>
                <Grid item xs={12} sm={6} md={6} lg={4}>
                  <Box sx={{ height: "100%", m: 1 }}>
                    <Paper elevation={10}>
                      <Stack direction="column">
                        <Stack
                          direction="row"
                          columnGap={2}
                          sx={{ m: 1, overflowWrap: "anywhere" }}
                        >
                          <EmailOutlined />
                          <Typography variant="body1">E-mail adresa</Typography>
                        </Stack>
                        <Divider />
                        <Typography
                          variant="body1"
                          sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                        >
                          {account?.email}
                        </Typography>
                      </Stack>
                    </Paper>
                  </Box>
                </Grid>
                <Grid item xs={12} sm={6} md={6} lg={4}>
                  <Box sx={{ height: "100%", m: 1 }}>
                    <Paper elevation={10}>
                      <Stack direction="column">
                        <Stack
                          direction="row"
                          columnGap={2}
                          sx={{ m: 1, overflowWrap: "break-word" }}
                        >
                          <PhoneOutlined />
                          <Typography variant="body1">
                            Kontakt telefon
                          </Typography>
                        </Stack>
                        <Divider />
                        <Typography
                          variant="body1"
                          sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                        >
                          {account?.phoneNumber}
                        </Typography>
                      </Stack>
                    </Paper>
                  </Box>
                </Grid>
              </Grid>
            </Grid>
          </div>
        </div>
      </div>
    );
  };
  const getContentOfProductsTab = () => {
    return <UserProducts accountId={id} />;
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
      sx={{
        width: "calc(100% - 96px)",
        height: "auto",
        m: 6,
        justifyContent: "center",
      }}
    >
      <Tabs type="card" defaultActiveKey="1" items={tabItems} />
    </Paper>
  );
}
