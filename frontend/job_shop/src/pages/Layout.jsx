import React from 'react';
import { Outlet } from 'react-router-dom';

import '../styles/index.scss';
import '../styles/App.scss';

function Layout() {
	return (
		<div>
			<Outlet />
		</div>
	);
}

export default Layout;
