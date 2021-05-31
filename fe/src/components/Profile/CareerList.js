import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
    deleteCareer,
    profileSelector,
} from '../../state/reducer/profile.reducer';

const CareerList = () => {
    const dispatch = useDispatch();
    const state = useSelector(profileSelector);

    return (
        <>
            <div id="temp_career career-form">
                {state.careerList.map((career) => {
                    return (
                        <div
                            key={career.id}
                            className="input-group career_item"
                        >
                            <input
                                type="hidden"
                                name="careerTypeArr"
                                value="C"
                            />
                            <div class="input-group-prepend wp32">
                                <input
                                    type="text"
                                    className="form-control"
                                    value={career.year}
                                    readOnly
                                />
                            </div>
                            <div className="input-group-prepend wp32">
                                <input
                                    type="text"
                                    className="form-control"
                                    value={career.gerne}
                                    readOnly
                                />
                            </div>

                            <div className="input-group-prepend wp32">
                                <input
                                    type="text"
                                    maxLength="120"
                                    name="title"
                                    className="form-control"
                                    placeholder="작품명 입력"
                                    value={career.title}
                                    readOnly
                                />
                            </div>
                            <input
                                type="text"
                                maxLength="200"
                                name="contents"
                                className="form-control careerContent"
                                placeholder="주/조연, 배역, 배역 설명 등"
                                value={career.contents}
                                readOnly
                            />
                        </div>
                    );
                })}
                <button
                    onClick={() => {
                        dispatch(deleteCareer(state.careerList.id));
                    }}
                    className="btn_input_delete"
                    type="button"
                >
                    삭제
                </button>
            </div>
        </>
    );
};

export default CareerList;
