interface ValueProps {
    children: React.ReactNode
}

export function Value({ children }: ValueProps) {
    return <span className="text-sm">{children}</span>
}