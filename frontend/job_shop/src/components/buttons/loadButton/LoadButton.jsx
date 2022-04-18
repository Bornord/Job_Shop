import React from 'react';
import './LoadButton.scss';

const LoadButton = ({className,onClick,children,type}) => {
    return <button type = {type} class={`${className} button button--fenrir`} onClick={onClick}>
    <svg aria-hidden="true" class="progress" width="70" height="70" viewbox="0 0 70 70">
        <path class="progress__circle" d="m35,2.5c17.955803,0 32.5,14.544199 32.5,32.5c0,17.955803 -14.544197,32.5 -32.5,32.5c-17.955803,0 -32.5,-14.544197 -32.5,-32.5c0,-17.955801 14.544197,-32.5 32.5,-32.5z" />
        <path class="progress__path" d="m35,2.5c17.955803,0 32.5,14.544199 32.5,32.5c0,17.955803 -14.544197,32.5 -32.5,32.5c-17.955803,0 -32.5,-14.544197 -32.5,-32.5c0,-17.955801 14.544197,-32.5 32.5,-32.5z" pathLength="1" />
    </svg>
    <span>{children}</span>
    </button>
    
}
export default LoadButton;