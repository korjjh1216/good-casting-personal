import React, { useState } from 'react';
import styled from 'styled-components';
import DatePickerComponent from '../DatePicker/DatePicker';
import { hireList, setType } from '../../state/reducer/hire.reducer';
import { useDispatch } from 'react-redux';
import RangeSearchComponent from '../Core/RangeSearch';
import SearchBtnComponent from '../Core/SearchBtn';

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

const Check = ({ children }) => {
    const [active, setActive] = useState(false);

    return (
        <CheckStyled
            className={`toggle-item ${active ? 'active' : ''}`}
            onClick={() => {
                setActive(!active);
            }}
        >
            {children}
        </CheckStyled>
    );
};

const HireListSidebar = ({ pageRequest }) => {
    const dispatch = useDispatch();

    return (
        <>
            {/* <!-- Sidebar Start --> */}
            {/* <div className="widgets mb-11">
                <h4 className="font-size-6 font-weight-semibold mb-6">성별</h4>
                <ul className="list-unstyled filter-check-list">
                    <li className="mb-2">
                        <Check>남성</Check>
                    </li>
                    <li className="mb-2">
                        <Check>여성</Check>
                    </li>
                </ul>
            </div> */}
            <div className="widgets mb-11 ">
                <div>
                    <h4 className="font-size-6 font-weight-semibold mb-6 w-75">
                        촬영 기간
                    </h4>
                    {/* <!-- Range Slider --> */}
                    <h6 className="font-size-4 font-weight-semibold mb-6 w-75">
                        시작일
                    </h6>
                    <DatePickerComponent isRangeSearch={true} />
                    <SearchBtnComponent
                        pageRequest={pageRequest}
                        type={'f'}
                        text={'기간 설정'}
                        className="btn btn-primary line-height-reset h-50 w-50 text-uppercase"
                    />
                </div>
            </div>
            <RangeSearchComponent className={'출연료'} />
            <SearchBtnComponent
                pageRequest={pageRequest}
                type={'g'}
                text={'출연료 설정'}
                className="btn btn-primary line-height-reset h-50 w-50 text-uppercase"
            />

            {/* <!-- Sidebar End --> */}
        </>
    );
};

export default HireListSidebar;
