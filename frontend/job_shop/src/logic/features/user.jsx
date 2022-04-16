import { createSlice } from '@reduxjs/toolkit';

const initialValue = {
	name: '',
	status: 'member',
	id: '0',
	token: 'defaultToken',
};
export const userSlice = createSlice({
	name: 'user',
	initialState: {
		value: initialValue,
	},
	reducers: {
		login: (state, action) => {
			state.value = action.payload;
		},

		logout: (state) => {
			state.value = initialValue;
		},
	},
});

export const { login, logout } = userSlice.actions;

export default userSlice.reducer;
