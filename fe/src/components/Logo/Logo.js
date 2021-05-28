import React from "react";
import { Link } from "gatsby";

import logo from '../../assets/image/goodcLogo.png';

const Logo = ({ white, height, className = "", ...rest }) => {
  return (
    <Link to="/" className={`d-block ${className}`} {...rest}>
        <img src={logo} alt="" />
    </Link>
  );
};

export default Logo;
