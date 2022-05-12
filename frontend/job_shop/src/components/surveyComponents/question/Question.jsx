import React, { useState, useEffect } from 'react';
import "./Question.scss";
import NextButton from '../../buttons/nextButton/NextButton'
import { TiArrowLeft } from 'react-icons/ti';
import LineText from '../../lineText/LineText';

const Question = ({question,answers,next,previous,submit,multiple}) =>{

    const isAText = answers.length == 1 && answers[0].placeholder ===  ""
    const [_answers,setAnswers] = useState(answers)
    const isLast = answers.filter((a)=>{return a.nextQuestion !=  null}).length == 0
    return (
        <div className='question'>
            <div className="previous" onClick={()=>{
                previous()
            }
            }>
                <TiArrowLeft className='icon-back'/>
            </div>
            <h2>{question}</h2>

            {isAText ?
                <input type="text" className="question-input" />
                :
                _answers.map((answer)=>{
                    const active = answer.isSelected ? "active-answer":""
                    return <div key={answer.placeholder} className={'answer '+active} 
                    onClick={
                        (e)=>{
                            setAnswers((prev)=>{
                                return prev.map((a) => {
                                    let res = a.isSelected
                                    if(!multiple){
                                        res = answer.placeholder === a.placeholder
                                    }else{
                                        if(answer.placeholder === a.placeholder){
                                            res  = !res
                                        }
                                    }
                                    return {...a,isSelected:res}
                                })
                            })
                        }
                    }>{answer.placeholder}</div>
                })
            }
            <br />
            {isLast ? 
                <NextButton
                    className="next_button"
                    onClick={
                        ()=>{
                            const res = _answers.filter((a)=> a.isSelected)
                            if((isAText && _answers[0].user_response != "") || res.length > 0){
                                submit(res[0])
                            }
                        }
                    }
                >
                    Submit
                </NextButton>
            :
                <>
                    <NextButton
                        className="next_button"
                        onClick={
                            ()=>{
                                const res = _answers.filter((a)=> a.isSelected)
                                if((isAText && _answers[0].user_response != "") || res.length > 0){
                                    next(res[0])
                                }
                            }
                        }
                    >
                        Next
                    </NextButton>
                    <div className='linetext'
                    onClick={
                        ()=>{
                            const res = _answers.filter((a)=> a.isSelected)
                            if((isAText && _answers[0].user_response != "") || res.length > 0){
                                submit(res[0])
                            }
                        }
                    }>
                        <LineText>or submit now..</LineText>                        
                    </div>
                </>
            }
        </div>
    )
}

export default Question;