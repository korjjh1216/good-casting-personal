import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { messageSelector, messageList } from '../../state/reducer/message.reducer';

const MessageDropdown = () => {
    const dispatch = useDispatch();
    const msgList = useSelector(messageSelector).messageList;

    useEffect(() => {
        dispatch(messageList());
        console.log(msgList);
        console.log(msgList.actionType);
    }, [dispatch]);

    return (
        <>
            <p className="dropdown-item py-2 font-size-3 font-weight-semibold line-height-1p2 text-uppercase">ㄹㅇㄹㄴㄹㄴㅇ</p>
            {msgList.map((msg) => {
                return <p className="dropdown-item py-2 font-size-3 font-weight-semibold line-height-1p2 text-uppercase">{msg.actionType}</p>;
            })}
        </>
    );
};

export default MessageDropdown;
