import React, { useState } from 'react';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { AuthForm } from '../molecules/AuthForm';
import { Api, type LoginRequestDTO } from "@/types/api.ts";
import { useNavigate } from 'react-router-dom';
import {Button} from "@/appComponents/atoms/Button.tsx";

export function LoginForm() {
    const navigate = useNavigate();
    const [loginForm, setLoginForm] = useState<LoginRequestDTO>({
        email: "",
        password: "",
    });

    const apiClient = new Api();

    const handleFormInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = event.target;
        setLoginForm((prevData) => ({
            ...prevData,
            [id]: value,
        }));
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            const res = await apiClient.api.login(loginForm);

            if(res.ok){
                navigate("/");
            } else {
                const errorJson = await res.json();
                console.log(errorJson);
                alert(errorJson.message || "Failed to log in.");
            }
        } catch (error) {
            console.error("Error logging in:", error);
            alert("Failed to log in.");
        }
    };

    const fields = [
        {
            id: "email",
            label: "Email",
            type: "email",
            placeholder: "Enter your email",
            value: loginForm.email,
            onChange: handleFormInputChange,
            required: true,
        },
        {
            id: "password",
            label: "Password",
            type: "password",
            placeholder: "Enter your password",
            value: loginForm.password,
            onChange: handleFormInputChange,
            required: true,
        },
    ];

    return (
        <div className="flex flex-col gap-y-10">
            <Card className="w-full max-w-md mx-auto">
                <CardHeader>
                    <CardTitle>Log In</CardTitle>
                    <CardDescription>Access your account</CardDescription>
                </CardHeader>
                <CardContent>
                    <AuthForm
                        fields={fields}
                        onSubmit={handleSubmit}
                        submitButtonLabel="Log In"
                        showSwitch={false}
                    />

                    <div className="relative">
                        <div className="absolute inset-0 flex items-center">
                            <span className="w-full border-t" />
                        </div>
                        <div className="relative flex justify-center text-xs uppercase">
                            <span className="bg-background px-2 text-muted-foreground">Or continue with</span>
                        </div>
                    </div>
                    <Button variant="outline" className="w-full" onClick={() => navigate(`/oauth2/authorization/google?userType=candidate`)}>
                        <svg className="mr-2 h-4 w-4" aria-hidden="true" focusable="false" data-prefix="fab"
                             data-icon="google" role="img" xmlns="http://www.w3.org/2000/svg"
                             viewBox="0 0 488 512">
                            <path fill="currentColor"
                                  d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"></path>
                        </svg>
                        Log in with Google
                    </Button>
                </CardContent>
            </Card>
        </div>
    )
}
