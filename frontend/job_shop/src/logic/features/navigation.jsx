import { createSlice } from '@reduxjs/toolkit';

const initialValue = [
    {
      title : "Acceuil",
      to : "/",
      visible : false,
      active : false,
      id : 1
    },
    {
      title : "J'ai déja un compte",
      to : "/login",
      visible : true,
      active : true,
      id : 6
    },
    {
      title : "About Us",
      to : "/AboutUs",
      visible : true,
      active : false,
      id : 7
    },
    {
      title : "Blog",
      to : "/Blog",
      visible : true,
      active : false,
      id : 5
    },
    {
      title : "Offres",
      to : "/undifiened/offers",
      visible : false,
      active : false,
      id : 2
    },
    {
      title : "Trouver un stage",
      to :"/undifiened/survey",
      visible : false,
      active : false,
      id : 3
    },
    {
      title : "Espace recruteur",
      to : "/undifiened/recruiters",
      visible : false,
      active : false,
      id : 4
    },
  ]

const To = [(arg) => `/${arg}/`,
(arg) => `/${arg}/offers`,
 (arg) => `/${arg}/survey/1`,
 (arg) => `/${arg}/recruiter`
]


export const navigationSlice = createSlice({
	name: 'navigation',
	initialState: {
		value: initialValue,
	},
	reducers: {
		goto: (state, action) => {
      console.log(action.payload);
      const setVisible = (...ids) =>{
        return (state.value.map((link) => {
          const {title,to,visible,active,id} = link
          return {
            title : title,
            to : to,
            visible : ids.includes(id) ? true : false,
            active : active,
            id:id
          }
        }))
      }

      switch (action.payload.id) {
        case 6:
          state.value = setVisible(6,7)
          break;
        case 1:
          state.value = setVisible(1,2,5)
          
          break;
        default:
          break;
      }
      console.log(state.value);

      state.value = state.value.map((link) => {

        let {title,to,visible,active,id} = link

        if(id in [1,2,3,4]){
          to =  To[id-1](action.payload.arg)
        }

        return {
          title : title,
          to : to,
          visible : visible,
          active : action.payload.id == id ? true : false,
          id:id
        }
      })
      console.log(state.value);
		},
	},
});

export const { goto } = navigationSlice.actions;

export default navigationSlice.reducer;