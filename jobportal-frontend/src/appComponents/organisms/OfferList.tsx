import React from 'react';
import {OfferCard} from "@/appComponents/molecules/OfferCard.tsx";
import type {OfferResponseDTO} from "@/types/api.ts";

interface OfferListProps {
    offers: OfferResponseDTO[];
}

export const OfferList: React.FC<OfferListProps> = ({ offers }) => (
    <div className="space-y-4">
        {offers.map((offer) => (
            <OfferCard key={offer.id} offer={offer} />
        ))}
    </div>
);