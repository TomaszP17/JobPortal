import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Avatar } from "../atoms/Avatar"
import { ProfileField } from "../molecules/ProfileField"
import { ProfileLink } from "../molecules/ProfileLink"
import { Calendar, Github, Linkedin, Briefcase, FileText, User } from "lucide-react"

interface ProfileCardProps {
    userType: string
    createDate: string
    email: string
    githubLink: string
    linkedinLink: string
    companyName: string
    nip: string
    experienceYears: number
    firstName: string
    lastName: string
    pdfUrl: string
    nickname: string
}

export function ProfileCard({
                                userType,
                                createDate,
                                email,
                                githubLink,
                                linkedinLink,
                                companyName,
                                nip,
                                experienceYears,
                                firstName,
                                lastName,
                                pdfUrl,
                                nickname
                            }: ProfileCardProps) {
    return (
        <Card className="w-full max-w-3xl mx-auto">
            <CardHeader>
                <div className="flex items-center space-x-4">
                    <Avatar
                        src={`https://api.dicebear.com/6.x/initials/svg?seed=${firstName} ${lastName}`}
                        fallback={`${firstName[0]}${lastName[0]}`}
                    />
                    <div>
                        <CardTitle className="text-2xl">{firstName} {lastName}</CardTitle>
                        <p className="text-sm text-muted-foreground">@{nickname}</p>
                    </div>
                </div>
            </CardHeader>
            <CardContent>
                <div className="grid gap-4">
                    <div className="grid grid-cols-2 gap-4">
                        <ProfileField label="User Type" value={userType} icon={<User className="w-4 h-4 text-muted-foreground" />} />
                        <ProfileField label="Joined" value={new Date(createDate).toLocaleDateString()} icon={<Calendar className="w-4 h-4 text-muted-foreground" />} />
                    </div>
                    <ProfileField label="Company" value={companyName} icon={<Briefcase className="w-4 h-4 text-muted-foreground" />} />
                    <ProfileField label="NIP" value={nip} />
                    <ProfileField label="Experience" value={`${experienceYears} years`} />
                    <ProfileField label="Email" value={email} />
                    <div className="flex space-x-2">
                        <ProfileLink href={githubLink} icon={<Github className="w-4 h-4 mr-2" />}>
                            GitHub
                        </ProfileLink>
                        <ProfileLink href={linkedinLink} icon={<Linkedin className="w-4 h-4 mr-2" />}>
                            LinkedIn
                        </ProfileLink>
                        <ProfileLink href={pdfUrl} icon={<FileText className="w-4 h-4 mr-2" />}>
                            Resume
                        </ProfileLink>
                    </div>
                </div>
            </CardContent>
        </Card>
    )
}