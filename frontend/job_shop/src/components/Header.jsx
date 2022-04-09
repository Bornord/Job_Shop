import { Link } from 'react-router-dom';
import '../styles/App.css';
import { useParams } from 'react-router-dom';

const classNames = require('classnames');

// import logo from '../assets/logo.png';

function Header() {
	const bool = true;
	const nom = classNames({ green: bool, black: !bool });
	const parsing = useParams().type;
	return (
		<div>
			<nav className="header_wrapper">
				<div>
					<div>
						{/* <img
						src={logo}
						alt="Logo de JobShop"
					/> */}
					</div>
					<div>JobShop</div>
				</div>
				<div className="nav_container">
					<div className="nav_wrapper">
						<Link className="hyperlink" to="/parsing/">
							Accueil
						</Link>
						<Link className="hyperlink" to="/parsing/offers">
							Offres
						</Link>
						<div>
							<Link
								className="header_button"
								to="/parsing/survey/1"
							>
								Trouver un stage
							</Link>
						</div>
						<Link className={nom} to="/*/recruiters">
							Espace recruteur
						</Link>
					</div>
				</div>
			</nav>
		</div>
	);
}

export default Header;
