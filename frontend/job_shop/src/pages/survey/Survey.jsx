import './Survey.scss';
import SurveySection from '../../components/surveyComponents/SurveySection/SurveySection';
import { useDispatch, useSelector } from 'react-redux';
import React, { useState, useEffect  } from 'react';
import { get, post } from '../../logic/api/api';



function Survey() {
	const user = useSelector((state) => state.user.value);

	const isRecruiter = user.status == 2001
	const handleSubmit = (profile)=>{
		if(user.status != 1999) {
			const path = isRecruiter ? "addProfileRecruiter" : "addProfileStudent"
			post(path,profile,(data)=>{
				console.log(data);
			})
		} 
	}

	return <div className='survey'>
		<h1>Questionnaire :</h1>

		<input type="datetime-local" name="start_date" id="" />
		<input type="datetime-local" name="end_date" id="" />
		<input type="number" name="term" id="" />

		<SurveySection onSubmit={handleSubmit}/>
	</div>
}

export default Survey;
