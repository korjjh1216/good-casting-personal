import React, { useState } from 'react';
import styled from 'styled-components';
import DatePickerComponent from '../DatePicker/DatePicker';
import RangeSearchComponent from '../Core/RangeSearch';
import { hireList, hireSelector } from '../../state/reducer/hire.reducer';

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

const HireListSidebar = () => {
    return (
        <>
            <div className="widgets mb-11 ">
                <div>
                    <h4 className="font-size-6 font-weight-semibold mb-6 w-75">촬영 기간</h4>
                    <h6 className="font-size-4 font-weight-semibold mb-6 w-75">시작일</h6>
                    <DatePickerComponent isRangeSearch={true} />
                </div>
            </div>
            <RangeSearchComponent list={hireList} selector={hireSelector} MAX={1000000} MIN={0} STEP={1000} text={'출연료'} />
        </>
    );
};

export default HireListSidebar;
