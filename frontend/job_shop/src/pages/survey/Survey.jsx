import '../../styles/App.scss';
import SurveySection from '../../components/surveyComponents/SurveySection/SurveySection';

function Survey() {

	const handleSubmit = ()=>{
		
	}

	return <div className='survey'>
		<h1>Questionnaire :</h1>
		<SurveySection onSubmit={handleSubmit}/>
	</div>
}

export default Survey;
