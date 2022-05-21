import './Survey.scss';
import SurveySection from '../../components/surveyComponents/SurveySection/SurveySection';
import { useDispatch, useSelector } from 'react-redux';
import React, { useState, useEffect  } from 'react';
import { get } from '../../logic/api/api';

const testSurvey = {
    title:"question 1",
    multiple : true,
    responses:[{
        placeholder :"R1",
        user_response:"",
        isSelected:false,
        nextQuestion:{
            title:"question2 après R1",
            multiple : false,
            responses:[{
                placeholder:"",
                user_response:"",
                isSelected:false
            }]
        }
    },
    {
        placeholder :"R2",
        user_response:"",
        isSelected:false,
        nextQuestion:{
            title:"question2 après R2",
            multiple : false,
            responses:[{
                placeholder:"",
                user_response:"",
                isSelected:false
            }]
        }
    },
    {
        placeholder :"R3",
        user_response:"",
        isSelected:false,
        nextQuestion:{
            title:"question2 après R3",
            multiple : true,
            responses:[{
                placeholder:"R4",
                user_response:"",
                isSelected:false
            },{
                placeholder:"R5",
                user_response:"",
                isSelected:false
            },
            ]
        }
    }]
}

function Survey() {
	const user = useSelector((state) => state.user.value);
	const [survey,setSurvey] = useState(testSurvey);
	const isRecruiter = user.status == 2001
	const handleSubmit = (profile)=>{
		const path = isRecruiter ? "addProfileRecruiter" : "addProfileStudent"
		get(path,profile,(res)=>setSurvey(res.data))
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
