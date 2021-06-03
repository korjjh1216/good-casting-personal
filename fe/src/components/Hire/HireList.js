import React, { useEffect } from 'react';
import { Link } from 'gatsby';
import { hireList, hireSelector } from '../../state/reducer/hire.reducer';
import iconL from '../../assets/image/svg/icon-loaction-pin-black.svg';
import iconC from '../../assets/image/svg/icon-clock.svg';
import { useDispatch, useSelector } from 'react-redux';
import imgF from '../../assets/image/svg/icon-fire-rounded.svg';

const HireList = () => {
    const dispatch = useDispatch();
    const pageRequest = useSelector(hireSelector).pageRequest;
    const { dtoList } = useSelector(hireSelector).pageResult;
    const { reset } = useSelector(hireSelector);
    useEffect(() => {
        dispatch(hireList(pageRequest));
    }, [reset]);

    return (
        <>
            {dtoList.map((hire) => {
                return (
                    <ul key={hire.hireId} style={{ listStyleType: 'none', paddingLeft: '0' }}>
                        <li>
                            <Link state={{ id: hire.hireId }} to={`/hire-detail`}>
                                <div style={{ width: '830px' }} className="pt-6 px-xl-6 px-lg-6 px-7 pb-7 light-mode-texts bg-white rounded hover-shadow-3 ">
                                    <div className="row">
                                        <div className="col-md-3">
                                            <div className="media align-items-center">
                                                <div>
                                                    <h3 className="mb-0">
                                                        <Link className="font-size-6 heading-default-color">{hire.project}</Link>
                                                    </h3>
                                                    <Link to="/#" className="font-size-3 text-default-color line-height-2">
                                                        AirBnb
                                                    </Link>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-6 text-right pt-7 pt-md-5">
                                        <div className="media justify-content-md-end">
                                            <div className="image mr-5 mt-2">
                                                <img src={imgF} alt="" />
                                            </div>
                                            <p className="font-weight-bold font-size-7 text-hit-gray mb-0">
                                                <span className="text-black-2">80-90K</span> PLN
                                            </p>
                                        </div>
                                    </div>
                                    <div className="row pt-8">
                                        <div className="col-md-7">
                                            <ul className="d-flex list-unstyled mr-n3 flex-wrap">
                                                <li>
                                                    <Link to="/#" className="bg-regent-opacity-15 min-width-px-96 mr-3 text-center rounded-3 px-6 py-1 font-size-3 text-black-2 mt-2">
                                                        {hire.cast}
                                                    </Link>
                                                </li>
                                            </ul>
                                        </div>
                                        <div className="col-md-5">
                                            <ul className="d-flex list-unstyled mr-n3 flex-wrap mr-n8 justify-content-md-end">
                                                <li className="mt-2 mr-8 font-size-small text-black-2 d-flex">
                                                    <span
                                                        className="mr-4"
                                                        css={`
                                                            margin-top: -2px;
                                                        `}
                                                    >
                                                        <img src={iconL} alt="" />
                                                    </span>
                                                    <span className="font-weight-semibold">Berlyn, UK</span>
                                                </li>

                                                <li className="mt-2 mr-8 font-size-small text-black-2 d-flex">
                                                    <span
                                                        className="mr-4"
                                                        css={`
                                                            margin-top: -2px;
                                                        `}
                                                    >
                                                        <img src={iconC} alt="" />
                                                    </span>
                                                    <span className="font-weight-semibold">{hire.modDate.slice(0, 10)}</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </Link>
                        </li>
                    </ul>
                );
            })}
        </>
    );
};

export default HireList;
