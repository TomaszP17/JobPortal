import React from 'react';

interface Offer {
    id: number;
    title: string;
    company: string;
    latitude: number;
    longitude: number;
}

interface JobOfferTooltipProps {
    offer: Offer;
}

const JobOfferTooltip: React.FC<JobOfferTooltipProps> = ({ offer }) => (
    <div className="tooltip">
        <h4>{offer.title}</h4>
        <p>{offer.company}</p>
    </div>
);

export default JobOfferTooltip;
