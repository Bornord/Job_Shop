import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { post,get } from '../../../logic/api/api';
import Signup from '../../../components/signup/Signup';
import './SignupAsStudent.scss';
import{ translateType} from '../signUpUtils'; 

/*const inputs = [
	{
		id: 'name',
		type: 'text',
		label: 'Prénom :',
	},
	{
		id: 'surname',
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
		id: 'confirmedPassword',
		type: 'password',
		label: 'Confirmer le mot de passe :',
	},
];*/
function SignupAsStudent() {
	const [inputs,setInputs] = useState([])
	useEffect(()=>{
		get("getStudentFields",(res)=> {
			const data = res.data
			const _inputs = data.filter((input)=> input.label !== "").map(
				(input)=>{
					let {id,type,label} = input 
					type = translateType(id,type)
					return {
						id:id,
						type : type,
						label : label == "" ? "..." : label,
					} 
				} 
			)
			_inputs.push(	{
				id: 'confirmedPassword',
				type: 'password',
				label: 'Confirmer le mot de passe :',
			})
			console.log(_inputs);
			setInputs(_inputs)
		} ,(e)=> {
			console.log(e);
		} )
	} 
	,[] )  

	const isValid = (state) => {
		const {password,confirmedPassword} = state
		const emptyFieldName = state.name === ''||  state.surname === '';
		const emptyFieldMail = state.email === '';
		const emptyFieldPassword = password === ''||  confirmedPassword === '';
		if (password.value !== confirmedPassword.value) {
			state.error('Les mots de passe ne correspondent pas');
			return false;
		} else if (emptyFieldName) {
			state.error(
				'Vous devez remplir les champs Nom et Prénom.'
			);
			return false;
		} else if (emptyFieldMail) {
			state.error('Vous devez remplir le champ Email.');
			return false;
		} else if (emptyFieldPassword) {
			state.error('Vous devez remplir les champs Mot de passe.');
			return false;
		} else {
			return true;
		}
	};

	return (
		<div className="signup-student">
			<div className="left-panel">
			{
				inputs.length > 0 &&
				<Signup
					title="Bienvenue"
					subtitle="Inscrivez vous ici !"
					inputs={inputs}
					errorStack=""
					status={2000}
					isValid={isValid}
				/>
			}	
				
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
