import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { hireList, hireSelector } from '../../state/reducer/hire.reducer';

const SearchBtnComponent = ({ data, text, className }) => {
    const dispatch = useDispatch();

    const pageRequest = useSelector(hireSelector).pageRequest;

    const clickSearch = (text) => {
        if (text === 'search') {
            dispatch(
                hireList({
                    ...pageRequest,
                    searchKey: { keyword: data },
                })
            );
        }
        if (text === '기간 설정') {
            dispatch(
                hireList({
                    ...pageRequest,
                    period: { from: data.startDate, to: data.endDate },
                })
            );
        }
        if (text === '출연료 검색') {
            dispatch(
                hireList({
                    ...pageRequest,
                    pay: {
                        start: data[0],
                        end: data[1],
                    },
                })
            );
        }
    };

    return (
        <>
            <div className="button-block">
                <button onClick={() => clickSearch(text)} className={className}>
                    {text}
                </button>
            </div>
        </>
    );
};

export default SearchBtnComponent;
