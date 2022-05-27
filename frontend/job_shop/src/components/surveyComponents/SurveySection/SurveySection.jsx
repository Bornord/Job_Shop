import React, { useState, useEffect  } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Question from '../question/Question';
import "./SurveySection.scss";
import {api,get,post} from '../../../logic/api/api';

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

const SurveySection = ({onSubmit})=>{
    const [survey,setSurvey] = useState( [] );
    const [currentQuestion,setCurrentQuestion] = useState(survey)
    const [previousQuestion,setPreviousQuestion] = useState([])
    const [error, setError] = useState('');
    const [profile,setProfile] = useState([])
    const user = useSelector((state) => state.user.value);

    useEffect(()=>{

        get("getCurrentSurvey",(res)=>{
            console.log(res);
            setSurvey(res.data)
            setCurrentQuestion(survey)
        },(e) =>{
            setError(e)
        } ) 
    },[])

    const handleNext = (response)=>{
        setPreviousQuestion((prev)=> {
            prev.push(currentQuestion)
            return prev
        })
        setProfile((prev)=>{
            prev.push({
                idUser:user.id,
                idQuestion:currentQuestion.id,
                idResponse:response.id,
                extra:response.user_response
            })
        })
        setCurrentQuestion(response.nextQuestion)
    }
    const handlePrevious = ()=>{
        if(previousQuestion.length > 0) {
            setPreviousQuestion((prev)=>{
                prev.pop()
                return prev
            }
            )
            setCurrentQuestion(previousQuestion[previousQuestion.length-1])
        }
    }
    const handleSubmit = ()=>{
        onSubmit()
    }

    return <>
            {error !== '' && <div className='error'>{error}</div>}
           {
               survey.length > 0 &&
                <Question question={currentQuestion.title} multiple={currentQuestion.multiple} answers={currentQuestion.responses} previous={handlePrevious} next={handleNext} submit={handleSubmit}/>
           } 
    </>
}

export default SurveySection;