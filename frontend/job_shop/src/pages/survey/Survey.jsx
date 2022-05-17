import './Survey.scss';
import SurveySection from '../../components/surveyComponents/SurveySection/SurveySection';
import { get } from '../../logic/api/api';

function Survey() {
	const user = useSelector((state) => state.user.value);
	const isRecruiter = user.status == 2001
	const handleSubmit = (profile)=>{
		const path = isRecruiter ? "addProfileRecruiter" : "addProfileStudent"
		get(path,profile,(res)=>setSurvey(res.data))
	}

	return <div className='survey'>
		<h1>Questionnaire :</h1>

		<input type="datetime-local" name="start_date" id="" />
		<input type="datetime-local" name="end_date" id="" />
		<input type="number" name="term" id="" />

		<SurveySection onSubmit={handleSubmit}/>
	</div>
}

export default Survey;
