import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import { login } from '../../logic/features/user';
import { useDispatch, useSelector } from 'react-redux';
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';
import './Login.scss';

function Login() {
	let navigate = useNavigate();
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const dispatch = useDispatch();
	const user = useSelector((state) => state.user.value);
	const [state, setState] = useState([
		{ id: 'mail', value: '' },
		{ id: 'password', value: '' },
	]);
	const [error, setError] = useState('');
	state.erreurFonction = (reason) => {
		setError(reason);
	};
	const handleSubmit = () => {
		const mail = state.find((input) => input.id == 'mail');
		const password2 = state.find((input) => input.id == 'password');
		// recopie (email & password) dans la variable state.
		// La recopie vaut "" si les champs ne sont pas remplis
		mail.value = email;
		password2.value = password;
		if (mail.value != '' && password2.value != '') {
			dispatch(login(state));
			navigate('/');
		} else {
			state.erreurFonction('Vous devez remplir les champs.');
		}
	};
	return (
		<div className="login">
			<form
				onSubmit={(e) => {
					e.preventDefault();
					handleSubmit();
				}}
			>
				<div className="form">
					<h1 className="title">Bienvenue</h1>
					<h2 className="subtitle">bla bla bla</h2>
					<p className="errorStack">{error}</p>
					<div className="input-container ic1">
						<input
							id="email"
							className={'login-input'}
							type="email"
							value={email}
							onChange={(e) => setEmail(e.target.value)}
							placeholder=" "
						/>
						<div className="cut cut-short"></div>
						<label htmlFor="email" className="placeholder">
							Email :
						</label>
					</div>
					<div className="input-container ic2">
						<input
							id="password"
							className={'login-input'}
							type="password"
							value={password}
							onChange={(e) => {
								setPassword(e.target.value);
							}}
							placeholder=" "
						/>
						<div className="cut"></div>
						<label htmlFor="password" className="placeholder">
							Password :
						</label>
					</div>
					<SelectButton2 className={'submit_button'} type="submit">
						Se connecter
					</SelectButton2>
				</div>
			</form>
		</div>
	);
}

export default Login;
