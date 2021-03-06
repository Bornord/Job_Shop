import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import { login,LOGIN } from '../../logic/features/user';
import { useDispatch, useSelector } from 'react-redux';
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';
import './Login.scss';

function Login() {
	let navigate = useNavigate();
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [error, setError] = useState('');
	const dispatch = useDispatch();
	const user = useSelector((state) => state.user.value);

	const handleError = (e) =>{
		console.log(e);
		if(e.message != null){
			setError(e.message)
		} else{
			setError(e)
		} 
	} 

	const validate = (password,email)=> {
		return password !== "" && email !== ""
	} 
	const handleSubmit = () => {
		// recopie (email & password) dans la variable state.
		// La recopie vaut "" si les champs ne sont pas remplis
		if (validate(password,email)) {

			const data ={
				login:email,
				password:password,
				error: handleError
			} 

			LOGIN(data,(input)=>{
				dispatch(login(input));
				navigate('/');
			} )
			//dispatch(login(data));
			//
		} else {
			handleError('Vous devez remplir les champs.');
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
					{error !== '' && <div className='error'>{error}</div>}	
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
