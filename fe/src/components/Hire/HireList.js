import React, { useCallback, useEffect, useState } from 'react'
import { Link } from 'gatsby'
import { hireList, pageListChange } from '../../state/reducer/hire.reducer'
import iconL from '../../assets/image/svg/icon-loaction-pin-black.svg'
import iconC from '../../assets/image/svg/icon-clock.svg'
import { useDispatch } from 'react-redux'

const HireList = ({ pageResult, pageRequest }) => {
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(hireList(pageRequest))
    }, [])

    return (
        <>
            {pageResult.dtoList.map((hire) => {
                return (
                    <ul key={hire.hireId} style={{ listStyleType: 'none' }}>
                        <li>
                            <Link state={{ id: hire.hireId }} to="/hire-detail">
                                <div className="pt-9 px-xl-9 px-lg-7 px-7 pb-7 light-mode-texts bg-white rounded hover-shadow-3 ">
                                    <div className="row">
                                        <div className="col-md-6">
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
                )
            })}
            <div>
                {pageResult.prev ? (
                    <div className="text-center pt-5 pt-lg-13">
                        <button
                            style={{ border: 0, outline: 0 }}
                            onClick={() => {
                                console.log('click')
                                dispatch(pageListChange(pageResult.start - 1))
                            }}
                            className="text-green font-weight-bold text-uppercase font-size-3"
                        >
                            prev
                        </button>
                    </div>
                ) : (
                    <></>
                )}
                {pageResult.pageList.map((page, idx) => {
                    return (
                        <div key={idx} className="text-center pt-5 pt-lg-13">
                            <button
                                style={{ border: 0, outline: 0 }}
                                onClick={() => {
                                    dispatch(
                                        hireList({
                                            ...pageRequest,
                                            page,
                                        })
                                    )
                                }}
                                className="text-green font-weight-bold text-uppercase font-size-6"
                            >
                                {page}
                            </button>
                        </div>
                    )
                })}
                {pageResult.next ? (
                    <div className="text-center pt-5 pt-lg-13">
                        <button
                            style={{ border: 0, outline: 0 }}
                            onClick={() => {
                                dispatch(pageListChange(pageResult.end + 1))
                            }}
                            className="text-green font-weight-bold text-uppercase font-size-3"
                        >
                            next
                            <i className="fas fa-sort-down ml-3"></i>
                        </button>
                    </div>
                ) : (
                    <></>
                )}
            </div>
        </>
    )
}

export default HireList
