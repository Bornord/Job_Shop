import React from 'react';
import * as ReactDOMClient from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './styles/index.scss';
import Welcome from './pages/welcome/Welcome';
import App from './pages/App';
import Signup from './components/signup/Signup';
import Login from './components/login/Login';
import Header from './components/header/Header';
import Footer from './components/footer/Footer';
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
				<Header />
				<Routes>
					<Route path="*" element={<Welcome />} />
					<Route path="/:student/signup" element={<Signup />} />
					<Route path="/:recruiter/signup" element={<Signup />} />
					<Route path="/login" element={<Login />} />
					<Route path="/:student/*" element={<App />} />
					<Route path="/:recruiter/*" element={<App />} />
				</Routes>
			</BrowserRouter>
			<Footer />
		</Provider>
	</div>
);

reportWebVitals();
