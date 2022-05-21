import { createSlice } from '@reduxjs/toolkit';
import { post } from './../api/api';

const initialValue = {
	name: '',
	first_name: '',
	mdp: '',
	pseudo: '',
	mail: '',
	status: 1999, //1999 -> non identifié ,2000 -> student , 2001 -> recruteur , 2002 -> admin
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
			post(
				'signupAsStudent',
				action.payload,
				(res) => {
					console.log(res);
				},
				(e) => console.log(e)
			);
			state.value.first_name = action.payload[0].value;
			state.value.name = action.payload[0].value;
			state.value.status = 2000;
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
	return [2000, 2001, 2002].includes(user.status);
};

export const { login, logout, signupAsStudent, signupAsRecruiter } =
	userSlice.actions;

export default userSlice.reducer;
