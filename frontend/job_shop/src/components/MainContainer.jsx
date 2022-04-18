import React from 'react';
import { Routes, Route } from 'react-router-dom';

import '../styles/index.scss';

import Home from '../pages/home/Home';
import Survey from '../pages/survey/Survey';
import Offers from '../pages/offers/Offers';
import Results from '../pages/results/Results';
import Error from './error/Error';

import Layout from '../pages/Layout';

import RequireAuth from '../logic/Auth/RequireAuth';

function MainContainer() {
	//console.log(useLocation().pathname);
	return (
		<Routes>
			<Route path="/" element={<Layout />}>
				<Route path="/" element={<Home />}></Route>
				<Route element={<RequireAuth />}>
					<Route
						path="/survey/:questionNumber"
						element={<Survey />}
					></Route>
					<Route path="/offers" element={<Offers />}></Route>
					<Route path="/results" element={<Results />}></Route>
					<Route path="/*" element={<Error />}></Route>
				</Route>
			</Route>
		</Routes>
	);
}

export default MainContainer;
