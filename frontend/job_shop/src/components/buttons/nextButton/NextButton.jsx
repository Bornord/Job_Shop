import React from 'react';
import './NextButton.scss';

const NextButton = ({className,onClick,children,type}) => {
    return <button type = {type} className={`${className} button button--mimas`} onClick={onClick}><span>{children}</span></button>
}
export default NextButton;