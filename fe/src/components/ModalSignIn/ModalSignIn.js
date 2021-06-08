import React, { useCallback, useContext, useState, useEffect } from 'react';
import styled from 'styled-components';
import { Modal } from 'react-bootstrap';
import GlobalContext from '../../context/GlobalContext';
import { useDispatch } from 'react-redux';
import { signin } from '../../state/reducer/user.reducer';

const ModalStyled = styled(Modal)`
    /* &.modal {
    z-index: 10050;
  } */
`;

const ModalSignIn = (props) => {
    const gContext = useContext(GlobalContext);
    const dispatch = useDispatch();

    const [showPass, setShowPass] = useState(true);

    const [inputs, setInputs] = useState({
        username: '',
        password: '',
    });

    const handleClose = () => {
        setInputs({
            username: '',
            password: '',
        });
        gContext.toggleSignInModal();
    };

    const togglePassword = () => {
        setShowPass(!showPass);
    };

    const onChange = useCallback((e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        });
    });

    return (
        <ModalStyled
            {...props}
            size="lg"
            centered
            show={gContext.signInModalVisible}
            onHide={handleClose}
        >
            <Modal.Body className="p-0">
                <button
                    type="button"
                    className="circle-32 btn-reset bg-white pos-abs-tr mt-md-n6 mr-lg-n6 focus-reset z-index-supper"
                    onClick={handleClose}
                >
                    <i className="fas fa-times"></i>
                </button>
                <div className="login-modal-main bg-white rounded-8 overflow-hidden">
                    <div className="row no-gutters">
                        <div className="col-lg-5 col-md-6">
                            <div className="pt-10 pb-6 pl-11 pr-12 bg-black-2 h-100 d-flex flex-column dark-mode-texts">
                                <div className="pb-9">
                                    <h3 className="font-size-8 text-white line-height-reset pb-4 line-height-1p4">
                                        로그인
                                    </h3>
                                    <p className="mb-0 font-size-4 text-white">
                                        굿캐스팅에 로그인하여 <br /> 새로운
                                        공고들을 탐색해보세요
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-7 col-md-6">
                            <div className="bg-white-2 h-100 px-11 pt-11 pb-7">
                                <div className="form-group">
                                    <label
                                        htmlFor="username"
                                        className="font-size-4 text-black-2 font-weight-semibold line-height-reset"
                                    >
                                        아이디
                                    </label>
                                    <input
                                        type="username"
                                        className="form-control"
                                        placeholder="아이디를 입력해주세요"
                                        id="username"
                                        name="username"
                                        value={inputs.username}
                                        onChange={onChange}
                                    />
                                </div>
                                <div className="form-group">
                                    <label
                                        htmlFor="password"
                                        className="font-size-4 text-black-2 font-weight-semibold line-height-reset"
                                    >
                                        비밀번호
                                    </label>
                                    <div className="position-relative">
                                        <input
                                            type={
                                                showPass ? 'password' : 'text'
                                            }
                                            className="form-control"
                                            id="password"
                                            placeholder="비밀번호를 입력해주세요"
                                            name="password"
                                            value={inputs.password}
                                            onChange={onChange}
                                        />
                                        <a
                                            href="/#"
                                            className="show-password pos-abs-cr fas mr-6 text-black-2"
                                            onClick={(e) => {
                                                e.preventDefault();
                                                togglePassword();
                                            }}
                                        >
                                            <span className="d-none">none</span>
                                        </a>
                                    </div>
                                </div>
                                <div className="form-group d-flex flex-wrap justify-content-between">
                                    <label
                                        htmlFor="terms-check"
                                        className="gr-check-input d-flex  mr-3"
                                    >
                                        <input
                                            className="d-none"
                                            type="checkbox"
                                            id="terms-check"
                                        />
                                        <span className="checkbox mr-5"></span>
                                        <span className="font-size-3 mb-0 line-height-reset mb-1 d-block">
                                            Remember password
                                        </span>
                                    </label>
                                    <a
                                        href="/#"
                                        className="font-size-3 text-dodger line-height-reset"
                                    >
                                        비밀번호 찾기
                                    </a>
                                </div>
                                <div className="form-group mb-8">
                                    <button
                                        className="btn btn-primary btn-medium w-100 rounded-5 text-uppercase"
                                        onClick={() => {
                                            dispatch(signin(inputs));
                                            gContext.toggleSignInModal();
                                            handleClose();
                                        }}
                                    >
                                        로그인
                                    </button>
                                </div>
                                <p className="font-size-4 text-center heading-default-color">
                                    계정이 없으신가요?{' '}
                                    <a href="/#" className="text-primary">
                                        회원가입하기
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </Modal.Body>
        </ModalStyled>
    );
};

export default ModalSignIn;
