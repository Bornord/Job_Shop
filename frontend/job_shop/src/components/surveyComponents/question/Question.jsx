import React, { useState, useEffect } from 'react';
import "./Question.scss";
import NextButton from '../../buttons/nextButton/NextButton'
import { TiArrowLeft } from 'react-icons/ti';
import LineText from '../../lineText/LineText';

const Question = ({question,answers,next,previous,submit,multiple}) =>{

    const isAText = answers.length == 1 && answers[0].placeholder ===  ""
    const [_answers,setAnswers] = useState(answers)

    useEffect(()=>{
        if(_answers.nextQuestion){
            //console.log(_answers);
            setAnswers((p)=>{
                const tmp = answers.nextQuestion.map((a)=>{return  {...a,isSelected:false} } )
                console.log(tmp);
                return tmp
            } ) 
        }else{
            //console.log(_answers);
            setAnswers((p)=>{
                const tmp = answers.map((a)=>{return  {...a,isSelected:false} } )
                console.log(tmp);
                return tmp
            } ) 
        } 
    },[answers] )

    const [extra,setExtra] = useState("")  
    const [isLast,setIsLast] = useState(answers.filter((a)=>{return a.nextQuestion !=  null}).length == 0) 

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
                <input type="text" className="question-input" value={extra}
                    onChange={
                        (e)=>{
                            setExtra(e.target.value)
                        } 
                    } 
                />
                :
                _answers.map((answer)=>{
                    const active = answer.isSelected ? "active-answer":""
                    return <div key={answer.placeholder} className={'answer '+active} 
                    onClick={
                        (e)=>{
                            const index = _answers.indexOf(answer)
                            const nextSelection = !answer.isSelected 
                            if(nextSelection) {
                                setIsLast(answer.nextQuestion == null)
                            }

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
                            if((isAText && _answers[0].user_response != "") ){
                                const _res = {..._answers[0],extra:extra } 
                                submit(_res)
                            } 
                            if(res.length > 0){
                                const _res = {...res[0],extra:"" } 
                                submit(_res)
                            }
                        }
                    }
                >
                    Valider
                </NextButton>
            :
                <>
                    <NextButton
                        className="next_button"
                        onClick={
                            ()=>{
                                const res = _answers.filter((a)=> a.isSelected)
                                if((isAText && _answers[0].user_response != "") ){
                                    const _res = {..._answers[0],extra:extra } 
                                    next(_res)
                                } 
                                if(res.length > 0){
                                    const _res = {...res[0],extra:"" } 
                                    next(_res)
                                }
                            }
                        }
                    >
                        Suivant
                    </NextButton>
                    <div className='linetext'
                    onClick={
                        ()=>{
                            const res = _answers.filter((a)=> a.isSelected)
                            if((isAText && _answers[0].user_response != "") ){
                                const _res = {..._answers[0],extra:extra } 
                                submit(_res)
                            } 
                            if(res.length > 0){
                                const _res = {...res[0],extra:"" } 
                                submit(_res)
                            }
                        }
                    }>
                        <LineText>ou valider maintenant..</LineText>                        
                    </div>
                </>
            }
        </div>
    )
}

export default Question;