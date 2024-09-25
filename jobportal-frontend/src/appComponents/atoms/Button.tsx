import * as React from "react"
import {Button as ButtonPrimitive} from "@/components/ui/button.tsx";

interface ButtonProps {
    children: React.ReactNode
    variant?: "default" | "outline"
    type?: "button" | "submit"
    onClick?: () => void
}

export function Button({ children, variant = "default", type = "button", onClick }: ButtonProps) {
    return (
        <ButtonPrimitive variant={variant} type={type} onClick={onClick} className={"!px-20"}>
            {children}
        </ButtonPrimitive>
    )
}