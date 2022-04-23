import React from 'react';
import { useNavigate } from 'react-router-dom';
import SelectButton from '../../components/buttons/selectButton/SelectButton'
import './Welcome.scss';

export default function Welcome() {
	let navigate = useNavigate();
	return (
		<div className='welcome'>
			<h1>
				Binvenue sur JobShop !
			</h1>
			<h2>Lorem ipsum dolor sit amet consectetur adipisicing elit. Architecto dicta expedita natus placeat et? Atque ea a ratione perspiciatis aspernatur.</h2>


			<SelectButton
				className={"welcome_button"}
				onClick={() => {
					navigate('/SignUpAsStudent');
				}}
			>
					Je cherche un stage
			</SelectButton>
			<SelectButton
				className={"welcome_button"}
				onClick={() => {
					navigate('/SignUpAsRecruiter');
				}}
			>
					Je cherche un stagiaire
			</SelectButton>


			

		</div>
	);
}