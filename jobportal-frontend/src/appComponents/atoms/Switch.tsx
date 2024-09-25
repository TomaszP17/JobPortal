import * as React from "react"
import { Switch as SwitchPrimitive } from "@/components/ui/switch"
import { Label } from "@/components/ui/label";

interface SwitchProps {
    id: string
    label: string
    checked: boolean
    onCheckedChange: (checked: boolean) => void
}

export function Switch({ id, label, checked, onCheckedChange }: SwitchProps) {
    return (
        <div className="flex items-center space-x-2">
            <SwitchPrimitive id={id} checked={checked} onCheckedChange={onCheckedChange} />
            <Label htmlFor={id}>{label}</Label>
        </div>
    )
}