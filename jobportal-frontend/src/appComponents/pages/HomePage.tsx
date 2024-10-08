// src/pages/HomePage.tsx

import React, { useState, useEffect } from 'react';
import { OfferList } from '@/appComponents/organisms/OfferList';
import { Api } from '@/types/api';
import type { OfferResponseDTO } from '@/types/api';
import { Spinner } from '@/appComponents/atoms/Spinner';
import PageWithNavbarAndFooterLayout from '@/appComponents/layouts/PageWithNavbarAndFooterLayout';
import JobOffersMap from '@/appComponents/organisms/JobOffersMap';

export const HomePage: React.FC = () => {
    const [offers, setOffers] = useState<OfferResponseDTO[]>([]);
    const [error, setError] = useState<string | null>(null);

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
                            <JobOffersMap/>
                        </div>
                    </div>
                </div>
            </div>
        </PageWithNavbarAndFooterLayout>
    );
};
