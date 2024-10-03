import React from 'react';

interface OfferSalaryProps {
    min: number;
    max: number;
}

export const OfferSalary: React.FC<OfferSalaryProps> = ({ min, max }) => (
    <p className="text-lg font-medium text-gray-600">
        Salary: ${min} - ${max}
    </p>
);