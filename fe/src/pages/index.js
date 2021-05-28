import React from 'react';
import PageWrapper from '../components/PageWrapper';
import Hero from '../sections/landing1/Hero';

const IndexPage = () => {
    return (
        <>
            <PageWrapper
                headerConfig={{
                    bgClass: 'dynamic-sticky-bg',
                }}
            >
                <Hero />
            </PageWrapper>
        </>
    );
};
export default IndexPage;
