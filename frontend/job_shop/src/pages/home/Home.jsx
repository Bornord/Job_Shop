import '../../styles/App.scss';
import { useSelector } from 'react-redux';
import { useDispatch } from 'react-redux';
import { login, logout } from '../../logic/features/user';
import { changeColor } from '../../logic/features/theme';
import ListStatus from '../../components/viewstatus/liststatus/ListStatus'
import React, { useState } from 'react';

function Home() {
	const user = useSelector((state) => state.user.value);
	const dispatch = useDispatch();
	return (
		<div>
			<h1>Bienvenue {user.name}</h1>

			<p>Liste des {user.status === 2001 ? "étudiant" : "offres"} pouvant vous intéressé :</p>
			{ user.status === 2000 && <ListStatus />} 
			<button
				onClick={() => {
					dispatch(logout());
				}}
			>
				Logout
			</button>
		</div>
	);
}

export default Home;
