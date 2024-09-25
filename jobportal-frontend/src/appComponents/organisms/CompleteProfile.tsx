import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { InputField } from '../atoms/InputField';
import { Button } from '../atoms/Button';

export const CompleteProfile: React.FC = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const { email, scope } = location.state;

    const isCandidate = scope.includes('CANDIDATE');
    const isCompany = scope.includes('COMPANY');

    const [formData, setFormData] = useState({
        email: email || '',
        name: '',
        firstName: '',
        lastName: '',
        nip: '',
    });

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = event.target;
        setFormData((prev) => ({
            ...prev,
            [id]: value,
        }));
    };

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        try {
            if (isCandidate) {
                console.log('Submit candidate profile:', {
                    email: formData.email,
                    firstName: formData.firstName,
                    lastName: formData.lastName,
                });
            } else if (isCompany) {
                console.log('Submit company profile:', {
                    email: formData.email,
                    name: formData.name,
                    nip: formData.nip,
                });
            }

            navigate('/');
        } catch (error) {
            console.error('Error submitting profile:', error);
        }
    };

    return (
        <div className="form-container">
            <h1>Complete Your Profile</h1>
            <form onSubmit={handleSubmit}>
                {/* Email is pre-filled and disabled */}
                <InputField
                    id="email"
                    label="Email"
                    type="email"
                    placeholder="Enter your email"
                    value={formData.email}
                    onChange={handleInputChange}
                    required
                    disabled
                />

                {isCandidate && (
                    <>
                        <InputField
                            id="firstName"
                            label="First Name"
                            type="text"
                            placeholder="Enter your first name"
                            value={formData.firstName}
                            onChange={handleInputChange}
                            required
                        />
                        <InputField
                            id="lastName"
                            label="Last Name"
                            type="text"
                            placeholder="Enter your last name"
                            value={formData.lastName}
                            onChange={handleInputChange}
                            required
                        />
                    </>
                )}

                {isCompany && (
                    <>
                        <InputField
                            id="name"
                            label="Company Name"
                            type="text"
                            placeholder="Enter your company name"
                            value={formData.name}
                            onChange={handleInputChange}
                            required
                        />
                        <InputField
                            id="nip"
                            label="NIP"
                            type="text"
                            placeholder="Enter your NIP"
                            value={formData.nip}
                            onChange={handleInputChange}
                            required
                        />
                    </>
                )}

                <Button type="submit">Submit</Button>
            </form>
        </div>
    );
};
