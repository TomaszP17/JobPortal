let isRefreshing = false;
let refreshSubscribers: Array<() => void> = [];

const addRefreshSubscriber = (callback: () => void) => {
    refreshSubscribers.push(callback);
};

const onRefreshed = () => {
    refreshSubscribers.forEach((callback) => callback());
    refreshSubscribers = [];
};

export const customFetch: typeof fetch = async (input, init = {}) => {
    init.credentials = 'include';

    let response = await fetch(input, init);

    if (response.status === 401) {
        if (!isRefreshing) {
            isRefreshing = true;
            try {
                const refreshResponse = await fetch('http://localhost:8080/api/auth/refresh', {
                    method: 'POST',
                    credentials: 'include',
                });

                if (refreshResponse.ok) {
                    isRefreshing = false;
                    onRefreshed();

                    response = await fetch(input, init);
                } else {
                    isRefreshing = false;
                    window.location.href = '/login';
                    throw new Error('Sesja wygasła. Proszę zalogować się ponownie.');
                }
            } catch (error) {
                isRefreshing = false;
                window.location.href = '/login';
                throw error;
            }
        } else {
            await new Promise<void>((resolve) => {
                addRefreshSubscriber(() => {
                    resolve();
                });
            });
            response = await fetch(input, init);
        }
    }

    return response;
};
