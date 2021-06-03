import React, { useEffect } from 'react';
import { Link } from 'gatsby';

import { useDispatch, useSelector } from 'react-redux';
import { profileList, profileSelector } from '../../state/reducer/profile.reducer';

const ProfileList = () => {
    const dispatch = useDispatch();
    const { reset } = useSelector(profileSelector);
    const pageRequest = useSelector(profileSelector).pageRequest;
    const pageResult = useSelector(profileSelector).pageResult;

    useEffect(() => {
        dispatch(profileList(pageRequest));
    }, [reset]);

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
    );
};

export default ProfileList;
