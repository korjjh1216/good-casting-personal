import React from 'react';

import PageWrapper from '../components/PageWrapper';

import MyProfileList from '../components/Profile/MyprofileList';

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
                    <div style={{ height: '2000px' }} className="container">
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
