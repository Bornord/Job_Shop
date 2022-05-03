import './Navbar.scss';
import { Link, useLocation, useParams } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import LineText from '../lineText/LineText';
import { FaUserCircle } from 'react-icons/fa';

const studentLinks = [
	{
		title: 'Blog',
		to: '/Blog',
		visible: true,
		active: false,
		id: 2,
	},
	{
		title: 'Trouver un stage',
		to: '/Survey',
		visible: false,
		active: false,
		id: 1,
	},
];

const recruiterLinks = [
	{
		title: 'Blog',
		to: '/Blog',
		visible: true,
		active: false,
		id: 5,
	},
	{
		title: 'Espace recruteur',
		to: '/Recruiters',
		visible: false,
		active: false,
		id: 4,
	},
	{
		title: 'Offres',
		to: '/Offers',
		visible: false,
		active: false,
		id: 2,
	},
];

const adminLinks = [
	{
		title: 'Dashboard',
		to: '/Dashboard',
		active: true,
		id: 1,
	},
	{
		title: 'Suivie',
		to: '/FollowUp',
		active: false,
		id: 2,
	},
];

const unregisteredUserLinks = [
	{
		title: "J'ai déja un compte",
		to: '/login',
		active: true,
		id: 1,
	},
	{
		title: 'About Us',
		to: '/AboutUs',
		active: false,
		id: 2,
	},
	{
		title: 'Blog',
		to: '/Blog',
		active: false,
		id: 3,
	},
];

const initLinks = (status) => {
	switch (status) {
		case 1999:
			return unregisteredUserLinks;
		case 2000:
			return studentLinks;
		case 2001:
			return recruiterLinks;
		case 2002:
			return adminLinks;
		default:
			return unregisteredUserLinks;
	}
};

const Navbar = () => {
	const user = useSelector((state) => state.user.value);

	//console.log(user);

	const [links, setLinks] = useState(initLinks(user.status));
	//console.log(links);
	useEffect(() => {
		setLinks(initLinks(user.status));
	}, [user]);

	return (
		<nav className="navbar">
			<div className="title">
				<Link to="/">
					<h1>JobShop</h1>
				</Link>
			</div>
			<ul className="navigation-list">
				{links
					.sort((link1, link2) => link1.id - link2.id)
					.map((link) => {
						let active = link.active ? 'active' : '';
						return (
							<li key={link.id}>
								<Link
									key={link.id}
									className={`separator ${active}`}
									to={link.to}
									onClick={() => {
										setLinks((state) => {
											return state.map((l) => {
												let { title, to, active, id } =
													l;
												return {
													title: title,
													to: to,
													active:
														link.id == id
															? true
															: false,
													id: id,
												};
											});
										});
									}}
								>
									<LineText>{link.title}</LineText>
								</Link>
							</li>
						);
					})}
			</ul>
			{user.status != 1999 && (
				<div className="user">
					<Link to="/">
						<p>{user.name != '' ? user.name : 'User'}</p>
					</Link>
					<FaUserCircle className="icon" />
				</div>
			)}
		</nav>
	);
};

export default Navbar;
