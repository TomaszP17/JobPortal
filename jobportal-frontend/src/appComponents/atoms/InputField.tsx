import * as React from "react";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import type { ChangeEvent } from "react";

export interface InputFieldProps {
    id: string;
    label: string;
    type: string;
    placeholder: string;
    value: string;
    onChange: (event: ChangeEvent<HTMLInputElement>) => void;
    required?: boolean;
    disabled?: boolean;
}

export function InputField({ id, label, type, placeholder, value, onChange, required = false, disabled = false }: InputFieldProps) {
    return (
        <div className="space-y-2">
            <Label htmlFor={id}>{label}</Label>
            <Input
                id={id}
                type={type}
                placeholder={placeholder}
                value={value}
                onChange={onChange}
                required={required}
                disabled={disabled}
            />
        </div>
    );
}
