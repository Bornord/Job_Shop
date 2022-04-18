import React from 'react';
import './NextButton.scss';

const NextButton = ({className,onClick,children,type}) => {
    return <button  type = {type} class={`${className} button button--greip`} onClick={onClick}><span><span>{children}</span></span></button>
    
}
export default NextButton;