import { createSlice } from '@reduxjs/toolkit';
import { post } from './../api/api';

const initialValue = {
	accessToken: 'defaultAccessToken',
	refreshToken: 'defaultRefreshToken',
};
export const userSlice = createSlice({
	name: 'token',
	initialState: {
		value: initialValue,
	},
	reducers: {
		acquireToken: (state, action) => {
			state.value = action.payload;
		},
		deleteToken: (state) => {
			state.value = initialValue;
		},
	},
});

export const { acquireToken, deleteToken } = userSlice.actions;

export default userSlice.reducer;
