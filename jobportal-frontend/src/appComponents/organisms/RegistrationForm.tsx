// components/organisms/RegistrationForm.tsx
import React, { useState } from 'react';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { AuthForm } from '../molecules/AuthForm';
import { Api } from "@/types/api";
import {Link, useNavigate} from 'react-router-dom';
import {Button} from "@/appComponents/atoms/Button.tsx";

export function RegistrationForm() {
    const navigate = useNavigate();
    const apiClient = new Api();

    const [isCompanyAccount, setIsCompanyAccount] = useState(false);
    const [formDataCompany, setFormDataCompany] = useState({
        name: "",
        email: "",
        password: "",
        nip: "",
    });

    const [formDataCandidate, setFormDataCandidate] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
    });

    const handleCompanyInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = event.target;
        setFormDataCompany((prevData) => ({
            ...prevData,
            [id]: value,
        }));
    };

    const handleCandidateInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = event.target;
        setFormDataCandidate((prevData) => ({
            ...prevData,
            [id]: value,
        }));
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            if (isCompanyAccount) {
                await apiClient.api.createCompany(formDataCompany);
                alert("Company account created successfully!");
            } else {
                await apiClient.api.createCandidate(formDataCandidate);
                alert("Candidate account created successfully!");
            }
            navigate('/');
        } catch (error) {
            console.error("Error creating account:", error);
            alert("Failed to create account.");
        }
    };

    const fields = isCompanyAccount
        ? [
            {
                id: "name",
                label: "Company Name",
                type: "text",
                placeholder: "Enter your company name",
                value: formDataCompany.name,
                onChange: handleCompanyInputChange,
                required: true,
            },
            {
                id: "email",
                label: "Email",
                type: "email",
                placeholder: "Enter your email",
                value: formDataCompany.email,
                onChange: handleCompanyInputChange,
                required: true,
            },
            {
                id: "password",
                label: "Password",
                type: "password",
                placeholder: "Enter your password",
                value: formDataCompany.password,
                onChange: handleCompanyInputChange,
                required: true,
            },
            {
                id: "nip",
                label: "NIP",
                type: "text",
                placeholder: "Enter your NIP",
                value: formDataCompany.nip,
                onChange: handleCompanyInputChange,
                required: true,
            },
        ]
        : [
            {
                id: "firstName",
                label: "First Name",
                type: "text",
                placeholder: "Enter your first name",
                value: formDataCandidate.firstName,
                onChange: handleCandidateInputChange,
                required: true,
            },
            {
                id: "lastName",
                label: "Last Name",
                type: "text",
                placeholder: "Enter your last name",
                value: formDataCandidate.lastName,
                onChange: handleCandidateInputChange,
                required: true,
            },
            {
                id: "email",
                label: "Email",
                type: "email",
                placeholder: "Enter your email",
                value: formDataCandidate.email,
                onChange: handleCandidateInputChange,
                required: true,
            },
            {
                id: "password",
                label: "Password",
                type: "password",
                placeholder: "Enter your password",
                value: formDataCandidate.password,
                onChange: handleCandidateInputChange,
                required: true,
            },
        ];

    return (
        <div className="flex flex-col gap-y-10">
            <Card className="w-full max-w-md mx-auto min-w-96">
                <CardHeader>
                    <CardTitle>Create an Account</CardTitle>
                    <CardDescription>Sign up for a new account</CardDescription>
                </CardHeader>
                <CardContent>
                    <AuthForm
                        fields={fields}
                        onSubmit={handleSubmit}
                        submitButtonLabel="Register"
                        showSwitch={true}
                        switchLabel={isCompanyAccount ? "Company Account" : "Candidate Account"}
                        switchState={isCompanyAccount}
                        onSwitchChange={setIsCompanyAccount}
                    />

                    <div className="relative">
                        <div className="absolute inset-0 flex items-center">
                            <span className="w-full border-t" />
                        </div>
                        <div className="relative flex justify-center text-xs uppercase">
                            <span className="bg-background px-2 text-muted-foreground">Or continue with</span>
                        </div>
                    </div>
                    <Link to={`${import.meta.env.VITE_API_BASE_URL}oauth2/authorization/google?userType=${isCompanyAccount ? "company" : "candidate"}`}>
                        <Button variant="outline">
                            <svg
                                className="mr-2 h-4 w-4"
                                aria-hidden="true"
                                focusable="false"
                                data-prefix="fab"
                                data-icon="google"
                                role="img"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 488 512"
                            >
                                <path
                                    fill="currentColor"
                                    d="M488 261.8C488 403.3 391.1 504 248 504 110.8 504 0 393.2 0 256S110.8 8 248 8c66.8 0 123 24.5 166.3 64.9l-67.5 64.9C258.5 52.6 94.3 116.6 94.3 256c0 86.5 69.1 156.6 153.7 156.6 98.2 0 135-70.4 140.8-106.9H248v-85.3h236.1c2.3 12.7 3.9 24.9 3.9 41.4z"
                                ></path>
                            </svg>
                            Sign in with Google
                        </Button>
                    </Link>
                </CardContent>
            </Card>
        </div>
    );
}
