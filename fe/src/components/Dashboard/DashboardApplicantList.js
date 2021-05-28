import React, { useContext } from 'react';
import { Link } from 'gatsby';
import { Select } from '../Core';
import GlobalContext from '../../context/GlobalContext';

import imgP1 from '../../assets/image/table-one-profile-image-1.png';

const defaultJobs = [
    { value: 'pd', label: 'Product Designer' },
    { value: 'all', label: '전체' },
];

const DashboardApplicantList = () => {
    const gContext = useContext(GlobalContext);

    return (
        <div className="container">
            <div className="mb-14">
                <div className="row mb-11 align-items-center">
                    <div className="col-lg-6 mb-lg-0 mb-4">
                        <h3 className="font-size-6 mb-0">지원자 리스트 (1)</h3>
                    </div>
                    <div className="col-lg-6">
                        <div className="d-flex flex-wrap align-items-center justify-content-lg-end">
                            <p className="font-size-4 mb-0 mr-6 py-2">검색 :</p>
                            <div className="h-px-48">
                                <Select
                                    options={defaultJobs}
                                    className="pl-0 h-100 arrow-3 arrow-3-black min-width-px-273  text-black-2 d-flex align-items-center w-100"
                                    border={false}
                                />
                            </div>
                        </div>
                    </div>
                </div>
                <div className="bg-white shadow-8 pt-7 rounded pb-8 px-11">
                    <div className="table-responsive">
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <th
                                        scope="col"
                                        className="pl-0  border-0 font-size-4 font-weight-normal"
                                    >
                                        이름
                                    </th>
                                    <th
                                        scope="col"
                                        className="border-0 font-size-4 font-weight-normal"
                                    >
                                        지원한 작품
                                    </th>
                                    <th
                                        scope="col"
                                        className="border-0 font-size-4 font-weight-normal"
                                    >
                                        지원 날짜
                                    </th>
                                    <th
                                        scope="col"
                                        className="border-0 font-size-4 font-weight-normal"
                                    ></th>
                                    <th
                                        scope="col"
                                        className="border-0 font-size-4 font-weight-normal"
                                    ></th>
                                    <th
                                        scope="col"
                                        className="border-0 font-size-4 font-weight-normal"
                                    ></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr className="border border-color-2">
                                    <th
                                        scope="row"
                                        className="pl-6 border-0 py-7 pr-0"
                                    >
                                        <div className="media min-width-px-235 align-items-center">
                                            <div className="circle-36 mr-6">
                                                <img
                                                    src={imgP1}
                                                    alt=""
                                                    className="w-100"
                                                />
                                            </div>
                                            <h4 className="font-size-4 mb-0 font-weight-semibold text-black-2">
                                                Nicolas Bradley
                                            </h4>
                                        </div>
                                    </th>
                                    <td className="table-y-middle py-7 min-width-px-235 pr-0">
                                        <h3 className="font-size-4 font-weight-normal text-black-2 mb-0">
                                            Senior Project Manager
                                        </h3>
                                    </td>
                                    <td className="table-y-middle py-7 min-width-px-170 pr-0">
                                        <h3 className="font-size-4 font-weight-normal text-black-2 mb-0">
                                            12 July, 2020
                                        </h3>
                                    </td>
                                    <td className="table-y-middle py-7 min-width-px-170 pr-0">
                                        <div className="">
                                            <a
                                                href="/#"
                                                className="font-size-3 font-weight-bold text-black-2 text-uppercase"
                                                onClick={(e) => {
                                                    e.preventDefault();
                                                    gContext.toggleApplicationModal();
                                                }}
                                            >
                                                프로필 보기
                                            </a>
                                        </div>
                                    </td>
                                    <td className="table-y-middle py-7 min-width-px-110 pr-0">
                                        <div className="">
                                            <Link
                                                to="/contact"
                                                className="font-size-3 font-weight-bold text-green text-uppercase"
                                            >
                                                합격
                                            </Link>
                                        </div>
                                    </td>
                                    <td className="table-y-middle py-7 min-width-px-100 pr-0">
                                        <div className="">
                                            <Link
                                                to="#"
                                                className="font-size-3 font-weight-bold text-red-2 text-uppercase"
                                            >
                                                불합격
                                            </Link>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div className="pt-2">
                        <nav aria-label="Page navigation example">
                            <ul className="pagination pagination-hover-primary rounded-0 ml-n2">
                                <li className="page-item rounded-0 flex-all-center">
                                    <a
                                        href="#"
                                        className="page-link rounded-0 border-0 px-3active"
                                        aria-label="Previous"
                                    >
                                        <i className="fas fa-chevron-left"></i>
                                    </a>
                                </li>
                                <li className="page-item">
                                    <a
                                        href="#"
                                        className="page-link border-0 font-size-4 font-weight-semibold px-3"
                                    >
                                        1
                                    </a>
                                </li>
                                <li className="page-item">
                                    <a
                                        href="#"
                                        className="page-link border-0 font-size-4 font-weight-semibold px-3"
                                    >
                                        2
                                    </a>
                                </li>
                                <li className="page-item">
                                    <a
                                        href="#"
                                        className="page-link border-0 font-size-4 font-weight-semibold px-3"
                                    >
                                        3
                                    </a>
                                </li>
                                <li className="page-item disabled">
                                    <a
                                        href="#"
                                        className="page-link border-0 font-size-4 font-weight-semibold px-3"
                                    >
                                        ...
                                    </a>
                                </li>
                                <li className="page-item ">
                                    <a
                                        href="#"
                                        className="page-link border-0 font-size-4 font-weight-semibold px-3"
                                    >
                                        7
                                    </a>
                                </li>
                                <li className="page-item rounded-0 flex-all-center">
                                    <a
                                        href="/#"
                                        className="page-link rounded-0 border-0 px-3"
                                        aria-label="Next"
                                    >
                                        <i className="fas fa-chevron-right"></i>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DashboardApplicantList;
