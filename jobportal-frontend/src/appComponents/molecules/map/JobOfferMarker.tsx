// src/components/molecules/JobOfferMarker.tsx

import React from 'react';
import { Marker } from 'react-map-gl';
import JobOfferIcon from '../../atoms/map/JobOfferIcon.tsx';

interface JobOfferMarkerProps {
    latitude: number;
    longitude: number;
    onClick: () => void;
}

const JobOfferMarker: React.FC<JobOfferMarkerProps> = ({ latitude, longitude, onClick }) => (
    <Marker latitude={latitude} longitude={longitude}>
        <div onClick={onClick}>
            <JobOfferIcon />
        </div>
    </Marker>
);

export default JobOfferMarker;
