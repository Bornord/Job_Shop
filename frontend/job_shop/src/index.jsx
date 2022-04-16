import React from 'react';
import * as ReactDOMClient from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './styles/index.scss';

import WelcomePage from './pages/WelcomePage';
import App from './pages/App';

import reportWebVitals from './config/reportWebVitals';
import { configureStore } from '@reduxjs/toolkit';
import { Provider } from 'react-redux';
import userReducer from './logic/features/user';
import themeReducer from './logic/features/theme';

const store = configureStore({
	reducer: {
		user: userReducer,
		theme: themeReducer,
	},
});
const container = document.getElementById('root');
const root = ReactDOMClient.createRoot(container);
root.render(
	<div className="App">
		<Provider store={store}>
			<BrowserRouter>
				<Routes>
					<Route path="*" element={<WelcomePage />} />

					<Route path="/:student/*" element={<App />} />
					<Route path="/:recruiter/*" element={<App />} />
				</Routes>
			</BrowserRouter>
		</Provider>
	</div>
);

reportWebVitals();
