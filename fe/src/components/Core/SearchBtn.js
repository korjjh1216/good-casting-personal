import React from 'react';
import { useDispatch } from 'react-redux';
import { hireList, setType } from '../../state/reducer/hire.reducer';

const SearchBtnComponent = ({ type, pageRequest, text, className }) => {
    const dispatch = useDispatch();
    return (
        <>
            <div className="button-block">
                <button
                    onClick={() => {
                        console.log('type: ' + pageRequest.type);
                        if (!pageRequest.type.includes(type)) {
                            dispatch(
                                hireList({
                                    ...pageRequest,
                                    type: pageRequest.type + type,
                                })
                            );
                            dispatch(setType(type));
                        } else {
                            dispatch(
                                hireList({
                                    ...pageRequest,
                                    type: pageRequest.type,
                                })
                            );
                        }
                    }}
                    className={className}
                >
                    {text}
                </button>
            </div>
        </>
    );
};

export default SearchBtnComponent;
