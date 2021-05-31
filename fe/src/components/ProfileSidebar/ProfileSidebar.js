import React, { useEffect } from 'react'
import { Link } from 'gatsby'

import imgP from '../../assets/image/l3/png/pro-img.png'
import { useDispatch, useSelector } from 'react-redux'
import { actorSelctor, getActorInfo } from '../../state/reducer/actor.reducer'

const Sidebar = () => {
    const dispatch = useDispatch()
    const state = useSelector(actorSelctor)

    useEffect(() => {
        dispatch(getActorInfo())
    }, [dispatch])

    return (
        <>
            <div className="bg-white shadow-9 rounded-4">
                <div className="px-5 py-11 text-center border-bottom border-mercury">
                    <Link to="/#" className="mb-4">
                        <img className="circle-54" src={imgP} alt="" />
                    </Link>
                    <h4 className="mb-0 text-black-2 font-size-6 font-weight-semibold">{state.actor.name}</h4>
                    <p className="mb-8 text-gray font-size-4">{state.actor.agency}</p>
                    <div className="icon-link d-flex align-items-center justify-content-center flex-wrap">
                        <Link to="/actor-info">
                            <button className="btn btn-primary text-uppercase font-size-3">회원정보입력하기</button>
                        </Link>
                    </div>
                </div>
                <div className="px-9 pt-lg-5 pt-9 pt-xl-9 pb-5">
                    <h5 className="text-black-2 mb-8 font-size-5">Contact Info</h5>
                    <div className="mb-7">
                        <p className="font-size-4 mb-0">E-mail</p>
                        <h5 className="font-size-4 font-weight-semibold mb-0">
                            <div className="text-black-2 text-break">{state.actor.email}</div>
                        </h5>
                    </div>
                    <div className="mb-7">
                        <p className="font-size-4 mb-0">Phone</p>
                        <h5 className="font-size-4 font-weight-semibold mb-0">
                            <div className="text-black-2 text-break">{state.actor.phone}</div>
                        </h5>
                    </div>
                    <div className="mb-7">
                        <p className="font-size-4 mb-0">년도</p>
                        <h5 className="font-size-4 font-weight-semibold mb-0">
                            <div className="text-black-2 text-break">{state.actor.birthday}</div>
                        </h5>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Sidebar
