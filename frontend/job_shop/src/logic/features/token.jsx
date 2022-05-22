import { createSlice } from '@reduxjs/toolkit';

const initialValue = {
	accessToken: 'defaultAccessToken',
	refreshToken: 'defaultRefreshToken',
};

export const tokenSlice = createSlice({
	name: 'token',
	initialState: {
		value: initialValue,
	},
	reducers: {
		acquireToken: (state, action) => {
			console.log(action.payload);
			state.value = action.payload;
		},
		deleteToken: (state) => {
			state.value = initialValue;
		},
	},
});

export const { acquireToken, deleteToken } = tokenSlice.actions;

export default tokenSlice.reducer;
