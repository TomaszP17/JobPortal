import * as React from "react"
import {Button as ButtonPrimitive} from "@/components/ui/button.tsx";
import {cn} from "@/lib/utils";

interface ButtonProps {
    children: React.ReactNode;
    variant?: "default" | "outline";
    type?: "button" | "submit";
    onClick?: () => void;
    className?: string;
}

export function Button({ children, variant = "default", type = "button", onClick, className }: ButtonProps) {
    return (
        <ButtonPrimitive variant={variant} type={type} onClick={onClick} className={cn("!px-20", className)}>
            {children}
        </ButtonPrimitive>
    )
}