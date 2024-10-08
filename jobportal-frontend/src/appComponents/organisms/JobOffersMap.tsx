import React, { useState } from 'react';
import ReactMapGL, {type ViewStateChangeEvent } from 'react-map-gl';
import JobOfferMarker from '@/appComponents/molecules/map/JobOfferMarker';
import JobOfferTooltip from '@/appComponents/molecules/map/JobOfferTooltip';

interface Offer {
    id: number;
    title: string;
    company: string;
    latitude: number;
    longitude: number;
}

interface JobOffersMapProps {
    offers: Offer[];
}

const JobOffersMap: React.FC<JobOffersMapProps> = ({ offers }) => {
    const [viewport, setViewport] = useState({
        latitude: 52.2297,
        longitude: 21.0122,
        zoom: 10,
    });

    const [selectedOffer, setSelectedOffer] = useState<Offer | null>(null);

    const handleViewportChange = (event: ViewStateChangeEvent) => {
        setViewport(event.viewState);
    };

    return (
        <div className="h-full w-full rounded-lg overflow-hidden">
            <ReactMapGL
                {...viewport}
                width="100%"
                height="100%"
                mapStyle="mapbox://styles/mapbox/light-v10"
                mapboxAccessToken={import.meta.env.VITE_MAPBOX_ACCESS_TOKEN as string}
                onMove={handleViewportChange}
            >
                {/*{offers.map((offer) => (*/}
                {/*    <JobOfferMarker*/}
                {/*        key={offer.id}*/}
                {/*        latitude={offer.latitude}*/}
                {/*        longitude={offer.longitude}*/}
                {/*        onClick={() => setSelectedOffer(offer)}*/}
                {/*    />*/}
                {/*))}*/}

                {/*{selectedOffer && <JobOfferTooltip offer={selectedOffer} />}*/}
            </ReactMapGL>
        </div>
    );
};

export default JobOffersMap;
