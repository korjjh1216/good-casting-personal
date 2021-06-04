import React from 'react';
import { Nav, Tab } from 'react-bootstrap';
import { Link } from 'gatsby';
import PageWrapper from '../components/PageWrapper';
import ProfileSidebar from '../components/ProfileSidebar';

const CandidateProfile = () => {
    return (
        <>
            <PageWrapper headerConfig={{ button: 'profile' }}>
                <div className="bg-default-2 pt-22 pt-lg-25 pb-13 pb-xxl-32">
                    <div className="container">
                        <div className="col-12 col-xxl-8 col-lg-8 col-md-7 order-2 order-xl-1">
                            <div className="row justify-content-center">
                                <div className="col-12 dark-mode-texts">
                                    <div className="mb-9">
                                        <Link to="/#" className="d-flex align-items-center ml-4">
                                            {' '}
                                            <i className="icon icon-small-left bg-white circle-40 mr-5 font-size-7 text-black font-weight-bold shadow-8"></i>
                                            <span className="text-uppercase font-size-3 font-weight-bold text-gray">Back</span>
                                        </Link>
                                    </div>
                                </div>
                            </div>
                            <Tab.Container id="left-tabs-example" defaultActiveKey="one">
                                <div className="bg-white rounded-4 shadow-9">
                                    {/* <!-- Tab Section Start --> */}
                                    <Nav className="nav border-bottom border-mercury pl-12" role="tablist">
                                        <li className="tab-menu-items nav-item pr-12">
                                            <Nav.Link eventKey="one" className="text-uppercase font-size-3 font-weight-bold text-default-color py-3 px-0">
                                                Overview
                                            </Nav.Link>
                                        </li>
                                        <li className="tab-menu-items nav-item pr-12">
                                            <Nav.Link eventKey="two" className="text-uppercase font-size-3 font-weight-bold text-default-color py-3 px-0">
                                                Contact
                                            </Nav.Link>
                                        </li>
                                    </Nav>
                                    {/* <!-- Tab Content --> */}
                                    <Tab.Content>
                                        <Tab.Pane eventKey="one">
                                            {/* <!-- Excerpt Start --> */}
                                            <ProfileSidebar />
                                            {/* <!-- Card Section End --> */}
                                        </Tab.Pane>
                                        <Tab.Pane eventKey="two">
                                            {/* <!-- Excerpt Start --> */}
                                            <div className="pr-xl-11 p-5 pl-xs-12 pt-9 pb-11">
                                                <form action="/">
                                                    <div className="row">
                                                        <div className="col-12 mb-7">
                                                            <label htmlFor="name3" className="font-size-4 font-weight-semibold text-black-2 mb-5 line-height-reset">
                                                                Your Name
                                                            </label>
                                                            <input id="name3" type="text" className="form-control" placeholder="Jhon Doe" />
                                                        </div>
                                                        <div className="col-lg-6 mb-7">
                                                            <label htmlFor="email3" className="font-size-4 font-weight-semibold text-black-2 mb-5 line-height-reset">
                                                                E-mail
                                                            </label>
                                                            <input id="email3" type="email" className="form-control" placeholder="example@gmail.com" />
                                                        </div>
                                                        <div className="col-lg-6 mb-7">
                                                            <label htmlFor="subject3" className="font-size-4 font-weight-semibold text-black-2 mb-5 line-height-reset">
                                                                Subject
                                                            </label>
                                                            <input id="subject3" type="text" className="form-control" placeholder="Special contract" />
                                                        </div>
                                                        <div className="col-lg-12 mb-7">
                                                            <label htmlFor="message3" className="font-size-4 font-weight-semibold text-black-2 mb-5 line-height-reset">
                                                                Message
                                                            </label>
                                                            <textarea name="message" id="message3" placeholder="Type your message" className="form-control h-px-144"></textarea>
                                                        </div>
                                                        <div className="col-lg-12 pt-4">
                                                            <button className="btn btn-primary text-uppercase w-100 h-px-48">Send Now</button>
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
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};
export default CandidateProfile;
