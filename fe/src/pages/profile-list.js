import React from 'react'
import { Link } from 'gatsby'
import PageWrapper from '../components/PageWrapper'
import Sidebar from '../components/Sidebar'
import ProfileList from '../components/Profile/ProfileList'
import { profileSelector } from '../state/reducer/profile.reducer'
import { useSelector } from 'react-redux'
import '../scss/css/fileUpload.css'

const SearchGrid = () => {
    const pageResult = useSelector(profileSelector).pageResult
    const pageRequest = useSelector(profileSelector).pageRequest
    return (
        <>
            <PageWrapper>
                <div className="bg-default-1 pt-26 pt-lg-28 pb-13 pb-lg-25">
                    <div className="container">
                        <div className="row">
                            <div className="col-12 col-md-4 col-xs-8">
                                <Sidebar />
                            </div>
                            <div className="col-12 col-md-8 col-xs-12 ">
                                {/* <!-- form --> */}
                                <form action="/" className="search-form search-2-adjustment ml-lg-0 ml-md-15">
                                    <div className="filter-search-form-2 bg-white rounded-sm shadow-7 pr-6 py-6 pl-6">
                                        <div className="filter-inputs">
                                            <div className="form-group position-relative w-lg-45 w-xl-40 w-xxl-45">
                                                <div className="filebox">
                                                    <label>
                                                        사진을 업로드해주세요
                                                        <input type="file" img className="form-control focus-reset pl-13" id="keyword" />
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="button-block">
                                            <button className="btn btn-primary line-height-reset h-100 btn-submit w-100 text-uppercase">검색</button>
                                        </div>
                                    </div>
                                </form>
                                <div className="pt-12 ml-lg-0 ml-md-15">
                                    <div className="d-flex align-items-center justify-content-between">
                                        <h5 className="font-size-4 font-weight-normal text-default-color">
                                            <span className="heading-default-color">120</span>
                                            results for <span className="heading-default-color">UI Designer</span>
                                        </h5>
                                        <div className="d-flex align-items-center result-view-type">
                                            <Link to="/search-list" className="heading-default-color pl-5 font-size-6 hover-text-hitgray">
                                                <i className="fa fa-list-ul"></i>
                                            </Link>
                                            <Link to="/search-grid" className="heading-default-color pl-5 font-size-6 hover-text-hitgray active">
                                                <i className="fa fa-th-large"></i>
                                            </Link>
                                        </div>
                                    </div>
                                    <div className="pt-6">
                                        <div className="row justify-content-center">
                                            <ProfileList pageResult={pageResult} pageRequest={pageRequest} />
                                        </div>
                                    </div>
                                    <div className="text-center pt-5 pt-lg-13">
                                        <Link to="/#" className="text-green font-weight-bold text-uppercase font-size-3 d-flex align-items-center justify-content-center">
                                            Load More <i className="fas fa-sort-down ml-3 mt-n2 font-size-4"></i>
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
    )
}
export default SearchGrid
