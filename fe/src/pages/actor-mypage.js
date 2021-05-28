import React, { useEffect } from 'react';
import { Link } from 'gatsby';
import PageWrapper from '../components/PageWrapper';
import ProfileList from '../components/Profile/ProfileList';
import ProfileSidebar from '../components/ProfileSidebar';
import { actorList } from '../state/reducer/actor.reducer';
import { useDispatch } from 'react-redux';

const userInfo = JSON.parse(localStorage.getItem('USER'));

const ActorMypage = () => {
    useEffect(() => {
        console.log(userInfo);
        dispatch(actorList());
    }, []);

    const dispatch = useDispatch();

    return (
        <>
            <PageWrapper>
                <div className="bg-default-1 pt-26 pt-lg-28 pb-13 pb-lg-25">
                    <div className="container">
                        <div className="row">
                            <div className="col-12 col-xxl-3 col-lg-4 col-md-5 mb-11 mb-lg-0">
                                <ProfileSidebar />
                            </div>
                            <div className="col-12 col-md-8 col-xs-12 ">
                                <div className="pt-12 ml-lg-0 ml-md-15">
                                    <div className="pt-6">
                                        <Link to="/profile-register">
                                            <button className="btn btn-primary text-uppercase font-size-3">
                                                프로필등록하기
                                            </button>
                                        </Link>
                                        <div className="row justify-content-center">
                                            <div className="col-12 col-lg-6">
                                                <ProfileList />
                                            </div>
                                            <div className="col-12 col-lg-6"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </PageWrapper>
        </>
    );
};
export default ActorMypage;
