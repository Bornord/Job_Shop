import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Signup from '../../../components/signup/Signup';
import './SignupAsStudent.scss';

function SignupAsStudent() {
	const inputs = [
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
		const name = state.find((input) => input.id == 'name');
		const firstname = state.find((input) => input.id == 'firstname');
		const email = state.find((input) => input.id == 'email');
		const emptyFieldName = name.value == '' && firstname.value == '';
		const emptyFieldMail = email.value == '';
		const emptyFieldMDP =
			email.value == '' && confirmedPassword.value == '';
		console.log(emptyFieldMDP);
		if (password.value != confirmedPassword.value) {
			state.erreurFonction('Les mots de passe ne correspondent pas');
			return false;
		} else if (emptyFieldName) {
			state.erreurFonction(
				'Vous devez remplir les champs Nom et Prénom.'
			);
			return false;
		} else if (emptyFieldMail) {
			state.erreurFonction('Vous devez remplir le champ Email.');
			return false;
		} else if (emptyFieldMDP) {
			state.erreurFonction(
				'Vous devez remplir les champs relatif à votre mot de passe.'
			);
			return false;
		} else {
			return true;
		}
	};

	return (
		<div className="signup-student">
			<div className="left-panel">
				<Signup
					title="Bienvenue"
					subtitle="Inscrivez vous ici !"
					inputs={inputs}
					errorStack=""
					status={2000}
					isValid={isValid}
				/>
			</div>
			<div className="right-panel">
				<h1>Student</h1>
				<h2>Inscription ...</h2>
				<p>
					Lorem ipsum dolor sit amet consectetur adipisicing elit.
					Reprehenderit deserunt rerum, pariatur iste facere obcaecati
					voluptatem ab voluptas, neque qui illum dolore omnis beatae
					aliquid fuga enim ea necessitatibus id.
				</p>
			</div>
		</div>
	);
}

export default SignupAsStudent;
