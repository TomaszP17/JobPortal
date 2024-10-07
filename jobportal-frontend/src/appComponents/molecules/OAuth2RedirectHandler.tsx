import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import {useSearchParams} from "react-router-dom";
import {Spinner} from "@/appComponents/atoms/Spinner.tsx";

interface DecodedToken {
    email: string;
    scope: string;
}

export const OAuth2RedirectHandler: React.FC = () => {
    const navigate = useNavigate();
    const [urlParams] = useSearchParams();

    useEffect(() => {
        const requiresAdditionalInfo = urlParams.get("requiresAdditionalInfo");

        if (requiresAdditionalInfo){
            const name = urlParams.get("name") || '';
            const email = urlParams.get("email") || '';
            navigate(`/complete-profile?name=${encodeURIComponent(name)}&email=${encodeURIComponent(email)}`);
        }
        else {
            navigate(`/`);
        }
    }, [navigate, urlParams]);

    return <Spinner/>;
};
