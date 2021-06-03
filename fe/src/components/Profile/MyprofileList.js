import React, { useEffect } from 'react'
import { Link } from 'gatsby'
import { useSelector, useDispatch } from 'react-redux'
import { profileList, profileSelector } from '../../state/reducer/profile.reducer'

const MyProfileList = () => {
    const dispatch = useDispatch()

    const pageResult = useSelector(profileSelector).pageResult
    const pageRequest = useSelector(profileSelector).pageRequest

    const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null

    useEffect(() => {
        dispatch(
            profileList({
                ...pageRequest,
                actorId: userInfo[1].actorId,
            })
        )
    }, [])

    return (
        <>
            {pageResult.dtoList.map((profile) => {
                return (
                    <>
                        <ul
                            style={{
                                listStyleType: 'none',
                            }}
                            key={profile.profileId}
                        >
                            <div>
                                <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                                    <div className="d-block mb-7">
                                        <Link to="/profile-detail">
                                            <img
                                                style={{
                                                    width: '150px',
                                                    height: '200px',
                                                }}
                                                src={'http://localhost:8080/files/display?fileName=s_' + profile.fileUuid + '_' + profile.fileName}
                                                alt=""
                                            />
                                        </Link>
                                    </div>
                                    <h2 className="mt-n4">
                                        <p className="font-size-7 text-black-2 font-weight-bold mb-4">{profile.actorName}</p>
                                    </h2>
                                    <div className="card-btn-group">
                                        <Link to="/profile-detail" className="btn btn-green text-uppercase btn-medium rounded-3">
                                            프로필보기
                                        </Link>
                                    </div>
                                </div>
                            </div>
                        </ul>
                    </>
                )
            })}
        </>
    )
}

export default MyProfileList
