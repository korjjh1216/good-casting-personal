import React from 'react';
import MyApplyList from '../components/Profile/MyApplyList';
import PageWrapper from '../components/PageWrapper';

const DashboardApplicants = () => {
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
                    <MyApplyList />
                </div>
            </PageWrapper>
        </>
    );
};
export default DashboardApplicants;
