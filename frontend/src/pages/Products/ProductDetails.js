import React, { useEffect, useState } from "react";
import {
  Avatar,
  Box,
  Divider,
  Grid,
  Paper,
  Stack,
  Typography,
} from "@mui/material";
import { Image, Tabs } from "antd";
import moment from "moment/moment";
import { useNavigate, useParams } from "react-router-dom";
import productsService from "../../services/products.service";
import attributeService from "../../services/attribute.service";
import Comments from "../../components/Comments";
import {
  CalendarMonthOutlined,
  LocationOnOutlined,
  NewReleasesOutlined,
  SellOutlined,
} from "@mui/icons-material";
import accountService from "../../services/account.service";

export default function ProductDetails() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [productData, setProductData] = useState();
  const { id } = useParams();
  const [productAccount, setProductAccount] = useState();
  const navigate = useNavigate();

  const [attributes, setAttributes] = useState([]);
  const [photoUrl, setPhotoUrl] = useState();
  useEffect(() => {
    validateUser();
    loadProductData();
    loadPhoto();
  }, []);

  const handleOnBuy = () => {
    navigate("/buy");
  };

  const loadPhoto = () => {
    productsService.getPhotoByProductId(id).then((result) => {
      if (result.data.length > 0) {
        setPhotoUrl(result.data.pop().photoUrl);
      } else {
        setPhotoUrl(
          "https://static.vecteezy.com/system/resources/previews/005/337/799/non_2x/icon-image-not-found-free-vector.jpg"
        );
      }
    });
  };

  const loadProductData = () => {
    productsService.getProductById(id).then((result) => {
      setProductData(result.data);
      // console.log(result.data);
      attributeService.getArributeValuesByProductId(id).then((response) => {
        setAttributes(response.data);
      });
      accountService.getAccountById(result.data.accountId).then((response) => {
        setProductAccount(response.data);
      });
    });
  };

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

  const getContentOfProductTab = () => {
    return (
      <div className="container" style={{ marginTop: "3%" }}>
        <div className="row" style={{ width: "100%" }}>
          <Grid item xs={12} sm={12} md={12} lg={12} sx={{ textAlign: "left" }}>
            <Typography variant="h6">Osnovni podaci</Typography>
            <Divider variant="fullWidth" />
          </Grid>
          <Grid item xs={12} sm={12} md={12} lg={12} sx={{ textAlign: "left" }}>
            <Typography variant="h4" component="h1" sx={{ mb: 2, mt: 2 }}>
              {productData?.name}
            </Typography>
          </Grid>
          <div className="col-sm">
            <Image width={430} src={photoUrl} />
          </div>
          <div className="col-sm" style={{ marginTop: "10%" }}>
            {productData && (
              <div className="container-fluid">
                <div className="row row-color-1">
                  <div className="col-md-12">
                    <Grid container spacing={1}>
                      <Grid item xs={12} sm={6} md={4} lg={3}>
                        <Box sx={{ height: "100%", m: 1 }}>
                          <Paper elevation={10}>
                            <Stack direction="column">
                              <Stack
                                direction="row"
                                columnGap={2}
                                sx={{ m: 1, overflowWrap: "anywhere" }}
                              >
                                <NewReleasesOutlined />
                                <Typography variant="body1">Stanje</Typography>
                              </Stack>
                              <Divider />
                              <Typography
                                variant="body1"
                                sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                              >
                                {"Nov"}
                              </Typography>
                            </Stack>
                          </Paper>
                        </Box>
                      </Grid>
                      <Grid item xs={12} sm={6} md={4} lg={3}>
                        <Box sx={{ height: "100%", m: 1 }}>
                          <Paper elevation={10}>
                            <Stack direction="column">
                              <Stack
                                direction="row"
                                columnGap={2}
                                sx={{ m: 1, overflowWrap: "anywhere" }}
                              >
                                <LocationOnOutlined />
                                <Typography variant="body1">
                                  Lokacija
                                </Typography>
                              </Stack>
                              <Divider />
                              <Typography
                                variant="body1"
                                sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                              >
                                {productAccount?.location}
                              </Typography>
                            </Stack>
                          </Paper>
                        </Box>
                      </Grid>
                      <Grid item xs={12} sm={6} md={4} lg={3}>
                        <Box sx={{ height: "100%", m: 1 }}>
                          <Paper elevation={10}>
                            <Stack direction="column">
                              <Stack
                                direction="row"
                                columnGap={2}
                                sx={{ m: 1, overflowWrap: "anywhere" }}
                              >
                                <SellOutlined />
                                <Typography variant="body1">Cijena</Typography>
                              </Stack>
                              <Divider />
                              <Typography
                                variant="body1"
                                sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                              >
                                {new Intl.NumberFormat("ba", {
                                  style: "currency",
                                  currency: "BAM",
                                }).format(productData?.price)}
                              </Typography>
                            </Stack>
                          </Paper>
                        </Box>
                      </Grid>
                      <Grid item xs={12} sm={6} md={4} lg={3}>
                        <Box sx={{ height: "100%", m: 1 }}>
                          <Paper elevation={10}>
                            <Stack direction="column">
                              <Stack
                                direction="row"
                                columnGap={2}
                                sx={{ m: 1, overflowWrap: "break-word" }}
                              >
                                <CalendarMonthOutlined />
                                <Typography variant="body1">Datum</Typography>
                              </Stack>
                              <Divider />
                              <Typography
                                variant="body1"
                                sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                              >
                                {moment(productData?.createdTime).format(
                                  "DD. MM. yyyy."
                                )}
                              </Typography>
                            </Stack>
                          </Paper>
                        </Box>
                      </Grid>
                    </Grid>
                  </div>
                </div>
                <div className="row row-color-2">
                  <div className="col-md-12">
                    <Grid
                      container
                      spacing={1}
                      style={{ margin: "5%", width: "100%" }}
                    >
                      <Grid item xs={12} sm={6} md={4} lg={3}>
                        {isAuthenticated && (
                          <div className="d-flex align-items-center">
                            <button
                              type="submit"
                              className="btn btn-main w-100 login-submit"
                              onClick={() => handleOnBuy()}
                            >
                              <Typography variant="body1">Kupi</Typography>
                            </button>
                          </div>
                        )}
                      </Grid>
                    </Grid>
                  </div>
                </div>
                {/* <ul>
                    {attributeValues.map((attribute, index) => (
                      <li key={index}>
                        <strong>{attribute.attributeName}:</strong>{" "}
                        <Tag bordered={true} color="volcano">
                          {attribute.value}
                        </Tag>
                      </li>
                    ))}
                  </ul> */}
              </div>
            )}{" "}
            {/* Conditional rendering */}
          </div>
        </div>
        <div className="row" style={{ width: "100%", marginBottom: "2%" }}>
          <Grid item xs={12} sm={12} md={12} lg={12} sx={{ textAlign: "left" }}>
            <Typography variant="h6">Dodatni podaci</Typography>
            <Divider variant="fullWidth" />
          </Grid>
          <Grid container spacing={1}>
            {attributes.map((attribute) => (
              <Grid
                key={attribute.attributeName}
                item
                xs={12}
                sm={6}
                md={4}
                lg={3}
              >
                <Box sx={{ height: "100%", mt: 2 }}>
                  <Paper elevation={10}>
                    <Stack direction="column">
                      <Typography
                        variant="body1"
                        sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                      >
                        {attribute.attributeName}
                      </Typography>
                      <Divider />
                      <Typography
                        variant="body1"
                        sx={{ m: 1, ml: 2, overflowWrap: "break-word" }}
                      >
                        {attribute.value}
                      </Typography>
                    </Stack>
                  </Paper>
                </Box>
              </Grid>
            ))}
            <Grid item xs={12} sm={12} md={12} lg={12} sx={{ mt: 4 }}>
              <Typography variant="h6">Detaljan opis</Typography>
              <Divider variant="fullWidth" />
            </Grid>
            <Grid item xs={12} sm={12} md={12} lg={12}>
              <Box sx={{ height: "100%", width: "100%", mt: 2 }}>
                <Paper elevation={10}>
                  <Typography
                    variant="body1"
                    sx={{ p: 3, textAlign: "justify", whiteSpace: "pre-wrap" }}
                  >
                    {productData?.description}
                  </Typography>
                </Paper>
              </Box>
            </Grid>
            <Grid item xs={12} sm={12} md={12} lg={12} sx={{ mt: 4 }}>
              <Typography variant="h6">Podaci o prodavcu</Typography>
              <Divider variant="fullWidth" />
            </Grid>
            <Grid item xs={12} sm={12} md={12} lg={12}>
              <Box sx={{ height: "100%", mt: 2 }}>
                <Paper elevation={10}>
                  <Stack sx={{ p: 3 }} rowGap={1}>
                    <Stack direction="row" columnGap={2}>
                      {productData && (
                        <Avatar
                          style={{
                            backgroundColor: "#7265e6",
                            verticalAlign: "middle",
                            borderRadius: "50%",
                          }}
                          size="large"
                          gap={1}
                        >
                          {/* {comment.username.charAt(0).toUpperCase()} */}S
                        </Avatar>
                      )}

                      <Typography sx={{ mt: 1 }} variant="body1">
                        <a href={`/users/${productAccount?.id}`}>
                          {productAccount?.username}
                        </a>
                      </Typography>
                    </Stack>
                  </Stack>
                </Paper>
              </Box>
            </Grid>
          </Grid>
        </div>
      </div>
    );
  };

  const getContentOfPCommentTab = () => {
    return (
      <div>
        <Comments productId={id} />
      </div>
    );
  };

  const tabItems = [
    {
      key: "1",
      label: <Typography variant="body1">Podaci o proizvodu</Typography>,
      children: getContentOfProductTab(),
    },
    {
      key: "2",
      label: <Typography variant="body1">Komentari i pitanja</Typography>,
      children: getContentOfPCommentTab(),
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
