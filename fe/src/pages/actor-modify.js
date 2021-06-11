import React, { useState, useCallback, useEffect } from 'react';
import { navigate } from 'gatsby';
import ProfileCareer from '../components/Profile/ProfileCareer';
import { useDispatch, useSelector } from 'react-redux';
import {
    profileUpdate,
    profileSelector,
    resetStatus,
    profileDetail,
} from '../state/reducer/profile.reducer';
import PageWrapper from '../components/PageWrapper';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Switch from '@material-ui/core/Switch';
import '../scss/css/fileUpload.css';

const ActorModify = ({ location }) => {
    const dispatch = useDispatch();
    const profile = useSelector(profileSelector).profile;
    const { status } = useSelector(profileSelector);
    const [image, setImages] = useState(null);

    const [inputs, setInputs] = useState({
        privacy: true,
    });

    useEffect(() => {
        console.log('profile changed....', profile);
        setInputs({ ...profile });
    }, [profile]);

    useEffect(() => {
        dispatch(profileDetail(location.state.id));
    });

    if (status === 'success') {
        navigate('/actor-mypage');
        dispatch(resetStatus());
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        dispatch(profileUpdate(inputs));
    };
    const handleChange = useCallback(
        (e) => {
            e.preventDefault();
            setInputs({
                ...inputs,
                [e.target.name]: e.target.value,
            });
        },
        [inputs]
    );
    const handleToggle = useCallback((e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.checked,
        });
    });

    return (
        <PageWrapper
            headerConfig={{
                button: 'profile',
                isFluid: true,
                bgClass: 'bg-default',
                reveal: false,
            }}
        >
            <div className="mt-24 mt-lg-31" id="dashboard-body">
                <div className="container">
                    <div className="mb-15 mb-lg-23">
                        <div className="dashboard-main-container mt-25 mt-lg-31">
                            <h5 className="font-size-6 font-weight-semibold mb-11">
                                프로필 수정
                            </h5>
                            <div className="contact-form bg-white shadow-8 rounded-4 pl-sm-10 pl-4 pr-sm-11 pr-4 pt-15 pb-13">
                                <div className="upload-file mb-16 text-center">
                                    <div className="m-auto px-6 mb-7">
                                        <img
                                            style={{
                                                width: '150px',
                                                height: '200px',
                                            }}
                                            src={
                                                'http://localhost:8080/files/display?fileName=s_' +
                                                profile.fileUuid +
                                                '_' +
                                                profile.fileName
                                            }
                                            alt=""
                                        />
                                    </div>
                                    <p>※ 프로필 사진을 등록해주세요</p>
                                </div>
                                <form onSubmit={handleSubmit}>
                                    <fieldset>
                                        <div className="col-md-12">
                                            <div className="form-group">
                                                <label
                                                    htmlFor="aboutTextarea"
                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                >
                                                    경력
                                                </label>
                                                <ProfileCareer />
                                            </div>
                                        </div>
                                        <div className="col-md-12">
                                            <div className="form-group">
                                                <label
                                                    htmlFor="aboutTextarea"
                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                >
                                                    자기소개
                                                </label>
                                                <textarea
                                                    name="contents"
                                                    id="aboutTextarea"
                                                    cols="30"
                                                    rows="7"
                                                    className="border border-mercury text-gray w-100 pt-4 pl-6"
                                                    value={inputs.contents}
                                                    onChange={handleChange}
                                                />
                                            </div>
                                        </div>
                                        <div className="col-md-12">
                                            <div className="form-group">
                                                <label
                                                    htmlFor="aboutTextarea"
                                                    className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                >
                                                    공개/비공개 설정
                                                </label>
                                                <FormControlLabel
                                                    control={
                                                        <Switch
                                                            color="primary"
                                                            checked={
                                                                inputs.privacy
                                                            }
                                                            onChange={
                                                                handleToggle
                                                            }
                                                            name="privacy"
                                                        />
                                                    }
                                                    label="공개"
                                                />
                                            </div>
                                        </div>
                                        <div className="row">
                                            <button
                                                type="submit"
                                                className="btn btn-green btn-h-60 text-white min-width-px-210 rounded-5 text-uppercase btn-center"
                                            >
                                                수정하기
                                            </button>
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </PageWrapper>
    );
};
export default ActorModify;
