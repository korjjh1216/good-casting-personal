import React from 'react'
import PageWrapper from '../components/PageWrapper'
import DashboardHireList from '../components/Dashboard/DashboardHireList'

const DashboardHires = () => {
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
                    <DashboardHireList />
                </div>
            </PageWrapper>
        </>
    )
}
export default DashboardHires
