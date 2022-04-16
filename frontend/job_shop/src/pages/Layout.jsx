import React from 'react';
import { Outlet } from 'react-router-dom';

import '../styles/index.scss';

import Header from '../components/Header';
import Footer from '../components/Footer';
import MainContainer from '../components/MainContainer';

import '../styles/App.scss';

function Layout() {
	return (
		<div>
			<Outlet />
		</div>
	);
}

export default Layout;
