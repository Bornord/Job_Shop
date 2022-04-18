import { useLocation, Navigate, Outlet } from 'react-router-dom';
import { useSelector } from 'react-redux';

function RequireAuth() {
	const user = useSelector((state) => state.user.value);
	const location = useLocation();
	console.log(location);
	return user.name !== '' ? (
		<Outlet />
	) : (
		<Navigate to={`/`} state={{ from: location }} replace />
	);
}
//test
export default RequireAuth;
