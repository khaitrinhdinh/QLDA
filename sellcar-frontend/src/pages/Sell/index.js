import React, { useEffect, useState } from "react";
import { Box, Button, Grid, Tab, Tabs } from "@mui/material";
import api from "../../api";
import { useDispatch, useSelector } from 'react-redux';
// import CarCard from "../../components/CarCard";
import "./SellCar.css";

const SellCar = () => {
  const [value, setValue] = useState(0);
  const [carList, setCarList] = useState([]);
  const userData = useSelector(state => state.accountReducer);
  

  const fetchCars = async () => {
    try {
      const response = await api.get(`/car/sellcar/${userData.id}`);
        console.log("Response data:", response.data);
      setCarList(response.data);
    } catch (error) {
      console.error("Error fetching cars:", error);
    }
  };

  useEffect(() => {
    fetchCars();
  }, []);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  console.log("carList:", carList, "Type:", typeof carList);
  const filteredCars = carList.filter(car =>
    value === 0 ? true : value === 1 ? !car.sold : car.sold
  );

  return (
    <section className="sellcar-page">
      <div className="template">
        <h1>Danh sách xe đang bán</h1>
        <Tabs value={value} onChange={handleChange} centered>
          <Tab label="Tất cả" />
          <Tab label="Đang bán" />
          <Tab label="Đã bán" />
        </Tabs>
        {/* <Box sx={{ marginTop: "24px" }}>
          <Grid container spacing={2}>
            {filteredCars.length > 0 ? (
              filteredCars.map((car) => (
                <Grid item xs={12} sm={6} md={4} key={car.id}>
                  <CarCard car={car} />
                </Grid>
              ))
            ) : (
              <p>Không có xe nào trong danh mục này.</p>
            )}
          </Grid>
        </Box> */}
      </div>
    </section>
  );
};

export default SellCar;
