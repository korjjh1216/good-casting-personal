import React, { useContext, useEffect } from 'react';
import { Link } from 'gatsby';
import { Collapse } from 'react-bootstrap';
import GlobalContext from '../../context/GlobalContext';
import imgL from '../../assets/image/logo-main-black.png';
import Logo from '../Logo';
import { useDispatch } from 'react-redux';
import { getActorInfo } from '../../state/reducer/actor.reducer';

const ActorSidebar = () => {
    const gContext = useContext(GlobalContext);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getActorInfo());
    }, []);

    return (
        <>
            <Collapse in={gContext.showSidebarActor}>
                <div className="dashboard-sidebar-wrapper pt-11" id="sidebar">
                    <div className="brand-logo  px-11">
                        <Logo white={gContext.header.theme === 'dark'} />
                    </div>
                    <div className="my-15 px-11">
                        <Link
                            to="/actor-register"
                            className="btn btn-primary btn-xl w-100 text-uppercase"
                        >
                            <span className="mr-5 d-inline-block">+</span>프로필
                            등록하기
                        </Link>
                    </div>
                    <ul className="list-unstyled dashboard-layout-sidebar">
                        <li className="">
                            <Link
                                activeClassName="active"
                                to="/actor-mypage"
                                className="px-10 py-1 my-5 font-size-4 font-weight-semibold flex-y-center"
                            >
                                <i className="icon icon-layout-11 mr-7"></i>
                                프로필 리스트
                            </Link>
                        </li>
                        <li className="">
                            <Link
                                to="/actor-applylist"
                                activeClassName="active"
                                className="px-10 py-1 my-5 font-size-4 font-weight-semibold flex-y-center"
                            >
                                <i className="fas fa-user mr-7"></i>지원 리스트
                            </Link>
                        </li>
                        <li className="">
                            <Link
                                to="/actor-info"
                                activeClassName="active"
                                className="px-10 py-1 my-5 font-size-4 font-weight-semibold flex-y-center"
                            >
                                <i className="fas fa-cog mr-7"></i>정보수정
                            </Link>
                        </li>
                    </ul>
                </div>
            </Collapse>
            <a
                href="/#"
                className="sidebar-mobile-button"
                onClick={(e) => {
                    e.preventDefault();
                    gContext.toggleShowSidebarActor();
                }}
            >
                <i className="icon icon-sidebar-2"></i>
            </a>
        </>
    );
};

export default ActorSidebar;
