import React from 'react';
import styled from 'styled-components';
import { profileSelector } from '../../state/reducer/profile.reducer';

import CustomizedRadios from '../Core/CustomizedRadios';
import RangeSearchComponent from '../Core/RangeSearch';

const CheckStyled = styled.span`
    cursor: pointer;
    display: flex;
    align-items: center;
    color: #2b3940 !important;
    font-size: 16px;
    color: inherit;
    &::before {
        content: '\f0c8';
        font-weight: 400;
        font-family: 'Font Awesome 5 Free';
        display: inline-block;
        color: #7e8989;
        margin-right: 11px;
        margin-top: 2px;
    }
    &.active {
        color: #2b3940 !important;
        font-weight: 600;
        &::before {
            content: '\f14a';
            font-weight: 900;
            color: #00b074;
        }
    }
`;

const Sidebar = () => {
    return (
        <>
            {/* <!-- Sidebar Start --> */}
            <div className="widgets mb-11">
                <h4 className="font-size-6 font-weight-semibold mb-6 w-75">성별</h4>
                <CustomizedRadios />
            </div>
            <div className="widgets mb-11 ">
                <div className="d-flex align-items-center pr-15 pr-xs-0 pr-md-0 pr-xl-22">
                    <RangeSearchComponent selector={profileSelector} MAX={100} MIN={0} STEP={1} text={'나이'} />
                </div>
                <div className="d-flex align-items-center pr-15 pr-xs-0 pr-md-0 pr-xl-22">
                    <RangeSearchComponent selector={profileSelector} MAX={200} MIN={80} STEP={5} text={'키'} />
                </div>
                <div className="d-flex align-items-center pr-15 pr-xs-0 pr-md-0 pr-xl-22">
                    <RangeSearchComponent selector={profileSelector} MAX={150} MIN={20} STEP={5} text={'몸무게'} />
                </div>
            </div>

            {/* <!-- Sidebar End --> */}
        </>
    );
};

export default Sidebar;
