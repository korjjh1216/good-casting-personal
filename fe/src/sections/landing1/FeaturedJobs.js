import React from "react";
import { Link } from "gatsby";
import imgF1 from "../../assets/image/l1/png/feature-brand-1.png";
import imgF2 from "../../assets/image/l1/png/feature-brand-2.png";
import imgF3 from "../../assets/image/l1/png/feature-brand-3.png";
import imgF4 from "../../assets/image/l1/png/feature-brand-4.png";
import imgF5 from "../../assets/image/l1/png/feature-brand-5.png";
import imgF6 from "../../assets/image/l1/png/feature-brand-6.png";

const FeaturedJobs = () => {
  return (
    <>
      {/* <!-- FeaturedJobs Area -->  */}
      <div className="pt-11 pt-lg-27 pb-7 pb-lg-26 bg-black-2 dark-mode-texts">
        <div className="container">
          {/* <!-- Section Top --> */}
          <div className="row align-items-center pb-14">
            {/* <!-- Section Title --> */}
            <div className="col-12 col-xl-6 col-lg-6">
              <div className="text-center text-lg-left mb-13 mb-lg-0">
                <h2 className="font-size-9 font-weight-bold">Featured Jobs</h2>
              </div>
            </div>
            {/* <!-- Section Button --> */}
            <div className="col-12 col-xl-6 col-lg-6">
              <div className="text-center text-lg-right">
                <Link to="/#" className="btn btn-outline-white text-uppercase">
                  Explore All
                </Link>
              </div>
            </div>
            {/* <!-- Section Button End --> */}
          </div>
          {/* <!-- End Section Top --> */}
          <div className="row justify-content-center">
            <div
              className="col-12 col-lg-4 col-md-6 px-xxl-7"
              data-aos="fade-up"
              data-aos-duration="800"
            >
              {/* <!-- Start Feature One --> */}
              <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                <div className="d-block mb-7">
                  <Link to="/#">
                    <img src={imgF1} alt="" />
                  </Link>
                </div>
                <Link to="/#" className="font-size-3 d-block mb-0 text-gray">
                  Google INC
                </Link>
                <h2 className="mt-n4">
                  <Link
                    to="/#"
                    className="font-size-7 text-black-2 font-weight-bold mb-4"
                  >
                    Product Designer
                  </Link>
                </h2>
                <ul className="list-unstyled mb-1 card-tag-list">
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-denim font-size-3 rounded-3"
                    >
                      <i className="icon icon-pin-3 mr-2 font-weight-bold"></i>{" "}
                      Berlyn
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-orange font-size-3 rounded-3"
                    >
                      <i className="fa fa-briefcase mr-2 font-weight-bold"></i>{" "}
                      Full-time
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-eastern font-size-3 rounded-3"
                    >
                      <i className="fa fa-dollar-sign mr-2 font-weight-bold"></i>{" "}
                      80K-90K
                    </Link>
                  </li>
                </ul>
                <p className="mb-7 font-size-4 text-gray">
                  We are looking for Enrollment Advisors who are looking to take
                  30-35 appointments per week. All leads are pre-scheduled.
                </p>
                <div className="card-btn-group">
                  <Link
                    to="/#"
                    className="btn btn-green text-uppercase btn-medium rounded-3"
                  >
                    Apply Now
                  </Link>
                  <Link
                    to="/#"
                    className="btn btn-outline-mercury text-black-2 text-uppercase btn-medium rounded-3"
                  >
                    <i className="icon icon-bookmark-2 font-weight-bold mr-4 font-size-4"></i>{" "}
                    Save it
                  </Link>
                </div>
              </div>
              {/* <!-- End Feature One --> */}
            </div>
            <div
              className="col-12 col-lg-4 col-md-6 px-xxl-7"
              data-aos="fade-up"
              data-aos-duration="800"
            >
              {/* <!-- Start Feature One --> */}
              <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                <div className="d-block mb-7">
                  <Link to="/#">
                    <img src={imgF2} alt="" />
                  </Link>
                </div>
                <Link to="/#" className="font-size-3 d-block mb-0 text-gray">
                  AirBnb
                </Link>
                <h2 className="mt-n4">
                  <Link
                    to="/#"
                    className="font-size-7 text-black-2 font-weight-bold mb-4"
                  >
                    UI/UX Designer
                  </Link>
                </h2>
                <ul className="list-unstyled mb-1 card-tag-list">
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-denim font-size-3 rounded-3"
                    >
                      <i className="icon icon-pin-3 mr-2 font-weight-bold"></i>{" "}
                      Berlyn
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-orange font-size-3 rounded-3"
                    >
                      <i className="fa fa-briefcase mr-2 font-weight-bold"></i>{" "}
                      Full-time
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-eastern font-size-3 rounded-3"
                    >
                      <i className="fa fa-dollar-sign mr-2 font-weight-bold"></i>{" "}
                      80K-90K
                    </Link>
                  </li>
                </ul>
                <p className="mb-7 font-size-4 text-gray">
                  We are looking for Enrollment Advisors who are looking to take
                  30-35 appointments per week. All leads are pre-scheduled.
                </p>
                <div className="card-btn-group">
                  <Link
                    to="/#"
                    className="btn btn-green text-uppercase btn-medium rounded-3"
                  >
                    Apply Now
                  </Link>
                  <Link
                    to="/#"
                    className="btn btn-outline-mercury text-black-2 text-uppercase btn-medium rounded-3"
                  >
                    <i className="icon icon-bookmark-2 font-weight-bold mr-4 font-size-4"></i>{" "}
                    Save it
                  </Link>
                </div>
              </div>
              {/* <!-- End Feature One --> */}
            </div>
            <div
              className="col-12 col-lg-4 col-md-6 px-xxl-7"
              data-aos="fade-up"
              data-aos-duration="800"
            >
              {/* <!-- Start Feature One --> */}
              <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                <div className="d-block mb-7">
                  <Link to="/#">
                    <img src={imgF3} alt="" />
                  </Link>
                </div>
                <Link to="/#" className="font-size-3 d-block mb-0 text-gray">
                  Shopify
                </Link>
                <h2 className="mt-n4">
                  <Link
                    to="/#"
                    className="font-size-7 text-black-2 font-weight-bold mb-4"
                  >
                    iOS Developer
                  </Link>
                </h2>
                <ul className="list-unstyled mb-1 card-tag-list">
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-denim font-size-3 rounded-3"
                    >
                      <i className="icon icon-pin-3 mr-2 font-weight-bold"></i>{" "}
                      Berlyn
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-orange font-size-3 rounded-3"
                    >
                      <i className="fa fa-briefcase mr-2 font-weight-bold"></i>{" "}
                      Full-time
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-eastern font-size-3 rounded-3"
                    >
                      <i className="fa fa-dollar-sign mr-2 font-weight-bold"></i>{" "}
                      80K-90K
                    </Link>
                  </li>
                </ul>
                <p className="mb-7 font-size-4 text-gray">
                  We are looking for Enrollment Advisors who are looking to take
                  30-35 appointments per week. All leads are pre-scheduled.
                </p>
                <div className="card-btn-group">
                  <Link
                    to="/#"
                    className="btn btn-green text-uppercase btn-medium rounded-3"
                  >
                    Apply Now
                  </Link>
                  <Link
                    to="/#"
                    className="btn btn-outline-mercury text-black-2 text-uppercase btn-medium rounded-3"
                  >
                    <i className="icon icon-bookmark-2 font-weight-bold mr-4 font-size-4"></i>{" "}
                    Save it
                  </Link>
                </div>
              </div>
              {/* <!-- End Feature One --> */}
            </div>
            <div
              className="col-12 col-lg-4 col-md-6 px-xxl-7"
              data-aos="fade-up"
              data-aos-duration="800"
            >
              {/* <!-- Start Feature One --> */}
              <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                <div className="d-block mb-7">
                  <Link to="/#">
                    <img src={imgF4} alt="" />
                  </Link>
                </div>
                <Link to="/#" className="font-size-3 d-block mb-0 text-gray">
                  Uber
                </Link>
                <h2 className="mt-n4">
                  <Link
                    to="/#"
                    className="font-size-7 text-black-2 font-weight-bold mb-4"
                  >
                    Creative Director{" "}
                  </Link>
                </h2>
                <ul className="list-unstyled mb-1 card-tag-list">
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-denim font-size-3 rounded-3"
                    >
                      <i className="icon icon-pin-3 mr-2 font-weight-bold"></i>{" "}
                      Berlyn
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-orange font-size-3 rounded-3"
                    >
                      <i className="fa fa-briefcase mr-2 font-weight-bold"></i>{" "}
                      Full-time
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-eastern font-size-3 rounded-3"
                    >
                      <i className="fa fa-dollar-sign mr-2 font-weight-bold"></i>{" "}
                      80K-90K
                    </Link>
                  </li>
                </ul>
                <p className="mb-7 font-size-4 text-gray">
                  We are looking for Enrollment Advisors who are looking to take
                  30-35 appointments per week. All leads are pre-scheduled.
                </p>
                <div className="card-btn-group">
                  <Link
                    to="/#"
                    className="btn btn-green text-uppercase btn-medium rounded-3"
                  >
                    Apply Now
                  </Link>
                  <Link
                    to="/#"
                    className="btn btn-outline-mercury text-black-2 text-uppercase btn-medium rounded-3"
                  >
                    <i className="icon icon-bookmark-2 font-weight-bold mr-4 font-size-4"></i>{" "}
                    Save it
                  </Link>
                </div>
              </div>
              {/* <!-- End Feature One --> */}
            </div>
            <div
              className="col-12 col-lg-4 col-md-6 px-xxl-7"
              data-aos="fade-up"
              data-aos-duration="800"
            >
              {/* <!-- Start Feature One --> */}
              <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                <div className="d-block mb-7">
                  <Link to="/#">
                    <img src={imgF5} alt="" />
                  </Link>
                </div>
                <Link to="/#" className="font-size-3 d-block mb-0 text-gray">
                  Facebook
                </Link>
                <h2 className="mt-n4">
                  <Link
                    to="/#"
                    className="font-size-7 text-black-2 font-weight-bold mb-4"
                  >
                    Marketing Manager{" "}
                  </Link>
                </h2>
                <ul className="list-unstyled mb-1 card-tag-list">
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-denim font-size-3 rounded-3"
                    >
                      <i className="icon icon-pin-3 mr-2 font-weight-bold"></i>{" "}
                      Berlyn
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-orange font-size-3 rounded-3"
                    >
                      <i className="fa fa-briefcase mr-2 font-weight-bold"></i>{" "}
                      Full-time
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-eastern font-size-3 rounded-3"
                    >
                      <i className="fa fa-dollar-sign mr-2 font-weight-bold"></i>{" "}
                      80K-90K
                    </Link>
                  </li>
                </ul>
                <p className="mb-7 font-size-4 text-gray">
                  We are looking for Enrollment Advisors who are looking to take
                  30-35 appointments per week. All leads are pre-scheduled.
                </p>
                <div className="card-btn-group">
                  <Link
                    to="/#"
                    className="btn btn-green text-uppercase btn-medium rounded-3"
                  >
                    Apply Now
                  </Link>
                  <Link
                    to="/#"
                    className="btn btn-outline-mercury text-black-2 text-uppercase btn-medium rounded-3"
                  >
                    <i className="icon icon-bookmark-2 font-weight-bold mr-4 font-size-4"></i>{" "}
                    Save it
                  </Link>
                </div>
              </div>
              {/* <!-- End Feature One --> */}
            </div>
            <div
              className="col-12 col-lg-4 col-md-6 px-xxl-7"
              data-aos="fade-up"
              data-aos-duration="800"
            >
              {/* <!-- Start Feature One --> */}
              <div className="bg-white px-8 pt-9 pb-7 rounded-4 mb-9 feature-cardOne-adjustments">
                <div className="d-block mb-7">
                  <Link to="/#">
                    <img src={imgF6} alt="" />
                  </Link>
                </div>
                <Link to="/#" className="font-size-3 d-block mb-0 text-gray">
                  Oculus
                </Link>
                <h2 className="mt-n4">
                  <Link
                    to="/#"
                    className="font-size-7 text-black-2 font-weight-bold mb-4"
                  >
                    Software Engineer
                  </Link>
                </h2>
                <ul className="list-unstyled mb-1 card-tag-list">
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-denim font-size-3 rounded-3"
                    >
                      <i className="icon icon-pin-3 mr-2 font-weight-bold"></i>{" "}
                      Berlyn
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-orange font-size-3 rounded-3"
                    >
                      <i className="fa fa-briefcase mr-2 font-weight-bold"></i>{" "}
                      Full-time
                    </Link>
                  </li>
                  <li>
                    <Link
                      to="/#"
                      className="bg-regent-opacity-15 text-eastern font-size-3 rounded-3"
                    >
                      <i className="fa fa-dollar-sign mr-2 font-weight-bold"></i>{" "}
                      80K-90K
                    </Link>
                  </li>
                </ul>
                <p className="mb-7 font-size-4 text-gray">
                  We are looking for Enrollment Advisors who are looking to take
                  30-35 appointments per week. All leads are pre-scheduled.
                </p>
                <div className="card-btn-group">
                  <Link
                    to="/#"
                    className="btn btn-green text-uppercase btn-medium rounded-3"
                  >
                    Apply Now
                  </Link>
                  <Link
                    to="/#"
                    className="btn btn-outline-mercury text-black-2 text-uppercase btn-medium rounded-3"
                  >
                    <i className="icon icon-bookmark-2 font-weight-bold mr-4 font-size-4"></i>{" "}
                    Save it
                  </Link>
                </div>
              </div>
              {/* <!-- End Feature One --> */}
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default FeaturedJobs;
