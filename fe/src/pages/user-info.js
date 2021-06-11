import React, { useCallback, useState, useEffect } from 'react';
import { navigate } from 'gatsby';
import PageWrapper from '../components/PageWrapper';
import { useDispatch } from 'react-redux';
import '../scss/css/actorInfo.css';
import { updateUserInfo } from '../state/reducer/user.reducer';
import Swal from 'sweetalert2';

const UserInfo = () => {
    const dispatch = useDispatch();
    const userInfo = JSON.parse(localStorage.getItem('USER'));

    const [inputs, setInputs] = useState({
        currentPwd: '',
        newPwd: '',
        newPwdCheck: '',
    });

    const handleSubmit = (e) => {
        console.log('submit enter?');
        e.preventDefault();
        dispatch(
            updateUserInfo({
                ...userInfo[0],
                password: inputs.currentPwd,
                newPassword: inputs.newPwd,
            })
        );
    };

    const handleChange = useCallback((e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        });
    });

    const checkValidate = (newP, checkP, curP) => {
        if (newP === '' || checkP === '' || curP === '') {
            Swal.fire({
                icon: 'error',
                title: '비밀번호를 입력하세요',
            });

            return true;
        }
        if (checkP !== newP) {
            Swal.fire({
                icon: 'error',
                title: '새 비밀번호와 비밀번호 확인이 일치하지 않습니다.',
            });

            return true;
        }
    };

    return (
        <>
            <PageWrapper
                headerConfig={{
                    button: 'profile',
                    isFluid: true,
                    bgClass: 'bg-default',
                    reveal: false,
                }}
            >
                <div className="mt-24 mt-lg-31" id="dashboard-body">
                    <div className="container" style={{ paddingLeft: '250px' }}>
                        <div className="mb-15 mb-lg-23">
                            <div className="row">
                                <div className="col-xxxl-9 px-lg-13 px-6">
                                    <h5 className="font-size-6 font-weight-semibold mb-11">
                                        비밀번호 변경
                                    </h5>
                                    <div className="contact-form bg-white shadow-8 rounded-4 pl-sm-10 pl-4 pr-sm-11 pr-4 pt-15 pb-13">
                                        <form
                                            onSubmit={handleSubmit}
                                            method="post"
                                        >
                                            <fieldset>
                                                <div className="row mb-xl-1 mb-9">
                                                    <div className="col-lg-6">
                                                        <div className="form-group">
                                                            <label
                                                                htmlFor="namedash"
                                                                className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                            >
                                                                현재 비밀번호
                                                            </label>
                                                            <input
                                                                type="text"
                                                                className="form-control h-px-48"
                                                                id="namedash"
                                                                value={
                                                                    inputs.currentPwd
                                                                }
                                                                name="currentPwd"
                                                                onChange={
                                                                    handleChange
                                                                }
                                                            />
                                                        </div>
                                                        <div className="form-group">
                                                            <label
                                                                htmlFor="namedash"
                                                                className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                            >
                                                                새 비밀번호
                                                            </label>
                                                            <input
                                                                type="text"
                                                                className="form-control h-px-48"
                                                                id="namedash"
                                                                value={
                                                                    inputs.newPwd
                                                                }
                                                                name="newPwd"
                                                                onChange={
                                                                    handleChange
                                                                }
                                                            />
                                                        </div>
                                                        <div className="form-group">
                                                            <label
                                                                htmlFor="namedash"
                                                                className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                            >
                                                                새 비밀번호 확인
                                                            </label>
                                                            <input
                                                                type="text"
                                                                className="form-control h-px-48"
                                                                id="namedash"
                                                                value={
                                                                    inputs.newPwdCheck
                                                                }
                                                                name="newPwdCheck"
                                                                onChange={
                                                                    handleChange
                                                                }
                                                            />
                                                        </div>
                                                    </div>
                                                </div>

                                                <div className="row">
                                                    <div className="col-md-12">
                                                        <button
                                                            onClick={(e) => {
                                                                if (
                                                                    checkValidate(
                                                                        inputs.newPwd,
                                                                        inputs.newPwdCheck,
                                                                        inputs.currentPwd
                                                                    )
                                                                ) {
                                                                    e.preventDefault();
                                                                }
                                                            }}
                                                            className="btn btn-green btn-h-60 text-white min-width-px-210 rounded-5 text-uppercase"
                                                        >
                                                            변경
                                                        </button>
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </form>
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

export default UserInfo;
