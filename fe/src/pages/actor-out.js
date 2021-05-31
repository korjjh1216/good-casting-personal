import React from 'react';
import { Link } from 'gatsby';
import PageWrapper from '../components/PageWrapper';

const ActorOut = () => {
    return (
        <>
            <PageWrapper>
                <div className="404-page bg-default min-h-100vh flex-all-center pt-lg-15 pt-xxl-17 pt-27 pb-lg-0 pb-18">
                    <div className="container">
                        <div className="row justify-content-center">
                            <div className="col-lg-7 px-lg-9">
                                <div
                                    className="card-404 text-center"
                                    data-aos="zoom-in"
                                    data-aos-duration="1000"
                                >
                                    <div className="404-texts pt-14">
                                        <h3 className="card-title font-size-9 font-weight-bold">
                                            회원탈퇴가 완료되었습니다.
                                        </h3>
                                        <p className="card-text font-size-4 px-xxl-28 px-xs-10 px-sm-13 px-lg-13 px-md-28 px-xl-22 px-0 mb-11">
                                            굿캐스팅을 이용해주시고 사랑해주셔서
                                            감사합니다. <br /> 더욱더 노력하고
                                            발전하는 굿캐스팅이 되곘습니다.
                                        </p>
                                        <Link
                                            to="/"
                                            s
                                            className="btn btn-green btn-h-60 text-white rounded-5 w-180 m-auto text-uppercase"
                                        >
                                            확인
                                        </Link>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};

export default ActorOut;
