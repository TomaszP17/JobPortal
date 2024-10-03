import React from 'react';

interface OfferTitleProps {
    title: string;
}

export const OfferTitle: React.FC<OfferTitleProps> = ({ title }) => (
    <h2 className="text-xl font-semibold text-gray-800">{title}</h2>
);