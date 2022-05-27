import './Survey.scss';
import { useNavigate } from 'react-router-dom';
import SurveySection from '../../components/surveyComponents/SurveySection/SurveySection';
import { useDispatch, useSelector } from 'react-redux';
import React, { useState, useEffect  } from 'react';
import { get, post } from '../../logic/api/api';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

function Survey() {
	const user = useSelector((state) => state.user.value);
	let navigate = useNavigate();
	const [startDate,setStartDate] = useState(new Date());
	const [endDate,setEndDate] = useState(new Date());
	const [term,setTerm] = useState(0);
	const [error,setError] = useState(); 
	const isRecruiter = user.status == 2001

	const validate = ()=>{
		return user.id != 1999 && startDate != null && endDate != null && term != null && term > 0;
	} 

	const handleSubmit = (p)=>{
		if(validate()) {
			let profile = {
				idUser:user.id,
				term:parseInt(term),
				isRecruiter:user.status==2001,
				startDate:startDate.toISOString().slice(0,10),
				endDate:endDate.toISOString().slice(0,10),
				surveyAnswer:p
			} 
			console.log(profile);
			const path = isRecruiter ? "addProfileRecruiter" : "addProfileStudent"
			post(path,profile,(data)=>{
				console.log(data);
				navigate('/Home');
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

	return <div className='survey'>
		<h1>Questionnaire :</h1>
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

		<SurveySection onSubmit={handleSubmit}/>
	</div>
}

export default Survey;
