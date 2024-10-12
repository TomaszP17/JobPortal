import React, { useEffect, useState } from 'react';
import Map, {Marker, Popup, type ViewStateChangeEvent} from 'react-map-gl';
import 'mapbox-gl/dist/mapbox-gl.css';
import { Api, type LocalizationCoordinatesResponseDTO } from '@/types/api';
import JobOfferMarker from "@/appComponents/molecules/map/JobOfferMarker.tsx";
import JobOfferTooltip from "@/appComponents/molecules/map/JobOfferTooltip.tsx";

const JobOffersMap: React.FC = () => {
    const [markers, setMarkers] = useState<LocalizationCoordinatesResponseDTO[]>([]);
    const [viewport, setViewport] = useState({
        latitude: 52.2297,
        longitude: 21.0122,
        zoom: 10,
    });

    const [popupInfo, setPopupInfo] = useState<LocalizationCoordinatesResponseDTO | null>(null);

    useEffect(() => {
        const fetchLocalization = async () => {
            try {
                const apiClient = new Api();
                const response = await apiClient.api.getOffersLocalizationAndOfferId();
                const data: LocalizationCoordinatesResponseDTO[] = await response.json();

                setMarkers(data);

                console.log('Fetched markers:', data);
            } catch (err) {
                console.error('Failed to fetch localizations:', err);
            }
        };

        fetchLocalization();
    }, []);

    const handleViewportChange = (event: ViewStateChangeEvent) => {
        setViewport(event.viewState);
    };

    return (
            <Map
                {...viewport}
                mapStyle="mapbox://styles/mapbox/light-v11"
                mapboxAccessToken={import.meta.env.VITE_MAPBOX_ACCESS_TOKEN as string}
                onMove={handleViewportChange}
                style={{borderRadius: "10px"}}
                minZoom={4}
            >
                {markers.map((marker, id) => (
                    (marker.lng && marker.lat) &&

                    <JobOfferMarker key={id} longitude={marker.lng} latitude={marker.lat} onClick={() => console.log("sigma")} />

                    // <Marker
                    //     key={id}
                    //     longitude={marker.lng}
                    //     latitude={marker.lat}
                    //     anchor="bottom"
                    //     onClick={(e) => {
                    //         e.originalEvent.stopPropagation();
                    //         setPopupInfo(marker);
                    //     }}
                    // >
                    //     <div style={{
                    //         backgroundColor: 'red',
                    //         width: '10px',
                    //         height: '10px',
                    //         borderRadius: '50%',
                    //         cursor: 'pointer',
                    //     }} />
                    // </Marker>
                ))}

                {popupInfo && (
                    <Popup
                        anchor="top"
                        longitude={Number(popupInfo.lng)}
                        latitude={Number(popupInfo.lat)}
                        onClose={() => setPopupInfo(null)}
                    >
                        <div>
                            <h3>Oferta ID: </h3>
                        </div>
                    </Popup>
                )}
            </Map>
    );
};

export default JobOffersMap;
