import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { hireList, hireSelector } from '../../state/reducer/hire.reducer';

const SearchBtnComponent = ({ data, text, className }) => {
    const dispatch = useDispatch();

    const pageRequest = useSelector(hireSelector).pageRequest;

    const clickSearch = (text) => {
        if (text === 'search') {
            console.log('search: ' + text);
            dispatch(
                hireList({
                    ...pageRequest,
                    searchKey: data,
                })
            );
        }

        if (text === '촬영 날짜') {
            dispatch(
                hireList({
                    ...pageRequest,
                    period: { from: data.startDate, to: data.endDate },
                })
            );
        }
    };

    return (
        <>
            <div className="button-block">
                <button onClick={() => clickSearch(text)} className={className}>
                    search
                </button>
            </div>
        </>
    );
};

export default SearchBtnComponent;
