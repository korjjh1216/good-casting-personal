import React, { useCallback, useState, useEffect } from 'react'
import PageWrapper from '../components/PageWrapper'
import { useDispatch, useSelector } from 'react-redux'
import { producerSelctor, updateProducerInfo, getProducerInfo } from '../state/reducer/producer.reducer'

const DashboardSettings = () => {
    const dispatch = useDispatch()

    const state = useSelector(producerSelctor)

    const [inputs, setInputs] = useState([])
    useEffect(() => {
        setInputs(state.producer)
    }, [])

    const handleSubmit = (e) => {
        e.preventDefault()
        console.log(inputs)
        dispatch(updateProducerInfo(inputs))
    }

    const handleChange = useCallback((e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        })
    })

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
                <div className="dashboard-main-container mt-24 mt-lg-31" id="dashboard-body">
                    <div className="container">
                        <div className="mb-15 mb-lg-23">
                            <div className="row">
                                <div className="col-xxxl-9 px-lg-13 px-6">
                                    <h5 className="font-size-6 font-weight-semibold mb-11">Update Company Profile</h5>
                                    <div className="contact-form bg-white shadow-8 rounded-4 pl-sm-10 pl-4 pr-sm-11 pr-4 pt-15 pb-13">
                                        <div className="upload-file mb-16 text-center">
                                            <div id="userActions" className="square-144 m-auto px-6 mb-7">
                                                <label htmlFor="fileUpload" className="mb-0 font-size-4 text-smoke">
                                                    Browse or Drag and Drop
                                                </label>
                                                <input type="file" id="fileUpload" className="sr-only" />
                                            </div>
                                        </div>
                                        <form onSubmit={handleSubmit} method="post">
                                            <fieldset>
                                                <div className="row mb-xl-1 mb-9">
                                                    <div className="col-lg-6">
                                                        <div className="form-group">
                                                            <label htmlFor="namedash" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                Agency
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" value={inputs.agency} name="agency" onChange={handleChange} placeholder="소속사를 입력해주세요" />
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group">
                                                            <label htmlFor="namedash" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                Name
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" placeholder="이름을 입력해주세요." value={inputs.name} name="name" onChange={handleChange} />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="row mb-8">
                                                    <div className="col-lg-6">
                                                        <div className="form-group">
                                                            <label htmlFor="namedash" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                Phone
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" placeholder="예) 010-1234-5678" value={inputs.phone} name="phone" onChange={handleChange} />
                                                        </div>
                                                    </div>
                                                    <div className="col-lg-6">
                                                        <div className="form-group">
                                                            <label htmlFor="namedash" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                Position
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" placeholder="직책을 입력해주세요." value={inputs.position} name="position" onChange={handleChange} />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="row mb-8">
                                                    <div className="col-lg-6">
                                                        <div className="form-group">
                                                            <label htmlFor="namedash" className="d-block text-black-2 font-size-4 font-weight-semibold mb-4">
                                                                E-mail
                                                            </label>
                                                            <input type="text" className="form-control h-px-48" id="namedash" placeholder="예)goodCasting@gmail.com" value={inputs.email} name="email" onChange={handleChange} />
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
export default DashboardSettings
