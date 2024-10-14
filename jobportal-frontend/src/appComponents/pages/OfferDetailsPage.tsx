import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import PageWithNavbarAndFooterLayout from '../layouts/PageWithNavbarAndFooterLayout'
import { OfferTitle } from '../atoms/OfferTitle'
import { OfferSalary } from '../atoms/OfferSalary'
import { OfferExpiryDate } from '../atoms/OfferExpiryDate'
import { Label } from '../atoms/Label'
import { Button } from '../atoms/Button'
import apiClient from "@/apiClient";
import {Spinner} from "@/appComponents/atoms/Spinner.tsx";

interface OfferResponseDTO {
    id?: number
    title?: string
    expiryDate?: string
    salaryMin?: number
    salaryMax?: number
    description?: string
}

export default function OfferDetailsPage() {
    const { offerId } = useParams<{ offerId: string }>()
    const [offer, setOffer] = useState<OfferResponseDTO | null>(null)
    const [isLoading, setIsLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        console.log(offerId)
        const fetchOfferDetails = async () => {
            try {
                if(offerId) {
                    const response = await apiClient.api.getOffer(parseInt(offerId));
                    const data = await response.json();
                    console.log(data);
                    setOffer(data);
                    setIsLoading(false);
                }
            } catch (err) {
                setError('Failed to fetch offers');
                setIsLoading(false);
            }
        }

        fetchOfferDetails();
    }, [offerId])

    if (isLoading) {
        return (
            <div className="flex justify-center items-center w-full h-screen">
                <Spinner size="lg" />
            </div>
        );
    }

    if (error) {
        return <div>{error}</div>
    }

    if (!offer) {
        return <div>Offer not found</div>
    }

    return (
        <PageWithNavbarAndFooterLayout>
            <div className="max-w-4xl mx-auto p-6 bg-matteBlack-600">
                <OfferTitle title={offer.title || ""} />
                <div className="mt-4 flex justify-between items-center">
                    <OfferSalary min={offer.salaryMin || 0} max={offer.salaryMax || 0} />
                    <OfferExpiryDate date={offer.expiryDate || ""} />
                </div>
                <div className="mt-6">
                    <Label>Description</Label>
                    <p className="mt-2 text-gray-600">{offer.description}</p>
                </div>
                <div className="mt-8">
                    <Button>Apply Now</Button>
                </div>
            </div>
        </PageWithNavbarAndFooterLayout>
    )
}