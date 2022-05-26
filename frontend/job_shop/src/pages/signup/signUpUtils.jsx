



export const translateType = (id,type)=>{
    if(id === "Email"|| id === "email" ){
        return "email"
    } 
    if(id === "password" || id === "Password"){
        return "password"
    }  
    if(type === "String"){
        return "text"
    } 
    if(type === "int" || type === "Int" ||type === "Integer"){
        return "number"
    } 
    if(type === "bool" || type === "boolean" ||type === "Boolean"){
        return "checkbox"
    } 
    if(type === "double" || type === "Double"){
        return "number"
    } 
    return type
} 
