import React from 'react';
import { Routes, Route } from 'react-router-dom';

import '../styles/index.css';

import Home from '../pages/home/Home';
import Survey from '../pages/survey/Survey';
import Offers from '../pages/offers/Offers';
import Results from '../pages/results/Results';
import Error from './Error';

function MainContainer() {
	//console.log(useLocation().pathname);
	return (
		<div className="padding">
			<Routes>
				<Route path="/" element={<Home />}></Route>
				<Route
					path="/survey/:questionNumber"
					element={<Survey />}
				></Route>
				<Route path="/offers" element={<Offers />}></Route>
				<Route path="/results" element={<Results />}></Route>
				<Route path="/*" element={<Error />}></Route>
			</Routes>
		</div>
	);
}

export default MainContainer;
