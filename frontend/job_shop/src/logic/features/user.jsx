import { createSlice } from '@reduxjs/toolkit';
import { post } from './../api/api';

const initialValue = {
	name: '',
	surname: '',
	email: '',
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
			state.value = action.payload
		},
		signin: (state, action) => {
			state.value = action.payload
		},
		logout: (state) => {
			state.value = initialValue;
		},
		signout: (state) => {
			state.value = initialValue
		},
		/*signupAsRecruiter: (state, action) => {
			post(
				'signupAsRecruiter',
				action.payload,
				(res) => {
					console.log('test de réception');
					console.log(res);
				},
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
		},*/

	},
});

export const LOGIN = (input,next)=>{
	const {password,login,error} = input 
	const data ={
		login:login,
		password:password,
	} 
	post(
		'login',
		data,
		(res) => {
			const {creationDate,id,login,name,password,role,status,surname} = res.data 
			const company = res.data.company
			const state ={
				name: name,
				surname: surname,
				email: login,
				company: company !== null ? company : '',
				status: status, //1999 -> non identifié ,2000 -> student , 2001 -> recruteur , 2002 -> admin
				id: id,
				accessToken: 'defaultAccessToken',
				refreshToken: 'defaultRefreshToken',
			}
			next(state);
		},
		(e) => error(e)
	);
}

export const SIGNUPASSTUDENT = (input,next)=>{
	const {error} = input 
	if(!(delete input['error'])){
		return ;
	} 
	const data = input
	post(
		'signInAsStudent',
		data,
		(res) => {
			const {creationDate,id,login,name,password,role,status,surname} = res.data 
			const company = res.data.company
			const state ={
				name: name,
				surname: surname,
				email: login,
				company: company !== null ? company : '',
				status: status, //1999 -> non identifié ,2000 -> student , 2001 -> recruteur , 2002 -> admin
				id: id,
				accessToken: 'defaultAccessToken',
				refreshToken: 'defaultRefreshToken',
			}
			next(state);
		},
		(e) => error(e)
	);
} 
export const SIGNUPASRECRUITER = (input,next)=>{
	const {error} = input 
	if(!(delete input['error'])){
		return ;
	} 
	const data = input
	post(
		'signInAsRecruiter',
		data,
		(res) => {
			const {creationDate,id,login,name,password,role,status,surname} = res.data 
			const company = res.data.company
			const state ={
				name: name,
				surname: surname,
				email: login,
				company: company !== null ? company : '',
				status: status, //1999 -> non identifié ,2000 -> student , 2001 -> recruteur , 2002 -> admin
				id: id,
				accessToken: 'defaultAccessToken',
				refreshToken: 'defaultRefreshToken',
			}
			next(state);
		},
		(e) => error(e)
	);
} 
export const isAuthentifated = (user) => {
	return [2000, 2001, 2002].includes(user.status);
};

export const { login, logout, signin,signout } =
	userSlice.actions;

export default userSlice.reducer;
