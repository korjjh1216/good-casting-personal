import React, { useCallback, useContext, useEffect, useState } from 'react';
import styled from 'styled-components';
import { Modal } from 'react-bootstrap';
import GlobalContext from '../../context/GlobalContext';
import { useDispatch } from 'react-redux';
import { signup } from '../../state/reducer/user.reducer';
import Swal from 'sweetalert2';

const ModalStyled = styled(Modal)`
    /* &.modal {
    z-index: 10050;
  } */
`;

const ModalSignUp = (props) => {
    const dispatch = useDispatch();
    const gContext = useContext(GlobalContext);

    const [showPassFirst, setShowPassFirst] = useState(true);
    const [showPassSecond, setShowPassSecond] = useState(true);
    const [checkValidate, setCheckValidate] = useState('');

    const [inputs, setInputs] = useState({
        username: '',
        password: '',
        confirmPassword: '',
        account: null,
    });

    useEffect(() => {
        setCheckValidate(
            inputs.confirmPassword !== inputs.password ? 'red' : ''
        );
    }, []);

    const togglePasswordFirst = () => {
        setShowPassFirst(!showPassFirst);
    };

    const togglePasswordSecond = () => {
        setShowPassSecond(!showPassSecond);
    };

    const onChange = useCallback((e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        });
    });

    const handleClose = () => {
        setInputs({
            username: '',
            password: '',
            confirmPassword: '',
        });
        gContext.toggleSignUpModal();
    };
    //handleClose
    return (
        <>
            <ModalStyled
                {...props}
                size="lg"
                centered
                show={gContext.signUpModalVisible}
                onHide={handleClose}
            >
                <Modal.Body className="p-0">
                    <button
                        type="button"
                        className="circle-32 btn-reset bg-white pos-abs-tr mt-n6 mr-lg-n6 focus-reset shadow-10"
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
                                            회원가입
                                        </h3>
                                        <p className="mb-0 font-size-4 text-white">
                                            회원가입을 하여 굿캐스팅에서 <br />
                                            제공하는 기능들을 즐겨보세요!
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div className="col-lg-7 col-md-6">
                                <div className="bg-white-2 h-100 px-11 pt-11 pb-7">
                                    <div className="form-grop">
                                        <input
                                            type="radio"
                                            name="radio"
                                            onClick={() => {
                                                setInputs({
                                                    position: true,
                                                    account: true,
                                                });
                                            }}
                                        />
                                        <label
                                            className="font-size-4 text-black-2 font-weight-semibold line-height-reset"
                                            htmlFor="radio"
                                        >
                                            &nbsp;배우
                                        </label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <input
                                            type="radio"
                                            name="radio"
                                            onClick={() => {
                                                setInputs({
                                                    position: false,
                                                    account: true,
                                                });
                                            }}
                                        />
                                        <label
                                            className="font-size-4 text-black-2 font-weight-semibold line-height-reset"
                                            htmlFor="radio"
                                        >
                                            &nbsp;제작자
                                        </label>
                                    </div>
                                    <form onSubmit={(e) => e.preventDefault()}>
                                        <div className="form-group">
                                            <label
                                                htmlFor="username2"
                                                className="font-size-4 text-black-2 font-weight-semibold line-height-reset"
                                            >
                                                아이디
                                            </label>
                                            <input
                                                type="id"
                                                className="form-control"
                                                id="username2"
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
                                                        showPassFirst
                                                            ? 'password'
                                                            : 'text'
                                                    }
                                                    className="form-control"
                                                    id="password"
                                                    name="password"
                                                    value={inputs.password}
                                                    onChange={onChange}
                                                    style={{
                                                        backgroundColor: `${checkValidate}`,
                                                        opacity: '0.4',
                                                        borderColor: 'gray',
                                                    }}
                                                />
                                                <a
                                                    href="/#"
                                                    className="show-password pos-abs-cr fas mr-6 text-black-2"
                                                    onClick={(e) => {
                                                        e.preventDefault();
                                                        togglePasswordFirst();
                                                    }}
                                                >
                                                    <span className="d-none">
                                                        none
                                                    </span>
                                                </a>
                                            </div>
                                        </div>
                                        <div className="form-group">
                                            <label
                                                htmlFor="password2"
                                                className="font-size-4 text-black-2 font-weight-semibold line-height-reset"
                                            >
                                                비밀번호 확인
                                            </label>
                                            <div className="position-relative">
                                                <input
                                                    type={
                                                        showPassSecond
                                                            ? 'password'
                                                            : 'text'
                                                    }
                                                    className="form-control"
                                                    id="password2"
                                                    name="confirmPassword"
                                                    value={
                                                        inputs.confirmPassword
                                                    }
                                                    onChange={onChange}
                                                    style={{
                                                        backgroundColor: `${checkValidate}`,
                                                        opacity: '0.4',
                                                        borderColor: 'gray',
                                                    }}
                                                />

                                                <a
                                                    href="/#"
                                                    className="show-password pos-abs-cr fas mr-6 text-black-2"
                                                    onClick={(e) => {
                                                        e.preventDefault();
                                                        togglePasswordSecond();
                                                    }}
                                                >
                                                    <span className="d-none">
                                                        none
                                                    </span>
                                                </a>
                                            </div>
                                        </div>
                                        <div className="form-group d-flex flex-wrap justify-content-between mb-1">
                                            <label
                                                htmlFor="terms-check2"
                                                className="gr-check-input d-flex  mr-3"
                                            >
                                                <input
                                                    className="d-none"
                                                    type="checkbox"
                                                    id="terms-check2"
                                                />
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
                                                    if (
                                                        inputs.account !== null
                                                    ) {
                                                        dispatch(
                                                            signup(inputs)
                                                        );
                                                        gContext.toggleSignUpModal();
                                                    } else {
                                                        Swal.fire({
                                                            icon: 'error',
                                                            title:
                                                                '배우 / 제작자를 선택해주세요!',
                                                        });
                                                    }
                                                }}
                                            >
                                                회원가입
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </Modal.Body>
            </ModalStyled>
        </>
    );
};

export default ModalSignUp;
