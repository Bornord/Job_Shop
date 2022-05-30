import React, { useState, useEffect  } from 'react';
import './DashBoard.scss';
import { useNavigate } from 'react-router-dom';
import SurveySection from '../../components/surveyComponents/SurveySection/SurveySection';
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';
import { Link } from 'react-router-dom';
import { TiTimes } from 'react-icons/ti';
import {api,get,post} from '../../logic/api/api'
import AddQuestion from '../../components/addQuestion/AddQuestion'

export default function DashBoard() {
    const [allSurvey,setAllSurvey] = useState([]) 
    const [errorOnSurvey,setErrorOnSurvey] = useState('') 
    const [selectedSurvey,setSelectedSurvey] = useState(0) 
    let navigate = useNavigate();
    const handleAddSurvey = (question,isAText,responses)=>{

        post("addSurvey",        {
            title: question,
            responses: isAText ? '' : responses,
        },(res)=>{
           
        } )
    } 
    const handleUpdateSurvey = ()=>{
        if(selectedSurvey != 0){
            post("setCurrentSurvey",        {
                id:parseInt(selectedSurvey)
            },(res)=>{
               
            } )
        } 
    } 

    useEffect(()=>{
        get("getAllSurveys",(res)=>{
            console.log(res);
            setAllSurvey(res.data)
        },(e)=>{
            if(e.message != null){
                setErrorOnSurvey(e.message);
            } else{
                setErrorOnSurvey(e)
            } 
        } )
    },[] )
	return (
		<div className='dashboard'>
            <div className="editQuestionnaire">
                <SurveySection onSubmit={()=>{}}/>
                <br />
                <Link to="/EditSurvey"><SelectButton2>Edit</SelectButton2></Link>
            </div>
            <div className="changeSurvey">
                {errorOnSurvey && 
                    <p>{errorOnSurvey}</p>
                }	
                <h2>Ajouter un questionnaire </h2>
                <AddQuestion handleSubmit={handleAddSurvey} submitText="Ajouter un questionnaire" />
                <select
                    onChange={
                        (e)=>{
                            setSelectedSurvey(e.target.value)
                        } 
                    }
                >
                    <option value={0} >Selectionnez un questionnaire</option>
                    {allSurvey.map((survey)=>{
                        return <option key={survey.id} value={survey.id} >{survey.title} </option>
                    } )} 
                </select>
               {selectedSurvey !== 0 &&
                    <SelectButton2 onClick={handleUpdateSurvey}>Utiliser ce questionnaire</SelectButton2>
               } 
            </div>
            <div className="newBlog">
                <Link to="/NewBlog"><SelectButton2>Ajouter un blog</SelectButton2></Link>
            </div>
		</div>
	);
}