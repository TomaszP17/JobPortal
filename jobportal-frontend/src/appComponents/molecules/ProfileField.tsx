import { Label } from "../atoms/Label"
import { Value } from "../atoms/Value"

interface ProfileFieldProps {
    label: string
    value: string | number
    icon?: React.ReactNode
}

export function ProfileField({ label, value, icon }: ProfileFieldProps) {
    return (
        <div className="flex items-center space-x-2">
            {icon}
            <Label>{label}:</Label>
            <Value>{value}</Value>
        </div>
    )
}