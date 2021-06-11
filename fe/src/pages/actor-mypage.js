import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import PageListComponent from '../components/Core/PageList';

import PageWrapper from '../components/PageWrapper';

import MyProfileList from '../components/Profile/MyprofileList';
import {
    profileSelector,
    resetProfileSelector,
} from '../state/reducer/profile.reducer';

const DashboardMain = () => {
    const dispatch = useDispatch();

    const pageRequest = useSelector(profileSelector).pageRequest;
    const pageResult = useSelector(profileSelector).pageResult;

    useEffect(() => {
        return () => {
            dispatch(resetProfileSelector());
        };
    }, []);

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
                    <div style={{ height: '1500px' }} className="container">
                        <div className="row mb-7">
                            <MyProfileList />
                        </div>
                        <PageListComponent
                            pageRequest={pageRequest}
                            pageResult={pageResult}
                            flag="hireList"
                        />
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};
export default DashboardMain;
