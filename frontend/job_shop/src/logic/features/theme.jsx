import { createSlice } from '@reduxjs/toolkit';

const initialValue = '';
export const themeSlice = createSlice({
	name: 'theme',
	initialState: {
		value: initialValue,
	},
	reducers: {
		changeColor: (state, action) => {
			state.value = action.payload;
		},
	},
});

export const { changeColor } = themeSlice.actions;

export default themeSlice.reducer;
