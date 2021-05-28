import React, { useCallback, useState, useEffect } from 'react'
import PageWrapper from '../components/PageWrapper'
import { Select } from '../components/Core'
import { useDispatch, useSelector } from 'react-redux'
import { actorSelctor, updateActorInfo, getActorInfo } from '../state/reducer/actor.reducer'

const defaultTypes = [
    { value: 'male', label: '남성' },
    { value: 'femail', label: '여성' },
]

const defaultMajor = [
    { value: true, label: '전공자' },
    { value: false, label: '비전공자' },
]

const ActorInfo = () => {
    const dispatch = useDispatch()

    const state = useSelector(actorSelctor)

    const [inputs, setInputs] = useState([])

    useEffect(() => {
        setInputs(state.actor)
    }, [])

    useEffect(() => {
        window.addEventListener('beforeunload', handleBeforeunload)
    }, [])

    const handleSubmit = (e) => {
        e.preventDefault()
        console.log(inputs)
        dispatch(updateActorInfo(inputs))
    }

    const handleChange = useCallback((e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        })
    })

    const handleBeforeunload = (e) => {
        if (handleChange) {
            e.preventDefault()
            e.returnValue = ''
            return ''
        }
        return undefined
    }

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
                    <div className="container">
                        <div className="mb-15 mb-lg-23">
                            <div className="row">
                                <div className="col-xxxl-9 px-lg-13 px-6">
                                    <h5 className="font-size-6 font-weight-semibold mb-11">회원정보 입력하기</h5>
                                    <div className="contact-form bg-white shadow-8 rounded-4 pl-sm-10 pl-4 pr-sm-11 pr-4 pt-15 pb-13">
                                        <form onSubmit={handleSubmit} method="post">
                                            <fieldset>
                                                <div className="row mb-xl-1 mb-9">
                                                    <div className="col-lg-6">
                                                        <div className="form-group">
                                                            <label htmlFor="namedash" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                이름
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.name} name="name" onChange={handleChange} />
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group">
                                                            <label htmlFor="select2" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                성별
                                                            </label>
                                                            <Select options={defaultTypes} className="form-control pl-0 arrow-3 w-100 font-size-4 d-flex align-items-center w-100 " border={false} />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="row mb-8">
                                                    <div className="col-lg-6 mb-xl-0 mb-7">
                                                        <div className="form-group position-relative">
                                                            <label htmlFor="select3" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                키(cm)
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.height} name="height" placeholder="숫자만 입력해주세요 예)180" onChange={handleChange} />
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group position-relative">
                                                            <label htmlFor="address" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                몸무게(kg)
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.weight} name="weight" placeholder="숫자만 입력해주세요 예)70" onChange={handleChange} />
                                                            <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6"></span>
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group position-relative">
                                                            <label htmlFor="address" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                생년
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.birthday} name="birthday" placeholder="숫자만 입력해주세요 예)2021" onChange={handleChange} />
                                                            <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6"></span>
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group position-relative">
                                                            <label htmlFor="address" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                나이
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.age} name="age" placeholder="" onChange={handleChange} />
                                                            <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6"></span>
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group position-relative">
                                                            <label htmlFor="address" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                이메일
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.email} name="email" placeholder="예)goodCasting@gmail.com" onChange={handleChange} />
                                                            <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6"></span>
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group position-relative">
                                                            <label htmlFor="address" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                전화번호
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.phone} name="phone" placeholder="예) 010-1234-5678" onChange={handleChange} />
                                                            <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6"></span>
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group position-relative">
                                                            <label htmlFor="address" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                소속사
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.agency} name="agency" placeholder="예) 010-1234-5678" onChange={handleChange} />
                                                            <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6"></span>
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group position-relative">
                                                            <label htmlFor="address" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                전공여부
                                                            </label>
                                                            <Select options={defaultMajor} className="form-control pl-0 arrow-3 w-100 font-size-4 d-flex align-items-center w-100 " border={false} />
                                                            <span className="h-100 w-px-50 pos-abs-tl d-flex align-items-center justify-content-center font-size-6"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-md-12">
                                                        <button className="btn btn-green btn-h-60 text-white min-width-px-210 rounded-5 text-uppercase">등록하기</button>
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
    )
}

export default ActorInfo
