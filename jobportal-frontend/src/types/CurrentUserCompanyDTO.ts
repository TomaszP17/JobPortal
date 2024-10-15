import type {UserDTO} from "@/types/api.ts";

export default interface CurrentUserCompanyDTO extends UserDTO {
    id: number;
    userType: string;
    email: string;
    name: string;
}