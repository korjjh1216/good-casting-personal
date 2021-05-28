import React from 'react';
import { Tab } from 'react-bootstrap';
import { Link } from 'gatsby';
import PageWrapper from '../components/PageWrapper';

import imgF1 from '../assets/image/l2/png/featured-job-logo-1.png';

const hireRegister = () => {
    return (
        <>
            <PageWrapper headerConfig={{ button: 'profile' }}>
                <div className="bg-default-2 pt-16 pt-lg-22 pb-lg-27">
                    <div className="container">
                        <div className="row justify-content-center">
                            <div className="col-12 mt-13 dark-mode-texts">
                                <div className="mb-9">
                                    <Link
                                        to="/#"
                                        className="d-flex align-items-center ml-4"
                                    >
                                        <i className="icon icon-small-left bg-white circle-40 mr-5 font-size-7 text-black font-weight-bold shadow-8"></i>
                                        <span className="text-uppercase font-size-3 font-weight-bold text-gray">
                                            Back
                                        </span>
                                    </Link>
                                </div>
                            </div>
                        </div>
                        <div className="row ">
                            <div className="col-12 col-xl-9 col-lg-8">
                                <div className="bg-white rounded-4 pt-11 shadow-9">
                                    <div className="d-xs-flex align-items-center pl-xs-12 mb-8 text-center text-xs-left">
                                        <Link
                                            to="/#"
                                            className="mr-xs-7 mb-5 mb-xs-0"
                                        >
                                            <img
                                                className="square-72 rounded-6"
                                                src={imgF1}
                                                alt=""
                                            />
                                        </Link>
                                        <div className="">
                                            <h2 className="mt-xs-n5">
                                                <Link
                                                    to="/#"
                                                    className="font-size-6 text-black-2 font-weight-semibold"
                                                >
                                                    Airbnb INC.
                                                </Link>
                                            </h2>
                                            <input
                                                type="text"
                                                className="form-control h-px-48"
                                                id="namedash"
                                            />
                                        </div>
                                    </div>
                                    <hr />
                                    <Tab.Container
                                        id="left-tabs-example"
                                        defaultActiveKey="jobs"
                                    >
                                        <Tab.Content className="pl-12 pt-10 pb-7 pr-12 pr-xxl-24">
                                            <Tab.Pane eventKey="jobs">
                                                {/* <!-- Middle Body Start --> */}
                                                <fieldset>
                                                    <div className="row mb-xl-1 mb-9">
                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label
                                                                    htmlFor="namedash"
                                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                >
                                                                    촬영 날짜
                                                                </label>
                                                                <input
                                                                    type="text"
                                                                    className="form-control h-px-48"
                                                                    id="namedash"
                                                                />
                                                            </div>
                                                        </div>
                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label
                                                                    htmlFor="namedash"
                                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                >
                                                                    마감날짜
                                                                </label>
                                                                <input
                                                                    type="text"
                                                                    className="form-control h-px-48"
                                                                    id="namedash"
                                                                />
                                                            </div>
                                                        </div>
                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label
                                                                    htmlFor="namedash"
                                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                >
                                                                    작품제목
                                                                </label>
                                                                <input
                                                                    type="text"
                                                                    className="form-control h-px-48"
                                                                    id="namedash"
                                                                />
                                                            </div>
                                                        </div>
                                                        <div className="col-lg-6">
                                                            <div className="form-group">
                                                                <label
                                                                    htmlFor="select2"
                                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                >
                                                                    모집인원
                                                                </label>
                                                                <input
                                                                    type="text"
                                                                    className="form-control h-px-48"
                                                                    id="namedash"
                                                                />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div className="row mb-8">
                                                        <div className="col-lg-6 mb-xl-0 mb-7">
                                                            <div className="form-group position-relative">
                                                                <label
                                                                    htmlFor="select3"
                                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                >
                                                                    모집역할
                                                                </label>
                                                                <input
                                                                    type="text"
                                                                    className="form-control h-px-48"
                                                                    id="namedash"
                                                                />
                                                            </div>
                                                        </div>
                                                        <div className="col-lg-6">
                                                            <div className="form-group position-relative">
                                                                <label
                                                                    htmlFor="address"
                                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                >
                                                                    급여
                                                                </label>
                                                                <input
                                                                    type="text"
                                                                    className="form-control h-px-48"
                                                                    id="namedash"
                                                                />
                                                                <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div className="row">
                                                        <div className="col-md-12">
                                                            <div className="form-group">
                                                                <label
                                                                    htmlFor="aboutTextarea"
                                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                >
                                                                    작품소개
                                                                </label>
                                                                <textarea
                                                                    name="textarea"
                                                                    id="aboutTextarea"
                                                                    cols="30"
                                                                    rows="7"
                                                                    className="border border-mercury text-gray w-100 pt-4 pl-6"
                                                                    placeholder="Describe about the company what make it unique"
                                                                ></textarea>
                                                            </div>
                                                        </div>
                                                        <div className="col-md-12">
                                                            <input
                                                                type="button"
                                                                value="등록하기"
                                                                className="btn btn-green btn-h-60 text-white min-width-px-210 rounded-5 text-uppercase"
                                                            />
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </Tab.Pane>
                                        </Tab.Content>
                                    </Tab.Container>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};

export default hireRegister;
