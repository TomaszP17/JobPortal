import React, { useState, useEffect } from 'react';
import { OfferList } from '@/appComponents/organisms/OfferList';
import type { OfferResponseDTO } from '@/types/api';
import { Spinner } from '@/appComponents/atoms/Spinner';
import PageWithNavbarAndFooterLayout from '@/appComponents/layouts/PageWithNavbarAndFooterLayout';
import JobOffersMap from '@/appComponents/organisms/JobOffersMap';
import apiClient from "@/apiClient/index.ts";

export const HomePage: React.FC = () => {
    const [offers, setOffers] = useState<OfferResponseDTO[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchOffers = async () => {
            try {
                const response = await apiClient.api.getOffers();
                const data: OfferResponseDTO[] = await response.json();

                setOffers(data);
                setIsLoading(false);
            } catch (err) {
                setError('Failed to fetch offers');
                setIsLoading(false);
            }
        };

        fetchOffers();
    }, []);

    if (isLoading) {
        return (
            <div className="flex justify-center items-center w-full h-screen">
                <Spinner size="lg" />
            </div>
        );
    }
    if (error) return <div>Error: {error}</div>;

    return (
        <PageWithNavbarAndFooterLayout>
            <div className="container mx-auto px-4 py-8">
                <h1 className="text-3xl font-bold mb-6">Job Offers</h1>
                <div className="flex">
                    <div className="w-1/2 pr-4" style={{maxHeight: '100vh', overflowY: 'auto'}}>
                        <OfferList/>
                    </div>
                    <div className="w-1/2 h-screen">
                        <div className="sticky top-0 h-[600px]">
                            <JobOffersMap offers={offers}/>
                        </div>
                    </div>
                </div>
            </div>
        </PageWithNavbarAndFooterLayout>
    );
};
