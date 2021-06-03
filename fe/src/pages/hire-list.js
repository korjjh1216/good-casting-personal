import React from 'react';
import PageWrapper from '../components/PageWrapper';
import HireList from '../components/Hire/HireList';
import { useSelector } from 'react-redux';
import { hireSelector } from '../state/reducer/hire.reducer';
import ActorSearch from '../components/Hire/ActorSearch';
import HireListSidebar from '../components/Hire/HireListSidebar';
import PageListComponent from '../components/Core/PageList';

const SearchGrid = () => {
    const pageResult = useSelector(hireSelector).pageResult;
    const pageRequest = useSelector(hireSelector).pageRequest;

    return (
        <>
            <PageWrapper>
                <div className="bg-default-1 pt-26 pt-lg-28 pb-13 pb-lg-25">
                    <div className="container">
                        <div className="row">
                            <div className="col-12 col-lg-4 col-md-5 col-xs-8">
                                <HireListSidebar pageRequest={pageRequest} />
                            </div>
                            {/* <!-- Main Body --> */}
                            <div className="col-12 col-xl-8 col-lg-8">
                                {/* <!-- form --> */}
                                <ActorSearch pageRequest={pageRequest} />

                                <div className="pt-12">
                                    <div className="d-flex align-items-center justify-content-between mb-6">
                                        <h5 className="font-size-4 font-weight-normal text-gray">
                                            <span className="heading-default-color">{pageResult.totalElement}</span>
                                            results for <span className="heading-default-color">Actor</span>
                                        </h5>
                                        <div className="button-block">
                                            <button onClick={() => {}} className="btn btn-primary line-height-reset h-5 w-5 text-uppercase font-weight-bold">
                                                초기화
                                            </button>
                                        </div>
                                    </div>
                                    <div className="mb-8">
                                        <HireList pageResult={pageResult} pageRequest={pageRequest} />
                                    </div>
                                    <PageListComponent flag={'hireList'} />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};
export default SearchGrid;
