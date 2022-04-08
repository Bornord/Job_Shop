import React from 'react';

import '../styles/index.css';

import Header from '../components/Header';
import Footer from '../components/Footer';
import MainContainer from '../components/MainContainer';

import '../styles/App.css';

function App() {
	return (
		<div>
			<Header />
			<MainContainer />
			<Footer />
		</div>
	);
}

export default App;
