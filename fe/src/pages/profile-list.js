import React from 'react';
import PageWrapper from '../components/PageWrapper';
import Sidebar from '../components/Profile/Sidebar';
import ProfileList from '../components/Profile/ProfileList';
import { profileSelector } from '../state/reducer/profile.reducer';
import { useSelector } from 'react-redux';
import '../scss/css/fileUpload.css';
import DragNDropComponent from '../components/Core/DragNDrop';
import PageListComponent from '../components/Core/PageList';

const SearchGrid = () => {
    const pageResult = useSelector(profileSelector).pageResult;
    const pageRequest = useSelector(profileSelector).pageRequest;
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

                                <DragNDropComponent pageRequest={pageRequest} />

                                <div className="pt-12 ml-lg-0 ml-md-15">
                                    <div className="d-flex align-items-center justify-content-between">
                                        <h5 className="font-size-4 font-weight-normal text-default-color">
                                            <span className="heading-default-color">120</span>
                                            results for <span className="heading-default-color">UI Designer</span>
                                        </h5>
                                    </div>
                                    <div className="pt-6">
                                        <div className="row justify-content-center">
                                            <ProfileList pageResult={pageResult} pageRequest={pageRequest} />
                                        </div>
                                    </div>
                                    <div className="text-center pt-5 pt-lg-13">
                                        <PageListComponent flag={'profileList'} />
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
