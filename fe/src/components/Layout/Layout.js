import React, { useState, useEffect, useLayoutEffect, useContext, useRef } from 'react';

import styled, { ThemeProvider } from 'styled-components';
import { Helmet } from 'react-helmet';
import AOS from 'aos';

import Header from '../Header';
import Footer from '../Footer';

import SidebarDashboard from '../SidebarDashboard/SidebarDashboard';
import ActorSidebar from '../SidebarDashboard/ActorSidebar';
import ModalVideo from '../ModalVideo';
import ModalApplication from '../ModalApplication';
import ModalSignIn from '../ModalSignIn';
import ModalSignUp from '../ModalSignUp';

import GlobalContext from '../../context/GlobalContext';

import GlobalStyle from '../../utils/globalStyle';

import imgFavicon from '../../assets/favicon.png';

import '../../assets/fonts/fontawesome-5/webfonts/fa-brands-400.ttf';
import '../../assets/fonts/fontawesome-5/webfonts/fa-regular-400.ttf';
import '../../assets/fonts/fontawesome-5/webfonts/fa-solid-900.ttf';

import '../../assets/fonts/icon-font/fonts/avasta.ttf';
import '../../assets/fonts/icon-font/css/style.css';

import '../../../node_modules/slick-carousel/slick/slick.css';
import '../../../node_modules/slick-carousel/slick/slick-theme.css';
import '../../../node_modules/aos/dist/aos.css';

import '../../assets/fonts/icon-font/css/style.css';
import '../../assets/fonts/fontawesome-5/css/all.css';

import '../../scss/bootstrap.scss';
import '../../scss/main.scss';

import { get, merge } from 'lodash';

// the full theme object
import { theme as baseTheme } from '../../utils';

const Loader = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background: #fff;
    z-index: 9999999999;
    opacity: 1;
    visibility: visible;
    transition: all 1s ease-out 0.5s;
    &.inActive {
        opacity: 0;
        visibility: hidden;
    }
`;

// options for different color modes
const modes = { light: 'light', dark: 'dark' };

// merge the color mode with the base theme
// to create a new theme object
const getTheme = (mode) =>
    merge({}, baseTheme, {
        colors: get(baseTheme.colors.modes, mode, baseTheme.colors),
    });

const Layout = ({ children, pageContext }) => {
    const gContext = useContext(GlobalContext);

    const [visibleLoader, setVisibleLoader] = useState(true);

    useLayoutEffect(() => {
        AOS.init({ once: true });
        setVisibleLoader(false);
    }, []);

    // Navbar style based on scroll
    const eleRef = useRef();

    useEffect(() => {
        window.addEventListener(
            'popstate',
            function(event) {
                // The popstate event is fired each time when the current history entry changes.
                gContext.closeOffCanvas();
            },
            false
        );
        window.addEventListener(
            'pushState',
            function(event) {
                // The pushstate event is fired each time when the current history entry changes.
                gContext.closeOffCanvas();
            },
            false
        );
    }, [gContext]);

    if (pageContext.layout === 'bare') {
        return (
            <ThemeProvider theme={gContext.themeDark ? getTheme(modes.dark) : getTheme(modes.light)}>
                <div data-theme-mode-panel-active data-theme="light">
                    <GlobalStyle />
                    <Helmet>
                        <title>JustCamp</title>
                        <link rel="icon" type="image/png" href={imgFavicon} />
                    </Helmet>
                    <Loader id="loading" className={visibleLoader ? '' : 'inActive'}>
                        <div className="load-circle">
                            <span className="one"></span>
                        </div>
                    </Loader>
                    <div className="site-wrapper overflow-hidden" ref={eleRef}>
                        {children}
                    </div>

                    <ModalVideo />
                    <ModalApplication />
                    <ModalSignIn />
                    <ModalSignUp />
                </div>
            </ThemeProvider>
        );
    }

    if (pageContext.layout === 'dashboard') {
        return (
            <ThemeProvider theme={gContext.themeDark ? getTheme(modes.dark) : getTheme(modes.light)}>
                <div data-theme-mode-panel-active data-theme="light">
                    <GlobalStyle />
                    <Helmet>
                        <title>JustCamp</title>
                        <link rel="icon" type="image/png" href={imgFavicon} />
                    </Helmet>
                    <Loader id="loading" className={visibleLoader ? '' : 'inActive'}>
                        <div className="load-circle">
                            <span className="one"></span>
                        </div>
                    </Loader>
                    <div className="site-wrapper overflow-hidden bg-default-2" ref={eleRef}>
                        <Header isDark={gContext.headerDark} />
                        <SidebarDashboard />

                        {children}
                    </div>

                    <ModalVideo />
                    <ModalApplication />
                    <ModalSignIn />
                    <ModalSignUp />
                </div>
            </ThemeProvider>
        );
    }

    if (pageContext.layout === 'actor-') {
        return (
            <ThemeProvider theme={gContext.themeDark ? getTheme(modes.dark) : getTheme(modes.light)}>
                <div data-theme-mode-panel-active data-theme="light">
                    <GlobalStyle />
                    <Helmet>
                        <title>JustCamp</title>
                        <link rel="icon" type="image/png" href={imgFavicon} />
                    </Helmet>
                    <Loader id="loading" className={visibleLoader ? '' : 'inActive'}>
                        <div className="load-circle">
                            <span className="one"></span>
                        </div>
                    </Loader>
                    <div className="site-wrapper overflow-hidden bg-default-2" ref={eleRef}>
                        <Header isDark={gContext.headerDark} />
                        <ActorSidebar />

                        {children}
                    </div>

                    <ModalVideo />
                    <ModalApplication />
                    <ModalSignIn />
                    <ModalSignUp />
                </div>
            </ThemeProvider>
        );
    }

    return (
        <>
            <ThemeProvider theme={gContext.themeDark ? getTheme(modes.dark) : getTheme(modes.light)}>
                <div data-theme-mode-panel-active data-theme="light">
                    <GlobalStyle />
                    <Helmet>
                        <title>JustCamp</title>
                        <link rel="icon" type="image/png" href={imgFavicon} />
                    </Helmet>
                    <Loader id="loading" className={visibleLoader ? '' : 'inActive'} />
                    <div className="site-wrapper overflow-hidden" ref={eleRef}>
                        <Header isDark={gContext.headerDark} />
                        {children}

                        <Footer isDark={gContext.footerDark} />
                    </div>

                    <ModalVideo />
                    <ModalApplication />
                    <ModalSignIn />
                    <ModalSignUp />
                </div>
            </ThemeProvider>
        </>
    );
};

export default Layout;
