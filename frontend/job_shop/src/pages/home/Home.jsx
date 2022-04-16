import '../../styles/App.scss';
import { useSelector } from 'react-redux';
import { useDispatch } from 'react-redux';
import { login, logout } from '../../logic/features/user';
import { changeColor } from '../../logic/features/theme';

import React, { useState } from 'react';

function Home() {
	const user = useSelector((state) => state.user.value);
	const themeColor = useSelector((state) => state.theme.value);
	const dispatch = useDispatch();
	const [color, setColor] = useState('');
	return (
		<div>
			<div style={{ color: themeColor }}>
				<p>Color : {themeColor}</p>
				<h1>Voici le home</h1>
				<p>Name: {user.name}</p>
				<p>Age:{user.age}</p>
				<p>Email:{user.email}</p>
			</div>
			<p> ----- </p>
			<button
				onClick={() => {
					dispatch(
						login({
							name: 'Enguerran',
							age: 20,
							email: 'd@c.co',
						})
					);
				}}
			>
				Login
			</button>
			<button
				onClick={() => {
					dispatch(logout());
				}}
			>
				Logout
			</button>
			<br />
			<input
				type="text"
				onChange={(e) => {
					setColor(e.target.value);
				}}
			/>
			<button
				onClick={() => {
					dispatch(changeColor(color));
				}}
			>
				Change Color
			</button>
		</div>
	);
}

export default Home;
