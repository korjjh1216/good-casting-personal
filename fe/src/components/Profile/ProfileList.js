import React, { useEffect } from 'react'
import { Link } from 'gatsby'
import { useDispatch } from 'react-redux'
import { profileList } from '../../state/reducer/profile.reducer'
import { readOne } from '../../state/reducer/profile.reducer'

const ProfileList = ({ pageRequest, pageResult }) => {
    const dispatch = useDispatch()

    useEffect(() => {
        console.log('ProfileList pageRequest: ' + JSON.stringify(pageRequest))
        dispatch(profileList(pageRequest))
    }, [])

    return (
        <>
            {pageResult.dtoList.map((profile) => (
                <>
                    <ul
                        style={{
                            listStyleType: 'none',
                        }}
                        key={profile.profileId}
                    >
                        <li>
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
                        </li>
                    </ul>
                </>
            ))}
        </>
    )
}

export default ProfileList
