import React from 'react';
import * as ReactDOMClient from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './styles/index.scss';
import App from './pages/App';
import SignupAsStudent from './pages/signup/signupAsStudent/SignupAsStudent';
import SignupAsRecruiter from './pages/signup/signupAsRecruiter/SignupAsRecruiter';
import Login from './components/login/Login';
import Header from './components/header/Header';
import Footer from './components/footer/Footer';
import reportWebVitals from './config/reportWebVitals';
import { configureStore } from '@reduxjs/toolkit';
import { Provider } from 'react-redux';
import userReducer from './logic/features/user';
import tokenReducer from './logic/features/token';
import themeReducer from './logic/features/theme';
import Welcome from './pages/welcome/Welcome';
import { PersistGate } from 'redux-persist/integration/react';
import { persistStore } from 'redux-persist';
import { combineReducers } from 'redux';
import { persistReducer } from 'redux-persist';
import thunk from 'redux-thunk';
import storage from 'redux-persist/lib/storage';

// configuration de la persistance redux
const reducers = combineReducers({
	user: userReducer,
	theme: themeReducer,
	token: tokenReducer,
});
const persistConfig = {
	key: 'root',
	storage,
};
const persistedReducer = persistReducer(persistConfig, reducers);
const store = configureStore({
	reducer: persistedReducer,
	devTools: process.env.NODE_ENV !== 'production',
	middleware: [thunk],
});
const persistor = persistStore(store);

// Config du projet react
const container = document.getElementById('root');
const root = ReactDOMClient.createRoot(container);

// C'est parti, on y va !
root.render(
	<div className="App">
		<Provider store={store}>
			<PersistGate loading={null} persistor={persistor}>
				<BrowserRouter>
					<Header />

					<Routes>
						<Route
							path="/SignUpAsRecruiter"
							element={<SignupAsRecruiter />}
						/>
						<Route
							path="/SignUpAsStudent"
							element={<SignupAsStudent />}
						/>
						<Route path="/Login" element={<Login />} />
						<Route path="/Welcome" element={<Welcome />} />
						<Route path="/*" element={<App />} />
					</Routes>
				</BrowserRouter>
				<Footer />
			</PersistGate>
		</Provider>
	</div>
);

reportWebVitals();
