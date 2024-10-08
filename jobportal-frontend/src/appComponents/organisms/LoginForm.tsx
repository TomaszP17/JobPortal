import React, {useState} from 'react';
import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "@/components/ui/card";
import {AuthForm} from '../molecules/AuthForm';
import {Api, type LoginRequestDTO} from "@/types/api.ts";
import {Link, useNavigate} from 'react-router-dom';
import {Button} from "@/appComponents/atoms/Button.tsx";
import {GoogleAuthButton} from "@/appComponents/atoms/GoogleAuthButton.tsx";

export function LoginForm() {
    const navigate = useNavigate();
    const [loginForm, setLoginForm] = useState<LoginRequestDTO>({
        email: "",
        password: "",
    });

    const apiClient = new Api();

    const handleFormInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const {id, value} = event.target;
        setLoginForm((prevData) => ({
            ...prevData,
            [id]: value,
        }));
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            const res = await apiClient.api.login(loginForm);

            if (res.ok) {
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
                            <span className="w-full border-t"/>
                        </div>
                        <div className="relative flex justify-center text-xs uppercase">
                            <span className="bg-background px-2 text-muted-foreground">Or continue with</span>
                        </div>
                    </div>
                    <GoogleAuthButton/>
                </CardContent>
            </Card>
        </div>
    )
}
