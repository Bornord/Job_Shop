import React from 'react';

import '../styles/index.scss';

import Header from '../components/Header';
import Footer from '../components/Footer';
import MainContainer from '../components/MainContainer';

import '../styles/App.scss';

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
