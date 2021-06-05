exports.onCreatePage = ({ page, actions }) => {
    const { createPage } = actions;

    if (page.path.match(/404/)) {
        page.context.layout = 'bare';
        createPage(page);
    }

    if (page.path.match(/dashboard/)) {
        page.context.layout = 'dashboard';
        createPage(page);
    }

    if (page.path.match(/actor-mypage/)) {
        page.context.layout = 'actor-mypage';
        createPage(page);
    }
};
