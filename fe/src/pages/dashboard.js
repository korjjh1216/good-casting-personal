import React, { useEffect } from 'react';
import CountUp from 'react-countup';
import LazyLoad from 'react-lazyload';
import PageWrapper from '../components/PageWrapper';

import DashboardHireList from '../components/Dashboard/DashboardHireList';
import DashboardApplicants from './dashboard-applicants';
import { useDispatch } from 'react-redux';
import { resetHireSelector } from '../state/reducer/hire.reducer';

const DashboardMain = () => {
    const dispatch = useDispatch();

    useEffect(() => {
        return () => {
            console.log('hire-list unmount');
            dispatch(resetHireSelector());
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
                <DashboardApplicants />
                <div className="dashboard-main-container mt-25 mt-lg-31">
                    <DashboardHireList />
                </div>
            </PageWrapper>
        </>
    );
};
export default DashboardMain;
