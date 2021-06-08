import React, { useState, useCallback } from 'react';
import { Select } from '../components/Core';
import PageWrapper from '../components/PageWrapper';
import MessageList from '../components/Message/MessageList';

const MessageBox = () => {
    const defaultTypes = [
        { value: 'all', label: '전체', name: 'messageActionType' },
        { value: 'SUPPORT', label: '지원', name: 'messageActionType' },
        { value: 'PASS', label: '합격', name: 'messageActionType' },
        { value: 'MESSAGE', label: '메시지', name: 'messageActionType' },
        { value: 'CONTACT', label: '연락 ', name: 'messageActionType' },
    ];

    const [select, setSelect] = useState('');

    return (
        <>
            <PageWrapper>
                <div className="bg-default-2 pt-16 pb-12 pt-lg-22 pb-lg-27">
                    <div className="container">
                        <div className="row justify-content-center mt-14">
                            <div className="col-xxl-6 col-xl-7 col-lg-8">
                                <div className="row mb-11 align-items-center">
                                    <div className="col-lg-6 mb-lg-0 mb-4">
                                        <h3 className="font-size-6 mb-0">메시지함</h3>
                                    </div>
                                    <div className="col-lg-6">
                                        <div className="d-flex flex-wrap align-items-center justify-content-lg-end">
                                            <p className="font-size-4 mb-0 mr-6 py-2">검색 :</p>
                                            <div className="h-px-48">
                                                <Select
                                                    options={defaultTypes}
                                                    className="pl-0 h-100 arrow-3 arrow-3-black min-width-px-273  text-black-2 d-flex align-items-center w-100"
                                                    border={false}
                                                    name="messageActionType"
                                                    onChange={(e) => {
                                                        setSelect(e.value);
                                                    }}
                                                />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="bg-white px-9 pt-9 pb-7 shadow-8 rounded-4">
                                    <MessageList select={select} />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};

export default MessageBox;
