// components/organisms/RegistrationForm.tsx
import React, { useState } from 'react';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { AuthForm } from '../molecules/AuthForm';
import { Api } from "@/types/api";
import {Link, useNavigate} from 'react-router-dom';
import {Button} from "@/appComponents/atoms/Button.tsx";
import {GoogleAuthButton} from "@/appComponents/atoms/GoogleAuthButton.tsx";

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
                    <GoogleAuthButton/>
                </CardContent>
            </Card>
        </div>
    );
}
