import React from 'react';
import {OfferTitle} from "@/appComponents/atoms/OfferTitle.tsx";
import {OfferSalary} from "@/appComponents/atoms/OfferSalary.tsx";
import {OfferExpiryDate} from "@/appComponents/atoms/OfferExpiryDate.tsx";
import type {OfferResponseDTO} from "@/types/api.ts";

interface OfferCardProps {
    offer: OfferResponseDTO;
}

export const OfferCard: React.FC<OfferCardProps> = ({ offer }) => (
    <div className="bg-white shadow-md rounded-lg p-6 mb-4">
        <OfferTitle title={offer.title || 'No title'} />
        <OfferSalary min={offer.salaryMin || 0} max={offer.salaryMax || 0} />
        <OfferExpiryDate date={offer.expiryDate || ''} />
        <p className="mt-2 text-gray-700">{offer.description}</p>
    </div>
);