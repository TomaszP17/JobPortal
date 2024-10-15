import {createSlice} from "@reduxjs/toolkit";
import {type UserDTO} from "@/types/api.ts";
import type CurrentUserCandidateDTO from "@/types/CurrentUserCandidateDTO.ts";
import type CurrentUserCompanyDTO from "@/types/CurrentUserCompanyDTO.ts";
import apiClient from "@/apiClient";

const initialState: CurrentUserCandidateDTO | CurrentUserCompanyDTO = {
    id: 0,
    userType: "",
    email: "",
    name: "",
    firstName: "",
}

const userSlice = createSlice({
    name: "user",
    initialState,
    reducers: {
        }
    }
})

export default userSlice.reducer;