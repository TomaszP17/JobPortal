import type {UserDTO} from "@/types/api.ts";

export default interface CurrentUserCandidateDTO extends UserDTO {
    id: number;
    userType: string;
    email: string;
    firstName: string;
}