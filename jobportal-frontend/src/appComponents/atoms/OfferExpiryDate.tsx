import React from 'react';

interface OfferExpiryDateProps {
    date: string;
}

export const OfferExpiryDate: React.FC<OfferExpiryDateProps> = ({ date }) => {
    const formattedDate = new Date(date).toLocaleDateString();
    return (
        <p className="text-sm text-gray-500">
            Expires on: {formattedDate}
        </p>
    );
};