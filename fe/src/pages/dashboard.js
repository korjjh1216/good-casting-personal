import React from 'react'
import CountUp from 'react-countup'
import LazyLoad from 'react-lazyload'
import PageWrapper from '../components/PageWrapper'

import DashboardHireList from '../components/Dashboard/DashboardHireList'
import DashboardApplicants from './dashboard-applicants'

const DashboardMain = () => {
    return (
        <>
            <PageWrapper
                headerConfig={{
                    button: 'profile',
                    isFluid: true,
                    bgClass: 'bg-default',
                    reveal: false,
                }}
            >
                <div className="dashboard-main-container mt-25 mt-lg-31">
                    <div className="container">
                        <div className="row mb-7">
                            <div className="col-xxl-3 col-xl-4 col-lg-6 col-sm-6">
                                <a href="/hire-list" className="media bg-white rounded-4 pl-8 pt-9 pb-9 pr-7 hover-shadow-1 mb-9 shadow-8">
                                    <div className="text-blue bg-blue-opacity-1 circle-56 font-size-6 mr-7">
                                        <i className="fas fa-briefcase"></i>
                                    </div>
                                    <div className="">
                                        <h5 className="font-size-8 font-weight-semibold text-black-2 line-height-reset font-weight-bold mb-1">
                                            <LazyLoad>
                                                <span className="counter">
                                                    <CountUp duration={6} end={5} />
                                                </span>
                                            </LazyLoad>
                                        </h5>
                                        <p className="font-size-4 font-weight-normal text-gray mb-0">업로드 리스트</p>
                                    </div>
                                </a>
                            </div>
                            <div className="col-xxl-3 col-xl-4 col-lg-6 col-sm-6">
                                <a href="/dashboard-applicants" className="media bg-white rounded-4 pl-8 pt-9 pb-9 pr-7 hover-shadow-1 mb-9 shadow-8">
                                    <div className="text-pink bg-pink-opacity-1 circle-56 font-size-6 mr-7">
                                        <i className="fas fa-user"></i>
                                    </div>
                                    <div className="">
                                        <h5 className="font-size-8 font-weight-semibold text-black-2 line-height-reset font-weight-bold mb-1">
                                            <LazyLoad>
                                                <span className="counter">
                                                    <CountUp duration={4} end={256} />
                                                </span>
                                            </LazyLoad>
                                        </h5>
                                        <p className="font-size-4 font-weight-normal text-gray mb-0">지원자 수</p>
                                    </div>
                                </a>
                            </div>
                            <div className="col-xxl-3 col-xl-4 col-lg-6 col-sm-6">
                                <a href="/#" className="media bg-white rounded-4 pl-8 pt-9 pb-9 pr-7 hover-shadow-1 mb-9 shadow-8">
                                    <div className="text-orange bg-orange-opacity-1 circle-56 font-size-6 mr-7">
                                        <i className="fas fa-eye"></i>
                                    </div>
                                    <div className="">
                                        <h5 className="font-size-8 font-weight-semibold text-black-2 line-height-reset font-weight-bold mb-1">
                                            <LazyLoad>
                                                <span className="counter">
                                                    <CountUp duration={4} decimal="." decimals={1} end={16.5} />
                                                </span>
                                                K
                                            </LazyLoad>
                                        </h5>
                                        <p className="font-size-4 font-weight-normal text-gray mb-0">공고 조회수</p>
                                    </div>
                                </a>
                            </div>
                            <div className="col-xxl-3 col-xl-4 col-lg-6 col-sm-6">
                                <a href="/#" className="media bg-white rounded-4 pl-8 pt-9 pb-9 pr-7 hover-shadow-1 mb-9 shadow-8">
                                    <div className="text-egg-blue bg-egg-blue-opacity-1 circle-56 font-size-6 mr-7">
                                        <i className="fas fa-mouse-pointer"></i>
                                    </div>
                                    <div className="">
                                        <h5 className="font-size-8 font-weight-semibold text-black-2 line-height-reset font-weight-bold mb-1">
                                            <LazyLoad>
                                                <span className="counter">
                                                    <CountUp duration={4} decimal="." decimals={1} end={18.6} />
                                                </span>
                                                %
                                            </LazyLoad>
                                        </h5>
                                        <p className="font-size-4 font-weight-normal text-gray mb-0">지원 비율</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <DashboardApplicants />
                <div className="dashboard-main-container mt-25 mt-lg-31">
                    <DashboardHireList />
                </div>
            </PageWrapper>
        </>
    )
}
export default DashboardMain
