import React from 'react';
import { Link } from 'gatsby';

import imgF1 from '../../assets/image/l2/png/featured-job-logo-1.png';

import imgF from '../../assets/image/svg/icon-fire-rounded.svg';
import iconL from '../../assets/image/svg/icon-loaction-pin-black.svg';
import iconS from '../../assets/image/svg/icon-suitecase.svg';
import iconC from '../../assets/image/svg/icon-clock.svg';

const HireList = () => {
    return (
        <>
            <div className="pt-9 px-xl-9 px-lg-7 px-7 pb-7 light-mode-texts bg-white rounded hover-shadow-3 ">
                <div className="row">
                    <div className="col-md-6">
                        <div className="media align-items-center">
                            <div className="square-72 d-block mr-8">
                                <img src={imgF1} alt="" />
                            </div>
                            <div>
                                <h3 className="mb-0">
                                    <Link
                                        to="/#"
                                        className="font-size-6 heading-default-color"
                                    >
                                        Product Designer
                                    </Link>
                                </h3>
                                <Link
                                    to="/#"
                                    className="font-size-3 text-default-color line-height-2"
                                >
                                    AirBnb
                                </Link>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-6 text-right pt-7 pt-md-5">
                        <div className="media justify-content-md-end">
                            <div className="image mr-5 mt-2">
                                <img src={imgF} alt="" />
                            </div>
                            <p className="font-weight-bold font-size-7 text-hit-gray mb-0">
                                <span className="text-black-2">80-90K</span> PLN
                            </p>
                        </div>
                    </div>
                </div>
                <div className="row pt-8">
                    <div className="col-md-7">
                        <ul className="d-flex list-unstyled mr-n3 flex-wrap">
                            <li>
                                <Link
                                    to="/#"
                                    className="bg-regent-opacity-15 min-width-px-96 mr-3 text-center rounded-3 px-6 py-1 font-size-3 text-black-2 mt-2"
                                >
                                    Agile
                                </Link>
                            </li>
                            <li>
                                <Link
                                    to="/#"
                                    className="bg-regent-opacity-15 min-width-px-96 mr-3 text-center rounded-3 px-6 py-1 font-size-3 text-black-2 mt-2"
                                >
                                    Wireframing
                                </Link>
                            </li>
                            <li>
                                <Link
                                    to="/#"
                                    className="bg-regent-opacity-15 min-width-px-96 mr-3 text-center rounded-3 px-6 py-1 font-size-3 text-black-2 mt-2"
                                >
                                    Prototyping
                                </Link>
                            </li>
                        </ul>
                    </div>
                    <div className="col-md-5">
                        <ul className="d-flex list-unstyled mr-n3 flex-wrap mr-n8 justify-content-md-end">
                            <li className="mt-2 mr-8 font-size-small text-black-2 d-flex">
                                <span
                                    className="mr-4"
                                    css={`
                                        margin-top: -2px;
                                    `}
                                >
                                    <img src={iconL} alt="" />
                                </span>
                                <span className="font-weight-semibold">
                                    Berlyn, UK
                                </span>
                            </li>
                            <li className="mt-2 mr-8 font-size-small text-black-2 d-flex">
                                <span
                                    className="mr-4"
                                    css={`
                                        margin-top: -2px;
                                    `}
                                >
                                    <img src={iconS} alt="" />
                                </span>
                                <span className="font-weight-semibold">
                                    Full-time
                                </span>
                            </li>
                            <li className="mt-2 mr-8 font-size-small text-black-2 d-flex">
                                <span
                                    className="mr-4"
                                    css={`
                                        margin-top: -2px;
                                    `}
                                >
                                    <img src={iconC} alt="" />
                                </span>
                                <span className="font-weight-semibold">
                                    9d ago
                                </span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </>
    );
};

export default HireList;
