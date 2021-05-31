import React from 'react'
import { Link } from 'gatsby'
import { useSelector } from 'react-redux'
import PageWrapper from '../components/PageWrapper'
import ProfileSidebar from '../components/ProfileSidebar'
import { profileSelector } from '../state/reducer/profile.reducer'
import MyProfileList from '../components/Profile/MyprofileList'

const ActorMypage = () => {
    const state = useSelector(profileSelector)
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
                                <div className="pt-6">
                                    <Link to="/profile-register">
                                        <button className="btn btn-primary text-uppercase font-size-3">프로필등록하기</button>
                                    </Link>
                                    <div className="pt-6 row justify-content-center">
                                        {state.profile !== null ? (
                                            <div className="col-12 col-lg-6">
                                                <MyProfileList />
                                            </div>
                                        ) : (
                                            <p>프로필없음</p>
                                        )}
                                        <div className="col-12 col-lg-6"></div>
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
export default ActorMypage
