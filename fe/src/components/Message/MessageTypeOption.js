import React, { useEffect, useState } from 'react';
import { Link } from 'gatsby';
import { components } from '@atlaskit/select';

const MessageTypeOption = ({ children, ...props }) => {
    const { Option } = components;
    const { value } = props.data;

    const [receiver, setReceiver] = useState('');

    useEffect(() => {
        const user = JSON.parse(localStorage.getItem('USER'));
        console.log(user);
        setReceiver(user[0].userId);
    }, []);
    return (
        <>
            <Link to={`/${value}`}>
                <Option {...props}>{children}</Option>
            </Link>
        </>
    );
};

export default MessageTypeOption;
