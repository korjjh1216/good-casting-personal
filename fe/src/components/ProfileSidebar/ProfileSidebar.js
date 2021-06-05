import React, { useEffect } from 'react';

import ReactPlayer from 'react-player';
import { useDispatch, useSelector } from 'react-redux';
import { profileDetail, profileSelector } from '../../state/reducer/profile.reducer';

const ProfileSidebar = (props) => {
    const dispatch = useDispatch();

    const profile = useSelector(profileSelector).profile;

    const video = profile.files.find((file) => file.photoType === false) || { fileName: '', uuid: '' };
    const photos = profile.files.filter((file) => file.photoType === true);

    useEffect(() => {
        dispatch(profileDetail(props.id));
    }, []);

    return (
        <>
            <div>
                <div className="pl-lg-5">
                    <div className="bg-white shadow-9 rounded-4">
                        <div className="px-5 py-5 text-left border-bottom border-mercury">
                            <h4>{profile.actor.name}</h4>
                        </div>
                        <div className="px-5 py-11 text-center border-bottom border-mercury">
                            <ReactPlayer component="video" width="100%" height="100%" controls url={`http://localhost:8080/files/display?fileName=${video.uuid}_${video.fileName}`} />
                            {/* 
                            <div className="icon-link d-flex align-items-center justify-content-center flex-wrap">
                                <button className="btn btn-primary text-uppercase font-size-3">회원정보입력하기</button>
                            </div> */}
                        </div>
                        <div className="px-5 py-11 text-center border-bottom border-mercury">
                            {photos.map((photo) => {
                                return (
                                    <div key={photo.uuid}>
                                        <img style={{ height: '200px', width: '150px' }} src={`http://localhost:8080/files/display?fileName=s_${photo.uuid}_${photo.fileName}`} />
                                    </div>
                                );
                            })}
                        </div>
                        <div className="px-9 pt-lg-5 pt-9 pt-xl-9 pb-5">
                            <h5 className="text-black-2 mb-8 font-size-5">소개</h5>
                            <div className="mb-7">{profile.contents}</div>
                            <h5 className="text-black-2 mb-8 font-size-5">Contact Info</h5>
                            <div className="mb-7">
                                <p className="font-size-4 mb-0">Location</p>
                                <h5 className="font-size-4 font-weight-semibold mb-0 text-black-2 text-break">New York , USA</h5>
                            </div>
                            <div className="mb-7">
                                <p className="font-size-4 mb-0">E-mail</p>
                                <h5 className="font-size-4 font-weight-semibold mb-0">
                                    <a className="text-black-2 text-break" href="mailto:name_ac@gmail.com">
                                        {profile.actor.email}
                                    </a>
                                </h5>
                            </div>
                            <div className="mb-7">
                                <p className="font-size-4 mb-0">Phone</p>
                                <h5 className="font-size-4 font-weight-semibold mb-0">
                                    <a className="text-black-2 text-break">{profile.actor.phone}</a>
                                </h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default ProfileSidebar;
