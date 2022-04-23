import { createSlice } from '@reduxjs/toolkit';

const initialValue = {
	name: '',
	status: 1999,//1999 -> non identifié ,2000 -> student , 2001 -> recruteur , 2002 -> admin
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
			console.log(action.payload);
			state.value = action.payload;
		},
		signupAsStudent: (state, action) => {
			console.log(action.payload);
			//state.value = action.payload;
		},
		signupAsRecruiter: (state, action) => {
			console.log(action.payload);
			//state.value = action.payload;
		},
		logout: (state) => {
			state.value = initialValue;
		},
	},
});

export const isAuthentifated = (user) => {
	return [2000,2001,2002].includes(user.status)
}

export const { login, logout, signupAsStudent, signupAsRecruiter } = userSlice.actions;

export default userSlice.reducer;
