import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { hireList, hireSelector, pageListChange } from '../../state/reducer/hire.reducer';
import { profileList } from '../../state/reducer/profile.reducer';

const PageListComponent = ({ flag }) => {
    const dispatch = useDispatch();
    const pageRequest = useSelector(hireSelector).pageRequest;

    const { next, prev, pageList, start, end } = useSelector(hireSelector).pageResult;

    const changePage = (page) => {
        if (flag === 'hireList') {
            dispatch(
                hireList({
                    ...pageRequest,
                    page,
                })
            );
        } else if (flag === 'profileList') {
            dispatch(
                profileList({
                    ...pageRequest,
                    page,
                })
            );
        }
    };

    const clickPrev = () => {
        dispatch(pageListChange(start - 1));
    };

    const clickNext = () => {
        dispatch(pageListChange(end + 1));
    };

    const btnStyle = { border: 0, outline: 0 };

    return (
        <>
            <div>
                {prev ? (
                    <div className="text-center pt-5 pt-lg-13">
                        <button style={btnStyle} onClick={clickPrev} className="text-green font-weight-bold text-uppercase font-size-3">
                            prev
                        </button>
                    </div>
                ) : (
                    <></>
                )}
                {pageList.map((page, idx) => {
                    return (
                        <div key={idx} className="text-center pt-5 pt-lg-13">
                            <button
                                style={btnStyle}
                                onClick={() => {
                                    changePage(page);
                                }}
                                className="text-green fSont-weight-bold text-uppercase font-size-6"
                            >
                                {page}
                            </button>
                        </div>
                    );
                })}
                {next ? (
                    <div className="text-center pt-5 pt-lg-13">
                        <button style={btnStyle} onClick={clickNext} className="text-green font-weight-bold text-uppercase font-size-3">
                            next
                            <i className="fas fa-sort-down ml-3"></i>
                        </button>
                    </div>
                ) : (
                    <></>
                )}
            </div>
        </>
    );
};

export default PageListComponent;
