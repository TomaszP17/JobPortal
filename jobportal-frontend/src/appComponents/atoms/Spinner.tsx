interface SpinnerProps {
    size?: 'sm' | 'md' | 'lg'
    className?: string
}

export function Spinner({ size = 'md', className = '' }: SpinnerProps) {
    const sizeClasses = {
        sm: 'w-6 h-6 border-2',
        md: 'w-10 h-10 border-3',
        lg: 'w-16 h-16 border-4'
    }

    return (
        <div className={`relative ${className}`}>
            <div
                className={`rounded-full border-t-2 border-electricPurple-500 animate-spin ${sizeClasses[size]}`}
            ></div>
            <div
                className={`absolute top-0 left-0 rounded-full border-2 border-matteBlack-200 opacity-20 ${sizeClasses[size]}`}
            ></div>
        </div>
    )
}