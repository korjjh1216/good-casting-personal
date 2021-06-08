import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import PageWrapper from '../components/PageWrapper';

import MyProfileList from '../components/Profile/MyprofileList';
import {
    profileSelector,
    resetProfileSelector,
} from '../state/reducer/profile.reducer';

const DashboardMain = () => {
    const dispatch = useDispatch();

    const pageRequest = useSelector(profileSelector).pageRequest;

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
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};
export default DashboardMain;
