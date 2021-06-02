import React from 'react'
import { useDispatch } from 'react-redux'
import { hireList, pageListChange } from '../../state/reducer/hire.reducer'
import { profileList } from '../../state/reducer/profile.reducer'

const PageListComponent = ({ pageResult, pageRequest, flag }) => {
    const dispatch = useDispatch()

    return (
        <>
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
                                    if (flag === 'hireList') {
                                        dispatch(
                                            hireList({
                                                ...pageRequest,
                                                page,
                                            })
                                        )
                                    } else if (flag === 'profileList') {
                                        dispatch(
                                            profileList({
                                                ...pageRequest,
                                                page,
                                            })
                                        )
                                    }
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

export default PageListComponent
