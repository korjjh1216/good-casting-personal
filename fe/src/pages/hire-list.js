import React, { useEffect } from 'react';
import PageWrapper from '../components/PageWrapper';
import HireList from '../components/Hire/HireList';
import { useDispatch, useSelector } from 'react-redux';
import { hireSelector, resetHireSelector } from '../state/reducer/hire.reducer';
import ActorSearch from '../components/Hire/ActorSearch';
import HireListSidebar from '../components/Hire/HireListSidebar';
import PageListComponent from '../components/Core/PageList';

const SearchGrid = () => {
    const pageResult = useSelector(hireSelector).pageResult;
    const pageRequest = useSelector(hireSelector).pageRequest;

    const dispatch = useDispatch();

    useEffect(() => {
        return () => {
            console.log('hire-list unmount');
            dispatch(resetHireSelector());
        };
    }, []);

    return (
        <>
            <PageWrapper>
                <div className="bg-default-1 pt-26 pt-lg-28 pb-13 pb-lg-25">
                    <div className="container">
                        <div className="row">
                            <div className="col-12 col-lg-4 col-md-5 col-xs-8">
                                <HireListSidebar pageRequest={pageRequest} />
                            </div>
                            <div className="col-12 col-xl-8 col-lg-8">
                                <ActorSearch pageRequest={pageRequest} />
                                <div className="pt-12">
                                    <div className="d-flex align-items-center justify-content-between mb-6">
                                        <h5 className="font-size-4 font-weight-normal text-gray">
                                            <span className="heading-default-color">
                                                {pageResult.totalElement}
                                            </span>
                                            개의 공고 모집중
                                        </h5>
                                    </div>
                                    <div className="mb-8">
                                        <HireList
                                            pageResult={pageResult}
                                            pageRequest={pageRequest}
                                        />
                                    </div>
                                    <PageListComponent
                                        pageRequest={pageRequest}
                                        pageResult={pageResult}
                                        flag="hireList"
                                    />
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
