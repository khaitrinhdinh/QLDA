import { useSelector } from "react-redux";
import { Navigate, useRoutes } from "react-router-dom";
import Layout from "../layout";
import NotFound from "../pages/NotFound";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Home from "../pages/Home";
import DetailCar from "../pages/DetailCar";
import ChatCar from "../components/ChatCar";
import CartPage from "../pages/Cart"; 
import SellCar from "../pages/Sell";

const Routers = () => {
    const userData = useSelector(state => state.accountReducer);
    // const userData = true;
    const routes = useRoutes([
        {
            path: '/',
            element: <Layout />,
            children: [
                {
                    index: true,
                    element: userData? <Navigate to="/home" replace /> : <Navigate to={"/login"}/>
                },
                {
                    path: '/detail/:id',
                    element: <DetailCar/>
                },
                {
                    path: '/chat-car',
                    element: <ChatCar/>
                },
                {
                    path: '/login',
                    element: <Login/>
                },
                {
                    path: '/home',
                    element: <Home/>
                },
                {
                    path: '/register',
                    element: <Register/>
                },
                {
                    path: '/my-cart',
                    element: <CartPage/>
                },
                {
                    path: '/sell',
                    element: <SellCar/>
                },
                {
                    path: '*',
                    element: <NotFound/>
                }
            ]
        },
    ])
    return routes;
}

export default Routers;