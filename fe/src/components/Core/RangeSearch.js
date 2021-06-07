import React, { useState } from 'react';
import { Range, getTrackBackground } from 'react-range';
import { useDispatch, useSelector } from 'react-redux';
import { hireList } from '../../state/reducer/hire.reducer';
import { profileList } from '../../state/reducer/profile.reducer';

const RangeSearchComponent = ({ text, MAX, MIN, STEP, selector }) => {
    const [rangeValues, setRangeValues] = useState([MIN, MAX]);

    const pageRequest = useSelector(selector).pageRequest;

    const dispatch = useDispatch();

    return (
        <div className="widgets mb-11 ">
            <div className="d-flex align-items-center pr-15 pr-xs-0 pr-md-0 pr-xl-22">
                <h4 className="font-size-6 font-weight-semibold mb-6 w-75">{text}</h4>
                {/* <!-- Range Slider --> */}

                <div className="slider-price w-25 text-right mr-7">
                    <p className="font-weight-bold">
                        <span
                            className="text-primary font-weight-semibold font-size-4 "
                            css={`
                                white-space: nowrap;
                            `}
                        >
                            {rangeValues[0].toFixed()} -{rangeValues[1].toFixed()}
                        </span>
                    </p>
                </div>
            </div>

            <div className="range-slider">
                <>
                    <Range
                        values={rangeValues}
                        step={STEP}
                        min={MIN}
                        max={MAX}
                        onChange={(values) => {
                            setRangeValues(values);
                        }}
                        renderTrack={({ props, children }) => (
                            <div
                                role="button"
                                tabIndex={0}
                                onMouseDown={props.onMouseDown}
                                onTouchStart={props.onTouchStart}
                                style={{
                                    ...props.style,
                                    height: '15px',
                                    display: 'flex',
                                    width: '290px',
                                }}
                            >
                                <div
                                    ref={props.ref}
                                    style={{
                                        height: '5px',
                                        width: '90%',
                                        borderRadius: '4px',
                                        background: getTrackBackground({
                                            values: rangeValues,
                                            colors: ['#ccc', '#755dd9', '#ccc'],
                                            min: MIN,
                                            max: MAX,
                                        }),
                                        alignSelf: 'center',
                                    }}
                                >
                                    {children}
                                </div>
                            </div>
                        )}
                        renderThumb={({ props, isDragged }) => (
                            <div
                                onMouseUp={() => {
                                    if (text === '출연료') {
                                        dispatch(
                                            hireList({
                                                ...pageRequest,
                                                pay: {
                                                    start: rangeValues[0],
                                                    end: rangeValues[1],
                                                },
                                            })
                                        );
                                    }
                                    if (text === '나이') {
                                        dispatch(
                                            profileList({
                                                ...pageRequest,
                                                age: {
                                                    from: rangeValues[0],
                                                    to: rangeValues[1],
                                                },
                                            })
                                        );
                                    }

                                    if (text === '키') {
                                        dispatch(
                                            profileList({
                                                ...pageRequest,
                                                height: {
                                                    from: rangeValues[0],
                                                    to: rangeValues[1],
                                                },
                                            })
                                        );
                                    }

                                    if (text === '몸무게') {
                                        dispatch(
                                            profileList({
                                                ...pageRequest,
                                                weight: {
                                                    from: rangeValues[0],
                                                    to: rangeValues[1],
                                                },
                                            })
                                        );
                                    }
                                }}
                                {...props}
                                style={{
                                    ...props.style,
                                    height: '17px',
                                    width: '17px',
                                    borderRadius: '50%',
                                    backgroundColor: '#FFF',
                                    display: 'flex',
                                    justifyContent: 'center',
                                    alignItems: 'center',
                                    boxShadow: 'none !important',
                                    outline: 'none !important',
                                }}
                                css={`
                                    &:focus {
                                        outline: none !important;
                                    }
                                `}
                            ></div>
                        )}
                    />
                </>
            </div>
        </div>
    );
};

export default RangeSearchComponent;
