import React, { useState, useContext, useEffect } from 'react'
import styled from 'styled-components'
import { Link } from 'gatsby'
import { Container, Dropdown } from 'react-bootstrap'
import { useScrollPosition } from '@n8tb1t/use-scroll-position'

import { useWindowSize } from '../../hooks/useWindowSize'
import GlobalContext from '../../context/GlobalContext'
import Offcanvas from '../Offcanvas'
import NestedMenu from '../NestedMenu'
import { device } from '../../utils'
import Logo from '../Logo'
import { actorMenuItems, menuItems, producerMenuItems } from './menuItems'

import imgP from '../../assets/image/header-profile.png'
import { useDispatch, useSelector } from 'react-redux'
import { isUserLoggendIn, userSelector } from '../../state/reducer/user.reducer'

const SiteHeader = styled.header`
    .dropdown-toggle::after {
        opacity: 0;
    }

    padding: 10px 0 10px 0;
    position: absolute !important;
    top: 0;
    right: 0;
    width: 100%;
    z-index: 999;
    @media ${device.lg} {
        position: fixed !important;
        transition: 0.6s;
        &.scrolling {
            transform: translateY(-100%);
            transition: 0.6s;
        }
        &.reveal-header {
            transform: translateY(0%);
            box-shadow: 0 12px 34px -11px rgba(65, 62, 101, 0.1);
            z-index: 9999;
            background: ${({ dark, theme }) => (dark ? theme.colors.heading : theme.colors.light)};
        }
    }
`

const ToggleButton = styled.button`
    color: ${({ dark, theme }) => (dark ? theme.colors.lightShade : theme.colors.heading)}!important;
    border-color: ${({ dark, theme }) => (dark ? theme.colors.lightShade : theme.colors.heading)}!important;
`

const Header = () => {
    const dispatch = useDispatch()

    const [showScrolling, setShowScrolling] = useState(false)
    const [showReveal, setShowReveal] = useState(false)

    const gContext = useContext(GlobalContext)
    const size = useWindowSize()
    const user = useSelector(userSelector)
    const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('USER')) : null

    useEffect(() => {
        if (localStorage.getItem('USER') !== null) {
            dispatch(isUserLoggendIn(user.loggedIn))
            JSON.stringify(userInfo[0].position)
            console.log('로그인 되어 있음 : ' + userInfo[0].position)
        } else {
            console.log('로그인 되어 있지 않음')
        }
    }, [])

    useScrollPosition(({ prevPos, currPos }) => {
        if (currPos.y < 0) {
            setShowScrolling(true)
        } else {
            setShowScrolling(false)
        }
        if (currPos.y < -300) {
            setShowReveal(true)
        } else {
            setShowReveal(false)
        }
    })

    return (
        <>
            <SiteHeader
                className={`site-header site-header--sticky  site-header--absolute py-7 py-xs-0 sticky-header ${gContext.header.bgClass} ${gContext.header.align === 'left' ? 'site-header--menu-left ' : gContext.header.align === 'right' ? 'site-header--menu-right ' : 'site-header--menu-center '}
        ${gContext.header.theme === 'dark' ? 'dark-mode-texts' : ' '} ${showScrolling ? 'scrolling' : ''} ${gContext.header.reveal && showReveal && gContext.header.theme === 'dark' ? 'reveal-header bg-blackish-blue' : gContext.header.reveal && showReveal ? 'reveal-header' : ''}`}
            >
                <Container fluid={gContext.header.isFluid} className={gContext.header.isFluid ? 'pr-lg-9 pl-lg-9' : ''}>
                    <nav className="navbar site-navbar offcanvas-active navbar-expand-lg px-0 py-0">
                        <div className="brand-logo">
                            <Logo white={gContext.header.theme === 'dark'} />
                        </div>
                        <div className="collapse navbar-collapse">
                            <div className="navbar-nav-wrapper">
                                <ul className="navbar-nav main-menu d-none d-lg-flex">
                                    {!user.loggedIn
                                        ? menuItems.map(({ label, isExternal = false, name, items, ...rest }, index) => {
                                              return (
                                                  <React.Fragment key={name + index}>
                                                      <li className="nav-item" {...rest}>
                                                          {isExternal ? (
                                                              <a className="nav-link" href={`${name}`} target="_blank" rel="noopener noreferrer">
                                                                  {label}
                                                              </a>
                                                          ) : (
                                                              <Link className="nav-link" to={`/${name}`} role="button" aria-expanded="false">
                                                                  {label}
                                                              </Link>
                                                          )}
                                                      </li>
                                                  </React.Fragment>
                                              )
                                          })
                                        : user.loggedIn && userInfo[0].position
                                        ? actorMenuItems.map(({ label, isExternal = false, name, items, ...rest }, index) => {
                                              return (
                                                  <React.Fragment key={name + index}>
                                                      <li className="nav-item" {...rest}>
                                                          {isExternal ? (
                                                              <a className="nav-link" href={`${name}`} target="_blank" rel="noopener noreferrer">
                                                                  {label}
                                                              </a>
                                                          ) : (
                                                              <Link className="nav-link" to={`/${name}`} role="button" aria-expanded="false">
                                                                  {label}
                                                              </Link>
                                                          )}
                                                      </li>
                                                  </React.Fragment>
                                              )
                                          })
                                        : producerMenuItems.map(({ label, isExternal = false, name, items, ...rest }, index) => {
                                              return (
                                                  <React.Fragment key={name + index}>
                                                      <li className="nav-item" {...rest}>
                                                          {isExternal ? (
                                                              <a className="nav-link" href={`${name}`} target="_blank" rel="noopener noreferrer">
                                                                  {label}
                                                              </a>
                                                          ) : (
                                                              <Link className="nav-link" to={`/${name}`} role="button" aria-expanded="false">
                                                                  {label}
                                                              </Link>
                                                          )}
                                                      </li>
                                                  </React.Fragment>
                                              )
                                          })}
                                </ul>
                            </div>
                        </div>

                        {gContext.header.button === 'cta' && (
                            <div className="header-btn ml-auto ml-lg-0 mr-6 mr-lg-0 d-none d-xs-block">
                                <Link to="/#" className={`btn btn-${gContext.header.variant}`}>
                                    {gContext.header.buttonText}
                                </Link>
                            </div>
                        )}

                        {gContext.header.button === 'profile' && (
                            <div className="header-btn-devider ml-auto ml-lg-5 pl-2 d-none d-xs-flex align-items-center">
                                <div>
                                    <Link to="/#" className="px-3 ml-7 font-size-7 notification-block flex-y-center position-relative">
                                        <i className="fas fa-bell heading-default-color"></i>
                                        <span className="font-size-3 count font-weight-semibold text-white bg-primary circle-24 border border-width-3 border border-white">3</span>
                                    </Link>
                                </div>
                                <div>
                                    <Dropdown className="show-gr-dropdown py-5">
                                        <Dropdown.Toggle as="a" className="proile media ml-7 flex-y-center">
                                            <div className="circle-40">
                                                <img src={imgP} alt="" />
                                            </div>
                                            <i className="fas fa-chevron-down heading-default-color ml-6"></i>
                                        </Dropdown.Toggle>
                                        {size.width <= 991 ? (
                                            <Dropdown.Menu className="gr-menu-dropdown border-0 border-width-2 py-2 w-auto bg-default" key="1">
                                                <Link to="/#" className="dropdown-item py-2 font-size-3 font-weight-semibold line-height-1p2 text-uppercase">
                                                    Settings
                                                </Link>
                                                <Link to="/#" className="dropdown-item py-2 font-size-3 font-weight-semibold line-height-1p2 text-uppercase">
                                                    Edit Profile
                                                </Link>
                                                <Link to="/#" className=" dropdown-item py-2 text-red font-size-3 font-weight-semibold line-height-1p2 text-uppercase">
                                                    Log Out
                                                </Link>
                                            </Dropdown.Menu>
                                        ) : (
                                            <div className="dropdown-menu gr-menu-dropdown dropdown-right border-0 border-width-2 py-2 w-auto bg-default" key="2">
                                                <Link to="/#" className="dropdown-item py-2 font-size-3 font-weight-semibold line-height-1p2 text-uppercase">
                                                    Settings
                                                </Link>
                                                <Link to="/#" className="dropdown-item py-2 font-size-3 font-weight-semibold line-height-1p2 text-uppercase">
                                                    Edit Profile
                                                </Link>
                                                <Link to="/#" className=" dropdown-item py-2 text-red font-size-3 font-weight-semibold line-height-1p2 text-uppercase">
                                                    Log Out
                                                </Link>
                                            </div>
                                        )}
                                    </Dropdown>
                                </div>
                            </div>
                        )}

                        {user.loggedIn ? (
                            <div className="header-btns header-btn-devider ml-auto pr-2 ml-lg-6 d-none d-xs-flex">
                                <a
                                    className={`btn btn-${gContext.header.variant} text-uppercase font-size-3`}
                                    href="/"
                                    onClick={() => {
                                        window.confirm('정말 로그아웃 하시겠습니까?') ? localStorage.clear() : window.location.reload()
                                    }}
                                >
                                    LogOut
                                </a>
                            </div>
                        ) : (
                            <div className="header-btns header-btn-devider ml-auto pr-2 ml-lg-6 d-none d-xs-flex">
                                <a
                                    className="btn btn-transparent text-uppercase font-size-3 heading-default-color focus-reset"
                                    href="/#"
                                    onClick={(e) => {
                                        e.preventDefault()
                                        gContext.toggleSignInModal()
                                    }}
                                >
                                    Log In
                                </a>
                                <a
                                    className={`btn btn-${gContext.header.variant} text-uppercase font-size-3`}
                                    href="/#"
                                    onClick={(e) => {
                                        e.preventDefault()
                                        gContext.toggleSignUpModal()
                                    }}
                                >
                                    Sign Up
                                </a>
                            </div>
                        )}

                        <ToggleButton className={`navbar-toggler btn-close-off-canvas ml-3 ${gContext.visibleOffCanvas ? 'collapsed' : ''}`} type="button" data-toggle="collapse" data-target="#mobile-menu" aria-controls="mobile-menu" aria-expanded="false" aria-label="Toggle navigation" onClick={gContext.toggleOffCanvas} dark={gContext.header.theme === 'dark' ? 1 : 0}>
                            {/* <i className="icon icon-simple-remove icon-close"></i> */}
                            <i className="icon icon-menu-34 icon-burger d-block"></i>
                        </ToggleButton>
                    </nav>
                </Container>
            </SiteHeader>
            <Offcanvas show={gContext.visibleOffCanvas} onHideOffcanvas={gContext.toggleOffCanvas}>
                <NestedMenu menuItems={menuItems} />
            </Offcanvas>
        </>
    )
}
export default Header
