import { Avatar as AvatarUI, AvatarFallback, AvatarImage } from "@/components/ui/avatar"

interface AvatarProps {
    src: string
    fallback: string
}

export function Avatar({ src, fallback }: AvatarProps) {
    return (
        <AvatarUI className="w-20 h-20">
            <AvatarImage src={src} />
            <AvatarFallback>{fallback}</AvatarFallback>
        </AvatarUI>
    )
}