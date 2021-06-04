import React, { useState, useEffect } from 'react';
import { Collapse } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import {
    messageSelector,
    messageList,
} from '../../state/reducer/message.reducer';
import MessageActionType from '../../utils/MessageActionType';

const MessageList = () => {
    const dispatch = useDispatch();

    const msgList = useSelector(messageSelector).messageList;

    const [openItem, setOpenItem] = useState(1);

    useEffect(() => {
        dispatch(messageList());
        console.log(msgList);
    }, []);

    console.log(MessageActionType[msgList[0].messageActionType]);

    return (
        <>
            <div className="border-bottom overflow-hidden">
                <div className="mb-0 border-bottom-0" id="heading2-4">
                    {msgList.map(() => {
                        return (
                            <button
                                className="btn-reset font-size-5 font-weight-semibold text-left px-0 pb-6 pt-7 accordion-trigger arrow-icon w-100 border-left-0 border-right-0 focus-reset mt-n2"
                                type="button"
                                onClick={() => setOpenItem(4)}
                                aria-expanded={openItem === 4}
                            >
                                How does the Jobium.com work?
                            </button>
                        );
                    })}
                </div>
                <Collapse in={openItem === 4}>
                    <div className="pr-7">
                        <div className="mt-n3 font-size-4 text-gray font-weight-normal pb-7 pr-7 pt-6">
                            Yes. You can cancel your subscription anytime. Your
                            subscription will continue to be active until the
                            end of your current term (month or year) but it will
                            not auto-renew. Unless you delete your account
                            manually, your account and all data will be deleted
                            60 days from the day your subscription becomes
                            inactive.
                        </div>
                    </div>
                </Collapse>
            </div>
        </>
    );
};

export default MessageList;
