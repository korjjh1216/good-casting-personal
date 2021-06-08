import React, { useEffect, useState } from 'react';
import { Link, navigate } from 'gatsby';
import PageWrapper from '../components/PageWrapper';
import { useDispatch, useSelector } from 'react-redux';
import { hireSelector } from '../state/reducer/hire.reducer';
import { profileList, profileSelector } from '../state/reducer/profile.reducer';
import {
    applySelector,
    hireApply,
    resetStatus,
} from '../state/reducer/apply.reducer';

const HireApply = ({ location }) => {
    const userInfo =
        typeof window !== `undefined`
            ? JSON.parse(localStorage.getItem('USER'))
            : null;

    const dispatch = useDispatch();

    const pageResult = useSelector(profileSelector).pageResult;
    const pageRequest = useSelector(profileSelector).pageRequest;
    const status = useSelector(applySelector).status;

    useEffect(() => {
        dispatch(
            profileList({
                ...pageRequest,
                actorId: userInfo[1].actorId,
            })
        );
    }, []);

    const [apply, setApply] = useState({
        applyStatus: 1,
        hire: { hireId: location.state.id },
    });
    const handleChange = (e) => {
        const profileId = e.target.getAttribute('data-profileid');

        if (e.target.checked) {
            setApply({ ...apply, profile: { profileId } });
        }
    };

    if (status === 'success') {
        navigate('/hire-list');
        dispatch(resetStatus());
    }

    const handleApply = (e) => {
        e.preventDefault();
        dispatch(hireApply(apply));
    };
    return (
        <>
            <PageWrapper>
                <div className="bg-default-1 pt-26 pt-lg-28 pb-13 pb-lg-25">
                    <div className="container">
                        <div className="row">
                            <div className="col-12 col-md-8 col-xs-12 ">
                                <div className="pt-6">
                                    <div className="row justify-content-center">
                                        {pageResult.dtoList.map((profile) => {
                                            return (
                                                <>
                                                    <ul
                                                        style={{
                                                            listStyleType:
                                                                'none',
                                                        }}
                                                        key={profile.profileId}
                                                    >
                                                        <div>
                                                            <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                                                                <div className="d-block mb-7">
                                                                    <Link to="/profile-detail">
                                                                        <img
                                                                            style={{
                                                                                width:
                                                                                    '150px',
                                                                                height:
                                                                                    '200px',
                                                                            }}
                                                                            src={
                                                                                'http://localhost:8080/files/display?fileName=s_' +
                                                                                profile.fileUuid +
                                                                                '_' +
                                                                                profile.fileName
                                                                            }
                                                                            alt=""
                                                                        />
                                                                    </Link>
                                                                </div>
                                                                <h2 className="mt-n4">
                                                                    <p className="font-size-7 text-black-2 font-weight-bold mb-4">
                                                                        {
                                                                            profile.actorName
                                                                        }
                                                                    </p>
                                                                </h2>
                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                <input
                                                                    type="radio"
                                                                    data-profileid={
                                                                        profile.profileId
                                                                    }
                                                                    name="select"
                                                                    onChange={(
                                                                        e
                                                                    ) =>
                                                                        handleChange(
                                                                            e
                                                                        )
                                                                    }
                                                                />
                                                            </div>
                                                        </div>
                                                    </ul>
                                                </>
                                            );
                                        })}
                                    </div>
                                </div>
                                <div className="card-btn-group">
                                    <div
                                        className="btn btn-green text-uppercase btn-medium rounded-3 center"
                                        onClick={handleApply}
                                    >
                                        APPLY
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};

export default HireApply;
