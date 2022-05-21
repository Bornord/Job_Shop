import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import SelectButton from '../../components/buttons/selectButton/SelectButton';
import './Welcome.scss';

import { get } from './../../logic/api/api';

export default function Welcome() {
	let navigate = useNavigate();
	let [rep, setRep] = useState();
	useEffect(() => {
		get(
			'',
			(res) => setRep(res.data.msg),
			(e) => console.log(e)
		);
	});
	const user = useSelector((state) => state.user.value);
	return (
		<div className="welcome">
			<h1>Binvenue sur JobShop !</h1>
			{console.log('test')}
			{console.log(user.name)}
			<p>Nom post-log : {user.name} </p>
			<h2>
				Lorem ipsum dolor sit amet consectetur adipisicing elit.
				Architecto dicta expedita natus placeat et? Atque ea a ratione
				perspiciatis aspernatur.
			</h2>
			<SelectButton
				className={'welcome_button'}
				onClick={() => {
					navigate('/SignUpAsStudent');
				}}
			>
				Je cherche un stage
			</SelectButton>
			<SelectButton
				className={'welcome_button'}
				onClick={() => {
					navigate('/SignUpAsRecruiter');
				}}
			>
				Je cherche un stagiaire
			</SelectButton>
		</div>
	);
}
