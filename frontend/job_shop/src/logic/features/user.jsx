import { createSlice } from '@reduxjs/toolkit';
import { post } from './../api/api';
import { useDispatch, useSelector } from 'react-redux';

const initialValue = {
	name: '',
	first_name: '',
	mdp: '',
	pseudo: '',
	mail: '',
	company: '',
	status: 1999, //1999 -> non identifié ,2000 -> student , 2001 -> recruteur , 2002 -> admin
	id: '0',
};

export const userSlice = createSlice({
	name: 'user',
	initialState: {
		value: initialValue,
	},
	reducers: {
		login: (state, action) => {
			console.log(action.payload);
			post(
				'login',
				action.payload,
				(res) => {
					state.value.status = 2002;
					const token1 = 'abc1';
					const token2 = 'def2';
					action.payload.handleTokens(token1, token2);
				},
				(e) => action.payload.erreurFonction(e)
			);
		},
		signupAsStudent: (state, action) => {
			post(
				'signupAsStudent',
				action.payload,
				(res) => {
					state.value.first_name = action.payload[0].value;
					state.value.name = action.payload[0].value;
					state.value.status = 2000;
				},
				(e) => action.payload.erreurFonction(e)
			);
		},
		signupAsRecruiter: (state, action) => {
			post(
				'signupAsRecruiter',
				action.payload,
				(res) => {
					console.log(res);
					state.value.first_name = action.payload[0].value;
					state.value.name = action.payload[0].value;
					state.value.status = 2001;
				},
				(e) => action.payload.erreurFonction(e)
			);
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
