import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import '../styles/App.scss';
import Home from './home/Home';
import Survey from './survey/Survey';
import Offers from './offers/Offers';
import Results from './results/Results';
import Error from './error/Error';
import DashBoard from './dashboard/DashBoard';
import EditSurvey from './editSurvey/EditSurvey';

import RequireAuth from '../logic/Auth/RequireAuth';
function App() {
	return (
		<Routes>
			<Route path="/" element={<RequireAuth />}>
				<Route path="/Home" element={<Home />}></Route>
				<Route path="/Survey" element={<Survey />} />
				<Route path="/Offers" element={<Offers />}></Route>
				<Route path="/Results" element={<Results />}></Route>
				<Route path="/Dashboard" element={<DashBoard />}></Route>
				<Route path="/EditSurvey" element={<EditSurvey />}></Route>
				<Route
					path="/"
					element={<Navigate to={`/Home`} replace />}
				></Route>
				<Route path="/*" element={<Error />}></Route>
			</Route>
		</Routes>
	);
}

export default App;
