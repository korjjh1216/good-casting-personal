import React, { useEffect, useState } from 'react'
import { Link } from 'gatsby'
import { useDispatch, useSelector } from 'react-redux'
import { profileList, profileSelector } from '../../state/reducer/profile.reducer'
import { readOne } from '../../state/reducer/profile.reducer'

const ProfileList = ({ pageResult, pageRequest }) => {
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(profileList(pageRequest))
    }, [])

    return (
        <>
            {pageResult.dtoList.map((profile) => {
                return (
                    <ul key={profile.profileId} style={{ listStyleType: 'none' }}>
                        <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                            <div className="d-block mb-7">
                                <Link to="/#">{/*<img src={`http://localhost:8080/file/display?fileName=s_${profile.fileUuid}_${profile.fileName}`}/>*/}</Link>
                            </div>
                            <Link to="/#" className="font-size-3 d-block mb-0 text-gray"></Link>
                            <h2 className="mt-n4">
                                <Link to="/#" className="font-size-7 text-black-2 font-weight-bold mb-4">
                                    {profile.actorName}
                                </Link>
                            </h2>
                            <div className="card-btn-group">
                                <Link to="/profile-detail" className="btn btn-green text-uppercase btn-medium rounded-3">
                                    프로필보기
                                </Link>
                            </div>
                        </div>
                    </ul>
                )
            })}
        </>
    )
}

export default ProfileList
