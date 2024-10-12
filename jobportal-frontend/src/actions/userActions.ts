import axios from 'axios';
import type {Dispatch} from 'redux';
import {
    USER_INFO_REQUEST,
    USER_INFO_SUCCESS,
    USER_INFO_FAIL,
    USER_LOGOUT,
} from '../constants/userConstants';
import { UserResponse } from '../types/userTypes';

interface UserInfoRequestAction {
    type: typeof USER_INFO_REQUEST;
}

interface UserInfoSuccessAction {
    type: typeof USER_INFO_SUCCESS;
    payload: UserResponse;
}

interface UserInfoFailAction {
    type: typeof USER_INFO_FAIL;
    payload: string;
}

interface UserLogoutAction {
    type: typeof USER_LOGOUT;
}

export type UserAction =
    | UserInfoRequestAction
    | UserInfoSuccessAction
    | UserInfoFailAction
    | UserLogoutAction;

export const fetchUserInfo = () => async (dispatch: Dispatch<UserAction>) => {
    try {
        dispatch({ type: USER_INFO_REQUEST });

        const response = await axios.get<UserResponse>('/api/userinfo', {
            withCredentials: true,
        });

        dispatch({
            type: USER_INFO_SUCCESS,
            payload: response.data,
        });
    } catch (error: any) {
        dispatch({
            type: USER_INFO_FAIL,
            payload:
                error.response && error.response.data.message
                    ? error.response.data.message
                    : error.message,
        });
    }
};

export const logout = () => (dispatch: Dispatch<UserAction>) => {
    axios.post('/api/logout', {}, { withCredentials: true })
        .then(() => {
            dispatch({ type: USER_LOGOUT });
            localStorage.removeItem('user');
            window.location.href = '/login';
        })
        .catch(error => {
            console.error('Error during logout:', error);
        });
};
