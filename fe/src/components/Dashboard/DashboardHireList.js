import React, { useContext, useEffect } from 'react';
import { Link } from 'gatsby';
import { Select } from '../Core';
import { useSelector, useDispatch } from 'react-redux';
import { hireSelector, myHireList } from '../../state/reducer/hire.reducer';
import PageListComponent from '../Core/PageList';

const defaultJobs = [
    { value: 'current', label: '선택하세요' },
    { value: 'all', label: '전체' },
    { value: 'project', label: '프로젝트별' },
    { value: 'deadline', label: '마감순' },
];

const DashboardHireList = () => {
    const dispatch = useDispatch();

    const pageRequest = useSelector(hireSelector).pageRequest;
    const pageResult = useSelector(hireSelector).pageResult;

    const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null;

    useEffect(() => {
        console.log('myHireList pageRequest: ' + JSON.stringify(pageRequest));
        dispatch(
            myHireList({
                ...pageRequest,
                producerId: userInfo[1].producerId,
            })
        );
    }, []);

    return (
        <>
            <div className="container">
                <div className="mb-14">
                    <div className="row mb-11 align-items-center">
                        <div className="col-lg-6 mb-lg-0 mb-4">
                            <h3 className="font-size-6 mb-0">공고문 리스트</h3>
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
                    <div className="bg-white shadow-8 pt-7 rounded pb-9 px-11">
                        <div className="table-responsive ">
                            <table className="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col" className="pl-0 border-0 font-size-4 font-weight-normal">
                                            프로젝트명
                                        </th>
                                        <th scope="col" className="pl-4 border-0 font-size-4 font-weight-normal">
                                            모집 역할
                                        </th>
                                        <th scope="col" className="pl-4 border-0 font-size-4 font-weight-normal">
                                            업로드일
                                        </th>
                                        <th scope="col" className="pl-4 border-0 font-size-4 font-weight-normal">
                                            마감일
                                        </th>
                                        <th scope="col" className="pl-4 border-0 font-size-4 font-weight-normal">
                                            모집인원
                                        </th>
                                        <th scope="col" className="pl-4 border-0 font-size-4 font-weight-normal">
                                            지원자수
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {pageResult.dtoList.map((list) => {
                                        return (
                                            <>
                                                <tr className="border border-color-2" key={list.hireId}>
                                                    <th scope="row" className="pl-6 border-0 py-7 min-width-px-235">
                                                        <div className="">
                                                            <Link to="/hire-detail" className="font-size-4 mb-0 font-weight-semibold text-black-2">
                                                                {list.project}
                                                            </Link>
                                                        </div>
                                                    </th>
                                                    <td className="table-y-middle py-7 min-width-px-135">
                                                        <h3 className="font-size-4 font-weight-normal text-black-2 mb-0">{list.cast}</h3>
                                                    </td>
                                                    <td className="table-y-middle py-7 min-width-px-125">
                                                        <h3 className="font-size-4 font-weight-normal text-black-2 mb-0">{list.regDate.slice(0, 10)}</h3>
                                                    </td>
                                                    <td className="table-y-middle py-7 min-width-px-155">
                                                        <h3 className="font-size-4 font-weight-normal text-black-2 mb-0">{list.deadline.slice(0, 10)}</h3>
                                                    </td>
                                                    <td className="table-y-middle py-7 min-width-px-125">
                                                        <h3 className="font-size-4 font-weight-bold text-black-2 mb-0">{list.personnel}</h3>
                                                    </td>
                                                    <td className="table-y-middle py-7 min-width-px-205">
                                                        <h3 className="font-size-4 font-weight-bold text-black-2 mb-0">어찌할꺼야?</h3>
                                                    </td>
                                                    <td className="table-y-middle py-7 min-width-px-80">
                                                        <Link
                                                            to="/hire-modify"
                                                            className="font-size-3
                                                        font-weight-bold
                                                        text-green
                                                        text-uppercase"
                                                        >
                                                            수정하기
                                                        </Link>
                                                    </td>
                                                    <td className="table-y-middle py-7 min-width-px-100">
                                                        <a href="#" className="font-size-3 font-weight-bold text-red-2 text-uppercase">
                                                            삭제하기
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
        </>
    );
};

export default DashboardHireList;
