// src/api-client/index.ts

import { Api } from '../types/api.ts';
import { customFetch } from './customFetch';

const apiClient = new Api({
    baseUrl: 'http://localhost:8080',
    customFetch: customFetch,
    baseApiParams: {
        credentials: 'include',
    },
});

export default apiClient;
