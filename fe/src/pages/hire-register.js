import React, { useCallback, useEffect, useState } from 'react';
import { Tab } from 'react-bootstrap';
import { navigate } from 'gatsby';
import PageWrapper from '../components/PageWrapper';
import FileUpload from '../components/Core/FileUpload';
import { useDispatch, useSelector } from 'react-redux';
import { hireRegister, hireSelector, resetStatus } from '../state/reducer/hire.reducer';
import { producerSelector } from '../state/reducer/producer.reducer';
import { fileSelector } from '../state/reducer/file.reducer';
import '../scss/css/fileUpload.css';
import DatePickerComponent from '../components/DatePicker/DatePicker';

const HireRegister = () => {
    const dispatch = useDispatch();

    const fileState = useSelector(fileSelector);
    const { status } = useSelector(hireSelector);

    const { producerId } = JSON.parse(localStorage.getItem('USER'))[1];

    const [inputs, setInputs] = useState({
        producer: { producerId: producerId },
    });
    const [image, setImages] = useState(null);

    const [filming, setFilming] = useState(new Date());
    const [deadline, setDeadline] = useState(new Date());

    const handleSubmit = (e) => {
        e.preventDefault();
        dispatch(hireRegister(inputs));
    };
    const handleChange = useCallback(
        (e) => {
            setInputs({
                ...inputs,
                [e.target.name]: e.target.value,
            });
        },
        [inputs]
    );

    if (status === 'success') {
        navigate('/dashboard');
        dispatch(resetStatus());
    }

    useEffect(() => {
        setInputs({
            ...inputs,
            filming,
            deadline,
        });
    }, [filming, deadline]);

    useEffect(() => {
        setInputs({
            ...inputs,
            files: fileState.fileList,
        });
    }, [fileState]);

    return (
        <>
            <PageWrapper headerConfig={{ button: 'profile' }}>
                <div className="bg-default-2 pt-16 pt-lg-22 pb-lg-27">
                    <div className="container">
                        <div className="row justify-content-center">
                            <div className="col-12 mt-13 dark-mode-texts">
                                <div className="mb-9">
                                    <a
                                        onClick={(e) => {
                                            e.preventDefault();
                                            navigate(-1);
                                        }}
                                        href="/"
                                        className="d-flex align-items-center ml-4"
                                    >
                                        <i className="icon icon-smaxwll-left bg-white circle-40 mr-5 font-size-7 text-black font-weight-bold shadow-8" />
                                        <span className="text-uppercase font-size-3 font-weight-bold text-gray">Back</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="row ">
                            <form onSubmit={handleSubmit}>
                                <div className="col-12 col-xl-9 col-lg-8"></div>
                                <div className="bg-white rounded-4 pt-11 shadow-9">
                                    <div className="d-xs-flex align-items-center pl-xs-12 mb-8 text-center text-xs-left">
                                        <div style={{ height: '425px' }}>
                                            <label
                                                htmlFor="namedash"
                                                className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                            >
                                                공고 제목
                                            </label>
                                            <input
                                                type="text"
                                                className="form-control h-px-48 thumnail-margin"
                                                name="title"
                                                onChange={handleChange}
                                                value={inputs.title}
                                            />
                                            <FileUpload setImages={setImages} image={image} />
                                        </div>
                                        <hr />
                                        <Tab.Container id="left-tabs-example" defaultActiveKey="jobs">
                                            <Tab.Content className="pl-12 pt-10 pb-7 pr-12 pr-xxl-24">
                                                <Tab.Pane eventKey="jobs">
                                                    {/* <!-- Middle Body Start --> */}
                                                    <fieldset>
                                                        <div className="row mb-xl-1 mb-9">
                                                            <div className="col-lg-6">
                                                                <div className="form-group">
                                                                    <label
                                                                        htmlFor="namedash"
                                                                        className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                    >
                                                                        촬영 날짜
                                                                    </label>
                                                                    <DatePickerComponent setDate={setFilming} />
                                                                </div>
                                                            </div>
                                                            <div className="col-lg-6">
                                                                <div className="form-group">
                                                                    <label
                                                                        htmlFor="namedash"
                                                                        className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                    >
                                                                        마감날짜
                                                                    </label>
                                                                    <DatePickerComponent setDate={setDeadline} />
                                                                </div>
                                                            </div>
                                                            <div className="col-lg-6">
                                                                <div className="form-group">
                                                                    <label
                                                                        htmlFor="namedash"
                                                                        className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                    >
                                                                        작품제목
                                                                    </label>
                                                                    <input
                                                                        type="text"
                                                                        className="form-control h-px-48"
                                                                        id="namedash"
                                                                        name="project"
                                                                        onChange={handleChange}
                                                                        value={inputs.project}
                                                                    />
                                                                </div>
                                                            </div>
                                                            <div className="col-lg-6">
                                                                <div className="form-group">
                                                                    <label
                                                                        htmlFor="select2"
                                                                        className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                    >
                                                                        모집인원
                                                                    </label>
                                                                    <input
                                                                        type="text"
                                                                        className="form-control h-px-48"
                                                                        id="namedash"
                                                                        name="personnel"
                                                                        onChange={handleChange}
                                                                        value={inputs.personnel}
                                                                    />
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div className="row mb-8">
                                                            <div className="col-lg-6 mb-xl-0 mb-7">
                                                                <div className="form-group position-relative">
                                                                    <label
                                                                        htmlFor="select3"
                                                                        className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                    >
                                                                        모집역할
                                                                    </label>
                                                                    <input
                                                                        type="text"
                                                                        className="form-control h-px-48"
                                                                        id="namedash"
                                                                        name="cast"
                                                                        onChange={handleChange}
                                                                        value={inputs.cast}
                                                                    />
                                                                </div>
                                                            </div>
                                                            <div className="col-lg-6">
                                                                <div className="form-group position-relative">
                                                                    <label
                                                                        htmlFor="address"
                                                                        className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                    >
                                                                        급여
                                                                    </label>
                                                                    <input
                                                                        type="text"
                                                                        className="form-control h-px-48"
                                                                        id="namedash"
                                                                        name="guarantee"
                                                                        onChange={handleChange}
                                                                        value={inputs.guarantee}
                                                                    />
                                                                    <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6" />
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div className="row">
                                                            <div className="col-md-12">
                                                                <div className="form-group">
                                                                    <label
                                                                        htmlFor="aboutTextarea"
                                                                        className="d-block text-black-2 font-size-4 font-weight-semibold mb-4"
                                                                    >
                                                                        작품소개
                                                                    </label>
                                                                    <textarea
                                                                        name="contents"
                                                                        id="aboutTextarea"
                                                                        cols="30"
                                                                        rows="7"
                                                                        className="border border-mercury text-gray w-100 pt-4 pl-6"
                                                                        placeholder="Describe about the company what make it unique"
                                                                        onChange={handleChange}
                                                                        value={inputs.contents}
                                                                    />
                                                                </div>
                                                            </div>
                                                            <div className="row">
                                                                <button className="btn btn-green btn-h-60 text-white min-width-px-210 rounded-5 text-uppercase">
                                                                    등록하기
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </fieldset>
                                                </Tab.Pane>
                                            </Tab.Content>
                                        </Tab.Container>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};

export default HireRegister;
