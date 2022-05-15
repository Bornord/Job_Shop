import React, { useState, useEffect } from 'react';
import './EditSurvey.scss';
import SelectButton2 from '../../components/buttons/selectButton2/SelectButton2';
import { TiTimes } from 'react-icons/ti';
import LineText from '../../components/lineText/LineText';
import api from '../../logic/api/api';

const testQuestion = {
	title: 'test question ?',
	responses: [
		{
			placeholder: 'R1',
			user_response: '',
			isSelected: false,
		},
		{
			placeholder: 'R2',
			user_response: '',
			isSelected: false,
		},
		{
			placeholder: 'R3',
			user_response: '',
			isSelected: false,
		},
	],
};

export default function EditSurvey() {
	const [allQuestion, setAllQuestion] = useState({});
	const [question, setQuestion] = useState('');
	const [isAText, setOnText] = useState(true);
	const [responses, setResponses] = useState([]);
	const [currentResponse, setCurrentResponse] = useState('');
	const [isSearchBarVisible, setSearchBarVisible] = useState('');
	const [searchedQuestion, setSearchQuestion] = useState('');
	const [foundQuestion, setFoundQuestion] = useState({
		question: {},
		hasBeenFound: false,
	});
	const searchQuestion = (question) => {
		setFoundQuestion({ question: testQuestion, hasBeenFound: true });
	};

	const getFoundQuestionText = () => {
		const nb = foundQuestion.question.responses.filter(
			(response) => response.isSelected
		).length;
		let text = 'Ajouter après ';
		if (nb > 0) {
			const selectedResponse = foundQuestion.question.responses
				.filter((response) => response.isSelected)
				.reduce(
					(prev, response) =>
						prev + (prev !== '' ? ', ' : '') + response.placeholder,
					''
				);
			text +=
				(nb == 1 ? 'la réponse ' : 'les réponses ') + selectedResponse;
		} else {
			text += 'la question ';
		}
		return text;
	};

	useEffect(() => {
		const fecthQuestion = async () => {
			try {
				const res = await api.get('/getAllQuestion');
				setAllQuestion(res.data);
			} catch (e) {}
		};
		fecthQuestion();
	}, []);

	const validate = () => {
		return question != '' && (responses.lenght > 0 || isAText);
	};

	const handleAddToEnd = () => {
		const postQuestion = async () => {
			try {
				const res = await api.post('/addQuestionToEnd', {
					title: question,
					responses: isAText ? '' : responses,
				});
			} catch (e) {}
		};
		if (validate()) postQuestion();
	};
	const handleAddToQuestion = () => {
		const postQuestion = async () => {
			try {
				const res = await api.post('/addQuestionToQuestion', {
					addedToQuestion: foundQuestion.question?.title,

					addedToResponses:
						foundQuestion.question?.responses != ''
							? foundQuestion.question?.responses.filter(
									(response) => response.isSelected
							  )
							: '',

					title: question,
					responses: isAText ? '' : responses,
				});
			} catch (e) {}
		};
		if (validate()) postQuestion();
	};
	return (
		<div className="edit-survey">
			<div className="add-to-end">
				<form
					onSubmit={(e) => {
						e.preventDefault();
					}}
				>
					<div className="input-container ic1">
						<input
							id="question"
							className={'question-input'}
							type="text"
							value={question}
							onChange={(e) => setQuestion(e.target.value)}
							placeholder=" "
						/>
						<div className="cut-question"></div>
						<label htmlFor="question" className="placeholder">
							Question :
						</label>
					</div>

					<p>Réponse attendu :</p>
					<div className="choice-switch">
						<div className="choice-switch-item">
							<input
								type="radio"
								id="text"
								name="choice"
								value="text"
								onChange={(e) => setOnText(true)}
							/>
							<label htmlFor="text">Texte</label>
						</div>
						<div className="choice-switch-item">
							<input
								type="radio"
								id="responses"
								name="choice"
								value="responses"
								onChange={(e) => {
									setOnText(false);
								}}
							/>
							<label htmlFor="responses">Liste de réponse</label>
						</div>
					</div>
					{!isAText && (
						<>
							{responses.map((response) => {
								return (
									<div key={response} className="response">
										{response}
										<div
											onClick={(e) =>
												setResponses((prev) =>
													prev.filter(
														(r) => r !== response
													)
												)
											}
										>
											<TiTimes />
										</div>
									</div>
								);
							})}

							<div className="input-container ic1">
								<input
									id="response"
									className={'question-input'}
									type="text"
									value={currentResponse}
									onChange={(e) =>
										setCurrentResponse(e.target.value)
									}
									placeholder=" "
									onKeyDown={(e) => {
										if (e.key === 'Enter') {
											setResponses((prev) => {
												if (
													prev.includes(
														currentResponse
													)
												)
													return prev;
												return [
													...prev,
													currentResponse,
												];
											});
											setCurrentResponse('');
										}
									}}
								/>
								<div className="cut-question"></div>
								<label
									htmlFor="response"
									className="placeholder"
								>
									Réponse :
								</label>
							</div>
						</>
					)}
					<br />
					<SelectButton2
						onClick={(e) => {
							handleAddToEnd();
						}}
					>
						Ajouter à la fin du questionnaire
					</SelectButton2>

					<div
						className="linetext"
						onClick={(e) => {
							setSearchBarVisible(true);
						}}
					>
						<LineText>Ajouter après la question </LineText>
					</div>
					{isSearchBarVisible && (
						<>
							<div className="input-container ic1">
								<input
									id="search-question"
									className={'question-input'}
									type="text"
									value={searchedQuestion}
									onChange={(e) => {
										setSearchQuestion(e.target.value);
									}}
									placeholder=" "
									onKeyDown={(e) => {
										if (e.key === 'Enter') {
											searchQuestion(searchedQuestion);
											setCurrentResponse('');
										}
									}}
								/>
								<div className="cut-question"></div>
								<label
									htmlFor="search-question"
									className="placeholder"
								>
									Question désirée :
								</label>
							</div>
							{foundQuestion.hasBeenFound && (
								<>
									{foundQuestion.question?.title}
									<br />
									{foundQuestion.question?.responses != ''
										? foundQuestion.question?.responses.map(
												(response) => {
													const active =
														response.isSelected
															? 'active-response'
															: '';
													return (
														<div
															key={
																response.placeholder
															}
															className={
																'response ' +
																active
															}
															onClick={(e) => {
																setFoundQuestion(
																	(prev) => {
																		let {
																			question,
																		} = prev;
																		const responses =
																			question.responses.map(
																				(
																					r
																				) => {
																					if (
																						r.placeholder ==
																						response.placeholder
																					) {
																						r.isSelected =
																							!r.isSelected;
																					}
																					return r;
																				}
																			);
																		return {
																			...prev,
																			question:
																				{
																					...question,
																					responses:
																						responses,
																				},
																		};
																	}
																);
															}}
														>
															{
																response.placeholder
															}
														</div>
													);
												}
										  )
										: ''}
									<br />
									<SelectButton2
										onClick={(e) => {
											handleAddToQuestion();
										}}
									>
										{getFoundQuestionText()}
									</SelectButton2>
								</>
							)}
						</>
					)}
				</form>
			</div>
		</div>
	);
}
