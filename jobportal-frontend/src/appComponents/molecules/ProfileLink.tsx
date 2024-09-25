import { Button } from "@/components/ui/button"

interface ProfileLinkProps {
    href: string
    icon: React.ReactNode
    children: React.ReactNode
}

export function ProfileLink({ href, icon, children }: ProfileLinkProps) {
    return (
        <Button variant="outline" size="sm" asChild>
            <a href={href} target="_blank" rel="noopener noreferrer">
                {icon}
                {children}
            </a>
        </Button>
    )
}