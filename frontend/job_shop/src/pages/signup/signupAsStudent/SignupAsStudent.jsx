import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import Signup from '../../../components/signup/Signup';
import './SignupAsStudent.scss'

function SignupAsStudent() {
	const inputs = [
		{
			id : "firstname",
			type : "text",
			label : "Prénom :",
		},
		{
			id : "name",
			type : "text",
			label : "Nom :",
		},
		{
			id : "email",
			type : "email",
			label : "Email :",
		},
		{
			id : "password",
			type : "password",
			label : "Mot de passe :",
		},
		{
			id : "confirmed-password",
			type : "password",
			label : "Confirmer le mot de passe :",
		},
    ]

	const isValid = (state) => {
		const password = state.find((input)=> input.id == "password")
		const confirmedPassword = state.find((input)=> input.id == "confirmed-password")

		return password.value == confirmedPassword.value
	}

	return <div className='signup-student'>
		<div className='left-panel'>
			<Signup 
				title="Bienvenue" 
				subtitle="Inscrivez vous ici !" 
				inputs={inputs} 
				status = {2000}
				isValid={isValid}
			/>
		</div>
		<div className='right-panel'>
			<h1>Student</h1>
			<h2>Inscription ...</h2>
			<p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Reprehenderit deserunt rerum, pariatur iste facere obcaecati voluptatem ab voluptas, neque qui illum dolore omnis beatae aliquid fuga enim ea necessitatibus id.</p>
		</div>
	</div>;
}

export default SignupAsStudent;

