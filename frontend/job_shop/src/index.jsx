import React from 'react';
import * as ReactDOMClient from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

import './styles/index.css';

import Index from './pages/App';
import Error from './components/Error.jsx';
// import { ThemeProvider } from './logic/Context/Context';
import reportWebVitals from './config/reportWebVitals';

const container = document.getElementById('root');
const root = ReactDOMClient.createRoot(container);
root.render(
	<div className="App">
		<BrowserRouter>
			{/* <ThemeProvider> */}
			<Routes>
				<Route path="/*" element={<Index />} />
				<Route path="*" element={<Error />} />
			</Routes>
			{/* </ThemeProvider> */}
		</BrowserRouter>
	</div>
);

reportWebVitals();
