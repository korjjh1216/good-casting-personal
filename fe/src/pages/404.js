import React from 'react';
import { Link } from 'gatsby';
import PageWrapper from '../components/PageWrapper';
import imgLogo from '../assets/image/goodcLogo.png';
import imgError from '../assets/image/svg/404.svg';

const Contact = () => {
    return (
        <>
            <PageWrapper>
                <div className="header pt-11 pos-abs-tl w-100">
                    <div className="container">
                        <div className="row">
                            <div className="col-12 text-center">
                                <Link to="/" className="brand-logo">
                                    <img src={imgLogo} alt="" />
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="404-page bg-default min-h-100vh flex-all-center pt-lg-15 pt-xxl-17 pt-27 pb-lg-0 pb-18">
                    <div className="container">
                        <div className="row justify-content-center">
                            <div className="col-lg-7 px-lg-9">
                                {/* <!-- card start --> */}
                                <div
                                    className="card-404 text-center"
                                    data-aos="zoom-in"
                                    data-aos-duration="1000"
                                >
                                    {/* <!-- card image start --> */}
                                    <img
                                        src={imgError}
                                        alt=""
                                        className="w-100 px-9"
                                    />
                                    {/* <!-- card image end --> */}
                                    {/* <!-- card-icon start --> */}
                                    <div className="404-texts pt-14">
                                        <h3 className="card-title font-size-9 font-weight-bold">
                                            Page is not found!
                                        </h3>
                                        {/* <!-- card-texts start --> */}
                                        <p className="card-text font-size-4 px-xxl-28 px-xs-10 px-sm-13 px-lg-13 px-md-28 px-xl-22 px-0 mb-11">
                                            Collaboratively administrate
                                            empowered markets via plug-and-play
                                            networks. Dynamically procrastinate
                                            B2C users after installed base.
                                        </p>
                                        {/* <!-- card-texts end --> */}
                                        <Link
                                            to="/"
                                            className="btn btn-green btn-h-60 text-white rounded-5 w-180 m-auto text-uppercase"
                                        >
                                            Back to home
                                        </Link>
                                    </div>
                                </div>
                                {/* <!-- card end --> */}
                            </div>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};
export default Contact;
