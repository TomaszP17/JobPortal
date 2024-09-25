import * as React from "react"
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

interface InputFieldProps {
    id: string
    label: string
    type: string
    placeholder: string
    required?: boolean
}

export function InputField({ id, label, type, placeholder, required = false }: InputFieldProps) {
    return (
        <div className="space-y-2">
            <Label htmlFor={id}>{label}</Label>
            <Input id={id} type={type} placeholder={placeholder} required={required} />
        </div>
    )
}