import React from 'react';
import './DashBoard.scss';
import SurveySection from '../../components/surveyComponents/SurveySection/SurveySection';
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';
import { Link } from 'react-router-dom';

export default function DashBoard() {
	return (
		<div className='dashboard'>
            <div className="editQuestionnaire">
                <SurveySection onSubmit={()=>{}}/>
                <br />
                <Link to="/EditSurvey"><SelectButton2>Edit</SelectButton2></Link>
            </div>
		</div>
	);
}