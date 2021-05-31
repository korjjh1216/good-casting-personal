import React, { useCallback, useState } from 'react';
import { Select } from '../Core';
import { addCareer } from '../../state/reducer/profile.reducer';
import { useDispatch } from 'react-redux';
import CareerList from './CareerList';

import '../../scss/css/career.css';

const defaultYear = [
    { value: '0', label: '연도선택', name: 'year' },
    { value: '2021', label: '2021년', name: 'year' },
    { value: '2020', label: '2020년', name: 'year' },
    { value: '2019', label: '2019년', name: 'year' },
    { value: '2018', label: '2018년', name: 'year' },
    { value: '2017', label: '2017년', name: 'year' },
    { value: '2016', label: '2016년', name: 'year' },
    { value: '2015', label: '2015년', name: 'year' },
    { value: '2014', label: '2014년', name: 'year' },
    { value: '2013', label: '2013년', name: 'year' },
    { value: '2012', label: '2012년', name: 'year' },
    { value: '2011', label: '2011년 이전', name: 'year' },
];

const defaultGerne = [
    { value: '0', label: '작품유형', name: 'gerne' },
    { value: '드라마', label: '드라마', name: 'gerne' },
    { value: '영화', label: '영화', name: 'gerne' },
    { value: '웹드라마', label: '웹드라마', name: 'gerne' },
    { value: '공연', label: '공연', name: 'gerne' },
];

const ProfileCareer = () => {
    const dispatch = useDispatch();

    const [inputs, setInputs] = useState({});

    const handleSelectChagne = useCallback((e) => {
        console.log(e.value);
        setInputs({
            ...inputs,
            [e.name]: e.value,
        });
    });

    const handleChagne = useCallback((e) => {
        e.preventDefault();
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        });
    });

    return (
        <>
            <div id="temp_career">
                <div class="input-group career_item">
                    <input type="hidden" name="careerTypeArr" value="C" />
                    <div class="input-group-prepend wp16">
                        <Select
                            className="form-control pl-0 arrow-3 w-100 font-size-4 d-flex align-items-center w-100 "
                            boarder={false}
                            options={defaultYear}
                            onChange={handleSelectChagne}
                        />
                    </div>

                    <div class="input-group-prepend wp16">
                        <Select
                            className="form-control pl-0 arrow-3 w-100 font-size-4 d-flex align-items-center w-100 "
                            boarder={false}
                            options={defaultGerne}
                            onChange={handleSelectChagne}
                        />
                    </div>

                    <div class="input-group-prepend wp32">
                        <input
                            type="text"
                            maxLength="120"
                            name="title"
                            class="form-control"
                            placeholder="작품명 입력"
                            onChange={handleChagne}
                        />
                    </div>
                    <input
                        type="text"
                        maxLength="200"
                        name="contents"
                        class="form-control careerContent"
                        placeholder="주/조연, 배역, 배역 설명 등"
                        onChange={handleChagne}
                    />
                    <button
                        className="btn_input_add"
                        type="button"
                        onClick={() => {
                            dispatch(addCareer(inputs));
                            setInputs('');
                        }}
                    >
                        추가
                    </button>
                </div>
            </div>
            <CareerList />
        </>
    );
};

export default ProfileCareer;
