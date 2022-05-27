import React, { useState, useEffect  } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Question from '../question/Question';
import "./SurveySection.scss";
import {api,get,post} from '../../../logic/api/api';


const SurveySection = ({onSubmit})=>{
    /*const [survey,setSurvey] = useState( [] );
    const [currentQuestion,setCurrentQuestion] = useState()
    const [previousQuestion,setPreviousQuestion] = useState([])
    const [profile,setProfile] = useState([])*/

    const [state,setState] = useState() 
    const [error, setError] = useState('');
    const user = useSelector((state) => state.user.value);

    useEffect(()=>{

        get("getCurrentSurvey",(res)=>{
            console.log("get current survey");
            console.log(res.data.question);
            /*setSurvey(res.data)
            setCurrentQuestion(res.data.question)*/
            setState((prev) => {
                return {
                    survey:res.data,
                    currentQuestion:res.data.question,
                    previousQuestion:[],
                    profile:[]  
                }  
            } )
        },(e) =>{
            setError(e)
        } ) 
    },[])

    const handleNext = (response)=>{
        setState((prev) => {
            const {survey,currentQuestion,previousQuestion,profile} = prev
            const pq = [...previousQuestion,currentQuestion]
            const pr = [...profile,{
                idQuestion:currentQuestion.id,
                idResponse:response.id,
                extra:response.extra
            }]  
            const cq = response.nextQuestion
            const next = {
                survey:survey,
                currentQuestion:cq,
                previousQuestion:pq,
                profile:pr  
            }  
            console.log(next);
            return next
        } )
    }
    const handlePrevious = ()=>{
        if(state.previousQuestion.length > 0) {
            setState((prev) => { 
                const {survey,currentQuestion,previousQuestion,profile} = prev
                const cq = previousQuestion[previousQuestion.length-1]
                previousQuestion.pop()
                profile.pop()                
                const pq = [...previousQuestion,currentQuestion]
                const pr = [...profile]   
                const next = {
                    survey:survey,
                    currentQuestion:cq,
                    previousQuestion:pq,
                    profile:pr  
                }  
                console.log(next);
                return next
            } )
        }
    }
    const handleSubmit = (response)=>{
        console.log(response);
        let _profile = state.profile
        _profile.push({
            idQuestion:state.currentQuestion.id,
            idResponse:response.id,
            extra:response.extra
        })
        onSubmit(_profile)
        setState((prev) => {
            let {survey,currentQuestion,previousQuestion,profile} = prev
            return {
                survey:survey,
                currentQuestion:currentQuestion,
                previousQuestion:previousQuestion,
                profile:[]   
            }  
        } )
        
        
    }
    return <>
            {error !== '' && <div className='error'>{error}</div>}
           {
               (state != null && state.currentQuestion != null) &&
                <Question question={state.currentQuestion.title} multiple={state.currentQuestion.multiple} answers={state.currentQuestion.responses} previous={handlePrevious} next={handleNext} submit={handleSubmit}/>
           } 
    </>
}

export default SurveySection;