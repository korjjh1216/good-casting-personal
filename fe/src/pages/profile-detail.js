import React from 'react';
import { Nav, Tab } from 'react-bootstrap';
import { Link } from 'gatsby';
import PageWrapper from '../components/PageWrapper';
import ProfileSidebar from '../components/ProfileSidebar';

import imgB1 from '../assets/image/l2/png/featured-job-logo-1.png';
import imgB2 from '../assets/image/l1/png/feature-brand-1.png';
import imgB3 from '../assets/image/svg/harvard.svg';
import imgB4 from '../assets/image/svg/mit.svg';

import imgL from '../assets/image/svg/icon-loaction-pin-black.svg';

const CandidateProfile = () => {
    return (
        <>
            <PageWrapper headerConfig={{ button: 'profile' }}>
                <div className="bg-default-2 pt-22 pt-lg-25 pb-13 pb-xxl-32">
                    <div className="container">
                        {/* <!-- back Button --> */}
                        {/* <div className="row justify-content-center">
                            <div className="col-12 dark-mode-texts">
                                <div className="mb-9">
                                    <Link
                                        to="/#"
                                        className="d-flex align-items-center ml-4"
                                    >
                                        {' '}
                                        <i className="icon icon-small-left bg-white circle-40 mr-5 font-size-7 text-black font-weight-bold shadow-8"></i>
                                        <span className="text-uppercase font-size-3 font-weight-bold text-gray">
                                            Back
                                        </span>
                                    </Link>
                                </div>
                            </div>
                        </div> */}
                        {/* <!-- back Button End --> */}
                        <div className="row">
                            {/* <!-- Left Sidebar Start --> */}
                            <div className="col-12 col-xxl-3 col-lg-4 col-md-5 mb-11 mb-lg-0">
                                <ProfileSidebar />
                            </div>
                            {/* <!-- Left Sidebar End --> */}
                            {/* <!-- Middle Content --> */}
                            <div className="col-12 col-xxl-6 col-lg-8 col-md-7 order-2 order-xl-1">
                                <Tab.Container
                                    id="left-tabs-example"
                                    defaultActiveKey="one"
                                >
                                    <div className="bg-white rounded-4 shadow-9">
                                        {/* <!-- Tab Section Start --> */}
                                        <Nav
                                            className="nav border-bottom border-mercury pl-12"
                                            role="tablist"
                                        >
                                            <li className="tab-menu-items nav-item pr-12">
                                                <Nav.Link
                                                    eventKey="one"
                                                    className="text-uppercase font-size-3 font-weight-bold text-default-color py-3 px-0"
                                                >
                                                    Overview
                                                </Nav.Link>
                                            </li>
                                            <li className="tab-menu-items nav-item pr-12">
                                                <Nav.Link
                                                    eventKey="two"
                                                    className="text-uppercase font-size-3 font-weight-bold text-default-color py-3 px-0"
                                                >
                                                    Contact
                                                </Nav.Link>
                                            </li>
                                        </Nav>
                                        {/* <!-- Tab Content --> */}
                                        <Tab.Content>
                                            <Tab.Pane eventKey="one">
                                                {/* <!-- Excerpt Start --> */}
                                                <div className="pr-xl-0 pr-xxl-14 p-5 px-xs-12 pt-7 pb-5">
                                                    <h4 className="font-size-6 mb-7 mt-5 text-black-2 font-weight-semibold">
                                                        About
                                                    </h4>
                                                    <p className="font-size-4 mb-8">
                                                        A talented professional
                                                        with an academic
                                                        background in IT and
                                                        proven commercial
                                                        development experience
                                                        as C++ developer since
                                                        1999. Has a sound
                                                        knowledge of the
                                                        software development
                                                        life cycle. Was involved
                                                        in more than 140
                                                        software development
                                                        outsourcing projects.
                                                    </p>
                                                    <p className="font-size-4 mb-8">
                                                        Programming Languages:
                                                        C/C++, .NET C++, Python,
                                                        Bash, Shell, PERL,
                                                        Regular expressions,
                                                        Python, Active-script.
                                                    </p>
                                                </div>
                                                {/* <!-- Excerpt End --> */}
                                                {/* <!-- Skills --> */}
                                                <div className="border-top pr-xl-0 pr-xxl-14 p-5 pl-xs-12 pt-7 pb-5">
                                                    <h4 className="font-size-6 mb-7 mt-5 text-black-2 font-weight-semibold">
                                                        Skills
                                                    </h4>
                                                    <ul className="list-unstyled d-flex align-items-center flex-wrap">
                                                        <li>
                                                            <Link
                                                                to="/#"
                                                                className="bg-polar text-black-2  mr-6 px-7 mt-2 mb-2 font-size-3 rounded-3 min-height-32 d-flex align-items-center"
                                                            >
                                                                Agile
                                                            </Link>
                                                        </li>
                                                        <li>
                                                            <Link
                                                                to="/#"
                                                                className="bg-polar text-black-2  mr-6 px-7 mt-2 mb-2 font-size-3 rounded-3 min-height-32 d-flex align-items-center"
                                                            >
                                                                Wireframing
                                                            </Link>
                                                        </li>
                                                        <li>
                                                            <Link
                                                                to="/#"
                                                                className="bg-polar text-black-2  mr-6 px-7 mt-2 mb-2 font-size-3 rounded-3 min-height-32 d-flex align-items-center"
                                                            >
                                                                Prototyping
                                                            </Link>
                                                        </li>
                                                        <li>
                                                            <Link
                                                                to="/#"
                                                                className="bg-polar text-black-2  mr-6 px-7 mt-2 mb-2 font-size-3 rounded-3 min-height-32 d-flex align-items-center"
                                                            >
                                                                Information
                                                            </Link>
                                                        </li>
                                                        <li>
                                                            <Link
                                                                to="/#"
                                                                className="bg-polar text-black-2  mr-6 px-7 mt-2 mb-2 font-size-3 rounded-3 min-height-32 d-flex align-items-center"
                                                            >
                                                                Waterfall Model
                                                            </Link>
                                                        </li>
                                                        <li>
                                                            <Link
                                                                to="/#"
                                                                className="bg-polar text-black-2  mr-6 px-7 mt-2 mb-2 font-size-3 rounded-3 min-height-32 d-flex align-items-center"
                                                            >
                                                                New Layout
                                                            </Link>
                                                        </li>
                                                        <li>
                                                            <Link
                                                                to="/#"
                                                                className="bg-polar text-black-2  mr-6 px-7 mt-2 mb-2 font-size-3 rounded-3 min-height-32 d-flex align-items-center"
                                                            >
                                                                Browsing
                                                            </Link>
                                                        </li>
                                                    </ul>
                                                </div>
                                                {/* <!-- Skills End --> */}
                                                {/* <!-- Card Section Start --> */}
                                                <div className="border-top p-5 pl-xs-12 pt-7 pb-5">
                                                    <h4 className="font-size-6 mb-7 mt-5 text-black-2 font-weight-semibold">
                                                        Work Exprerience
                                                    </h4>
                                                    {/* <!-- Single Card --> */}
                                                    <div className="w-100">
                                                        <div className="d-flex align-items-center pr-11 mb-9 flex-wrap flex-sm-nowrap">
                                                            <div className="square-72 d-block mr-8 mb-7 mb-sm-0">
                                                                <img
                                                                    src={imgB1}
                                                                    alt=""
                                                                />
                                                            </div>
                                                            <div className="w-100 mt-n2">
                                                                <h3 className="mb-0">
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-6 text-black-2 font-weight-semibold"
                                                                    >
                                                                        Lead
                                                                        Product
                                                                        Designer
                                                                    </Link>
                                                                </h3>
                                                                <Link
                                                                    to="/#"
                                                                    className="font-size-4 text-default-color line-height-2"
                                                                >
                                                                    Airabnb
                                                                </Link>
                                                                <div className="d-flex align-items-center justify-content-md-between flex-wrap">
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-4 text-gray mr-5"
                                                                    >
                                                                        Jun 2017
                                                                        - April
                                                                        2020- 3
                                                                        years
                                                                    </Link>
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-3 text-gray"
                                                                    >
                                                                        <span
                                                                            className="mr-4"
                                                                            css={`
                                                                                margin-top: -2px;
                                                                            `}
                                                                        >
                                                                            <img
                                                                                src={
                                                                                    imgL
                                                                                }
                                                                                alt=""
                                                                            />
                                                                        </span>
                                                                        New
                                                                        York,
                                                                        USA
                                                                    </Link>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    {/* <!-- Single Card End --> */}
                                                    {/* <!-- Single Card --> */}
                                                    <div className="w-100">
                                                        <div className="d-flex align-items-center pr-11 mb-9 flex-wrap flex-sm-nowrap">
                                                            <div className="square-72 d-block mr-8 mb-7 mb-sm-0">
                                                                <img
                                                                    src={imgB2}
                                                                    alt=""
                                                                />
                                                            </div>
                                                            <div className="w-100 mt-n2">
                                                                <h3 className="mb-0">
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-6 text-black-2 font-weight-semibold"
                                                                    >
                                                                        Senior
                                                                        UI/UX
                                                                        Designer
                                                                    </Link>
                                                                </h3>
                                                                <Link
                                                                    to="/#"
                                                                    className="font-size-4 text-default-color line-height-2"
                                                                >
                                                                    Google Inc
                                                                </Link>
                                                                <div className="d-flex align-items-center justify-content-md-between flex-wrap">
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-3 text-gray mr-5"
                                                                    >
                                                                        Jun 2017
                                                                        - April
                                                                        2020- 3
                                                                        years
                                                                    </Link>
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-3 text-gray"
                                                                    >
                                                                        <span
                                                                            className="mr-4"
                                                                            css={`
                                                                                margin-top: -2px;
                                                                            `}
                                                                        >
                                                                            <img
                                                                                src={
                                                                                    imgL
                                                                                }
                                                                                alt=""
                                                                            />
                                                                        </span>
                                                                        New
                                                                        York,
                                                                        USA
                                                                    </Link>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    {/* <!-- Single Card End --> */}
                                                </div>
                                                {/* <!-- Card Section End --> */}
                                                {/* <!-- Card Section Start --> */}
                                                <div className="border-top p-5 pl-xs-12 pt-7 pb-5">
                                                    <h4 className="font-size-6 mb-7 mt-5 text-black-2 font-weight-semibold">
                                                        Education
                                                    </h4>
                                                    {/* <!-- Single Card --> */}
                                                    <div className="w-100">
                                                        <div className="d-flex align-items-center pr-11 mb-9 flex-wrap flex-sm-nowrap">
                                                            <div className="square-72 d-block mr-8 mb-7 mb-sm-0">
                                                                <img
                                                                    src={imgB3}
                                                                    alt=""
                                                                />
                                                            </div>
                                                            <div className="w-100 mt-n2">
                                                                <h3 className="mb-0">
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-6 text-black-2"
                                                                    >
                                                                        Masters
                                                                        in Art
                                                                        Design
                                                                    </Link>
                                                                </h3>
                                                                <Link
                                                                    to="/#"
                                                                    className="font-size-4 text-default-color line-height-2"
                                                                >
                                                                    Harvard
                                                                    University
                                                                </Link>
                                                                <div className="d-flex align-items-center justify-content-md-between flex-wrap">
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-3 text-gray mr-5"
                                                                    >
                                                                        Jun 2017
                                                                        - April
                                                                        2020- 3
                                                                        years
                                                                    </Link>
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-3 text-gray"
                                                                    >
                                                                        <span
                                                                            className="mr-4"
                                                                            css={`
                                                                                margin-top: -2px;
                                                                            `}
                                                                        >
                                                                            <img
                                                                                src={
                                                                                    imgL
                                                                                }
                                                                                alt=""
                                                                            />
                                                                        </span>
                                                                        Brylin,
                                                                        USA
                                                                    </Link>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    {/* <!-- Single Card End --> */}
                                                    {/* <!-- Single Card --> */}
                                                    <div className="w-100">
                                                        <div className="d-flex align-items-center pr-11 mb-9 flex-wrap flex-sm-nowrap">
                                                            <div className="square-72 d-block mr-8 mb-7 mb-sm-0">
                                                                <img
                                                                    src={imgB4}
                                                                    alt=""
                                                                />
                                                            </div>
                                                            <div className="w-100 mt-n2">
                                                                <h3 className="mb-0">
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-6 text-black-2"
                                                                    >
                                                                        Bachelor
                                                                        in
                                                                        Software
                                                                        Engineering{' '}
                                                                    </Link>
                                                                </h3>
                                                                <Link
                                                                    to="/#"
                                                                    className="font-size-4 text-default-color line-height-2"
                                                                >
                                                                    Manipal
                                                                    Institute of
                                                                    Technology
                                                                </Link>
                                                                <div className="d-flex align-items-center justify-content-md-between flex-wrap">
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-3 text-gray mr-5"
                                                                    >
                                                                        Fed 2012
                                                                        - April
                                                                        2016 - 4
                                                                        years
                                                                    </Link>
                                                                    <Link
                                                                        to="/#"
                                                                        className="font-size-3 text-gray"
                                                                    >
                                                                        <span
                                                                            className="mr-4"
                                                                            css={`
                                                                                margin-top: -2px;
                                                                            `}
                                                                        >
                                                                            <img
                                                                                src={
                                                                                    imgL
                                                                                }
                                                                                alt=""
                                                                            />
                                                                        </span>
                                                                        New
                                                                        York,
                                                                        USA
                                                                    </Link>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    {/* <!-- Single Card End --> */}
                                                </div>
                                                {/* <!-- Card Section End --> */}
                                            </Tab.Pane>
                                            <Tab.Pane eventKey="two">
                                                {/* <!-- Excerpt Start --> */}
                                                <div className="pr-xl-11 p-5 pl-xs-12 pt-9 pb-11">
                                                    <form action="/">
                                                        <div className="row">
                                                            <div className="col-12 mb-7">
                                                                <label
                                                                    htmlFor="name3"
                                                                    className="font-size-4 font-weight-semibold text-black-2 mb-5 line-height-reset"
                                                                >
                                                                    Your Name
                                                                </label>
                                                                <input
                                                                    id="name3"
                                                                    type="text"
                                                                    className="form-control"
                                                                    placeholder="Jhon Doe"
                                                                />
                                                            </div>
                                                            <div className="col-lg-6 mb-7">
                                                                <label
                                                                    htmlFor="email3"
                                                                    className="font-size-4 font-weight-semibold text-black-2 mb-5 line-height-reset"
                                                                >
                                                                    E-mail
                                                                </label>
                                                                <input
                                                                    id="email3"
                                                                    type="email"
                                                                    className="form-control"
                                                                    placeholder="example@gmail.com"
                                                                />
                                                            </div>
                                                            <div className="col-lg-6 mb-7">
                                                                <label
                                                                    htmlFor="subject3"
                                                                    className="font-size-4 font-weight-semibold text-black-2 mb-5 line-height-reset"
                                                                >
                                                                    Subject
                                                                </label>
                                                                <input
                                                                    id="subject3"
                                                                    type="text"
                                                                    className="form-control"
                                                                    placeholder="Special contract"
                                                                />
                                                            </div>
                                                            <div className="col-lg-12 mb-7">
                                                                <label
                                                                    htmlFor="message3"
                                                                    className="font-size-4 font-weight-semibold text-black-2 mb-5 line-height-reset"
                                                                >
                                                                    Message
                                                                </label>
                                                                <textarea
                                                                    name="message"
                                                                    id="message3"
                                                                    placeholder="Type your message"
                                                                    className="form-control h-px-144"
                                                                ></textarea>
                                                            </div>
                                                            <div className="col-lg-12 pt-4">
                                                                <button className="btn btn-primary text-uppercase w-100 h-px-48">
                                                                    Send Now
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                {/* <!-- Excerpt End --> */}
                                            </Tab.Pane>
                                        </Tab.Content>
                                        {/* <!-- Tab Content End --> */}
                                        {/* <!-- Tab Section End --> */}
                                    </div>
                                </Tab.Container>
                            </div>
                            {/* <!-- Middle Content --> */}
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};
export default CandidateProfile;
