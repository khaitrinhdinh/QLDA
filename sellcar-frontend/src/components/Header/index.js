import { useNavigate } from 'react-router-dom';
import Logo from '../../assets/Logo.png';
import User from "../../assets/User.svg";
import './Header.css';
import { useDispatch, useSelector } from 'react-redux';
import { logoutAction } from '../../redux/action';

const Header = () => {
    const userData = useSelector(state => state.accountReducer);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    // Danh sách tab mặc định
    let tabList = [
        { path: "/my-cart", name: "Giỏ hàng của tôi" },
        { path: "/used-cars", name: "Quản lý xe" },
    ];

    if (userData?.role === "Dealer") {
        tabList.push({ path: "/sell", name: "Bán Xe" });
    }

    return (
        <div className="container--header">
            <img src={Logo} alt="Logo" onClick={() => navigate("/home")} />

            <div className='tab-list'>
                {tabList.map(it => 
                    <div key={it.path} className='tab' onClick={() => navigate(it.path)}>
                        {it.name}
                    </div>
                )}
            </div>

            <div className='user-section'>
                {userData ? (
                    <>
                        <div className='btn-sign-in' onClick={() => navigate("#")}>
                            <img src={User} alt="User" />
                            <p>Chào {userData.fullName}</p>
                        </div>
                        <div className='btn-sign-in' onClick={() => dispatch(logoutAction())}>
                            <p>Đăng xuất</p>
                        </div>
                    </>
                ) : (
                    <div className='btn-sign-in' onClick={() => navigate("/login")}>
                        <img src={User} alt="User" />
                        <p>Đăng Nhập</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Header;