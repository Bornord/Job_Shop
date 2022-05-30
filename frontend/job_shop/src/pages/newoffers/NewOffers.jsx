import React, { useState, useEffect  } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import SurveySection from '../../components/surveyComponents/SurveySection/SurveySection';
import "./NewOffers.scss";
import {api,get,post} from '../../logic/api/api'
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';

function Offers() {
	let navigate = useNavigate();
	const user = useSelector((state) => state.user.value);
	const [title,setTitle] = useState("");
	const [subtitle,setSubtitle] = useState("");
	const [description,setDescription] = useState("");
	const [contract,setContract] = useState("");
	const [startDate,setStartDate] = useState(new Date());
	const [endDate,setEndDate] = useState(new Date());
	const [term,setTerm] = useState(0);
	const [salary,setSalary] = useState(0);
	const [error,setError] = useState(); 
	const [profile,setProfile] = useState();
	const isRecruiter = user.status == 2001

	const validate = ()=>{
		return user.id != 1999 
		&& startDate != null 
		&& endDate != null 
		&& term != null && term > 0
		&& salary != null && salary > 0
		&& title !== ""
		&& subtitle !== ""
		&& description !== ""
		&& contract !== ""
		;
	} 
	const handleProfile = (p) => {
		let profile = {
			idUser:user.id,
			term:parseInt(term),
			isRecruiter:user.status==2001,
			startDate:startDate.toISOString().slice(0,10),
			endDate:endDate.toISOString().slice(0,10),
			surveyAnswer:p
		} 
		console.log(profile);
		setProfile(profile)
	} 

	const handleSubmit = ()=>{
		if(validate()) {
			const offer = {
				title:title,
				subTitle:subtitle,
				description:description,
				salary:parseInt(salary),
				contract:contract,
				startDate:startDate.toISOString().slice(0,10),
				endDate:endDate.toISOString().slice(0,10),
				profile:profile,
				idRecruiter:user.id,
				term:parseInt(term)
			} 
			const path = "addOffer"
			post(path,offer,(data)=>{
				console.log(data);
				navigate('/Offers');
			},(e)=>{
				console.log(e);
				if(e.message != null){
					setError(e.message)
				} else{
					setError(e)
				} 
			} )
			
		} 
	}	
	return <div className='offers'> 
		<h1>Nouvelle offre :</h1>
			{error && 
				<p>{error}</p>
			}	

			<DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />
			<DatePicker selected={endDate} onChange={(date) => setEndDate(date)} />
			<input type="number" name="term" id="term" value={term} 
				onChange= {(e)=>{
					setTerm(e.target.value)
				} } 
			/>
			<p>Remplissez ce questionnaire pour choisir votre profile ideal</p>
			<SurveySection onSubmit={handleProfile}/>
			<div className="offer-form">
					<div className="input-container ic1">
						<input
							id="title"
							className={'offer-input'}
							type="text"
							value={title}
							onChange={(e) => setTitle(e.target.value)}
							placeholder=" "
						/>
						<div className="cut"></div>
						<label htmlFor="title" className="placeholder">
							Titre :
						</label>
					</div>
					<div className="input-container ic2">
						<input
							id="subtitle"
							className={'offer-input'}
							type="text"
							value={subtitle}
							onChange={(e) => setSubtitle(e.target.value)}
							placeholder=" "
						/>
						<div className="cut"></div>
						<label htmlFor="subtitle" className="placeholder">
							Sous titre :
						</label>
					</div>
					<div className="input-container ic2">
						<input
							id="contract"
							className={'offer-input'}
							type="text"
							value={contract}
							onChange={(e) => setContract(e.target.value)}
							placeholder=" "
						/>
						<div className="cut"></div>
						<label htmlFor="contract" className="placeholder">
							Contract :
						</label>
					</div>
					<div className="input-container ic2">
						<p >
							Salaire (par mois) :
						</p>
						<input 
							type="number" 
							name="salary" 
							id="salary" 
							value={salary} 
							onChange= {(e)=>{
								setSalary(e.target.value)
							} } 
						/>
						<div className="cut"></div>
					</div>

					<div className="input-container ic2">
						<input
							id="description"
							className={'offer-input'}
							type="text"
							value={description}
							onChange={(e) => setDescription(e.target.value)}
							placeholder=" "
						/>
						<div className="cut"></div>
						<label htmlFor="description" className="placeholder">
							Description :
						</label>
					</div>
					<SelectButton2 className={'submit_button'} type="submit" onClick={handleSubmit} >
						Ajouter offre
					</SelectButton2>
				</div>
	</div>;
}

export default Offers;
