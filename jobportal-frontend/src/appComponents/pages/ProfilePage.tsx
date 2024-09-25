import { ProfileCard } from "@/appComponents/organisms/ProfileCard.tsx"

export default function ProfilePage() {
    // Random data for preview
    const userData = {
        userType: "ADMIN",
        createDate: "2021-03-15",
        email: "anna.kowalska@techcorp.pl",
        githubLink: "https://github.com/annakowalska",
        linkedinLink: "https://linkedin.com/in/annakowalska",
        companyName: "TechCorp Polska Sp. z o.o.",
        nip: "1234567890",
        experienceYears: 8,
        firstName: "Anna",
        lastName: "Kowalska",
        pdfUrl: "/path/to/resume.pdf",
        nickname: "akowalska"
    }

    return (
        <div className="container mx-auto p-6">
            <ProfileCard {...userData} />
        </div>
    )
}