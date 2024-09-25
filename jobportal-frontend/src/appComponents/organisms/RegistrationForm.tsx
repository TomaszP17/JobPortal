import * as React from "react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { Switch } from "../atoms/Switch";
import { InputField } from "../atoms/InputField";
import { Button } from "../atoms/Button";
import { Link } from "react-router-dom";
import {useEffect, useState} from "react";
import { Api } from "@/types/api";
import type { CreateCandidateRequestDTO, CreateCompanyRequestDTO } from "@/types/api.ts";

export function RegistrationForm() {
    const [isCompanyAccount, setIsCompanyAccount] = useState(false);
    const [formDataCompany, setFormDataCompany] = useState<CreateCompanyRequestDTO>({
        name: "",
        email: "",
        password: "",
        nip: "",
    });

    const [formDataCandidate, setFormDataCandidate] = useState<CreateCandidateRequestDTO>({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
    });


    const apiClient = new Api();

    const handleCandidateInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = event.target;
        setFormDataCandidate((prevData) => ({
            ...prevData,
            [id]: value,
        }));
    };

    const handleCompanyInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = event.target;
        setFormDataCompany((prevData) => ({
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
        } catch (error) {
            console.error("Error creating account:", error);
            alert("Failed to create account.");
        }
    };

    const handleGoogleSignIn = () => {
        // Handle Google sign-in logic here
    };

    return (
        <div className={"flex flex-col gap-y-10"}>
            <Switch
                id="accountType"
                label={isCompanyAccount ? "Company Account" : "Candidate Account"}
                checked={isCompanyAccount}
                onCheckedChange={setIsCompanyAccount}
            />
            <Card className="w-full max-w-md mx-auto min-w-96">
                <CardHeader>
                    <CardTitle>Create an Account</CardTitle>
                    <CardDescription>Sign up for a new account</CardDescription>
                </CardHeader>
                <CardContent>
                    <form onSubmit={handleSubmit} className="space-y-4">
                        {/* Candidate Form */}
                        {!isCompanyAccount && (
                            <>
                                <InputField
                                    id="firstName"
                                    label="First Name"
                                    type="text"
                                    placeholder="Enter your first name"
                                    value={formDataCandidate.firstName}
                                    onChange={handleCandidateInputChange}
                                    required
                                />
                                <InputField
                                    id="lastName"
                                    label="Last Name"
                                    type="text"
                                    placeholder="Enter your last name"
                                    value={formDataCandidate.lastName}
                                    onChange={handleCandidateInputChange}
                                    required
                                />
                                <InputField
                                    id="email"
                                    label="Email"
                                    type="email"
                                    placeholder="Enter your email"
                                    value={formDataCandidate.email}
                                    onChange={handleCandidateInputChange}
                                    required
                                />
                                <InputField
                                    id="password"
                                    label="Password"
                                    type="password"
                                    placeholder="Enter your password"
                                    value={formDataCandidate.password}
                                    onChange={handleCandidateInputChange}
                                    required
                                />
                            </>
                        )}

                        {/* Company Form */}
                        {isCompanyAccount && (
                            <>
                                <InputField
                                    id="name"
                                    label="Company Name"
                                    type="text"
                                    placeholder="Enter your company name"
                                    value={formDataCompany.name}
                                    onChange={handleCompanyInputChange}
                                    required
                                />
                                <InputField
                                    id="email"
                                    label="Email"
                                    type="email"
                                    placeholder="Enter your email"
                                    value={formDataCompany.email}
                                    onChange={handleCompanyInputChange}
                                    required
                                />
                                <InputField
                                    id="password"
                                    label="Password"
                                    type="password"
                                    placeholder="Enter your password"
                                    value={formDataCompany.password}
                                    onChange={handleCompanyInputChange}
                                    required
                                />
                                <InputField
                                    id="nip"
                                    label="NIP"
                                    type="text"
                                    placeholder="Enter your NIP"
                                    value={formDataCompany.nip || ""}
                                    onChange={handleCompanyInputChange}
                                    required
                                />
                            </>
                        )}

                        <Button type="submit">Register</Button>

                        <div className="relative">
                            <div className="absolute inset-0 flex items-center">
                                <span className="w-full border-t" />
                            </div>
                            <div className="relative flex justify-center text-xs uppercase">
                                <span className="bg-background px-2 text-muted-foreground">Or continue with</span>
                            </div>
                        </div>
                        <Link to={`${import.meta.env.VITE_API_BASE_URL}oauth2/authorization/google?userType=${isCompanyAccount ? "company" : "candidate"}`}>
                            <Button variant="outline" onClick={handleGoogleSignIn}>
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
                    </form>
                </CardContent>
            </Card>
        </div>
    );
}
