export const userLoggedIn = () => {
    return localStorage.getItem('token') !== null;
};
