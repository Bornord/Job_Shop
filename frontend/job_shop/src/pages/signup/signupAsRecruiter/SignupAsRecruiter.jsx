import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './SignupAsRecruiter.scss';
import Signup from '../../../components/signup/Signup';

function SignupAsRecruiter() {
	const inputs = [
		{
			id: 'company-name',
			type: 'text',
			label: 'Entreprise :',
		},
		{
			id: 'firstname',
			type: 'text',
			label: 'Prénom :',
		},
		{
			id: 'name',
			type: 'text',
			label: 'Nom :',
		},
		{
			id: 'email',
			type: 'email',
			label: 'Email :',
		},
		{
			id: 'password',
			type: 'password',
			label: 'Mot de passe :',
		},
		{
			id: 'confirmed-password',
			type: 'password',
			label: 'Confirmer le mot de passe :',
		},
	];
	const isValid = (state) => {
		const password = state.find((input) => input.id == 'password');
		const confirmedPassword = state.find(
			(input) => input.id == 'confirmed-password'
		);
		if (password.value != confirmedPassword.value) {
			return 'password';
		} else {
			return 'ok';
		}
	};

	return (
		<div className="signup-recruiter">
			<div className="left-panel">
				<h1>Recruiter</h1>
				<h2>Inscription ...</h2>
				<p>
					Lorem ipsum dolor sit amet consectetur adipisicing elit.
					Reprehenderit deserunt rerum, pariatur iste facere obcaecati
					voluptatem ab voluptas, neque qui illum dolore omnis beatae
					aliquid fuga enim ea necessitatibus id.
				</p>
			</div>
			<div className="right-panel">
				<Signup
					title="Bienvenue"
					subtitle="Inscrivez vous ici !"
					error2="Ttest"
					inputs={inputs}
					status={2001}
					isValid={isValid}
				/>
			</div>
		</div>
	);
}

export default SignupAsRecruiter;
