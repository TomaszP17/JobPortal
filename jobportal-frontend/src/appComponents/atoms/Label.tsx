interface LabelProps {
    children: React.ReactNode
}

export function Label({ children }: LabelProps) {
    return <span className="text-sm font-medium">{children}</span>
}