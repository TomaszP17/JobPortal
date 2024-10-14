import React, { useEffect, useState } from 'react';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { AuthForm } from '../molecules/AuthForm';
import apiClient from "@/apiClient";
import {useNavigate, useSearchParams} from 'react-router-dom';

interface FormData {
    email: string;
    name: string;
    nip: string;
    firstName: string;
    lastName: string;
}


export const CompleteProfile: React.FC = () => {
    const navigate = useNavigate();
    const [urlParams] = useSearchParams();
    const [isCompany, setIsCompany] = useState<boolean>(false);
    const [name, setName] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [formData, setFormData] = useState<FormData>({
        email: '',
        name: '',
        nip: '',
        firstName: '',
        lastName: ''
    });

    useEffect(() => {
        const emailParam = urlParams.get('email') || '';
        const nameParam = urlParams.get('name') || '';
        setEmail(emailParam);
        setName(nameParam);
        setFormData({
            email: emailParam,
            name: isCompany ? nameParam : '',
            nip: '',
            firstName: isCompany ? '' : nameParam,
            lastName: '',
        });
    }, [urlParams, isCompany]);


    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = event.target;
        setFormData((prevData) => {
            if (!prevData) return prevData;
            return {
                ...prevData,
                [id]: value,
            };
        });
    };
    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            if (isCompany) {
                const { email, name, nip } = formData;
                await apiClient.api.createCompanyOauth({
                    email,
                    name,
                    nip
                })
                alert("Company profile completed successfully!");
            } else {
                const { email ,firstName, lastName } = formData;
                await apiClient.api.createCandidateOauth({
                    email,
                    firstName,
                    lastName
                });
                alert("Candidate profile completed successfully!");
            }
            navigate('/login');
        } catch (error) {
            console.error("Error completing profile:", error);
            alert("Failed to complete profile.");
        }
    };

    const fields = isCompany
        ? [
            {
                id: "email",
                label: "Email",
                type: "email",
                placeholder: "Enter your email",
                value: formData.email,
                onChange: handleInputChange,
                required: true,
                disabled: true,
            },
            {
                id: "name",
                label: "Company Name",
                type: "text",
                placeholder: "Enter your company name",
                value: formData.name,
                onChange: handleInputChange,
                required: true,
            },
            {
                id: "nip",
                label: "NIP",
                type: "text",
                placeholder: "Enter your NIP",
                value: formData.nip,
                onChange: handleInputChange,
                required: true,
            },
        ]
        : [
            {
                id: "email",
                label: "Email",
                type: "email",
                placeholder: "Enter your email",
                value: formData.email,
                onChange: handleInputChange,
                required: true,
                disabled: true,
            },
            {
                id: "firstName",
                label: "First Name",
                type: "text",
                placeholder: "Enter your first name",
                value: formData.firstName,
                onChange: handleInputChange,
                required: true,
            },
            {
                id: "lastName",
                label: "Last Name",
                type: "text",
                placeholder: "Enter your last name",
                value: formData.lastName,
                onChange: handleInputChange,
                required: true,
            },
        ];

    return (
        <div className="flex flex-col gap-y-10">
            <Card className="w-full max-w-md mx-auto min-w-96">
                <CardHeader>
                    <CardTitle>Complete Your Profile</CardTitle>
                    <CardDescription>Provide the necessary information to complete your profile</CardDescription>
                </CardHeader>
                <CardContent>
                    <AuthForm
                        fields={fields}
                        onSubmit={handleSubmit}
                        submitButtonLabel="Update Profile"
                        showSwitch={true}
                        switchLabel={isCompany ? "Company Account" : "Candidate Account"}
                        switchState={isCompany}
                        onSwitchChange={setIsCompany}
                    />
                </CardContent>
            </Card>
        </div>
    );
}
