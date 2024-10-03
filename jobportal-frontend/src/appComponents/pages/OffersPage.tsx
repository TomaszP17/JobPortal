import React, {useState, useEffect} from 'react';
import {OfferList} from "@/appComponents/organisms/OfferList.tsx";
import {Api} from "@/types/api";
import type {OfferResponseDTO} from "@/types/api.ts";
import {Spinner} from "@/appComponents/atoms/Spinner.tsx";
import PageWithNavbarAndFooterLayout from "@/appComponents/layouts/PageWithNavbarAndFooterLayout.tsx";

export const OffersPage: React.FC = () => {
    const [offers, setOffers] = useState<OfferResponseDTO[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchOffers = async () => {
            try {
                const apiClient = new Api();
                const response = await apiClient.api.getOffers();
                const data = await response.json();

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
                <Spinner size="lg"/>
            </div>
        );
    }
    if (error) return <div>Error: {error}</div>;

    return (

        <PageWithNavbarAndFooterLayout>
            <div className={"container mx-auto px-4 py-8"}>
                <h1 className="text-3xl font-bold mb-6">Job Offers</h1>
                <OfferList offers={offers}/>
            </div>
        </PageWithNavbarAndFooterLayout>
    );
};