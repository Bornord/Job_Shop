import React, { useState, useEffect } from 'react';
import "./Question.scss";
import NextButton from '../../buttons/nextButton/NextButton'


const Question = ({question,answer,callback}) =>{



    return (
        <div className='question'>
            <h2>{question}</h2>
            <input type="text" className="question-input" />
            <br />
            <NextButton
                className="next_button"
                onClick={callback}
            >
				Next
            </NextButton>
        </div>
    )
}

export default Question;