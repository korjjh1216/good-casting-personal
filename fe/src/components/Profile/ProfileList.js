import React from 'react';
import { Link } from 'gatsby';

import imgB1 from '../../assets/image/l1/png/feature-brand-1.png';

const ProfileList = () => {
    return (
        <>
            <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                <div className="d-block mb-7">
                    <Link to="/#">
                        <img src={imgB1} alt="" />
                    </Link>
                </div>
                <Link to="/#" className="font-size-3 d-block mb-0 text-gray">
                    Google INC
                </Link>
                <h2 className="mt-n4">
                    <Link
                        to="/#"
                        className="font-size-7 text-black-2 font-weight-bold mb-4"
                    >
                        배우이름
                    </Link>
                </h2>
                <div className="card-btn-group">
                    <Link
                        to="/profile-detail"
                        className="btn btn-green text-uppercase btn-medium rounded-3"
                    >
                        프로필보기
                    </Link>
                </div>
            </div>
        </>
    );
};

export default ProfileList;
