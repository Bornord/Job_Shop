import { createSlice } from '@reduxjs/toolkit';
import { post } from './../api/api';

const initialValue = {
	name: '',
	first_name: '',
	mdp: '',
	pseudo: '',
	mail: '',
	company: '',
	status: 1999, //1999 -> non identifié ,2000 -> student , 2001 -> recruteur , 2002 -> admin
	id: '0',
	accessToken: 'defaultAccessToken',
	refreshToken: 'defaultRefreshToken',
};

export const userSlice = createSlice({
	name: 'user',
	initialState: {
		value: initialValue,
	},
	reducers: {
		login: (state, action) => {
			post(
				'login',
				action.payload,
				(res) => console.log(res),
				// json
				// id, name, surname, login, password, accesToken, refreshToken, role (STUDENT | RECRUITER | ADMIN), creationDate
				(e) => action.payload.erreurFonction(e)
			);
			state.value.status = 2001;
			const token1 = 'abc1';
			const token2 = 'def2';
			state.value.accessToken = token1;
			state.value.refreshToken = token2;
			state.value.mail = action.payload[2].value;
		},
		signupAsStudent: (state, action) => {
			post(
				'signupAsStudent',
				action.payload,
				(res) => console.log(res),
				(e) => action.payload.erreurFonction(e)
			);
			state.value.first_name = action.payload[0].value;
			state.value.name = action.payload[0].value;
			state.value.status = 2000;
			const token1 = 'abc1';
			const token2 = 'def2';
			state.value.accessToken = token1;
			state.value.refreshToken = token2;
			console.log(action.payload);

			state.value.mail = action.payload[2].value;
		},
		signupAsRecruiter: (state, action) => {
			post(
				'signupAsRecruiter',
				action.payload,
				(res) => console.log(res),
				(e) => action.payload.erreurFonction(e)
			);
			state.value.first_name = action.payload[0].value;
			state.value.name = action.payload[0].value;
			state.value.status = 2001;
			const token1 = 'abc1';
			const token2 = 'def2';
			state.value.accessToken = token1;
			state.value.refreshToken = token2;
			console.log(action.payload);
			state.value.mail = action.payload[2].value;
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
