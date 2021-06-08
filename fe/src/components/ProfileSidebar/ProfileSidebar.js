import { navigate } from 'gatsby-link';
import React, { useEffect } from 'react';
import Swal from 'sweetalert2';

import ReactPlayer from 'react-player';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'gatsby';
import { resetStatus } from '../../state/reducer/profile.reducer';
import {
    profileDelete,
    profileDetail,
    profileSelector,
} from '../../state/reducer/profile.reducer';

const ProfileSidebar = (props) => {
    const dispatch = useDispatch();

    const profile = useSelector(profileSelector).profile;
    const video = profile.files.find((file) => file.photoType === false) || {
        fileName: '',
        uuid: '',
    };
    const photos = profile.files.filter((file) => file.photoType === true);
    const userInfo =
        typeof window !== `undefined`
            ? JSON.parse(localStorage.getItem('USER'))
            : null;
    const status = useSelector(profileSelector).status;

    useEffect(() => {
        dispatch(profileDetail(props.id));
    }, []);

    if (status === 'success') {
        navigate(-1);
        dispatch(resetStatus());
    }

    return (
        <>
            <div>
                <div className="pl-lg-5">
                    <div className="bg-white shadow-9 rounded-4">
                        <div className="px-5 py-5 text-left border-bottom border-mercury">
                            <h4>{profile.actor.name}</h4>
                        </div>

                        {video.uuid === '' ? (
                            <></>
                        ) : (
                            <div className="px-5 py-11 text-center border-bottom border-mercury">
                                <ReactPlayer
                                    component="video"
                                    width="100%"
                                    height="100%"
                                    controls
                                    url={`http://localhost:8080/files/display?fileName=${video.uuid}_${video.fileName}`}
                                />
                            </div>
                        )}
                        <div className="px-5 py-11 text-center border-bottom border-mercury">
                            <ul
                                style={{
                                    listStyle: 'none',
                                }}
                            >
                                {photos.map((photo) => {
                                    return (
                                        <li key={photo.uuid}>
                                            <img
                                                style={{
                                                    height: '200px',
                                                    width: '150px',
                                                }}
                                                src={`http://localhost:8080/files/display?fileName=s_${photo.uuid}_${photo.fileName}`}
                                            />
                                        </li>
                                    );
                                })}
                            </ul>
                        </div>
                        <div
                            style={{
                                clear: 'left',
                            }}
                            className="px-9 pt-lg-5 pt-9 pt-xl-9 pb-5 "
                        >
                            <h5 className="text-black-2 mb-8 font-size-5">
                                소개
                            </h5>
                            {profile.contents != null ? (
                                <div className="mb-7">{profile.contents}</div>
                            ) : (
                                <div className="mb-7">소개말이 없습니다.</div>
                            )}
                            <h5 className="text-black-2 mb-8 font-size-5">
                                Contact Info
                            </h5>
                            <div className="mb-7">
                                <p className="font-size-4 mb-0">나이</p>
                                <h5 className="font-size-4 font-weight-semibold mb-0 text-black-2 text-break">
                                    {profile.actor.age}
                                </h5>
                            </div>
                            <div className="mb-7">
                                <p className="font-size-4 mb-0">메일주소</p>
                                <h5 className="font-size-4 font-weight-semibold mb-0">
                                    <a
                                        className="text-black-2 text-break"
                                        href="mailto:name_ac@gmail.com"
                                    >
                                        {profile.actor.email}
                                    </a>
                                </h5>
                            </div>
                            <div className="mb-7">
                                <p className="font-size-4 mb-0">전화번호</p>
                                <h5 className="font-size-4 font-weight-semibold mb-0">
                                    <a className="text-black-2 text-break">
                                        {profile.actor.phone}
                                    </a>
                                </h5>
                            </div>
                            {userInfo !== null &&
                            userInfo[1].actorId == profile.actor.actorId ? (
                                <div className="mb-7">
                                    <td className="table-y-middle py-7 min-width-px-80">
                                        <Link
                                            to="/actor-modify"
                                            className="font-size-3 font-weight-bold text-green text-uppercase"
                                        >
                                            수정하기
                                        </Link>
                                    </td>
                                    <td className="table-y-middle py-7 min-width-px-100">
                                        <a
                                            href="#"
                                            className="font-size-3 font-weight-bold text-red-2 text-uppercase"
                                            onClick={(e) => {
                                                e.preventDefault();

                                                Swal.fire({
                                                    title:
                                                        '지원한 공고가 있는 프로필 일 수 있습니다.',
                                                    text:
                                                        '정말 삭제하시겠습니까?',
                                                    icon: 'warning',
                                                    showCancelButton: true,
                                                    confirmButtonColor:
                                                        '#3085d6',
                                                    cancelButtonColor: '#d33',
                                                    confirmButtonText: 'delete',
                                                }).then((result) => {
                                                    if (result.isConfirmed) {
                                                        dispatch(
                                                            profileDelete(
                                                                props.id
                                                            )
                                                        );
                                                    }
                                                });
                                            }}
                                        >
                                            삭제하기
                                        </a>
                                    </td>
                                </div>
                            ) : (
                                undefined
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default ProfileSidebar;
