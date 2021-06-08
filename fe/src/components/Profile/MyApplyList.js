import React, { useContext, useEffect } from 'react';
import { Link } from 'gatsby';
import { Select } from '../Core';
import { useSelector, useDispatch } from 'react-redux';
import { applylist, applySelector, deleteApply } from '../../state/reducer/apply.reducer';
import PageListComponent from '../Core/PageList';
import { hireSelector, resetStatus } from '../../state/reducer/hire.reducer';
const defaultJobs = [
    { value: 'all', label: '전체' },
    { value: 'applydate', label: '지원날짜별' },
    { value: 'enddate', label: '마감날짜별' },
];
const MyApplyList = () => {
    const dispatch = useDispatch();
    const pageRequest = useSelector(applySelector).pageRequest;
    const pageResult = useSelector(applySelector).pageResult;
    const { status } = useSelector(hireSelector);
    const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null;
    useEffect(() => {
        console.log('ApplicantList pageRequest: ' + JSON.stringify(pageRequest));
        dispatch(
            applylist({
                ...pageRequest,
                actorId: userInfo[1].actorId,
            })
        );
        dispatch(resetStatus());
    }, [status]);
    console.log(status);
    return (
        <div style={{ height: '1500px' }} className="container">
            <div className="mb-14">
                <div className="row mb-11 align-items-center">
                    <div className="col-lg-6 mb-lg-0 mb-4">
                        <h3 className="font-size-6 mb-0">지원 리스트</h3>
                    </div>
                    <div className="col-lg-6">
                        <div className="d-flex flex-wrap align-items-center justify-content-lg-end">
                            <p className="font-size-4 mb-0 mr-6 py-2">검색 :</p>
                            <div className="h-px-48">
                                <Select options={defaultJobs} className="pl-0 h-100 arrow-3 arrow-3-black min-width-px-273  text-black-2 d-flex align-items-center w-100" border={false} />
                            </div>
                        </div>
                    </div>
                </div>
                <div className="bg-white shadow-8 pt-7 rounded pb-8 px-11">
                    <div className="table-responsive">
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col" className="pl-0  border-0 font-size-4 font-weight-normal">
                                        이름
                                    </th>
                                    <th scope="col" className="border-0 font-size-4 font-weight-normal">
                                        지원한 작품
                                    </th>
                                    <th scope="col" className="border-0 font-size-4 font-weight-normal">
                                        지원 날짜
                                    </th>
                                    <th scope="col" className="border-0 font-size-4 font-weight-normal"></th>
                                    <th scope="col" className="border-0 font-size-4 font-weight-normal"></th>
                                    <th scope="col" className="border-0 font-size-4 font-weight-normal"></th>
                                </tr>
                            </thead>
                            <tbody>
                                {pageResult.dtoList.map((apply) => {
                                    return (
                                        <>
                                            <tr className="border border-color-2">
                                                <th scope="row" className="pl-6 border-0 py-7 pr-0">
                                                    <div className="media min-width-px-235 align-items-center">
                                                        <h4 className="font-size-4 mb-0 font-weight-semibold text-black-2">{apply.profile.actorName}</h4>
                                                    </div>
                                                </th>
                                                <td className="table-y-middle py-7 min-width-px-235 pr-0">
                                                    <h3 className="font-size-4 font-weight-normal text-black-2 mb-0">{apply.hire.title}</h3>
                                                </td>
                                                <td className="table-y-middle py-7 min-width-px-170 pr-0">
                                                    <h3 className="font-size-4 font-weight-normal text-black-2 mb-0">{apply.modDate.slice(0, 10)}</h3>
                                                </td>
                                                <td className="table-y-middle py-7 min-width-px-170 pr-0">
                                                    <div className="">
                                                        <Link
                                                            state={{
                                                                id: apply.profile.profileId,
                                                            }}
                                                            to="/profile-detail"
                                                            className="font-size-3 font-weight-bold text-black-2 text-uppercase"
                                                        >
                                                            프로필보기
                                                        </Link>
                                                    </div>
                                                </td>
                                                <td className="table-y-middle py-7 min-width-px-100">
                                                    <a
                                                        href="#"
                                                        className="font-size-3 font-weight-bold text-red-2 text-uppercase"
                                                        onClick={(e) => {
                                                            e.preventDefault();
                                                            dispatch(deleteApply(apply.applyId));
                                                        }}
                                                    >
                                                        지원 취소
                                                    </a>
                                                </td>
                                            </tr>
                                        </>
                                    );
                                })}
                            </tbody>
                        </table>
                    </div>
                    <PageListComponent pageRequest={pageRequest} pageResult={pageResult} flag={'applicantList'} />
                </div>
            </div>
        </div>
    );
};
export default MyApplyList;
