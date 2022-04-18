import React from 'react';
import { useNavigate } from 'react-router-dom';

import '../styles/App.scss';

function WelcomePage() {
	let navigate = useNavigate();
	return (
		<div>
			<div className="welcomePageWrapper">
				<div> Jobshop</div>
				<div>
					<input
						type="button"
						value="J'ai déjà un comtpe"
						onClick={() => {
							navigate('/login');
						}}
					/>
				</div>

				<div
					className="box"
					onClick={() => {
						navigate('/:student/signup');
					}}
				>
					Je cherche un stage
				</div>
				<div
					className="box"
					onClick={() => {
						navigate('/:recruiter/signup');
					}}
				>
					Je cherche un stagiaire
				</div>
			</div>
		</div>
	);
}

export default WelcomePage;
