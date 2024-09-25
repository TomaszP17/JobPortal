import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import {jwtDecode} from "jwt-decode";
import { storeTokens } from '@/lib/utils.ts';

interface DecodedToken {
    email: string;
    scope: string;
}

export const OAuth2RedirectHandler: React.FC = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const accessToken = urlParams.get('accessToken');
        const refreshToken = urlParams.get('refreshToken');
        const requiresAdditionalInfo = urlParams.get('requiresAdditionalInfo') === 'true';

        if (accessToken && refreshToken) {
            storeTokens(accessToken, refreshToken);

            const decodedToken = jwtDecode<DecodedToken>(accessToken);

            console.log(decodedToken);

            if (requiresAdditionalInfo) {
                navigate('/complete-profile', {
                    state: {
                        email: decodedToken.email,
                        scope: decodedToken.scope,
                    },
                });
            } else {
                navigate('/');
            }
        } else {
            console.error('Access token or refresh token is missing');
            navigate('/login');
        }
    }, [navigate]);

    return <div>Processing OAuth redirect...</div>;
};
