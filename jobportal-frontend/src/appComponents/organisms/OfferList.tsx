// src/appComponents/organisms/OfferList.tsx

import React, { useState, useEffect, useRef } from 'react';
import { OfferCard } from '@/appComponents/molecules/OfferCard';
import {Api, type OfferResponseDTO, type PageOfferResponseDTO} from '@/types/api';
import Skeleton from '@/appComponents/atoms/Skeleton';

export const OfferList: React.FC = () => {
    const [offers, setOffers] = useState<OfferResponseDTO[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const [hasMore, setHasMore] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const observer = useRef<IntersectionObserver | null>(null);
    const page = useRef<number>(0);

    const fetchOffers = async () => {
        if (isLoading) return;
        setIsLoading(true);
        try {
            const apiClient = new Api();
            const response = await apiClient.api.getNextOffers({page: page.current, size: 10})
            const data: PageOfferResponseDTO = await response.json();

            const offersFromResponse = data.content ?? [];

            if (offersFromResponse.length === 0) {
                setHasMore(false);
            } else {
                setOffers((prevOffers) => [...prevOffers, ...offersFromResponse]);
                page.current += 1;
            }
        } catch (err) {
            console.error('Failed to fetch offers:', err);
            setError('Failed to fetch offers');
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        fetchOffers();
    }, []);

    useEffect(() => {
        if (isLoading) return;
        if (observer.current) {
            observer.current.disconnect();
        }

        observer.current = new IntersectionObserver(
            (entries) => {
                if (entries[0].isIntersecting && hasMore) {
                    fetchOffers();
                }
            },
            {
                root: null,
                rootMargin: '200px',
                threshold: 0.1,
            }
        );

        const trigger = document.querySelector('#loadMoreTrigger');
        if (trigger) {
            observer.current.observe(trigger);
        }

        return () => {
            if (observer.current) observer.current.disconnect();
        };
    }, [isLoading, hasMore]);

    return (
        <div className="space-y-4">
            {offers.map((offer) => (
                <OfferCard key={offer.id} offer={offer} />
            ))}
            {isLoading &&
                [...Array(3)].map((_, index) => (
                    <div key={index} className="bg-white shadow-md rounded-lg p-6 mb-4">
                        <Skeleton className="h-6 w-1/2 mb-2" />
                        <Skeleton className="h-4 w-1/3" />
                        <Skeleton className="h-4 w-full mt-2" />
                    </div>
                ))}
            {hasMore && <div id="loadMoreTrigger" />}
            {error && <div className="text-red-500">{error}</div>}
        </div>
    );
};
