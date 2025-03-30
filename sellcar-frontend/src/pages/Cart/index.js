import { Search } from "@mui/icons-material";
import {
  Box,
  Button,
  Grid,
  InputAdornment,
  TextField,
} from "@mui/material";
import { useDispatch, useSelector } from 'react-redux';
import React, { useEffect, useState } from "react";
import api from "../../api";
import RecommendCar from "../../components/RecommendCar";
import "./CartPage.css";

const CartPage = () => {
  const userData = useSelector(state => state.accountReducer);
  const [search, setSearch] = useState("");
  const [cartCars, setCartCars] = useState([]);

  async function fetchCartCars() {
    try {
      const response = await api.get(`car/cart/${userData.id}`);
      console.log(response.data);
      setCartCars(response.data);
    } catch (e) {
      console.error(e);
    }
  }

  useEffect(() => {
    fetchCartCars();
  }, []);

  return (
    <>
      <section className="cart-page">
        <div className="template">
          <div className="super--title">
            <h1>Giỏ hàng của bạn</h1>
            <p>Kiểm tra và hoàn tất đơn hàng của bạn</p>
          </div>
          <div className="search--cart">
            <Box sx={{ marginTop: "24px", paddingTop: "20px", paddingBottom: "20px" }}>
              <Grid container gap={1.5}>
                <Grid item xs={8}>
                  <TextField
                    fullWidth
                    type="search"
                    placeholder="Tìm kiếm xe trong giỏ"
                    variant="outlined"
                    onChange={(e) => setSearch(e.target.value)}
                    sx={{ background: "#f0f0f0" }}
                    InputProps={{
                      startAdornment: (
                        <InputAdornment position="start">
                          <Search sx={{ fontSize: 24 }} />
                        </InputAdornment>
                      ),
                    }}
                  />
                </Grid>
                <Grid item xs={3}>
                  <Button fullWidth variant="contained" onClick={fetchCartCars}>
                    Làm mới
                  </Button>
                </Grid>
              </Grid>
            </Box>
          </div>
          {cartCars.length > 0 ? (
            <RecommendCar listCar={cartCars.filter(car => car.title.includes(search))} />
          ) : (
            <p className="empty-cart-message">Không có xe nào trong giỏ</p>
          )}
        </div>
      </section>
    </>
  );
};

export default CartPage;
