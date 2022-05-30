import React, { useState, useEffect  } from 'react';
import "./Offers.scss";
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';
import { Link } from 'react-router-dom';
import ListOffers from '../../components/viewoffers/viewlistoffer/Offers'

function Offers() {
		
	return <div className='offers'> 
		<Link to="/NewOffers"><SelectButton2>Nouvelle offre de stage</SelectButton2></Link>

		<ListOffers />

	</div>;
}

export default Offers;
