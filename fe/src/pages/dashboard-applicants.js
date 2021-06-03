import React from 'react'
import DashboardApplicantList from '../components/Dashboard/DashboardApplicantList'
import PageWrapper from '../components/PageWrapper'

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
                    <DashboardApplicantList />
                </div>
            </PageWrapper>
        </>
    )
}
export default DashboardApplicants
