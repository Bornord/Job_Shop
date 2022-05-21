import React, { useState, useEffect  } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Question from '../question/Question';
import "./SurveySection.scss";
import {api} from '../../../logic/api/api';



const SurveySection = ({survey,setSurvey,onSubmit})=>{
    const [currentQuestion,setCurrentQuestion] = useState(survey)
    const [previousQuestion,setPreviousQuestion] = useState([])
    const [profile,setProfile] = useState([])
    const user = useSelector((state) => state.user.value);

    useEffect(()=>{
        const fecthQuestion = async ()=>{
            try{
                const res = await api.get("/getSurvey")
                setSurvey(res.data)
            }catch(e){
                
            }
        }
        //fecthQuestion()
        //setCurrentQuestion(survey)
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
        <Question question={currentQuestion.title} multiple={currentQuestion.multiple} answers={currentQuestion.responses} previous={handlePrevious} next={handleNext} submit={handleSubmit}/>
    </>
}

export default SurveySection;