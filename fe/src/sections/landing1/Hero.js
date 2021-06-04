import React from 'react';
import { Link } from 'gatsby';
import imgH from '../../assets/image/l1/png/hero-image.png';
import imgP from '../../assets/image/patterns/hero-pattern.png';

const Hero = () => {
    return (
        <>
            <div className="pt-26 pt-md-32 pt-lg-33 pt-xl-35 position-relative z-index-1 overflow-hidden">
                <div className="pos-abs-tr w-50 z-index-n2">
                    <img src={imgP} alt="" className="gr-opacity-1" />
                </div>
                <div className="container">
                    <div className="row position-relative align-items-center">
                        <div className="col-xxl-6 col-xl-7 col-lg-8 col-md-12 pt-lg-13 pb-lg-33 pb-xl-34 pb-md-33 pb-10" data-aos="fade-right" data-aos-duration="1000" data-aos-delay="500">
                            <h1 className="font-size-11 mb-12 pr-md-30 pr-lg-0">
                                텍스트가 아닌 이미지로 <br /> 원하는 캐릭터의 배우를 <br />
                                찾아드립니다
                            </h1>
                            <div className="button-block">
                                <Link to="/profile-list">
                                    <button className="btn btn-primary line-height-reset h-500 btn-submit w-50 text-uppercase">배우 검색</button>
                                </Link>
                            </div>
                        </div>
                        <div className="col-lg-6 col-md-4 col-sm-6 col-xs-6 col-8 pos-abs-br z-index-n1 position-static position-md-absolute mx-auto ml-md-auto" data-aos="fade-left" data-aos-duration="1000" data-aos-delay="500">
                            <div className=" ml-xxl-23 ml-xl-12 ml-md-7">
                                <img src={imgH} alt="" className="w-100" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default Hero;
