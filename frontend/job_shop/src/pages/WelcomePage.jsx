import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

import '../styles/App.scss';

function WelcomePage() {
	let navigate = useNavigate();
	return (
		<div>
			<p> Test</p>
			<div className="welcomePageWrapper">
				<div
					onClick={() => {
						navigate('/:student/');
					}}
				>
					Je cherche un stage
				</div>
				<div
					onClick={() => {
						navigate('/:recruiter/');
					}}
				>
					Je cherche un stagiaire
				</div>
			</div>
		</div>
	);
}

export default WelcomePage;
