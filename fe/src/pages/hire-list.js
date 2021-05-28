import React from 'react';
import { Link } from 'gatsby';
import PageWrapper from '../components/PageWrapper';
import Sidebar from '../components/Sidebar';
import { Select } from '../components/Core';
import HireList from '../components/Hire/HireList';

const defaultCountries = [
    { value: 'sp', label: 'Singapore' },
    { value: 'bd', label: 'Bangladesh' },
    { value: 'usa', label: 'United States of America' },
    { value: 'uae', label: 'United Arab Emirates' },
    { value: 'pk', label: 'Pakistan' },
];

const SearchGrid = () => {
    return (
        <>
            <PageWrapper>
                <div className="bg-default-1 pt-26 pt-lg-28 pb-13 pb-lg-25">
                    <div className="container">
                        <div className="row">
                            <div className="col-12 col-lg-4 col-md-5 col-xs-8">
                                <Sidebar />
                            </div>
                            {/* <!-- Main Body --> */}
                            <div className="col-12 col-xl-8 col-lg-8">
                                {/* <!-- form --> */}
                                <form action="/" className="search-form">
                                    <div className="filter-search-form-2 search-1-adjustment bg-white rounded-sm shadow-7 pr-6 py-6 pl-6">
                                        <div className="filter-inputs">
                                            <div className="form-group position-relative w-lg-45 w-xl-40 w-xxl-45">
                                                <input
                                                    className="form-control focus-reset pl-13"
                                                    type="text"
                                                    id="keyword"
                                                    placeholder="UI Designer"
                                                />
                                                <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6">
                                                    <i className="icon icon-zoom-2 text-primary font-weight-bold"></i>
                                                </span>
                                            </div>
                                            {/* <!-- .select-city starts --> */}
                                            <div className="form-group position-relative w-lg-55 w-xl-60 w-xxl-55">
                                                <Select
                                                    options={defaultCountries}
                                                    className="pl-8 h-100 arrow-3 font-size-4 d-flex align-items-center w-100"
                                                    border={false}
                                                />
                                                <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6">
                                                    <i className="icon icon-pin-3 text-primary font-weight-bold"></i>
                                                </span>
                                            </div>
                                            {/* <!-- ./select-city ends --> */}
                                        </div>
                                        <div className="button-block">
                                            <button className="btn btn-primary line-height-reset h-100 btn-submit w-100 text-uppercase">
                                                Search
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <div className="pt-12">
                                    <div className="d-flex align-items-center justify-content-between mb-6">
                                        <h5 className="font-size-4 font-weight-normal text-gray">
                                            <span className="heading-default-color">
                                                120
                                            </span>
                                            results for{' '}
                                            <span className="heading-default-color">
                                                UI Designer
                                            </span>
                                        </h5>
                                    </div>
                                    <div className="mb-8">
                                        <Link to="/hire-detail">
                                            <HireList />
                                        </Link>
                                    </div>
                                    <div className="text-center pt-5 pt-lg-13">
                                        <Link
                                            to="/#"
                                            className="text-green font-weight-bold text-uppercase font-size-3"
                                        >
                                            Load More{' '}
                                            <i className="fas fa-sort-down ml-3"></i>
                                        </Link>
                                    </div>
                                </div>
                                {/* <!-- form end --> */}
                            </div>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};
export default SearchGrid;
